package assets;

import dbTools.IdGenerator;
import java.sql.SQLException;

public class AssetFactory {

  // returns Objects of various types
  public static Bus getBusInstance(int busType, int availability, String vehicleNo) throws SQLException, ClassNotFoundException {
    int busId=IdGenerator.getNewBusId();
    Bus bus= new Bus(busId, busType, availability, vehicleNo);
    return bus;
  }

  public static BusPass getBusPassInstance(int userId, int routeId, int busId, int timing)
  {
    int busPassId=IdGenerator.getNewBusPassId();
    BusPass busPass = new BusPass(busPassId, userId, busId, routeId, timing);
    return busPass;
  }

  public static Feedback getFeedbackInstance(String comments, int userId)
  {
    int feedbackId=IdGenerator.getNewFeedbackId();
    Feedback feedback = new Feedback(feedbackId, comments, userId);
    return feedback;
  }

  public static Route getRouteInstance(int stops[])
  {
    int routeId=IdGenerator.getNewRouteId();
    Route route = new Route(routeId, stops);
    return route;
  }

  public static RouteRequest getRouteRequestInstance(int startStop, int endStop, int requesterId, boolean routeExists, int timing)
  {
    int routeRequestId=IdGenerator.getNewRouteRequestId();
    RouteRequest routeRequest= new RouteRequest(routeRequestId, startStop, endStop, requesterId, routeExists, timing);
    return routeRequest;
  }

  public static User getUserInstance(int employeeId, String fName, String lName, String email, String contactNo, String emergencyContactNumber, String emergencyContactName, String bloodGroup,
                              String password)
  {

    return new User(employeeId, fName, lName, email,
            contactNo, emergencyContactNumber,
            emergencyContactName, bloodGroup,
            password);
  }

  public static Stop getStopInstance(String name)
  {
    int stopId=IdGenerator.getNewStopId();
    Stop stop= new Stop(stopId, name);
    return stop;
  }
}