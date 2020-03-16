package dbTools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Singleton to execute queries related to DBs, received from DB Managers
// Only SQL Query Executor linked to DB
public class QueryExecutor {
  private static QueryExecutor queryExecutor;
  private QueryExecutor() { }

  public static QueryExecutor getInstance() {
    if ( queryExecutor == null) {
      queryExecutor = new QueryExecutor();
    }
    return queryExecutor;
  }

  // Proposed implementation, Only Executes a Query and returns Success, Only Write
  public void executeSQL(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectDatabase.getConnection();
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlQuery);
    System.out.println(sqlQuery + " Success!" + resultSet.toString()); // For test purpose
  }

  // Proposed implementation, Executes the Query as well as prints the result table, For Reads
  public void executeSQL(String sqlQuery, String[] fields) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectDatabase.getConnection();
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlQuery);
    System.out.println(resultSet);
    for(String field : fields) {
      System.out.print(field + " ");
    }
    if(!resultSet.next()){
      System.out.println("record not found");
    } else {
      while (resultSet.next()) {
        System.out.println();
        for (int i = 1; i <= fields.length; i++)
          System.out.print(resultSet.getString(i) + " ");
      }
      System.out.println();
    }
    conn.close();
  }

  public boolean validateQuery(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectDatabase.getConnection();
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlQuery);
    // ResultSet should not be empty
    // Condition to check if any result is found or not
    // If found return true
    return false;
  }

  public int getQueryNumber(String sqlQuery) throws SQLException, ClassNotFoundException {
    Connection conn = ConnectDatabase.getConnection();
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlQuery);
    if(resultSet.next()){
      return resultSet.getInt(1);
    }
    return -1;
  }

  /**
   * Create a QueryExecutor which returns a List of fields for an Sql Query
   * Used to get lists and perform local operation in modules such as AdminOperation
   * */
}
