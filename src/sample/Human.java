package sample;

public class Human {
    private float weight;
    private float height;
    private int age;
    private String name;
    private String gender;
    private float ccal;
    private int password;
    private String login;

    public Human(){
        setWeight(60);
        setHeight(170);
        setAge(18);
        setName("Valera");
        setGender("Male");
        ccal = 0;
        setPassword(1111);
        setLogin("Log");
    }

    public Human(float weight, float height, int age, String name, String gender, int password, String login){
        setWeight(weight);
        setHeight(height);
        setAge(age);
        setName(name);
        setGender(gender);
        setPassword(password);
        setLogin(login);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void calcCcal(){
        if(getGender().equals("Male")){
            setCcal((float) (88.36 + 13.4 * getWeight() + 4.8 * getHeight() - 5.7 * getAge()));
        }else if (getGender().equals("Female")){
            setCcal((float) (447.6 + 9.2 * getWeight() + 3.1 * getHeight() - 4.3 * getAge()));
        }
    }
}
