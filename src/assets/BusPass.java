package assets;

// POJO, Only holds data

public class BusPass {
  private int busPassId;
  private int userId;
  private int busId;
  private int routeId;
  private int timing;

  public BusPass(int busPassId, int userId, int busId, int routeId, int timing) {
    this.busPassId = busPassId;
    this.userId = userId;
    this.busId = busId;
    this.routeId = routeId;
    this.timing = timing;
  }

  //  Setters and Getters to be added
  //  BusPass can only be created with a valid key, user and busId
}
