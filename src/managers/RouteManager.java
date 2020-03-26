package managers;

import customExceptions.ApplicationException;
import dbTools.TimeConverter;
import java.sql.ResultSet;
import java.util.ArrayList;
import assets.Route;
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
    for(int i = 0; i<route.getStopIds().length; i++) {
      QueryBuilder queryBuilder = this.getInsertInstance()
                                      .insertValue("routeid", route.getRouteId())
                                      .insertValue("stopid", route.getStopIds()[i])
                                      .onTable("route");

      String sqlQuery = this.buildQuery(queryBuilder);

      this.executeQuery(sqlQuery);

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
    String[] columns = {"routeid"};

    QueryBuilder queryBuilder = this.getSelectInstance()
                                    .selectColumns(columns)
                                    .onTable("route")
                                    .whereEq("stopid", stopId);

    String sqlQuery = this.buildQuery(queryBuilder);

    ResultSet resultSet = this.getResultSet(sqlQuery);

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

  public boolean delete(int routeIdToRemove) throws ApplicationException {

    QueryBuilder queryBuilder = this.getDeleteInstance()
            .onTable("route")
            .whereEq("routeid", routeIdToRemove);

    String sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(sqlQuery);

    return false;
  }

  public boolean displayAllRoutes() throws ApplicationException {
    String[] columns = {"routeid","stopname"};

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns(columns)
            .onTable("routestopname");

    String sqlQuery = this.buildQuery(queryBuilder);

    ResultSet resultSet = this.getResultSet(sqlQuery);

    if(!this.isNextPresent(resultSet)) {
      System.out.println("No routes were found");
      return false;
    }

    System.out.println("Route ID \t\t\t\t\t Stops from Start to End\n");
    String stopNamesString = "";
    String eachRecord = "";
    String currentStopName = "";

    int currentRouteId = 0;
    int previousRouteId = this.getInt(resultSet,1);

    do {
      currentRouteId = this.getInt(resultSet, 1);
      currentStopName = this.getString(resultSet, 2);
      if (currentRouteId == previousRouteId) {
        stopNamesString += currentStopName + "--> ";
      } else {
        eachRecord = previousRouteId + "\t\t" +
                  "--> " + stopNamesString ;

        System.out.println(eachRecord );

        stopNamesString = currentStopName + "--> ";
        previousRouteId = currentRouteId;
      }
    } while (this.isNextPresent(resultSet));

    System.out.println( currentRouteId + "\t\t" +
            "--> " + stopNamesString );

    return true;
  }
}

