import classes.*;

public class Main {
    public static void main(String[] args){

        State state = new State("Belarus");
        Region regionM = new Region("Minsk");
        Region regionG = new Region("Gomel");
        Region regionV = new Region("Vitebsk");

        state.add(regionM);
        City cityM = new City("Minsk", 1974800);
        regionM.addRegion(cityM);
        City cityS = new City("Soligorsk",106503);
        regionM.addRegion(cityS);
        City cityB = new City("Borisov", 143919);
        regionM.addRegion(cityB);

        state.add(regionG);
        City cityG = new City("Gomel", 535229);
        regionG.addRegion(cityG);
        City cityMo = new City("Mozir", 112003);
        regionG.addRegion(cityMo);
        City cityZh = new City("Zhlobin", 75956);
        regionG.addRegion(cityZh);

        state.add(regionV);
        City cityV = new City("Vitebsk", 377595);
        regionV.addRegion(cityV);
        City cityO = new City("Orsha", 116552);
        regionV.addRegion(cityO);
        City cityN = new City("Novopolotsk", 102394);
        regionV.addRegion(cityN);

        System.out.println("\nState: " + state.getState());
        state.viewLocation();
        System.out.println("\nTotal country population size: " + state.getPopulationSize());
    }
}
