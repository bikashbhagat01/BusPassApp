package managers;

import assets.RouteRequest;
import dbTools.QueryExecutor;
import java.sql.SQLException;

public class RouteRequestManager {
  private static String sqlQuery;
  public static void create(RouteRequest routeRequest) {
    // Creates sqlQuery from RouteRequest Object and calls QueryExecutor.executeQuery(sqlQuery)
  }

  public void update() {
    // No requirements found
  }

  public void remove(int routeRequestId) {
    /*
    * Create SQLQuery to remove routeRequestId
    * Call QueryExecutor.executeQuery(sqlQuery)
    * */
  }

  public static boolean isEmpty() throws SQLException, ClassNotFoundException {
    sqlQuery = "select * from routerequest";
    return QueryExecutor.getInstance().isValidQuery(sqlQuery);
  }

  public void read() {
    /*
     * Create SQL Query to read all info. of table
     * Create Array of all field names
     * Call QueryExecutor(sqlQuery, fields[])
     * */
  }
}
