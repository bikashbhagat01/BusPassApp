package managers;

import assets.Bus;
import dbTools.QueryExecutor;
import dbTools.TimeConverter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusManager {
  private static String sqlQuery;

  public static void create(Bus bus) {

  }

  public static void update(int busId, String field, int newValue)
          throws SQLException, ClassNotFoundException {
    /* Updates field with new value */
    sqlQuery = "update bus set " + field + "= " + newValue + " where busid = " + busId;
    QueryExecutor.getInstance().executeSQL(sqlQuery);
  }

  public static void displayBusCount(String criteria) throws SQLException, ClassNotFoundException {
    String sqlQuery = "select " + criteria + ",count(*) " + "from bus group by " + criteria +
            " order by " + criteria + " desc;";
    String[] fields = {"Bus Type", "Number of Buses"};
    QueryExecutor.getInstance().executeSQL(sqlQuery, fields);
  }

  public static boolean displayAvailableBusTimingsAndRoutes(int[] routeIds, int timing)
          throws SQLException, ClassNotFoundException {
    System.out.println("Bus ID\tRoute ID\tStops\tStart Timing");
    int foundResultsCounter = 0;
    for(int routeId : routeIds) {
      sqlQuery = "select busid, routeid, stopname, timing from availableroutebusmap " +
              "where routeid = " + routeId + " and timing = " + timing + ";";
      ResultSet resultSet = QueryExecutor.getInstance().getResultSet(sqlQuery);
      if (resultSet.next()) {
        foundResultsCounter += 1;
        int previousBusId = resultSet.getInt(1);
        int previousRouteId = resultSet.getInt(2);
        String stopNamesString = "";
        String eachRecord = "";
        int currentBusId = 0;
        int currentRouteId = 0;
        int currentTiming = 0;
        do {
          currentBusId = resultSet.getInt(1);
          currentRouteId = resultSet.getInt(2);
          String currentStopName = resultSet.getString(3);
          if (currentBusId == previousBusId && currentRouteId == previousRouteId) {
            stopNamesString += currentStopName + " ";
            currentTiming = resultSet.getInt(4);
          } else {
            eachRecord = previousBusId + "\t" + previousRouteId + "\t" + stopNamesString + "\t"
                    + TimeConverter.getTimeAsString(currentTiming);
            System.out.println(eachRecord);
            stopNamesString = currentStopName + " ";
            currentTiming = resultSet.getInt(4);
            previousBusId = currentBusId;
            previousRouteId = currentRouteId;
          }
        } while (resultSet.next());
        System.out.println(currentBusId + "\t" + currentRouteId + "\t" + stopNamesString + "\t"
                + TimeConverter.getTimeAsString(currentTiming));
      }
    }
    if(foundResultsCounter == 0) {
      return false;
    }
    return true;
  }


  public static boolean displayAvailableBusTimingsAndRoutes() throws SQLException, ClassNotFoundException {
    sqlQuery = "select busid, routeid, stopname, timing from availableroutebusmap;";
    ResultSet resultSet = QueryExecutor.getInstance().getResultSet(sqlQuery);
    if(!resultSet.next()){
      System.out.println("No available Values found");
      return false;
    }
    System.out.println("Bus ID\tRoute ID\tStops\tStart Timing");
    int previousBusId = resultSet.getInt(1);
    int previousRouteId = resultSet.getInt(2);
    String stopNamesString = "";
    String eachRecord = "";
    int currentBusId = 0;
    int currentRouteId = 0;
    int timing = 0;
    do{
      currentBusId = resultSet.getInt(1);
      currentRouteId = resultSet.getInt(2);
      String currentStopName = resultSet.getString(3);
      if ( currentBusId == previousBusId && currentRouteId == previousRouteId ) {
        stopNamesString += currentStopName + " ";
        timing = resultSet.getInt(4);
      } else {
        eachRecord = previousBusId + "\t" + previousRouteId + "\t" + stopNamesString + "\t"
                      + TimeConverter.getTimeAsString(timing);
        System.out.println(eachRecord);
        stopNamesString = currentStopName + " ";
        timing = resultSet.getInt(4);
        previousBusId = currentBusId;
        previousRouteId = currentRouteId;
      }
    }while(resultSet.next());
    System.out.println(currentBusId + "\t" + currentRouteId + "\t" + stopNamesString + "\t"
            + TimeConverter.getTimeAsString(timing));
    return true;
  }

  public static boolean clearRouteAssignments(int routeId) throws SQLException, ClassNotFoundException {
    sqlQuery = "select * from bus where routeid = " + routeId + ";";
    ResultSet resultSetOfBus = QueryExecutor.getInstance().getResultSet(sqlQuery);
    if(!resultSetOfBus.next()) {
      return false;
    }
    while(resultSetOfBus.next()) {
      sqlQuery = "update bus set routeid = NULL, timing = NULL, availability = bustype " +
              "WHERE routeId = " + routeId + ";";
    }
    return true;
  }

  public static void delete(int removedBus) {

  }
}
