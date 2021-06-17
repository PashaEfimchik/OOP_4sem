package classes;

public class City implements ILocation{
    private String city;
    private int populationSize;

    public City(String city, int populationSize) {
        this.city = city;
        this.populationSize = populationSize;
    }

    public String getCityName(){
        return city;
    }

    @Override
    public int getPopulationSize() {
        return populationSize;
    }

    @Override
    public void viewLocation() {
        System.out.println(city + "  " + getPopulationSize());
    }
}
