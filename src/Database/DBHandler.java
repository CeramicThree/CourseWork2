package Database;

import java.sql.*;
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
    public static Object selectFromUsers(String columnName, String login, Human human){

        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "SELECT " + columnName + " FROM users WHERE Login = \"" + login + "\"";
            rs = stmt.executeQuery(query);


        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return 0;
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



}
