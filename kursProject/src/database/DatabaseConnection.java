package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection extends Configs{


    Connection dbConnection = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" +
                dbHost + ":" +
                dbPort + "/" +
                dbName + "?" + "autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.jdbc.Driver");

        try {
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            if(dbConnection!=null){
                System.out.println("Connection success!");
            }
            else{
                System.out.println("Connection fail!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dbConnection;
    }
}