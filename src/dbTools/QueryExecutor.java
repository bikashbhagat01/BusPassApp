package dbTools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Singleton to execute queries related to DBs, received from DB Managers
// Only SQL Query Executor linked to DB
public class QueryExecutor<T> {
  private static QueryExecutor queryExecutor;

  private QueryExecutor() {
  }

  public static QueryExecutor getInstance() {
    if (queryExecutor == null) {
      queryExecutor = new QueryExecutor();
    }
    return queryExecutor;
  }

  // Proposed implementation, Only Executes a Query and returns Success, Only Write
  public void executeSQL(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectDatabase.getConnection();
    Statement statement = conn.createStatement();
    int result = statement.executeUpdate(sqlQuery);
    if (result != 0) {
      System.out.println(sqlQuery + " Success!" + result);
    } else {
      System.out.println(sqlQuery + " Fail!" + result);
    }
  }

  // Proposed implementation, Executes the Query as well as prints the result table, For Reads
  public void executeSQL(String sqlQuery, String[] fields) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectDatabase.getConnection();
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlQuery);
    System.out.println(resultSet);
    for (String field : fields) {
      System.out.print(field + " ");
    }
    if (!resultSet.next()) {
      System.out.println("record not found");
    } else {
      while (resultSet.next()) {
        System.out.println();
        for (int i = 1; i <= fields.length; i++)
          System.out.print(resultSet.getString(i) + " ");
      }
      System.out.println();
    }
  }

  public boolean validateQuery(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectDatabase.getConnection();
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlQuery);
    if(resultSet.next()) {
      return true;
    }
    return false;
  }

  public int getQueryNumber(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectDatabase.getConnection();
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlQuery);
    if (resultSet.next()) {
      return resultSet.getInt(1);
    }
    return -1;
  }

  public List<Integer> getQueryNumberList(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectDatabase.getConnection();
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlQuery);
    List<Integer> resultArray = new ArrayList<>();
    if (!resultSet.next()) {
      return resultArray;
    } else {
      while (resultSet.next()) {
        resultArray.add(resultSet.getInt(1));
      }
    }
    return resultArray;
    }
  }

