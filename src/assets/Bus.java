package assets;

// POJO, Only holds data

public class Bus {

  private int busId;
  private int routeId;
  private int availability; // number of available seats
  private int timing; // In minutes. If no
  private int busType; // Capacity of the bus
  private String vehicleNo;

  public Bus(int busId, int availability, int busType, String vehicleNo) {
    this.busId = busId;
    this.availability = availability;
    this.busType = busType;
    this.vehicleNo = vehicleNo;
    this.timing = -1;
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

  public void setTiming(int timing) {
    this.timing = timing;
  }
}
