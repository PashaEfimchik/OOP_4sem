package com.company;

import java.util.ArrayList;

public class PrintData {
    public static void printTransport(AbstractTransport trans){
        if(trans instanceof AbstractTransport){
            System.out.print("\n----------------------");
            System.out.printf("\nID: " + trans.getId() + "\nCost: " + trans.getCost() + "\nRoute: " + trans.getRoute() +
                    "\nCapacity: " + trans.getCapacity() + "\nTime: " + trans.getTime() +
                    "\nType: " + trans.getTransType());
            System.out.print("\n----------------------");
        }
    }
    public static void printTransports(ArrayList<AbstractTransport> trans){
        for(AbstractTransport _trans : trans){
            printTransport(_trans);
        }
    }
    public static void showMenu(){
        System.out.println("\n1 - Add");
        System.out.println("2 - Show transport");
        System.out.println("3 - Get total price");
        System.out.println("4 - Remove");
        System.out.println("0 - Exit");
    }
}
