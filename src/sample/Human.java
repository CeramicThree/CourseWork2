package sample;

class Human {
    private float weight;
    private float height;
    private int age;
    private String name;
    private int gender;
    private float ccal;

    Human(){
        setWeight(60);
        setHeight(170);
        setAge(18);
        setName("Валера");
        setGender(1);
        ccal = 1800;
    }

    public Human(float weight, float height, int age, String name, int gender){
        setWeight(weight);
        setHeight(height);
        setAge(age);
        setName(name);
        setGender(gender);
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCcal() {
        return ccal;
    }

    public void setCcal(float ccal) {
        this.ccal = ccal;
    }

    public void calcCcal(){
        if(getGender() == 1){
            setCcal((float) (88.36 + 13.4 * getWeight() + 4.8 * getHeight() - 5.7 * getAge()));
        }else if (getGender() == 0){
            setCcal((float) (447.6 + 9.2 * getWeight() + 3.1 * getHeight() - 4.3 * getAge()));
        }
    }
}
