package managers;

import customExceptions.ApplicationException;
import java.sql.ResultSet;
import java.util.ArrayList;

import assets.Route;
import dbTools.QueryExecutor;
import java.util.List;
import queryHelper.QueryBuilder;

public class RouteManager extends BaseManager {

  private static RouteManager routeManager;

  public static RouteManager getInstance() {
    if(routeManager == null) {
      routeManager = new RouteManager();
    }
    return routeManager;
  }

  public boolean create(Route route) throws ApplicationException {

    // Creates sqlQuery from Route Object and calls QueryExecutor.executeQuery(sqlQuery)
    // Creates sqlQuery to write routeId and stopIds into route - stop table
    //     String sqlQuery="insert into route routeid="+route.getRouteId()+"stopid="+route.getStops()[i];

    for(int i=0;i<route.getStops().length;i++) {
      QueryBuilder queryBuilder = this.getInsertInstance()
                                      .insertValue("routeid", route.getRouteId())
                                      .insertValue("stopid", route.getStops()[i])
                                      .onTable("route");

      String sqlQuery = this.buildQuery(queryBuilder);

      this.executeQuery(QueryExecutor.getInstance(), sqlQuery);

    }
    System.out.println("RouteID and Stops updated\n");
    return true;
  }

  public int[] findRoutesForStops(int startStop, int endStop)
          throws ApplicationException {
    int[] startCommonRoutes = findRoutesForStops(startStop);
    int[] endCommonRoutes = findRoutesForStops(endStop);

    List<Integer> startRouteList = new ArrayList<>();
    List<Integer> resultList = new ArrayList<>();

    for(int startRouteId : startCommonRoutes) {
      startRouteList.add(startRouteId);
    }

    for(int endRouteId : endCommonRoutes) {
      if(startRouteList.contains(endRouteId)) {
        resultList.add(endRouteId);
      }
    }

    int[] resultArray = new int[resultList.size()];

    for(int i = 0; i < resultList.size(); i++) {
      resultArray[i] = resultList.get(i);
    }
    return resultArray;
  }

  public int[] findRoutesForStops(int stopId) throws ApplicationException {
//    String sqlQuery = "select routeid from route where stopid = " + stopId + ";";

    String[] columns = {"routeid"};

    QueryBuilder queryBuilder = this.getSelectInstance()
                                    .selectColumns(columns)
                                    .onTable("route")
                                    .whereEq("stopid", stopId);

    String sqlQuery = this.buildQuery(queryBuilder);

    ResultSet resultSet = this.getResultSet(QueryExecutor.getInstance(),sqlQuery);

    List<Integer> resultList = new ArrayList<>();

    int count = 1;

    while(this.isNextPresent(resultSet)){
      resultList.add(this.getInt(resultSet,count));
      ++count;
    }

    int[] resultArray = new int[resultList.size()];

    for(int i = 0; i < resultList.size(); i++) {
      resultArray[i] = resultList.get(i);
    }
    return resultArray;
  }

  public boolean delete(int routeIdToRemove) {
    return false;
  }
}

