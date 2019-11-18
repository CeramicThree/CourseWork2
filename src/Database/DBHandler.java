package Database;

import java.sql.*;

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

}
