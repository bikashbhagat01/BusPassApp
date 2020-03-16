package assets;

// POJO, Only holds data

import java.util.List;

public class RouteRequest {
  private int routeRequestId;
  private int startStop;
  private int endStop;
  private int requesterId;
  private boolean routeExists;
  private int timing;
// timimg to be added on a bus by admin while route creation
  public RouteRequest(int routeRequestId, int startStop, int endStop, int requesterId, boolean routeExists, int timing) {
    this.routeRequestId = routeRequestId;
    this.startStop = startStop;
    this.endStop = endStop;
    this.requesterId = requesterId;
    this.routeExists = routeExists;
    this.timing = timing;
  }
}
