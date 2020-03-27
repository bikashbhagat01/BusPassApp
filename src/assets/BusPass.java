package assets;

// POJO, Only holds data

public class BusPass {

  private int busPassId;
  private int userId;
  private int busId;
  private int routeId;
  private int timing;

  public BusPass(int busPassId, int userId, int routeId, int busId, int timing) {
    this.busPassId = busPassId;
    this.userId = userId;
    this.busId = busId;
    this.routeId = routeId;
    this.timing = timing;
  }

  public int getBusPassId() {
    return busPassId;
  }

  public int getUserId() {
    return userId;
  }

  public int getBusId() {
    return busId;
  }

  public int getRouteId() {
    return routeId;
  }

  public int getTiming() {
    return timing;
  }
}
