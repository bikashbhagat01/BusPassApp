package managers;

import assets.Stop;
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

    int stopId = this.getQueryNumber(sqlQuery);

    return stopId;
  }

  public boolean displayAllStops() throws ApplicationException {

    String[] columns = {"stopid", "stopname"};

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns(columns)
            .onTable("stop");

    String sqlQuery = this.buildQuery(queryBuilder);

    if(!this.hasResult(sqlQuery)) {
      return false;
    }

    String[] headers = {"STOP ID", "STOP NAME"};

    this.executeQuery(sqlQuery,headers);

    return true;
  }

  public void create(Stop stop) throws ApplicationException {
    QueryBuilder queryBuilder = this.getInsertInstance()
            .insertValue("stopid", stop.getStopId())
            .insertValue("stopname", stop.getStopName());

    String sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(sqlQuery);
  }
}
