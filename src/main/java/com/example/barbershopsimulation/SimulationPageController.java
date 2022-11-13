package com.example.barbershopsimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SimulationPageController {
    private final Simulation simulation = new Simulation();
    private final FileOutputStream file;

    {
        try {
            file = new FileOutputStream("simulation.txt", true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer customers = simulation.getNumberOfCustomers();
    private Integer repeatsCounter = 1;

    public void appendReport() {
        try {
            String s = "******* SIMULATION OF (" + repeatsCounter.toString() + ") ********\n";
            byte[] b = s.getBytes();
            file.write(b);
            file.write(simulation.generateReport().getBytes());
            file.write("************************************************".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void simulate() {
        if (repeatsCounter <= simulation.getRepeats()) {
            if (!simulation.getEndOfSimu()) {
                simulation.checkEvent(simulation.getNextEventTime(), barberShop, queue);
                showData();
                simulation.generate();
                simulation.setLastEventTime(simulation.getNextEventTime());
                if (simulation.getCurrentDepInServer() > simulation.getNextArrTime() || simulation.getCurrentDepInServer() == -1d) {
                    simulation.setNextEventTime(simulation.getNextArrTime());
                    simulation.setEvent(1);
                } else {
                    simulation.setNextEventTime(simulation.getCurrentDepInServer());
                    simulation.setEvent(0);
                }
            } else {
                data.appendText("**** END OF SIMULATION " + repeatsCounter + " ****\n");
                if (simulation.getReport()) {
                    appendReport();
                    Simulation.setClockSimu(0d);
                    Simulation.setAreQt(0d);
                    Simulation.setAreaBt(0d);
                    Simulation.setNumbDelay(0);
                    Simulation.setTotldelay(0d);
                    Simulation.queue.clear();
                    Simulation.setNumbInQueue(0);
                    simulation.setCurrentDepInServer(-1d);
                    Simulation.setServerStatus(0);
                    simulation.generate();
                    simulation.setNextEventTime(Simulation.getNextArrTime());
                }
                ++repeatsCounter;
                simulation.setEndOfSimu(false);
                simulation.setNumberOfCustomers(customers);
            }
        } else {
            data.appendText("**********************\n**** END OF REPEATS OF THE SIMULATION *****\n");

        }
    }

    @FXML
    private TextArea data;
    @FXML
    private Button nextEvent, auto;
    @FXML
    private ImageView barberShop, queue;

    public void showData() {
        String evnt, srvSts;
        if (simulation.getEvent() == 1)
            evnt = "Event Of Arrival";
        else if (simulation.getEvent() == 0)
            evnt = "Event of Departure";
        else
            evnt = "inv";
        if (simulation.getServerStatus() == 1)
            srvSts = "BUSY";
        else
            srvSts = "IDLE";
        data.appendText("\n******** Current data ******** " + "\n" + "--Server status : " + srvSts + "\n" +
                "--Number in queue : " + simulation.getNumbInQueue() + "\n" +
                "--event type : " + evnt + "\n" +
                "--event time : " + simulation.getNextEventTime() + "\n" +
                "--Number of delay : " + simulation.getNumbDelay() + "\n" +
                "--Total delay : " + simulation.getTotldelay() + "\n" +
                "--Current simulation clock : " + simulation.getClockSimu() + "\n" +
                "--Current customer in service arrival time : " + simulation.getInterArrTimeInServ() + "\n" +
                "--Current customer in service departure time : " + simulation.getCurrentDepInServer() + "\n");
    }

    @FXML
    protected void generateNextEvent(ActionEvent event) throws IOException {
        simulate();
    }

    @FXML
    protected void autoComplete(ActionEvent event) throws IOException {
        while (repeatsCounter <= simulation.getRepeats())
            simulate();
        simulate();
    }
}
