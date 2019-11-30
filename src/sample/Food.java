package sample;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String name;
    private float energyValue;
    public static List<Food> foodList = new ArrayList<>();
    public Food(){
        setName("Еда");
        setEnergyValue(100);
    }

    public Food(String name, float energyValue){
        setName(name);
        setEnergyValue(energyValue);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(float energyValue) {
        this.energyValue = energyValue;
    }
}
