package controllers;

import database.DatabaseConnection;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Product;

import java.sql.*;
import java.util.List;

import static controllers.CartController.cartStage;
import static controllers.CartController.cartItemObservableList;

public class CatalogController extends Stuff{

    public TableView<Product> productsTable;
    public Label articleLabel;
    public Label nameLabel;
    public Label supplierLabel;
    public TextField filterField;
    public ComboBox<String> choicesComboBox;
    public CartController cartController;
    public TextField quantityField;
    public Button addToCartButton;
    public Pane infoPane;
    public TextField totalPriceField;

    public CatalogController()
    {
        cartController = new CartController();
    }

    public void initialize() throws SQLException, ClassNotFoundException {

        infoPane.setVisible(false);
        addToCartButton.setVisible(false);
        quantityField.setVisible(false);
        totalPriceField.setVisible(false);

        ObservableList<String> choices = FXCollections.observableArrayList("Supplier", "Article", "Name", "Price+", "Price-");
        choicesComboBox.setItems(choices);
        choicesComboBox.setValue("Name");


        DatabaseConnection connectDB = new DatabaseConnection();
        Connection connection = connectDB.getConnection();
        String sql = "SELECT * FROM userdata.catalogg";// Where supplier = ? and article = ? and name = ? and price = ?";

        CallableStatement callableStatement = connection.prepareCall(sql);

        callableStatement.execute();
        ResultSet resultSet = callableStatement.getResultSet();
        ObservableList<Product> products = FXCollections.observableArrayList();
        while (resultSet.next()) {
            var product = new Product(
                    resultSet.getString("supplier"),
                    resultSet.getString("article"),
                    resultSet.getString("name"),
                    resultSet.getInt("price")
            );
            products.add(product);
        }
        callableStatement.close();
        productsTable.setItems(products);
        productsTable.setPlaceholder(new Label("It seems, there are no products here!"));

        TableColumn<Product, String> supplierColumn = new TableColumn<>("Supplier");
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        TableColumn<Product, String> articleColumn = new TableColumn<>("Article");
        articleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, String> priceColumn = new TableColumn<>("Price($)");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        supplierColumn.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        articleColumn.setMaxWidth(1f * Integer.MAX_VALUE * 15);
        nameColumn.setMaxWidth(1f * Integer.MAX_VALUE * 45);
        priceColumn.setMaxWidth(1f * Integer.MAX_VALUE * 20);

        productsTable.getColumns().add(supplierColumn);
        productsTable.getColumns().add(articleColumn);
        productsTable.getColumns().add(nameColumn);
        productsTable.getColumns().add(priceColumn);

        productsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if(productsTable.getSelectionModel().getSelectedItem() != null)
                {
                    var sel = productsTable.getSelectionModel().getSelectedItem();
                    System.out.println("Selected Value " + sel.getName() + " " + sel.getSupplierName());
                    articleLabel.setText("Article: "+sel.getArticle());
                    nameLabel.setText("Name: "+sel.getName());
                    supplierLabel.setText("Supplier: "+sel.getSupplierName());
                    infoPane.setVisible(true);
                    addToCartButton.setVisible(true);
                    quantityField.setVisible(true);
                    totalPriceField.setVisible(true);
                    var selected = productsTable.getSelectionModel().getSelectedItem();
                    if(selected != null){
                        int cost = Integer.parseInt("0"+quantityField.getText()) * selected.getPrice();
                        if(cost != 0){
                            totalPriceField.setText(String.valueOf(cost));
                        }else{
                            totalPriceField.clear();
                        }
                    }
                }
                else{
                    infoPane.setVisible(false);
                    addToCartButton.setVisible(false);
                    quantityField.setVisible(false);
                    quantityField.clear();
                    totalPriceField.setVisible(false);
                }
            }
        });

        choicesComboBox.getSelectionModel().selectedItemProperty().addListener(
                (options, oldValue, newValue) -> {
                    filterField.setText("");
                }
        );

        FilteredList<Product> filteredProducts = new FilteredList<>(products, b->true);
        filterField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredProducts.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                String choice = (String) choicesComboBox.getValue();
                switch (choice) {
                    case "Supplier":
                        return product.getSupplierName().toLowerCase().contains(lowerCaseFilter);
                    case "Article":
                        return product.getArticle().toLowerCase().contains(lowerCaseFilter);
                    case "Name":
                        return product.getName().toLowerCase().contains(lowerCaseFilter);
                    case "Price+":
                        try {
                            return product.getPrice() >= Integer.parseInt(lowerCaseFilter);
                        } catch (Exception e) {
                            return false;
                        }
                    case "Price-":
                        try {
                            return product.getPrice() <= Integer.parseInt(lowerCaseFilter);
                        } catch (Exception e) {
                            return false;
                        }
                    default:
                        return false;
                }
            });
        }));

        SortedList<Product> sortedProducts = new SortedList<>(filteredProducts);
        sortedProducts.comparatorProperty().bind(productsTable.comparatorProperty());
        productsTable.setItems(sortedProducts);

        quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[1-9]\\d*$|^[0]$")) {
                System.out.println("not match1");
                quantityField.setText(newValue.replaceAll("[^\\d]", ""));
                if (newValue.matches("[0]\\d+"))
                {
                    quantityField.setText(newValue.replaceAll("^[0]", ""));
                }
            }
            else{
                if (Integer.parseInt(newValue) > 1000){
                    quantityField.setText("1000");
                }
            }
            var selected = productsTable.getSelectionModel().getSelectedItem();
            if (selected != null){
                int cost = Integer.parseInt("0"+quantityField.getText()) * selected.getPrice();
                if(cost != 0){
                    totalPriceField.setText(String.valueOf(cost));
                }else{
                    totalPriceField.clear();
                }
            }

        });
    }

    public void addToCart(MouseEvent mouseEvent) {
        var selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null){
            int quantity = Integer.parseInt("0"+quantityField.getText());
            if (quantity != 0){
                cartController.addToCart(selectedProduct, quantity);
            }
        }
    }

    public void closeApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void showCart(ActionEvent actionEvent) {
        if(cartStage == null) {
            cartStage = getNewStage(actionEvent, "../views/Cart.fxml", false);
            if(cartStage != null) {
                cartStage.show();
            }
        }
    }

    public void logOut(ActionEvent actionEvent) {
        if(cartStage!= null){
            cartStage.close();
            cartStage = null;
        }
        cartItemObservableList.clear();
        Stage login = getNewStage(actionEvent, "../views/Login.fxml", true);
        if(login != null) {
            login.show();
        }
    }
}
