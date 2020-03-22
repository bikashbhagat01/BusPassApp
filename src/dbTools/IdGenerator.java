package dbTools;

import java.sql.SQLException;

public class IdGenerator {

  public static int getNewBusId() throws SQLException, ClassNotFoundException {
    /* Get latest id from idgenerator Table for string Bus
        Add 1 to that
        write in the table
        return this value
    * */
    String sqlQuery = "SELECT latestid from idgenerator where objectname = \"bus\";";
    int newBusId = QueryExecutor.getInstance().getQueryNumber(sqlQuery);
    sqlQuery = "update idgenerator set latestid = "+ newBusId + " where objectname = \"route\";";
    QueryExecutor.getInstance().executeSQL(sqlQuery);
    return newBusId;
  }

  public static int getNewRouteId() {
    return 1;
  }

  public static int getNewRouteRequestId() {
    return 1;
  }

  public static int getNewBusPassId() {
    return 1;
  }

  public static int getNewFeedbackId() {
    return 1;
  }

  public static int getNewStopId() {
    return 1;
  }
}