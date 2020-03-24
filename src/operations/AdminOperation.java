package operations;

// All operations that can be used by the Admin

import assets.AssetFactory;
import assets.Bus;
import assets.Route;
import customExceptions.ApplicationException;
import customExceptions.UserException;
import dbTools.TimeConverter;
import managers.BusManager;
import managers.RouteManager;
import managers.SeatManager;
import managers.StopManager;

public class AdminOperation extends BaseOperation {

  // Switch Case menu which calls other small functions in this class

  public boolean showMenu() throws ApplicationException {
    boolean exitCode = false;
    String choice = "";

    while (!exitCode) {
      System.out.println("\n1. Add Or remove Bus\n" +
              "2. Add Or remove Route\n" +
              "3. Assign a Bus to Route\n" +
              "4. Change type of a Bus\n" +
              "5. Display Number of Buses of each Type\n" +
              "6. Display timings and route for each bus\n" +
              "0. Exit to Main Menu");

      choice = OperationFactory.getScannerInstance().next();

      switch (choice) {
        case "1":
          addOrRemoveBus();
          break;

        case "2":
          addOrRemoveRoute();
          break;

        case "3":
          try {
            addBusToRoute();
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;

        case "4":
          try {
            changeBusType();
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;

        case "5":
          displayBusCountOfEachType();
          break;

        case "6":
          displayBusTimingsAndRoutes();
          break;

        case "0":
          exitCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option\n");
      }
    }

    System.out.println("Returning to previous Menu");

    return true;
  }

  private boolean addOrRemoveRoute() throws ApplicationException {


    boolean exitCode = false;
    String choice = "";

    while (!exitCode) {
      System.out.println("Please Select an Option : \n");
      System.out.println("1. Add a New Route" +
              "\n2. Remove an Existing Route\n" +
              "\n0. Return to Admin Menu");

      choice = OperationFactory.getScannerInstance().next();

      switch (choice) {

        case "1":
          try {
            addRoute();
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;

        case "2":
          try {
            removeRoute();
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;

        case "0":
          exitCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option\n");
      }
    }
    System.out.println("Returning to Previous Menu");

    return true;
  }

  private boolean addOrRemoveBus() throws ApplicationException {

    boolean exitCode = false;
    String choice = "";

    while (!exitCode) {
      System.out.println("Please Select an Option : \n");
      System.out.println("1. Add a New Bus\n" +
              "2. Remove an Existing Bus\n" +
              "0. Return to Admin Menu");

      choice = OperationFactory.getScannerInstance().next();

      switch (choice) {
        case "1":
          try {
            addBus();
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;
        case "2":
          try {
            removeBus();
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;
        case "0":
          exitCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option\n");
      }
    }

    System.out.println("Returning to Previous Menu");

    return true;
  }

  private void changeBusType() throws ApplicationException, UserException {
    /* Get the busId and new Type from Console
     * send the busId, field as [Type] and newValue as String to BusManager.update(busId, field, newValue)
     * */
    System.out.println("Enter Route Id");
    int routeId = this.getRouteId();

    if (BusManager.getInstance().isPresent("bus", "routeid", routeId)) {
      //Show busIds under the route
      System.out.println("Enter Bus Id");
      int busId = this.getBusId();

      if (BusManager.getInstance().isPresent("bus", "busid", busId)) {
        System.out.println("Enter BusType [Number Of Seats]");
        int busType = this.getBusType();

        System.out.println("Enter Vehicle No");
        String vehicleNo = this.getVehicleNo();

        SeatManager
                .getInstance()
                .updateSeatType(busType, busId, vehicleNo);
      } else {
        System.out.println("Bus Not Found");
      }
    } else {
      System.out.println("Route Not Found");
    }
    System.out.println("Bus Type and Vehicle Number Updated! ");
  }

  // create a Bus object by taking details from Console
  private boolean addBus() throws ApplicationException, UserException {
    System.out.println("Please Enter New Bus Details Below :\n");

    System.out.println("Vehicle Number:\n");
    String vehicleNo = this.getVehicleNo();

    if (BusManager.getInstance().isPresent("bus", "vehicleno", vehicleNo)) {
      System.out.println(" Vehicle already Assigned to different BusId ");
      return false;
    }

    System.out.println("Bus Type [Capacity/Number of Seats]\n");
    int busType = this.getBusType();

    Bus newBus = AssetFactory.getInstance().getBusInstance(busType, busType, vehicleNo);

    BusManager.getInstance().create(newBus);

    System.out.println("Your new Bus with the below details has been created:\n");
    System.out.println("Bus ID: " + newBus.getBusId() +
            " Bus Type: " + newBus.getBusType()
            + " Vehicle No: " + newBus.getVehicleNo());

    return true;
  }

  // Checks if Bus Id exists or not. If yes removed it, else returns to main menu
  private boolean removeBus() throws ApplicationException, UserException {
    System.out.println("Please enter Bus Id to remove : \n");
    int removedBus = this.getBusId();

    if (!BusManager.getInstance().isPresent("bus", "busid", removedBus)) {
      System.out.println("Invalid BusId entered \nReturning to previous Menu");
      return false;
    }

    BusManager.getInstance().delete(removedBus);
    System.out.println("Bus : " + removedBus + " has been removed!");

    return true;
  }

  // Takes StopNames, validates them and creates Route record.
  private void addRoute() throws ApplicationException, UserException {
    System.out.println("Pleas Enter New Route Details below :");

    System.out.println("Number of Stops:\n");
    int stopCount = this.getStopCount();

    int[] stops = new int[stopCount];
    int count = 0;

    String stopName = "";

    System.out.println("Stops [From StartStop to EndStop]\n");

    while (count < stopCount) {
      System.out.println("Enter Stop Name : " + (count + 1));
      stopName = this.getStopName();

      if (StopManager.getInstance().isPresent("stop", "stopname", stopName)) {
        stops[count] = StopManager.getInstance().getStopIdForName(stopName);
        ++count;
      } else {
        System.out.println("No such stop name found\n Please enter Valid Stop Name\n");
      }
    }

    Route newRoute = AssetFactory.getInstance().getRouteInstance(stops);
    RouteManager.getInstance().create(newRoute);

    System.out.println("New Route with Route ID : " + newRoute.getRouteId() + "has been created");
    System.out.println("Returning to Bus menu.");
  }

  // Checks if Route Id exists or not. If yes, remove route from route table
  private boolean removeRoute() throws ApplicationException, UserException {
    System.out.println("Please enter Route Id to remove : \n");
    int removedRouteId = this.getRouteId();

    if (!RouteManager.getInstance().isPresent("route", "routeid", removedRouteId)) {
      System.out.println("Invalid Route ID entered \nReturning to previous Menu");
      return false;
    }

    BusManager.getInstance().clearRouteAssignments(removedRouteId);
    RouteManager.getInstance().delete(removedRouteId);

    System.out.println("Route : " + removedRouteId + " has been removed from Route List!\n" +
            "All assignments of this Route to Buses have been cleared.");

    return true;
  }

  // Takes a RouteId, Start Timing  and adds it to the Bus table
  private boolean addBusToRoute() throws ApplicationException, UserException {
    System.out.println("Enter details :");

    System.out.println("Route ID:");
    int routeId = this.getRouteId();

    if (!RouteManager.getInstance().isPresent("route", "routeid", routeId)) {
      System.out.println("Route ID not found. Please add a Route from Route Menu");
      return false;
    }

    System.out.println("Bus Id : \n");
    int busId = this.getBusId();

    if (!RouteManager.getInstance().isPresent("bus", "busid", busId)) {
      System.out.println("Bus ID not found. Please add a Bus from Route Menu");
      return false;
    }

    System.out.println("Start Timing [in 24 hour format separated by : Or / or -]:\n");
    String timeString = this.getTimeString();

    int timeInMinutes = TimeConverter.getTimeAsMinutes(timeString);

    BusManager.getInstance().update(busId, "routeid", routeId);
    BusManager.getInstance().update(busId, "timing", timeInMinutes);

    System.out.println("Route : " + routeId + " & timing " + timeString +
            " has Been added for bus : " + busId);

    return true;
  }

  private void displayBusTimingsAndRoutes() throws ApplicationException {
    System.out.println("Below are Available Bus Timings and Routes");

    BusManager.getInstance().displayAvailableBusTimingsAndRoutes();
  }

  private void displayBusCountOfEachType() throws ApplicationException {
    // Executes Query to find Bus Type and their count
    BusManager.getInstance().displayBusCount("bustype");
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

}
