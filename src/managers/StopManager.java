package managers;

import assets.Stop;
import dbTools.QueryExecutor;
import java.sql.SQLException;

public class StopManager {

  public void create(Stop stop) {
    // Send sqlQuery to QueryExecutor to create and stop
  }

  public static int getStopIdForName(String stopName) throws SQLException, ClassNotFoundException {
    // returns stopId for searched stopName from stopTable
    String sqlQuery = "select stopid from stop where stopname = " + stopName;
    int stopId = QueryExecutor.getInstance().getQueryNumber(sqlQuery);
    return stopId;
  }
}
