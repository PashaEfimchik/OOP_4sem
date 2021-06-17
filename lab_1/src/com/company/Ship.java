package com.company;

public class Ship extends AbstractTransport {

    public double tax_coefficient = 1.4;

    public Ship() {}

    public Ship(int id, double cost, String route, int capacity, int time) {
        setId(id);
        setCost(cost);
        setRoute(route);
        setCapacity(capacity);
        setTime(time);
        type = TransportType.SHIP;
    }

    public double getCost() {
        return tax_coefficient * cost;
    }
    public int getCapacity() { return capacity; }
    public int getTime() { return time; }}
