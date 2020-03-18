package assets;

// POJO, Only holds data

import java.util.List;

public class Route {
  private int routeId;
  private int[] stopIds;
  // Create map of Stop to routeId for lookUp

  public Route(int routeId, int[] stopIds) {
    this.routeId = routeId;
    this.stopIds = stopIds;
  }

  public int[] getStops() {
    return new int[0];
  }

  public int getRouteId() {
    return routeId;
  }

  //  Setters and Getters to be added
  //  Route can only be created with a set of stops and a routeId
}
