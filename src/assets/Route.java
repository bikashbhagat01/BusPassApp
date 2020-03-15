package assets;

// POJO, Only holds data

import java.util.List;

public class Route {
  private int routeId;
  private int[] stopIds;
  // Create map of Stop to routeId for lookUp
  private String routeName; // Only keeps " FirstStop - LastStop "

  public Route(int routeId, int[] stopIds) {
    this.routeId = routeId;
    this.stopIds = stopIds;
  }

  //  Setters and Getters to be added
  //  Route can only be created with a set of stops and a routeId
}
