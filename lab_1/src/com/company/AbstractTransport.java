package com.company;

public abstract class AbstractTransport
{
    private int id;
    protected double cost;
    private String route;
    protected int capacity;
    protected int time;

    TransportType type;

    public int getId() {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    abstract public double getCost();

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    public String getRoute(){ return route;}
    public void setRoute(String route)
    {
        this.route = route;
    }

    abstract public int getCapacity();

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    abstract public int getTime();

    public void setTime(int time)
    {
        this.time = time;
    }

    public String getTransType(){
        switch (type){
            case AIRPLANE:
                return "Airplane";
            case SHIP:
                return "Ship";
            default:
                return "Bus";
        }
    }
}
