package managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import assets.Route;
import dbTools.QueryExecutor;
import interfaces.Creatable;
import java.util.Arrays;
import java.util.List;

public class RouteManager {
  static String sqlQuery;
  public static void create(Route route) throws SQLException, ClassNotFoundException {

    // Creates sqlQuery from Route Object and calls QueryExecutor.executeQuery(sqlQuery)
    // Creates sqlQuery to write routeId and stopIds into route - stop table
    for(int i=0;i<route.getStops().length;i++)
    {
      sqlQuery="insert into route routeid="+route.getRouteId()+"stopid="+route.getStops()[i];
      QueryExecutor.getInstance().executeSQL(sqlQuery);
      System.out.println("RouteID and Stops updated\n");
    }
  }

  public static int[] findRoutesForStops(int startStop, int endStop)
          throws SQLException, ClassNotFoundException {
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

  public static int[] findRoutesForStops(int stopId) throws SQLException, ClassNotFoundException {
    sqlQuery = "select routeid from route where stopid = " + stopId + ";";
    ResultSet resultSet = QueryExecutor.getInstance().getResultSet(sqlQuery);
    List<Integer> resultList = new ArrayList<>();
    int count = 1;
    while(resultSet.next()){
      resultList.add(resultSet.getInt(count));
      ++count;
    }
    int[] resultArray = new int[resultList.size()];
    for(int i = 0; i < resultList.size(); i++) {
      resultArray[i] = resultList.get(i);
    }
    return resultArray;
  }

  public static void delete(int routeId) {

  }
}

