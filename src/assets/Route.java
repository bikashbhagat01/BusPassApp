package assets;

// POJO, Only holds data

public class Route {

  private int routeId;
  private int[] stopIds;
  private int[] stopRanks;

  public Route(int routeId, int[] stopIds, int[] stopRanks) {
    this.routeId = routeId;
    this.stopIds = stopIds;
    this.stopRanks = stopRanks;
  }

  public int[] getStopIds() {
    return stopIds;
  }

  public int getRouteId() {
    return routeId;
  }

  public int[] getStopRanks() {
    return stopRanks;
  }
}
