package com.company;

import java.util.Scanner;

public class InputData {
    static Scanner scan = new Scanner(System.in);

    public static AbstractTransport getTransport(TransportType type){
        System.out.print("Input ID: ");
        int id = scan.nextInt();

        System.out.print("Input price: ");
        double cost = scan.nextDouble();

        System.out.print("Input name of route: ");
        String route = scan.next();

        System.out.print("Input capacity of transport: ");
        int capacity = scan.nextInt();

        System.out.print("Input travel time (in minutes): ");
        int time = scan.nextInt();

        if (type == TransportType.AIRPLANE){
            return new Airplane(id, cost, route, capacity, time);
        }
        else if(type == TransportType.SHIP) {
            return new Ship(id, cost, route, capacity, time);
        }
        else
        {
            return new Bus(id, cost, route, capacity, time);
        }
    }

    public static TransportType getTransType() throws Exception{
        System.out.println("1 - Airplane");
        System.out.println("2 - Ship");
        System.out.println("3 - Bus");
        int type = Integer.parseInt(scan.nextLine());
        switch(type){
            case 1:
                return TransportType.AIRPLANE;
            case 2:
                return TransportType.SHIP;
            case 3:
                return TransportType.BUS;
            default:
                throw new Exception("Invalid type");
        }
    }

    public static int getIdRemote(){
        System.out.print("Enter transport id to remove: ");
        return Integer.parseInt(scan.nextLine());
    }
}
