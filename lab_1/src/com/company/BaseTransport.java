package com.company;

import java.util.Scanner;

public class BaseTransport {

    public static void main(String[] args) {
        Trans allTrans = AllTransportFactory.getTransport();
        Scanner sc = new Scanner(System.in);
        allTrans.addTransport(new Airplane(1111,120.50, "route 1", 155, 180));
        allTrans.addTransport(new Ship(2222,75.60, "route 2", 1200, 450));
        allTrans.addTransport(new Bus(3333,0.80, "route 3", 45, 35));

        boolean flag = true;

        int comm;

        while(flag){
            try{
                PrintData.showMenu();
                comm = sc.nextInt();
                switch (comm){
                    case 1 -> {TransportType type = InputData.getTransType();
                        switch(type){
                            case AIRPLANE -> allTrans.addTransport(TransportFactory.getTransport(TransportType.AIRPLANE));
                            case SHIP -> allTrans.addTransport(TransportFactory.getTransport(TransportType.SHIP));
                            case BUS -> allTrans.addTransport(TransportFactory.getTransport(TransportType.BUS));
                        }
                    }
                    case 2 -> PrintData.printTransports(allTrans.getTrans());
                    case 3 -> System.out.printf("\nTotal price: %.2f\n", allTrans.getTotalCost());
                    case 4 -> allTrans.removeTrans(InputData.getIdRemote());
                    case 0 -> flag = false;
                }
            }
            catch (Exception e){
                System.out.println("\n" + e.getMessage());
            }
        }
    }
}
