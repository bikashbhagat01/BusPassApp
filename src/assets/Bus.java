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

  //  Setters and Getters to be added
  //  Bus can only be created with a valid key

}
