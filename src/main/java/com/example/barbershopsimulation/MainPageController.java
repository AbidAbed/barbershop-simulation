package com.example.barbershopsimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainPageController {
    @FXML
    private Button startBut;
    @FXML
    private TextField customers, repeats, speed, minServTime, maxServTime, minInArr, maxInArr;
    @FXML
    private CheckBox report;
    @FXML
    private TextArea log;

    public Boolean isInt(String string) {
        for (int i = 0; i < string.length(); i++) {
            if ((int) string.charAt(i) < 48 || (int) string.charAt(i) > 57) return false;
        }
        return true;
    }

    @FXML
    protected void startSimulation(ActionEvent event) throws IOException {
        if (!customers.getText().equals("") && !repeats.getText().equals("") && !speed.getText().equals("") && !minServTime.getText().equals("") && !maxServTime.getText().equals("") && !minInArr.getText().equals("") && !maxInArr.getText().equals("")) {
            if (!isInt(customers.getText()) || !isInt(repeats.getText()) || !isInt(speed.getText()) || !isInt(minServTime.getText()) || !isInt(maxServTime.getText()) || !isInt(minInArr.getText()) || !isInt(maxInArr.getText())) {
                log.appendText("\n>>Failed,Please enter valid data");
            } else {
                if (Integer.parseInt(minInArr.getText()) >= Integer.parseInt(maxInArr.getText()) || Integer.parseInt(minServTime.getText()) >= Integer.parseInt(maxServTime.getText()))
                    log.appendText("\n>>Failed.Please max must be bigger than min");
                else {
                    log.appendText("\n>>Starting simulation in seconds ...");
                    Simulation.setRepeats(Integer.parseInt(repeats.getText()));
                    Simulation.setSpeed(Integer.parseInt(speed.getText()));
                    Simulation.setReport(report.isSelected());
                    Simulation.setNumberOfCustomers(Integer.parseInt(customers.getText()));
                    Simulation.setActualCustomers(Integer.parseInt(customers.getText()));
                    Simulation.setMaxServTime(Integer.parseInt(maxServTime.getText()) * 1.0f);
                    Simulation.setMinServTime(Integer.parseInt(minServTime.getText()) * 1.0f);
                    Simulation.setMaxInterArrTime(Integer.parseInt(maxInArr.getText()) * 1.0f);
                    Simulation.setMinInterArrTime(Integer.parseInt(minInArr.getText()) * 1.0f);
                    //note that on this line you can substitue "SimulationPage.fxml" for a string chosen prior to this line.
                    Parent loader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SimulationPage.fxml")));//Creates a Parent called loader and assign it as ScReen2.FXML

                    Scene scene = new Scene(loader, 600, 600); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"

                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.

                    app_stage.setScene(scene); //This sets the scene as scene
                    app_stage.show(); // this shows the scene
                }
            }
        } else {
            log.appendText("\n>>Failed, Please don't leave any blanks");
        }

    }
}