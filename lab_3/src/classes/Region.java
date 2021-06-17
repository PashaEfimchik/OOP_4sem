package classes;

import java.util.ArrayList;

public class Region implements ILocation{

    private String region;
    private ArrayList<ILocation> cities;

    public Region(String reg) {
        region = reg;
        cities = new ArrayList<>();
    }

    public String getRegion() {
        return region;
    }

    public void addRegion(ILocation region){
        cities.add(region);
    }

    public void deleteRegion(ILocation region){
        cities.remove(region);
    }

    @Override
    public int getPopulationSize() {
        int counter = 0;
        for(var city: cities){
            counter += city.getPopulationSize();
        }
        return counter;
    }

    @Override
    public void viewLocation() {
        System.out.println("\n" + region + " region:");
        for(var city: cities){
            city.viewLocation();
        }
    }
}
