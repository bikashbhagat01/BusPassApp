package managers;

import customExceptions.ApplicationException;
import dbTools.TimeConverter;
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

    String sqlQuery = this.buildQuery(queryBuilder);

    sqlQuery = sqlQuery.substring(0,sqlQuery.length()-1) + " ORDER BY bustype DESC;";

    ResultSet resultSet = this.getResultSet(sqlQuery);

    if (this.isNextPresent(resultSet)) {
      busBooked = this.getInt(resultSet, 1);
      newAvailability = this.getInt(resultSet, 2) - 1;

      queryBuilder = this.getUpdateInstance()
              .onTable("bus")
              .updateValue("availability", newAvailability)
              .whereEq("busid", busBooked);

      sqlQuery = this.buildQuery(queryBuilder);

      this.executeQuery(sqlQuery);

      return busBooked;
    }
    return 0;
  }

  public boolean updateSeatType(int type, int busId, String vehicleNo) throws ApplicationException {
    int currentSeatCapacity, currentSeatAvailability, seatAvailabilityReduce, seatAvailabilityIncrease;
    int newVehicleType = type;

    String[] columns = {"availability", "bustype"};

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns(columns)
            .onTable("bus")
            .whereEq("busid", busId);

    String sqlQuery = this.buildQuery(queryBuilder);

    ResultSet resultSet = this.getResultSet(sqlQuery);

    this.isNextPresent(resultSet);
//    resultSet.next();
    currentSeatAvailability = this.getInt(resultSet, 1);
    currentSeatCapacity = this.getInt(resultSet, 2);

    seatAvailabilityReduce = currentSeatAvailability - (currentSeatCapacity - newVehicleType);
    seatAvailabilityIncrease = currentSeatAvailability + (newVehicleType - currentSeatCapacity);

    if (newVehicleType > currentSeatCapacity) {

      queryBuilder = this.getUpdateInstance()
              .onTable("bus")
              .updateValue("availability", seatAvailabilityIncrease)
              .updateValue("bustype", newVehicleType)
              .whereEq("busid", busId);

      sqlQuery = this.buildQuery(queryBuilder);

      this.executeQuery(sqlQuery);

      return true;
    }

    if (seatAvailabilityReduce >= currentSeatAvailability) {

      queryBuilder = this.getUpdateInstance()
              .onTable("bus")
              .updateValue("availability", seatAvailabilityReduce)
              .updateValue("bustype", newVehicleType)
              .updateValue("vehicleno", vehicleNo)
              .whereEq("busid", busId);

      sqlQuery = this.buildQuery(queryBuilder);

      this.executeQuery(sqlQuery);

      return true;
    }
    return false;
  }

  public boolean displaySeatAvailabilityPerRoute() throws ApplicationException {

    String columns[] = {"timing", "routeid", "availability"};

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns(columns)
            .onTable("bus")
            .whereGt("timing", -1)
            .groupBy("timing")
            .groupBy("routeid");

    String sqlQuery = this.buildQuery(queryBuilder);

    ResultSet resultSet = this.getResultSet(sqlQuery);

    if(!this.isNextPresent(resultSet)) {
      System.out.println("\nNo Seat Availability found on any route\n");
      return false;
    }
    this.goToPrevious(resultSet);

    System.out.println("START TIMING\tROUTE ID\t\tAVAILABLE SEATS");

    while (this.isNextPresent(resultSet)) {
      int timingInMinutes = this.getInt(resultSet,1);
      String timeString = TimeConverter.getTimeAsString(timingInMinutes);

      System.out.println(timeString + "\t\t\t\t\t\t" +
              this.getInt(resultSet, 2) + "\t\t\t\t\t\t" +
              this.getInt(resultSet, 3) + "\t\t");
    }
    return true;
  }
}