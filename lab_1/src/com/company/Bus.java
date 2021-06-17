package com.company;

public class Bus extends AbstractTransport {

    public double tax_coefficient = 1.2;

    public Bus() {}

    public Bus(int id, double cost, String route, int capacity, int time) {
        setId(id);
        setCost(cost);
        setRoute(route);
        setCapacity(capacity);
        setTime(time);
        type = TransportType.BUS;
    }

    public double getCost() {
        return tax_coefficient * cost;
    }
    public int getCapacity() { return capacity; }
    public int getTime() { return time; }

}
