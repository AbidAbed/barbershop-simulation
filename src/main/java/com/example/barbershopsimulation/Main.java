package com.example.barbershopsimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainPageController.class.getResource("main-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Barbershop Simulation");
        stage.setScene(scene);
        stage.setMaxHeight(600);
        stage.setMaxWidth(600);
        stage.setMinHeight(600);
        stage.setMinWidth(600);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}