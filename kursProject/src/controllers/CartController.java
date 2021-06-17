package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.CartItem;
import models.Product;

public class CartController {
    public static ObservableList<CartItem> cartItemObservableList = FXCollections.observableArrayList();
    public TableView<CartItem> cartTable;
    public static Stage cartStage;
    public Label overallPriceField;

    public void initialize(){

        cartTable.setItems(cartItemObservableList);
        cartTable.setPlaceholder(new Label("Your requested products will appear here"));

        cartItemObservableList.addListener(new ListChangeListener<>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends CartItem> c) {
                overallPriceField.setText("Overall cost: " + String.valueOf(getOverallCost()) + "$");
            }
        });

        TableColumn<CartItem, String> articleColumn = new TableColumn<>("Article");
        articleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));

        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<CartItem, String> supplierColumn = new TableColumn<>("Supplier");
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        TableColumn<CartItem, String> priceColumn = new TableColumn<>("Price($)");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<CartItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        cartTable.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        articleColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 10);
        nameColumn.setMaxWidth(1f * Integer.MAX_VALUE * 45);
        supplierColumn.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        priceColumn.setMaxWidth(1f * Integer.MAX_VALUE * 15);
        quantityColumn.setMaxWidth(1f * Integer.MAX_VALUE * 10);

        cartTable.getColumns().add(articleColumn);
        cartTable.getColumns().add(nameColumn);
        cartTable.getColumns().add(supplierColumn);
        cartTable.getColumns().add(priceColumn);
        cartTable.getColumns().add(quantityColumn);
        overallPriceField.setText("Overall cost: " + String.valueOf(getOverallCost()) + "$");
    }

    public void addToCart(Product product, int quantity){
        int currQ =0;
        int index = -1;
        for(var cartItem : cartItemObservableList){
            if(cartItem.getArticle().equals(product.getArticle()) &&
                    cartItem.getName().equals(product.getName()) &&
                    cartItem.getSupplierName().equals(product.getSupplierName()))
            {
                currQ = cartItem.getQuantity();
                index = cartItemObservableList.indexOf(cartItem);
            }
        }
        if(index != -1) {
            cartItemObservableList.remove(cartItemObservableList.get(index));
        }
        cartItemObservableList.add(new CartItem(product.getSupplierName(),product.getArticle(), product.getName(), product.getPrice(), currQ+quantity));
    }

    public void deleteCartItem(ActionEvent actionEvent) {
        var cartItem = cartTable.getSelectionModel().getSelectedItem();
        if(cartItem != null) {
            cartItemObservableList.remove(cartItem);
        }
    }

    public void clearCart(ActionEvent actionEvent) {
        cartItemObservableList.clear();
    }

    public void purchase(ActionEvent actionEvent) {
        if(cartItemObservableList.size() != 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cart");
            alert.setHeaderText(null);
            alert.setContentText("Purchased successfully!");
            alert.showAndWait();
        }
    }

    public void closeCart(ActionEvent actionEvent) {
        cartStage.close();
        cartStage = null;
    }
    public Integer getOverallCost(){
        int sum = 0;
        for(var item: cartItemObservableList){
            sum += item.getTotalPrice();
        }
        return sum;
    }
}