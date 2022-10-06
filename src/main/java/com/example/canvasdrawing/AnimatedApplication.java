package com.example.canvasdrawing;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AnimatedApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("canvas-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Canvas Example");
        stage.setMinWidth(400);
        stage.setMinHeight(200);
        stage.setScene(scene);

        scene.widthProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                AnimatedController.controller.setWidth(newSceneWidth.intValue());
            }
        });
        scene.heightProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                AnimatedController.controller.setHeight(newSceneHeight.intValue());
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}