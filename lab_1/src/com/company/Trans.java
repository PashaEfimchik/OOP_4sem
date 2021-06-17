package com.company;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;

public class Trans {

    private ArrayList<AbstractTransport> _trans;

    public Trans() {
        _trans = new ArrayList<AbstractTransport>();
    }

    public ArrayList<AbstractTransport> getTrans() {
        return _trans;
    }

    public void addTransport(AbstractTransport trans){
        _trans.add(trans);
    }

    public void removeTrans(int id) throws InstanceNotFoundException{
        for(AbstractTransport trans: _trans){
            if(trans.getId() == id){
                System.out.print(trans.getTransType() + "(ID: " + trans.getId() + ") - successfully deleted");
                _trans.remove(trans);
                return;
            }
        }
        throw new InstanceNotFoundException("Transport was not found");
    }

    public double getTotalCost() {
        double sum = 0;
        for (AbstractTransport trans : _trans)
        {
            System.out.printf("\n" + trans.getTransType() + "(ID: " + trans.getId() + " )" + " price: " + trans.getCost() * trans.getCapacity());
            sum += trans.getCost() * trans.getCapacity();
        }
        return sum;
    }
}
