package assets;

// POJO, Only holds data

import java.util.List;

public class RouteRequest {
  private int routeRequestId;
  private List<String> stops;
  private int requesterId;
  private boolean routeExists;
  private int timing;
// timimg to be added on a bus by admin while route creation
  public RouteRequest(int routeRequestId, List<String> stops, int requesterId, boolean routeExists) {
    this.routeRequestId = routeRequestId;
    this.stops = stops;
    this.requesterId = requesterId;
    this.routeExists = routeExists;
  }
}
