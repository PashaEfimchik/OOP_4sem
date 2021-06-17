package classes;

import java.util.ArrayList;

public class State implements ILocation{

    private String state;
    private ArrayList<ILocation> regions;

    public State(String state) {
        this.state = state;
        regions = new ArrayList<>();
    }

    public String getState() {
        return state;
    }

    public void add(ILocation region){
        this.regions.add(region);
    }

    public void delete(ILocation region){
        this.regions.remove(region);
    }

    @Override
    public int getPopulationSize() {
        int counter = 0;
        for(var region: regions){
            counter += region.getPopulationSize();
        }
        return counter;
    }

    @Override
    public void viewLocation() {
        for(var region: regions){
            region.viewLocation();
        }
    }
}
