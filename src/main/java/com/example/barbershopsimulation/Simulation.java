package com.example.barbershopsimulation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class Simulation extends SimulationAbs {
    private Boolean endOfSimu;
    private static Integer actualCustomers;
    private Double currentDepInServer;
    private Queue<Double> arrQueue;
    protected static Integer repeats, speed;

    public Double getCurrentDepInServer() {
        return currentDepInServer;
    }

    public void setCurrentDepInServer(Double currentDepInServer) {
        this.currentDepInServer = currentDepInServer;
    }

    protected static Boolean report;
    private Random rand;

    public Simulation() {

        super();
        this.rand = new Random(96);
        generate();
        setEvent(1);
        serverStatus = 0;
        this.currentDepInServer = getNextDepTime();
        arrQueue = new LinkedList<>();
        endOfSimu = false;
        setNextEventTime(getNextArrTime());
    }

    public Simulation(Integer serverStatus, Integer numbDelay, Integer event, Integer numbInQueue, Integer interArrTimeInServ, Float minServTime, Float maxServTime, Float minInterArrTime, Float maxInterArrTime, Double lastEventTime, Double clockSimu, Double totldelay, Double areQt, Double areaBt, Double nextArrTime, Double nextDepTime) {
        super(serverStatus, numbDelay, event, numbInQueue, interArrTimeInServ, minServTime, maxServTime, minInterArrTime, maxInterArrTime, lastEventTime, clockSimu, totldelay, areQt, areaBt, nextArrTime, nextDepTime);
        this.rand = new Random(96);
    }

    public Simulation(Integer numberOfCustomer, Integer repeats, Integer speed, Boolean report) {
        super();
        setNumberOfCustomers(numberOfCustomer);
        setRepeats(repeats);
        setSpeed(speed);
        setReport(report);
    }

    public static Integer getRepeats() {
        return repeats;
    }

    public static void setRepeats(Integer repeats) {
        Simulation.repeats = repeats;
    }

    public static Integer getSpeed() {
        return speed;
    }

    public static void setSpeed(Integer speed) {
        Simulation.speed = speed;
    }

    public static Boolean getReport() {
        return report;
    }

    public static void setReport(Boolean report) {
        Simulation.report = report;
    }

    public Boolean getEndOfSimu() {
        return endOfSimu;
    }

    public void setEndOfSimu(Boolean endOfSimu) {
        this.endOfSimu = endOfSimu;
    }

    public void arrivalEvent(ImageView barberShop, ImageView queueImg) {
        if (!getNumberOfCustomers().equals(0)) {
            if (getServerStatus() == 1) {
                queue.add(getNextDepTime());
                setNumbInQueue(queue.size());
                queueImg.setImage(new Image("file:src/main/resources/usedqueue.PNG"));
                arrQueue.add(getNextArrTime());
            } else {
                setServerStatus(1);
                if (getNumbInQueue() == 0) {
                    setInterArrTimeInServ((int) Math.round(getNextArrTime()));
                    setCurrentDepInServer(getNextDepTime());
                    barberShop.setImage(new Image("file:src/main/resources/barbershopUsed.PNG"));
                } else {
                    setInterArrTimeInServ((int) Math.round(getNextArrTime()));
                    setNumbInQueue(queue.size());
                    setCurrentDepInServer(queue.element());
                    barberShop.setImage(new Image("file:src/main/resources/barbershopEmpty.PNG"));
                    queueImg.setImage(new Image("file:src/main/resources/emptyqueue.PNG"));
                    queue.remove();
                    setEvent(0);
                    setNumbInQueue(queue.size());
                    barberShop.setImage(new Image("file:src/main/resources/barbershopUsed.PNG"));
                    if (queue.size() != 0)
                        queueImg.setImage(new Image("file:src/main/resources/usedqueue.PNG"));
                    setTotldelay(getTotldelay() + arrQueue.element() + getNextEventTime());
                    arrQueue.remove();
                }
            }
            setAreQt(getAreQt() + getNumbInQueue() * (getNextEventTime() - getLastEventTime()));
            setAreaBt(getAreaBt() + getNextEventTime() - getLastEventTime());
            setNumberOfCustomers(getNumberOfCustomers() - 1);
            if (getNumberOfCustomers() == 0)
                setEndOfSimu(true);
        }
    }

    public void departureEvent(ImageView barberShop, ImageView queueImg) {
        if (!getNumberOfCustomers().equals(0)) {
            setEvent(1);
            setNumbDelay(getNumbDelay() + 1);
            setServerStatus(0);
            if (getNumbInQueue() != 0) {
                barberShop.setImage(new Image("file:src/main/resources/barbershopEmpty.PNG"));
                arrivalEvent(barberShop, queueImg);
                return;
            } else
                setLastEventTime(getNextEventTime());
            barberShop.setImage(new Image("file:src/main/resources/barbershopEmpty.PNG"));
            setAreQt(getAreQt() + getNumbInQueue() * (getNextEventTime() - getLastEventTime()));
            setAreaBt(getAreaBt() + getNextEventTime() - getLastEventTime());
            setCurrentDepInServer(-1d);
            setEvent(0);
            setNumberOfCustomers(getNumberOfCustomers() - 1);
            if (getNumberOfCustomers() == 0)
                setEndOfSimu(true);
        }
    }

    public Boolean checkEvent(Double clock, ImageView barberShop, ImageView queue) {
        if (!getEndOfSimu()) {
            arrdep.put(nextArrTime + getClockSimu(), nextDepTime + getClockSimu());
            moveTime(clock);
            if (clock.equals(getNextEventTime())) {
                if (getNextArrTime() == clock) {
                    arrivalEvent(barberShop, queue);
                } else if (getNextDepTime() == clock || getCurrentDepInServer() == clock) {
                    departureEvent(barberShop, queue);
                }
                return true;
            } else {
                return false;
            }
        } else
            return false;
    }

    public void generate() {
        setNextArrTime(rand.nextDouble(getClockSimu() + getMinInterArrTime(), getClockSimu() + getMaxInterArrTime()));
        setNextDepTime(rand.nextDouble(getNextArrTime() + getClockSimu() + getMinServTime(), getNextArrTime() + getClockSimu() + getMaxServTime()));

    }

    public static Integer getActualCustomers() {
        return actualCustomers;
    }

    public static void setActualCustomers(Integer actualCustomers) {
        Simulation.actualCustomers = actualCustomers;
    }

    public void moveTime(Double time) {
        setClockSimu(time);
    }

    public String generateReport() {
        if (!Simulation.arrdep.isEmpty()) {
            String data = "--Number of delay : " + Simulation.getNumbDelay() + "\n" +
                    "--Total delay : " + Simulation.getTotldelay() + "\n" +
                    "--Current simulation clock : " + Simulation.getClockSimu() + "\n" +
                    "Number of customers : " + getActualCustomers().toString() + "\n" +
                    "Area under Q(t) : " + Simulation.getAreQt() + "\n" +
                    "Area under B(t) :" + Simulation.getAreaBt() + "\n";
            Integer i = 1;
            for (Map.Entry<Double, Double> entr : Simulation.arrdep.entrySet()) {
                data += "--Customer " + i.toString() + "\n" + "Arrival time : "
                        + entr.getKey().toString() + ", Departure time : " + entr.getValue() + "\n";
                ++i;
            }
            data += "Mean of customers = " + Simulation.getAreQt() / Simulation.getClockSimu() + "\n" +
                    "Mean of delay time = " + Simulation.getTotldelay() / Simulation.getNumbDelay() + "\n" +
                    "Server utilization = " + Simulation.getAreaBt() / Simulation.getClockSimu() + "\n";
            Simulation.arrdep.clear();

            return data;
        } else
            return "";
    }
}
