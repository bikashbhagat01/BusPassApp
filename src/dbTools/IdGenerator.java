package dbTools;

import java.sql.SQLException;
import queryHelper.QueryBuilder;

public class IdGenerator {

  public static int getNewBusId() throws Exception {
    /* Get latest id from idgenerator Table for string Bus
        Add 1 to that
        write in the table
        return this value
    * */
//    String sqlQuery = "SELECT latestid from idgenerator where objectname = bus;";


    QueryBuilder queryBuilder = QueryBuilder.getInstance()
                                            .select()
                                            .selectColumns("latestid")
                                            .onTable("idgenerator")
                                            .whereEq("objectname", "bus");
    String sqlQuery = queryBuilder.build();

    int newBusId =  QueryExecutor
                    .getInstance()
                    .getQueryNumber(sqlQuery) + 1;

//    sqlQuery = "update idgenerator set latestid = "+ newBusId + " where objectname = bus\';";

    queryBuilder = QueryBuilder.getInstance()
                                .update()
                                .onTable("idgenerator")
                                .updateValue("latestid", newBusId)
                                .whereEq("objectname", "bus");

    sqlQuery = queryBuilder.build();

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