package assets;

// POJO, Only holds data

import java.util.List;

public class RouteRequest {
  private int routeRequestId;
  private int startStop;
  private int endStop;
  private String startStopName;
  private String endStopName;
  private int requesterId;
  private boolean routeExists;
  private boolean stopsExist;
  private int timing;
// timimg to be added on a bus by admin while route creation
  public RouteRequest(int routeRequestId, int startStop, int endStop, int requesterId,
                      boolean routeExists, int timing) {
    this.routeRequestId = routeRequestId;
    this.startStop = startStop;
    this.endStop = endStop;
    this.requesterId = requesterId;
    this.routeExists = routeExists;
    this.timing = timing;
    this.stopsExist = true;
  }

  public RouteRequest(int routeRequestId, String startStopName, String endStopName, int requesterId,
                      boolean routeExists, int timing) {
    this.routeRequestId = routeRequestId;
    this.startStopName = startStopName;
    this.endStopName = endStopName;
    this.requesterId = requesterId;
    this.routeExists = routeExists;
    this.timing = timing;
    this.stopsExist = false;
  }

  public String getStartStopName() {
    return startStopName;
  }

  public String getEndStopName() {
    return endStopName;
  }

  public int getRouteRequestId() {
    return routeRequestId;
  }

  public int getStartStop() {
    return startStop;
  }

  public int getEndStop() {
    return endStop;
  }

  public int getRequesterId() {
    return requesterId;
  }

  public boolean isRouteExists() {
    return routeExists;
  }

  public int getTiming() {
    return timing;
  }
}
