package controllers;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController extends Stuff {

    public BorderPane borderPane;
    public TextField usernameField;
    public PasswordField passwordField;

    public void openRegister(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/Register.fxml"));
        borderPane.getChildren().removeAll();
        borderPane.getChildren().setAll(root);
    }

    public void loginUser(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        var username = usernameField.getText();
        var password = passwordField.getText();
        if(!username.equals("") && !password.equals(""))
        {
            DatabaseConnection connectDB = new DatabaseConnection();
            Connection connection = connectDB.getConnection();
            String sql = "SELECT * FROM users Where username = ? and password = ?";

            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            callableStatement.execute();
            ResultSet res = callableStatement.getResultSet();
            if (res.next()) {
                Stage mainStage = getNewStage(actionEvent, "../views/Catalog.fxml", true);
                if(mainStage != null){
                    mainStage.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(username);
                alert.setHeaderText(null);
                alert.setContentText("Wrong username/password combination");
                alert.showAndWait();
            }
            callableStatement.close();
        }
    }

    public void closeApp(ActionEvent actionEvent) {
        System.exit(0);
    }
}