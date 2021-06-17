package com.company;

public class Airplane extends AbstractTransport {

    public double tax_coefficient = 1.8;

    public Airplane() {}

    public Airplane(int id, double cost, String route, int capacity, int time) {
        setId(id);
        setCost(cost);
        setRoute(route);
        setCapacity(capacity);
        setTime(time);
        type = TransportType.AIRPLANE;
    }

    public double getCost() {
        return tax_coefficient * cost;
    }
    public int getCapacity() { return capacity; }
    public int getTime() { return time; }}
