package assets;

import customExceptions.ApplicationException;
import managers.FeedbackManager;
import managers.IdManager;

public class AssetFactory {

  private static AssetFactory assetFactory;

  public static AssetFactory getInstance() {
    if(assetFactory == null) {
      assetFactory = new AssetFactory();
    }
    return assetFactory;
  }

  // returns Objects of various types
  public Bus getBusInstance(int availability, int busType, String vehicleNo)
          throws ApplicationException {
    int busId = IdManager.getInstance().getNewId("bus");

    Bus bus = new Bus(busId, availability, busType, vehicleNo);
    return bus;
  }

  public BusPass getBusPassInstance(int userId, int routeId, int busId, int timing)
          throws ApplicationException {
    int busPassId = IdManager.getInstance().getNewId("buspass");

    BusPass busPass = new BusPass(busPassId, userId, busId, routeId, timing);
    return busPass;
  }

  public Feedback getFeedbackInstance(int userId, String comment) throws ApplicationException {
    int feedbackId = IdManager.getInstance().getNewId("feedback");
    Feedback feedback = new Feedback(feedbackId, userId, comment);
    return feedback;
  }

  public Route getRouteInstance(int stops[], int[] stopRanks) throws ApplicationException {
    int routeId = IdManager.getInstance().getNewId("route");
    Route route = new Route(routeId, stops, stopRanks);
    return route;
  }

  public RouteRequest getRouteRequestInstance(int startStop, int endStop,
                                                     int requesterId, boolean routeExists,
                                                     int timing) throws ApplicationException {
    int routeRequestId = IdManager.getInstance().getNewId("routerequest");
    RouteRequest routeRequest = new RouteRequest(routeRequestId, startStop, endStop,
            requesterId, routeExists, timing);
    return routeRequest;
  }

  public RouteRequest getRouteRequestInstance(String startStopName, String endStopName,
                                                     int requesterId, boolean routeExists,
                                                     int timing) throws ApplicationException {
    int routeRequestId = IdManager.getInstance().getNewId("routerequest");
    RouteRequest routeRequest = new RouteRequest(routeRequestId, startStopName, endStopName,
            requesterId, routeExists, timing);
    return routeRequest;
  }

  public User getUserInstance(int employeeId, String fName, String lName,
                                     String email, String contactNo, String emergencyContactNumber,
                                     String emergencyContactName, String bloodGroup,
                                     String password) {

    return new User(employeeId, fName, lName, email,
            contactNo, emergencyContactNumber,
            emergencyContactName, bloodGroup,
            password);
  }

  public Stop getStopInstance(String name) throws ApplicationException {
    int stopId = IdManager.getInstance().getNewId("stop");
    Stop stop = new Stop(stopId, name);
    return stop;
  }
}