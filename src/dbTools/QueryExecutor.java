package dbTools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Singleton to execute queries related to DBs, received from DB Managers
// Only SQL Query Executor linked to DB
public class QueryExecutor {

  private static QueryExecutor queryExecutor;

  private QueryExecutor() {
  }

  public static QueryExecutor getInstance() {
    if (queryExecutor == null) {
      queryExecutor = new QueryExecutor();
    }
    return queryExecutor;
  }

  // Only Executes a sqlQuery and returns Success. Only for Writes
  public boolean executeSQL(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectionManager.getConnection();
    Statement statement = conn.createStatement();

    int result = statement.executeUpdate(sqlQuery);

    if (result != 0) {
      return false;
    }
    return true;
  }

  // Executes sqlQuery and prints the result table with field headers. For Reads with print
  public boolean executeSQL(String sqlQuery, String[] headers) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectionManager.getConnection();
    Statement statement = conn.createStatement();

    ResultSet resultSet = statement.executeQuery(sqlQuery);

    for (String field : headers) {
      System.out.print(field + "\t\t");
    }

    if (!resultSet.next()) {
      System.out.println("No records found");
      return false;
    }

    while (resultSet.next()) {
      System.out.println();

      for (int i = 1; i <= headers.length; i++) {
        System.out.print(resultSet.getString(i) + "\t\t\t\t\t");
      }
    }
    System.out.println();

    return true;
  }

  // Checks if a record is present for an sqlQuery or not
  public boolean isRecordPresent(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectionManager.getConnection();
    Statement statement = conn.createStatement();

    ResultSet resultSet = statement.executeQuery(sqlQuery);

    if (resultSet.next()) {
      return true;
    }
    return false;
  }

  // Returns first single field value for a sqlQuery
  public int getQueryNumber(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectionManager.getConnection();
    Statement statement = conn.createStatement();

    ResultSet resultSet = statement.executeQuery(sqlQuery);

    if (resultSet.next()) {
      return resultSet.getInt(1);
    }
    return -1;
  }

  // Returns ResultSet object for a sqlQuery
  public ResultSet getResultSet(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectionManager.getConnection();
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlQuery);
    return resultSet;
  }
}

