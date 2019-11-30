package Database;

import java.sql.*;
import java.util.List;

import sample.*;
public class DBHandler extends Config {

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;
    private static String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return connection;
    }

    public static void executeQuery(String query){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }
    public static void insertIntoUsers(Human human){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "INSERT INTO users (Name, Age, Weight, Height, Gender, Ccal, Login, Password)\n" +
                    "VALUES (\"" + human.getName() + "\", " + human.getAge() + ", " + human.getWeight() + ", " + human.getHeight()
                    + ", \"" + human.getGender() + "\", " + human.getCcal() + ", \"" + human.getLogin() + "\" , " + human.getPassword() + ");";
            stmt.executeUpdate(query);
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    public static Human authorization(String login, int password, Human human){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "SELECT * FROM users WHERE Login = '" + login + "' AND Password = " + password + ";";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                human.setName(rs.getString(2));
                human.setAge(Integer.parseInt(rs.getString(3)));
                human.setWeight(Float.parseFloat(rs.getString(4)));
                human.setHeight(Float.parseFloat(rs.getString(5)));
                human.setCcal(Float.parseFloat(rs.getString(7)));
                human.setLogin(rs.getString(8));
                human.setPassword(Integer.parseInt(rs.getString(9)));
                human.setGender(rs.getString(6));
            }
        }catch (NullPointerException error) {
            return null;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return human;
    }

    public static void insertIntoFood(Food food){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "INSERT INTO food (Name, EnergyValue)\n" +
                    "VALUES (\"" + food.getName() + "\" ," + food.getEnergyValue() + ");";
            stmt.executeUpdate(query);
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    public static List<Food> selectFromFood(){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "SELECT * FROM food;";
            rs = stmt.executeQuery(query);
            while (rs.next()){
                Food _food = new Food(rs.getString(2), Float.parseFloat(rs.getString(3)));
                Food.foodList.add(_food);
            }
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }catch (NullPointerException ex){
            return null;
        }
        return Food.foodList;
    }

    public static void deleteFromFood(String where){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "DELETE FROM food WHERE Name = '" + where + "';";
            stmt.executeUpdate(query);
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }





}
