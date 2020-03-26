package assets;

// POJO, Only holds data

public class Route {

  private int routeId;
  private int[] stopIds;

  public Route(int routeId, int[] stopIds) {
    this.routeId = routeId;
    this.stopIds = stopIds;
  }

  public int[] getStopIds() {
    return stopIds;
  }

  public int getRouteId() {
    return routeId;
  }
}
