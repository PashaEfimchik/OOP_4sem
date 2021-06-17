package controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Stuff {
    private static double xOffset = 0;
    private static double yOffset = 0;
    public static Stage getNewStage(Event event, String pathToFxml, Boolean hideCurrent) {
        try{
            Parent root = FXMLLoader.load(Stuff.class.getResource(pathToFxml));
            Stage stage = new Stage();
            stage.setTitle("New Stage");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            root.setOnMousePressed(event1 -> {
                xOffset = event1.getSceneX();
                yOffset = event1.getSceneY();
            });
            root.setOnMouseDragged(event2 -> {
                stage.setX(event2.getScreenX() - xOffset);
                stage.setY(event2.getScreenY() - yOffset);
            });
            if(hideCurrent) {
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            return stage;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}