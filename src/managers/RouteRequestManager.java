package managers;

import assets.RouteRequest;
import customExceptions.ApplicationException;
import queryHelper.QueryBuilder;

public class RouteRequestManager extends BaseManager{

  private static RouteRequestManager routeRequestManager;

  public static RouteRequestManager getInstance() {
    if(routeRequestManager == null) {
      routeRequestManager = new RouteRequestManager();
    }
    return routeRequestManager;
  }

  public void create(RouteRequest routeRequest) throws ApplicationException {

    QueryBuilder queryBuilder = this.getInsertInstance()
                                    .onTable("routerequest")
                                    .insertValue("routerequestid", routeRequest.getRouteRequestId())
                                    .insertValue("startstop", routeRequest.getStartStopId())
                                    .insertValue("endstop", routeRequest.getEndStopId())
                                    .insertValue("startstopname", routeRequest.getStartStopName())
                                    .insertValue("endstopname", routeRequest.getEndStopName())
                                    .insertValue("requesterid", routeRequest.getRequesterId())
                                    .insertValue("routeexists", routeRequest.isRouteExists())
                                    .insertValue("stopsexist", routeRequest.isStopsExist())
                                    .insertValue("timing", routeRequest.getTiming());

    String sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(sqlQuery);
  }
}
