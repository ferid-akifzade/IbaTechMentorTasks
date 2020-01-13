package libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
  private static final String DB_URL = "jdbc:postgresql://rajje.db.elephantsql.com:5432/wmwrjkwx";
  private static final String USERNAME = "wmwrjkwx";
  private static final String USER_PASS = "CsjQ2Iq5X3vuFy1NH9Gd14_Ei3Nn7JFZ";

  private static Connection connection;

  private DbConnection() {}

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(DB_URL, USERNAME, USER_PASS);
      } catch (SQLException e) {
        throw new RuntimeException("Something went wrong during connection", e);
      }
    }
    return connection;
  }
}
