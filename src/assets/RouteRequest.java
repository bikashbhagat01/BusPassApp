package assets;

// POJO, Only holds data

public class RouteRequest {

  private int routeRequestId;
  private int startStopId;
  private int endStopId;
  private String startStopName;
  private String endStopName;
  private int requesterId;
  private boolean routeExists;
  private boolean stopsExist;
  private int timing;

  public RouteRequest(int routeRequestId, int startStop, int endStop, int requesterId,
                      boolean routeExists, int timing) {
    this.routeRequestId = routeRequestId;
    this.startStopId = startStop;
    this.endStopId = endStop;
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
    this.timing = timing;
    this.stopsExist = false;
    this.routeExists = routeExists;

    if(routeExists) {
      this.routeExists = false;
    }
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

  public int getStartStopId() {
    return startStopId;
  }

  public int getEndStopId() {
    return endStopId;
  }

  public int getRequesterId() {
    return requesterId;
  }

  public boolean isRouteExists() {
    return routeExists;
  }

  public boolean isStopsExist() {
    return stopsExist;
  }

  public int getTiming() {
    return timing;
  }
}
