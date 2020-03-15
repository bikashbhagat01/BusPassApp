package assets;

// POJO, Only holds data

import java.util.List;

public class RouteRequest {
  private int routeRequestId;
  private List<String> stops;
  private int requesterId;

  public RouteRequest(int routeRequestId, List<String> stops, int requesterId) {
    this.routeRequestId = routeRequestId;
    this.stops = stops;
    this.requesterId = requesterId;
  }
}
