package operations;

// All operations that can be used by the Admin

import assets.AssetFactory;
import assets.Bus;
import dbTools.QueryExecutor;
import java.sql.SQLException;
import java.util.Scanner;
import managers.BusManager;
import managers.SeatManager;

public class AdminOperation {

  private Scanner sc = OperationFactory.getScannerInstance();

  public void showMenu() throws SQLException, ClassNotFoundException {
    // Switch Case menu which calls other small functions in this class
    boolean exCode = false;
    String choice = "";
    while(!exCode){
      System.out.println( "\n1. Add Or remove Bus\n" +
                          "2. Add Or remove Route\n" +
                          "3. Change type of a Bus\n" +
                          "4. Display Number of Buses of each Type\n" +
                          "5. Display timings and route for each bus\n" +
                          "6. Exit to Main Menu");
      choice = sc.nextLine();
      switch (choice) {
        case "1":
          addOrRemoveBus();
          break;
        case "2":
          addOrRemoveRoute();
          break;
        case "3":
          changeBusType();
          break;
        case "4":
          displayBusCount("bustype");
          break;
        case "5":
          displayBusTimingsAndRoute();
          break;
        case "6":
          exCode = true;
          break;
        default:
        System.out.println("Please Enter Valid Option\n");
      }
    }
    System.out.println("Returning to Main Menu");
    OperationFactory.getAppDriverInstance().initiate();
  }

  private void addOrRemoveRoute() {
  }

  private void addOrRemoveBus() throws SQLException, ClassNotFoundException {
    System.out.println("Please Select an Option : \n");
    System.out.println("1. Add a Bus\n2. Remove a Bus\n3. Return to Admin Menu");
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
    if(query.validateQuery(sql))
    {
      //Show busIds under the route
      System.out.println("Enter Bus Id");
      int busid = sc.nextInt();

      String sql1="SELECT * from bus where busid="+busid;
      if(query.validateQuery(sql))
      {
        System.out.println("Enter BusType [Number Of Seats]");
        int bustype = sc.nextInt();
        System.out.println("Enter Vehicle No");
        int vehicleno = sc.nextInt();

        SeatManager update= SeatManager.getInstance();
        update.updateSeatType(bustype, busid, vehicleno);
      }
      else {
        System.out.println("Bus Not Found");
      }

    }
    else {
      System.out.println("Route Not Found");
    }
  }
  private void addBus() throws SQLException, ClassNotFoundException {
    /* create a Bus object by taking details from Console
      After Creating Bus Object, Confirm if route and timing is to be added or not
       send the bus object to BusManager.create()
    */
    System.out.println("Please Enter New Bus Details Below :\n");
    System.out.println("Vehicle Number:\n");
    String vehicleNo = sc.next();
    System.out.println("Bus Type [Capacity/Number of Seats]\n");
    int busType = sc.nextInt();
    Bus newBus = AssetFactory.getBusInstance(busType, busType, vehicleNo);
    System.out.println("Would you like to add a Route and timing for this bus? " +
            "Press 'Y' to confirm\n");
    String choice = sc.next();
    if (choice.equalsIgnoreCase("y")) {
      System.out.println("Route ID:");
      int routeId = sc.nextInt();
      newBus.setRouteId(routeId);
      System.out.println("Start Timing [in 24 hour format separated by : Or / or -]:\n");
      String timingString = sc.next();
      int timingInMinutes = convertIntoMinutes(timingString);
      newBus.setBusType(busType);
      newBus.setAvailability(busType);
      BusManager.create(newBus);
      System.out.println("Your new Bus with the below details has been created:\n");
      System.out.println("Bus ID: " + newBus.getBusId() + " Bus Type: "+ newBus.getBusType()
                      + " Route Id: " + newBus.getRouteId() + " Vehicle No: " + newBus.getVehicleNo() +
                        " Start Time: " + newBus.getTiming());
  } else {
      BusManager.create(newBus);
      System.out.println("Your new Bus with the below details has been created:\n");
      System.out.println("Bus ID: " + newBus.getBusId() + " Bus Type: "+ newBus.getBusType()
              + " Vehicle No: " + newBus.getVehicleNo());
    }
  }
  // implementations of Cases

  private int convertIntoMinutes(String timingString) {
    String[] timingArray = timingString.split(("[/:-]"));
    int hourPart = Integer.parseInt(timingArray[0]);
    int minutePart = Integer.parseInt(timingArray[1]);

    return (hourPart*60) + minutePart;
  }

  private void removeBus() {
    /*  Get the busId from Console
        send the busId to BusManager.delete()
    */
  }

  private void addRoute() {
 /* create a Route object by taking details from Console
    stopName(s) taken from user is searched via StopManager.search
    to get their stopIds, if no stopId found we call create StopManager(Stop Object via factory)
    to create a Stop
    pass these to factoryFor route to get a routeObject
    Ask Admin if Route should be attached to a Bus or not
    Send the route object to RouteManager.create()
    */
  }

  private void removeRoute() {
  /*  Get the routeId from Console
      send the routeId to RouteManager.delete()
    */
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

    
  private void displayBusTimingsAndRoute() {
  }

  private void displayBusCount(String criteria) {
    /*  Fields to Display - RouteId, Stops, Time
        Get RouteIds and timings from Buses. Find Stops for each routeId
        Display all 3 attributes on Console
    */
  }
}
