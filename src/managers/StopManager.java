package managers;

import customExceptions.ApplicationException;
import dbTools.QueryExecutor;
import queryHelper.QueryBuilder;

public class StopManager extends BaseManager {

  private static StopManager stopManager;

  public static StopManager getInstance() {
    if(stopManager == null) {
      stopManager = new StopManager();
    }
    return stopManager;
  }


  public int getStopIdForName(String stopName) throws ApplicationException {
    // returns stopId for searched stopName from stopTable

    String[] columns = {"stopid"};

    QueryBuilder queryBuilder = this.getSelectInstance()
                                    .selectColumns(columns)
                                    .onTable("stop")
                                    .whereEq("stopname", stopName);
    String sqlQuery = this.buildQuery(queryBuilder);

    int stopId = this.getQueryNumber(QueryExecutor.getInstance(), sqlQuery);

    return stopId;
  }
}
