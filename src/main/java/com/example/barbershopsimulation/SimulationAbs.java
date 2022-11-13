package com.example.barbershopsimulation;


import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class SimulationAbs {
    protected static Integer serverStatus, numbDelay, event, numbInQueue, interArrTimeInServ;
    protected static Integer numberOfCustomers;
    protected static Float minServTime, maxServTime, minInterArrTime, maxInterArrTime;
    protected static Double lastEventTime, clockSimu, totldelay, areQt, areaBt, nextArrTime, nextDepTime, nextEventTime;
    protected static Queue<Double> queue;
    protected static Map<Double, Double> arrdep;

    public SimulationAbs() {
        queue = new LinkedList<>();
        arrdep = new TreeMap<>();
        SimulationAbs.clockSimu = 0d;
        SimulationAbs.numbInQueue = 0;
        SimulationAbs.areaBt = 0d;
        SimulationAbs.areQt = 0d;
        SimulationAbs.lastEventTime = 0d;
        SimulationAbs.numbDelay = 0;
        SimulationAbs.totldelay = 0d;
        SimulationAbs.interArrTimeInServ = 0;
    }

    public SimulationAbs(Integer serverStatus, Integer numbDelay, Integer event, Integer numbInQueue, Integer interArrTimeInServ, Float minServTime, Float maxServTime, Float minInterArrTime, Float maxInterArrTime, Double lastEventTime, Double clockSimu, Double totldelay, Double areQt, Double areaBt, Double nextArrTime, Double nextDepTime) {
        SimulationAbs.serverStatus = serverStatus;
        SimulationAbs.numbDelay = numbDelay;
        SimulationAbs.event = event;
        SimulationAbs.numbInQueue = numbInQueue;
        SimulationAbs.interArrTimeInServ = interArrTimeInServ;
        SimulationAbs.minServTime = minServTime;
        SimulationAbs.maxServTime = maxServTime;
        SimulationAbs.minInterArrTime = minInterArrTime;
        SimulationAbs.maxInterArrTime = maxInterArrTime;
        SimulationAbs.lastEventTime = lastEventTime;
        SimulationAbs.clockSimu = clockSimu;
        SimulationAbs.totldelay = totldelay;
        SimulationAbs.areQt = areQt;
        SimulationAbs.areaBt = areaBt;
        SimulationAbs.nextArrTime = nextArrTime;
        SimulationAbs.nextDepTime = nextDepTime;
        queue = new LinkedList<>();
        arrdep = new TreeMap<>();
    }

    public static Integer getServerStatus() {
        return serverStatus;
    }

    public static void setServerStatus(Integer serverStatus) {
        SimulationAbs.serverStatus = serverStatus;
    }

    public static Integer getNumbDelay() {
        return numbDelay;
    }

    public static void setNumbDelay(Integer numbDelay) {
        SimulationAbs.numbDelay = numbDelay;
    }

    public static Integer getEvent() {
        return event;
    }

    public static void setEvent(Integer event) {
        SimulationAbs.event = event;
    }

    public static Integer getNumbInQueue() {
        return numbInQueue;
    }

    public static void setNumbInQueue(Integer numbInQueue) {
        SimulationAbs.numbInQueue = numbInQueue;
    }

    public static Integer getInterArrTimeInServ() {
        return interArrTimeInServ;
    }

    public static void setInterArrTimeInServ(Integer interArrTimeInServ) {
        SimulationAbs.interArrTimeInServ = interArrTimeInServ;
    }

    public static Integer getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public static void setNumberOfCustomers(Integer numberOfCustomers) {
        SimulationAbs.numberOfCustomers = numberOfCustomers;
    }

    public static Float getMinServTime() {
        return minServTime;
    }

    public static void setMinServTime(Float minServTime) {
        SimulationAbs.minServTime = minServTime;
    }

    public static Float getMaxServTime() {
        return maxServTime;
    }

    public static void setMaxServTime(Float maxServTime) {
        SimulationAbs.maxServTime = maxServTime;
    }

    public static Float getMinInterArrTime() {
        return minInterArrTime;
    }

    public static void setMinInterArrTime(Float minInterArrTime) {
        SimulationAbs.minInterArrTime = minInterArrTime;
    }

    public static Float getMaxInterArrTime() {
        return maxInterArrTime;
    }

    public static void setMaxInterArrTime(Float maxInterArrTime) {
        SimulationAbs.maxInterArrTime = maxInterArrTime;
    }

    public static Double getLastEventTime() {
        return lastEventTime;
    }

    public static void setLastEventTime(Double lastEventTime) {
        SimulationAbs.lastEventTime = lastEventTime;
    }

    public static Double getClockSimu() {
        return clockSimu;
    }

    public static void setClockSimu(Double clockSimu) {
        SimulationAbs.clockSimu = clockSimu;
    }

    public static Double getTotldelay() {
        return totldelay;
    }

    public static void setTotldelay(Double totldelay) {
        SimulationAbs.totldelay = totldelay;
    }

    public static Double getAreQt() {
        return areQt;
    }

    public static void setAreQt(Double areQt) {
        SimulationAbs.areQt = areQt;
    }

    public static Double getAreaBt() {
        return areaBt;
    }

    public static void setAreaBt(Double areaBt) {
        SimulationAbs.areaBt = areaBt;
    }

    public static Double getNextArrTime() {
        return nextArrTime;
    }

    public static void setNextArrTime(Double nextArrTime) {
        SimulationAbs.nextArrTime = nextArrTime;
    }

    public static Double getNextDepTime() {
        return nextDepTime;
    }

    public static void setNextDepTime(Double nextDepTime) {
        SimulationAbs.nextDepTime = nextDepTime;
    }

    public static Double getNextEventTime() {
        return nextEventTime;
    }

    public static void setNextEventTime(Double nextEventTime) {
        SimulationAbs.nextEventTime = nextEventTime;
    }
}