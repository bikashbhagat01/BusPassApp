package managers;

import customExceptions.ApplicationException;
import dbTools.QueryExecutor;
import java.sql.ResultSet;
import queryHelper.QueryBuilder;

public class SeatManager extends BaseManager {

  // checking and updating seat capacity for a bus in a route while generating buss pass

  private static SeatManager seatManager;

  private SeatManager() {
  }

  public static SeatManager getInstance() {
    if (seatManager == null) {
      seatManager = new SeatManager();
    }
    return seatManager;
  }

  public int updateSeatValue(int routeId, int time) throws ApplicationException {

    int newAvailability, busBooked;

    String columns[] = {"busid", "availability", "bustype"};

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns(columns)
            .onTable("bus")
            .whereEq("routeid", routeId)
            .whereEq("timing", time)
            .whereGt("availability", 0);

    String sqlQuery = this.buildQuery(queryBuilder) + " ORDER BY bustype DESC;";

    ResultSet resultSet = this.getResultSet(QueryExecutor.getInstance(), sqlQuery);

    if (this.isNextPresent(resultSet)) {
      busBooked = this.getInt(resultSet, 1);
      newAvailability = this.getInt(resultSet, 2) - 1;

      queryBuilder = this.getUpdateInstance()
              .onTable("bus")
              .updateValue("availability", newAvailability)
              .whereEq("busid", busBooked);
      sqlQuery = this.buildQuery(queryBuilder);

      this.executeQuery(QueryExecutor.getInstance(), sqlQuery);

      return busBooked;
    }
    return 0;
  }

  public boolean updateSeatType(int type, int busId, String vehicleNo) throws ApplicationException {
    //this is to update seat type in a bus based on a route
    int dbSeatCapacity, dbSeatAvailability, reducedSeatAvailability, increasedSeatAvailability;
    int newVehicleType = type;

    String[] columns = {"availability", "bustype"};

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns(columns)
            .onTable("bus")
            .whereEq("busid", busId);

    String sqlQuery = this.buildQuery(queryBuilder);

    ResultSet resultSet = this.getResultSet(QueryExecutor.getInstance(), sqlQuery);

    this.isNextPresent(resultSet);
//    resultSet.next();
    dbSeatAvailability = this.getInt(resultSet, 1);
    dbSeatCapacity = this.getInt(resultSet, 2);

    reducedSeatAvailability = dbSeatAvailability - (dbSeatCapacity - newVehicleType);
    increasedSeatAvailability = dbSeatAvailability + newVehicleType - dbSeatCapacity;

    if (newVehicleType > dbSeatCapacity) {

      queryBuilder = this.getUpdateInstance()
              .onTable("bus")
              .updateValue("availability", increasedSeatAvailability)
              .updateValue("bustype", newVehicleType)
              .whereEq("busid", busId);

      sqlQuery = this.buildQuery(queryBuilder);

      this.executeQuery(QueryExecutor.getInstance(), sqlQuery);

      return true;
    }

    if (reducedSeatAvailability >= dbSeatAvailability) {

      queryBuilder = this.getUpdateInstance()
              .onTable("bus")
              .updateValue("availability", reducedSeatAvailability)
              .updateValue("bustype", newVehicleType)
              .updateValue("vehicleno", vehicleNo)
              .whereEq("busid", busId);

      sqlQuery = this.buildQuery(queryBuilder);

      this.executeQuery(QueryExecutor.getInstance(), sqlQuery);

      return true;
    }
    return false;
  }

  public void displaySeatAvailabilityPerRoute() throws ApplicationException {

    String columns[] = {"timing", "routeid", "availability"};

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns(columns)
            .onTable("bus")
            .groupBy("routeid")
            .groupBy("timing");

    String sqlQuery = this.buildQuery(queryBuilder);

    ResultSet resultSet = this.getResultSet(QueryExecutor.getInstance(), sqlQuery);

    while (this.isNextPresent(resultSet)) {
      System.out.println(this.getInt(resultSet, 1) + "\t\t" +
              this.getInt(resultSet, 2) + "\t\t" +
              this.getInt(resultSet, 3) + "\t\t");
    }
  }
}