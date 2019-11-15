package sample;

public class Food {
    private String name;
    private float energyValue;

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
