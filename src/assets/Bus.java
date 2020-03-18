package assets;

// POJO, Only holds data

public class Bus {
  private int busId;
  private int routeId;
  private int availability; // number of available seats
  private int timing; // In minutes
  private int busType; // Capacity of the bus
  private String vehicleNo;

  public Bus(int busId, int availability, int busType, String vehicleNo) {
    this.busId = busId;
    this.availability = availability;
    this.busType = busType;
    this.vehicleNo = vehicleNo;
  }

  public int getBusId() {
    return busId;
  }

  public int getAvailability() {
    return availability;
  }

  public int getTiming() {
    return timing;
  }

  public int getBusType() {
    return busType;
  }

  public String getVehicleNo() {
    return vehicleNo;
  }

  public int getRouteId() {
    return routeId;
  }

  public void setRouteId(int routeId) {
    this.routeId = routeId;
  }

  public void setAvailability(int availability) {
    this.availability = availability;
  }

  public void setTiming(int timing) {
    this.timing = timing;
  }

  public void setBusType(int busType) {
    this.busType = busType;
  }

  public void setVehicleNo(String vehicleNo) {
    this.vehicleNo = vehicleNo;
  }

  //  Setters and Getters to be added
  //  Bus can only be created with a valid key

}
