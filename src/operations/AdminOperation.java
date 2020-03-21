package operations;

// All operations that can be used by the Admin

import assets.AssetFactory;
import assets.Bus;
import assets.Route;
import dbTools.QueryExecutor;
import dbTools.TimeConverter;
import dbTools.Validator;
import java.sql.SQLException;
import java.util.Scanner;
import managers.BusManager;
import managers.RouteManager;
import managers.SeatManager;
import managers.StopManager;

public class AdminOperation {

  private Scanner sc = OperationFactory.getScannerInstance();

  public void showMenu() throws SQLException, ClassNotFoundException {
    // Switch Case menu which calls other small functions in this class
    boolean exCode = false;
    String choice = "";
    while(!exCode){
      System.out.println( "\n1. Add Or remove Bus\n" +
                          "2. Add Or remove Route\n" +
                          "3. Assign a Bus to Route\n" +
                          "4. Change type of a Bus\n" +
                          "5. Display Number of Buses of each Type\n" +
                          "6. Display timings and route for each bus\n" +
                          "7. Exit to Main Menu");
      choice = sc.nextLine();
      switch (choice) {
        case "1":
          addOrRemoveBus();
          break;
        case "2":
          addOrRemoveRoute();
          break;
        case "3":
          addBusToRoute();
        case "4":
          changeBusType();
          break;
        case "5":
          displayBusCountOfEachType();
          break;
        case "6":
          displayBusTimingsAndRoutes();
          break;
        case "7":
          exCode = true;
          break;
        default:
        System.out.println("Please Enter Valid Option\n");
      }
    }
    System.out.println("Returning to Main Menu");
    OperationFactory.getAppDriverInstance().initiate();
  }

  private void addOrRemoveRoute() throws SQLException, ClassNotFoundException {
    System.out.println("Please Select an Option : \n");
    System.out.println("1. Add a New Route\n2. Remove an Existing Route\n" +
            "\n3. Return to Admin Menu");
    boolean exitCode = false;
    while(!exitCode) {
      switch (sc.next()) {
        case "1":
          addRoute();
          break;
        case "2":
          removeRoute();
          break;
        case "3":
          exitCode = true;
          break;
        default:
          System.out.println("Please Enter Valid Option\n");
      }
      System.out.println("Returning to Admin Menu");
      showMenu();
    }
  }

  private void addOrRemoveBus() throws SQLException, ClassNotFoundException {
    System.out.println("Please Select an Option : \n");
    System.out.println("1. Add a New Bus\n2. Remove an Existing Bus\n" +
            "\n3. Return to Admin Menu");
    boolean exitCode = false;
    while(!exitCode) {
      switch (sc.next()) {
        case "1":
          addBus();
          break;
        case "2":
          removeBus();
          break;
        case "3":
          exitCode = true;
          break;
        default:
          System.out.println("Please Enter Valid Option\n");
      }
      System.out.println("Returning to Admin Menu");
      showMenu();
    }
  }

  private void changeBusType() throws SQLException, ClassNotFoundException{
    /* Get the busId and new Type from Console
     * send the busId, field as [Type] and newValue as String to BusManager.update(busId, field, newValue)
     * */
    System.out.println("Enter Route Id");
    int routeid = sc.nextInt();
    String sql="SELECT * from bus where routeid="+routeid;
    QueryExecutor query = QueryExecutor.getInstance();
    if(query.isValidQuery(sql))
    {
      //Show busIds under the route
      System.out.println("Enter Bus Id");
      int busid = sc.nextInt();

      String sql1="SELECT * from bus where busid="+busid;
      if(query.isValidQuery(sql1)) {
        System.out.println("Enter BusType [Number Of Seats]");
        int busType = sc.nextInt();
        System.out.println("Enter Vehicle No");
        String vehicleNo = sc.next();

        SeatManager update= SeatManager.getInstance();
        update.updateSeatType(busType, busid, vehicleNo);
      }
      else {
        System.out.println("Bus Not Found");
      }
    }
    else {
      System.out.println("Route Not Found");
    }
    System.out.println("Bus Type and Vehicle Number Updated! ");
  }

  private boolean addBus() throws SQLException, ClassNotFoundException {
    // create a Bus object by taking details from Console
    System.out.println("Please Enter New Bus Details Below :\n");
    System.out.println("Vehicle Number:\n");
    String vehicleNo = sc.next();
    if(Validator.isPresent("bus","vehicleno", vehicleNo)){
      System.out.println(" Vehicle already Assigned to different BusId ");
      return false;
    }
    System.out.println("Bus Type [Capacity/Number of Seats]\n");
    int busType = sc.nextInt();
    Bus newBus = AssetFactory.getBusInstance(busType, busType, vehicleNo);
    BusManager.create(newBus);
    System.out.println("Your new Bus with the below details has been created:\n");
    System.out.println("Bus ID: " + newBus.getBusId() + " Bus Type: "+ newBus.getBusType()
            + " Vehicle No: " + newBus.getVehicleNo());
    return true;
  }

