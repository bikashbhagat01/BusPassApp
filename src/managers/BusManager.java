package managers;

import assets.Bus;
import customExceptions.ApplicationException;
import dbTools.QueryExecutor;
import dbTools.TimeConverter;
import java.sql.ResultSet;
import queryHelper.QueryBuilder;

public class BusManager extends BaseManager {

  private static BusManager busManager;

  public static BusManager getInstance() {
    if (busManager == null) {
      busManager = new BusManager();
    }
    return busManager;
  }

  public void create(Bus bus) throws ApplicationException {
    QueryBuilder queryBuilder = this.getInsertInstance()
                                    .onTable("bus")
                                    .insertValue("busid", bus.getBusId())
                                    .insertValue("routeid", bus.getRouteId())
                                    .insertValue("availability", bus.getAvailability())
                                    .insertValue("bustype", bus.getBusType())
                                    .insertValue("timing", bus.getTiming())
                                    .insertValue("vehicleno", bus.getVehicleNo());

    String sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(QueryExecutor.getInstance(),sqlQuery);
  }

  public void update(int busId, String field, int newValue) throws ApplicationException {

    QueryBuilder queryBuilder = this.getUpdateInstance()
                                    .onTable("bus")
                                    .updateValue(field, newValue)
                                    .whereEq("busid", busId);

    String sqlQuery = this.buildQuery(queryBuilder);

    System.out.println(sqlQuery + "from update in BusManager");

    this.executeQuery(QueryExecutor.getInstance(), sqlQuery);
  }

  public void displayBusCount(String criteria) throws ApplicationException {

    String[] columns = {criteria, "count(*)"};

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns(columns)
            .onTable("bus");

    String sqlQuery = this.buildQuery(queryBuilder);

    sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1) +
            " group by " +
            criteria +
            " order by " +
            criteria +
            " desc;";

    String[] fields = {"Bus Type", "Number of Buses"};

    this.executeQuery(QueryExecutor.getInstance(), sqlQuery, fields);
  }

  public boolean displayAvailableBusTimingsAndRoutes() throws ApplicationException {
    String[] columns = {"busid", "routeid", "stopname", "timing"};

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns(columns)
            .onTable("availableroutebusmap");

    String sqlQuery = this.buildQuery(queryBuilder);

    ResultSet resultSet = this.getResultSet(QueryExecutor.getInstance(), sqlQuery);

    if (!this.isNextPresent(resultSet)) {
      return false;
    }

    if (!displayDetailsFromResultSet(resultSet, true)) {
      System.out.println("No available Values found");
      return false;
    }
    return true;
  }

  public boolean displayAvailableBusTimingsAndRoutes(int[] routeIds, int timing)
          throws ApplicationException {
    System.out.println("Bus ID\t\tRoute ID\t\tStops\t\tStart Timing");
    int foundResultsCounter = 0;

    for (int routeId : routeIds) {

      String[] columns = {"busid", "routeid", "stopname", "timing"};

      QueryBuilder queryBuilder = this.getSelectInstance()
              .selectColumns(columns)
              .onTable("availableroutebusmap")
              .whereEq("routeid", routeId)
              .whereEq("timing", timing);
      String sqlQuery = this.buildQuery(queryBuilder);

      ResultSet resultSet = this.getResultSet(QueryExecutor.getInstance(), sqlQuery);

      if (displayDetailsFromResultSet(resultSet, false)) {
        foundResultsCounter++;
      }
    }

    if (foundResultsCounter > 0) {
      return true;
    }
    return false;
  }

  public boolean displayDetailsFromResultSet(ResultSet resultSet, boolean headingSwitch) throws ApplicationException {
    if (!this.isNextPresent(resultSet)) {
      return false;
    }

    if(headingSwitch) {
      System.out.println("Bus ID\t\tRoute ID\t\tStops\t\tStart Timing");
    }

    int previousBusId = this.getInt(resultSet, 1);
    int previousRouteId = this.getInt(resultSet, 2);

    String stopNamesString = "";
    String eachRecord = "";
    String currentStopName = "";

    int currentBusId = 0;
    int currentRouteId = 0;
    int currentTiming = 0;

    do {
      currentBusId = this.getInt(resultSet, 1);
      currentRouteId = this.getInt(resultSet, 2);
      currentStopName = this.getString(resultSet, 3);

      if (currentBusId == previousBusId && currentRouteId == previousRouteId) {
        stopNamesString += currentStopName + " ";
        currentTiming = this.getInt(resultSet, 4);
      } else {
        eachRecord = previousBusId + "\t\t" +
                previousRouteId + "\t\t" +
                stopNamesString + "\t\t" +
                TimeConverter.getTimeAsString(currentTiming);

        System.out.println(eachRecord);

        stopNamesString = currentStopName + " ";

        currentTiming = this.getInt(resultSet, 4);
        previousBusId = currentBusId;
        previousRouteId = currentRouteId;
      }
    } while (this.isNextPresent(resultSet));

    System.out.println(currentBusId + "\t\t" +
            currentRouteId + "\t\t" +
            stopNamesString + "\t\t" +
            TimeConverter.getTimeAsString(currentTiming));

    return true;
  }


  public boolean clearRouteAssignments(int routeId) throws ApplicationException {
    // Checks for availability of any buses linked to routeId

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectAllColumns()
            .onTable("bus")
            .whereEq("routeid", routeId);

    String sqlQuery = this.buildQuery(queryBuilder);

    ResultSet resultSetOfBus = this.getResultSet(QueryExecutor.getInstance(), sqlQuery);

    if (!this.isNextPresent(resultSetOfBus)) { // No Buses are linked, No update required
      return false;
    }

    queryBuilder = this.getUpdateInstance()
            .onTable("bus")
            .updateValue("routeid", "NULL")
            .updateValue("timing", "NULL")
            .updateValue("availability", "bustype")
            .whereEq("routeid", routeId);

    sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(QueryExecutor.getInstance(), sqlQuery);

    return true;
  }

  public void delete(int busIdToRemove) throws ApplicationException {
    QueryBuilder queryBuilder = this.getDeleteInstance()
            .onTable("bus")
            .whereEq("busid", busIdToRemove);

    String sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(QueryExecutor.getInstance(), sqlQuery);
  }


  public boolean isBusAvailableForRoutesAndTiming(int[] routeIds, int timing)
          throws ApplicationException {
    if (routeIds.length == 0) {
      return false;
    }

    for (int routeId : routeIds) {
      QueryBuilder queryBuilder = this.getSelectInstance()
              .selectAllColumns()
              .whereEq("routeid", routeId)
              .whereEq("timing", timing)
              .whereLte("availability", "bustype");

      String sqlQuery = this.buildQuery(queryBuilder);

      ResultSet resultSet = this.getResultSet(QueryExecutor.getInstance(), sqlQuery);

      if (this.isNextPresent(resultSet)) {
        return true;
      }
    }
    return false;
  }
}
