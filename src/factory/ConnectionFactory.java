package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  private static final String USERNAME = "guia";
  private static final String PASSWORD = "Duratinfinitum777";
  private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/exati_db";

  public static Connection openConnection() throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");

    Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    //System.out.println("Connection openned");

    return connection;
  }
}