  private boolean removeBus() throws SQLException, ClassNotFoundException {
    // Checks if Bus Id exists or not. If yes removed it, else returns to main menu
    System.out.println("Please enter Bus Id to remove : \n");
    int removedBus = sc.nextInt();
    String checkBus = "select * from bus where busid = " + removedBus;
    if(!Validator.isPresent("bus", "busid", removedBus)) {
      System.out.println("Invalid BusId entered \nReturning to previous Menu");
      return false;
    }
    BusManager.delete(removedBus);
    System.out.println("Bus : " + removedBus + " has been removed!");
    return true;
  }

  private void addRoute() throws SQLException, ClassNotFoundException {
  // Takes StopNames, validates them and creates Route record.
    System.out.println("Pleas Enter New Route Details below :");
    System.out.println("Number of Stops:\n");
    int stopCount = sc.nextInt();
    int[] stops = new int[stopCount];
    String stopName = "";
    int count = 0;
    System.out.println("Stops [From StartStop to EndStop]\n");
    while(count < stopCount){
      System.out.println("Enter Stop Name. : " + (count+1));
      stopName = sc.next();
      if(Validator.isValidStopName(stopName)) {
        stops[count] = StopManager.getStopIdForName(stopName);
        ++count;
      } else {
        System.out.println("No such stop name found\n Please enter Valid Stop Name\n");
      }
    }
    Route newRoute = AssetFactory.getRouteInstance(stops);
    RouteManager.create(newRoute);

    System.out.println("New Route with Route ID : " + newRoute.getRouteId() + "has been created");
    System.out.println("Returning to Bus menu.");
  }

  private boolean removeRoute() throws SQLException, ClassNotFoundException {
    // Checks if Route Id exists or not. If yes, remove route from route table
    System.out.println("Please enter Route Id to remove : \n");
    int removedRouteId = sc.nextInt();
    if(!Validator.isPresent("route", "routeid", removedRouteId)) {
      System.out.println("Invalid Route ID entered \nReturning to previous Menu");
      return false;
    }
    BusManager.clearRouteAssignments(removedRouteId);
    RouteManager.delete(removedRouteId);
    System.out.println("Route : " + removedRouteId + " has been removed from Route List!\n" +
            "All assignments of this Route to Buses have been cleared.");
    return true;
  }

  private boolean addBusToRoute() throws SQLException, ClassNotFoundException {
    // Takes a RouteId, Start Timing  and adds it to the Bus table
    System.out.println("Enter details :");
    System.out.println("Route ID:");
    int routeId = sc.nextInt();
    if(!Validator.isPresent("route","routeid",routeId)) {
      System.out.println("Route ID not found. Please add a Route from Route Menu");
      return false;
    }
    System.out.println("Bus Id : \n");
    int busId = sc.nextInt();
    if(!Validator.isPresent("bus","busid", busId)){
      System.out.println("Bus ID not found. Please add a Bus from Route Menu");
      return false;
    }
    System.out.println("Start Timing [in 24 hour format separated by : Or / or -]:\n");
    String timingString = sc.next();
    int timeInMinutes = TimeConverter.getTimeAsMinutes(timingString);
    BusManager.update(busId, "routeid", routeId);
    BusManager.update(busId,"timing", timeInMinutes);
    System.out.println("Route : " + routeId + " & timing " + timingString +
            " has Been added for bus : " + busId);
    return true;
  }

  public void approveRoute() {
    /*if route requests table empty, display no request found
    * if route requests exist, show details to Admin
    * if routeExists flag is true, assign routeId and timing to a bus
    * Search for Stops using StopManager.seacrh() - if found assign
    * Start and end as relevant StopIds
    * If stops not found,  ask Admin if they want to create a Stop using StopManager and get a stopId
    * or they want to deny
    * If they deny, delete the routeRequest
    * RouteManager.searchRoute(start,end), which searches for the set of Stops and returns a
    * set of routeIds where the Stops fall in
    * if route exists for stops mentioned but time doesn't, then admin assigns a Bus to the route
    * if route doesn't exist then admin creates route and creates/updates bus for the timing
    * */

  }

  private void displayBusTimingsAndRoutes() throws SQLException, ClassNotFoundException {
    System.out.println("Below are Available Bus Timings and Routes");
    BusManager.displayAvailableBusTimingsAndRoutes();
  }

  private void displayBusCountOfEachType() throws SQLException, ClassNotFoundException {
    // Executes Query to find Bus Type and their count
    BusManager.displayBusCount("bustype");
  }
}
