package com.company;

public class TransportFactory {
    public static AbstractTransport getTransport(TransportType type) {
        switch(type)
        {
            case AIRPLANE:
                return InputData.getTransport(type);
            case SHIP:
                return InputData.getTransport(type);
            case BUS:
                return InputData.getTransport(type);
            default:
                return InputData.getTransport(type);
        }
    }
}
