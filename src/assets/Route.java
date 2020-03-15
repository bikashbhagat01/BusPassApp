package assets;

// POJO, Only holds data

import java.util.List;

public class Route {
  private int routeId;
  private List<String> stops;
  private String routeName; // Only keeps " FirstStop - LastStop "

  public Route(int routeId, List<String> stops) {
    this.routeId = routeId;
    this.stops = stops;
  }

  //  Setters and Getters to be added
  //  Route can only be created with a sent of stops and routeId
}
