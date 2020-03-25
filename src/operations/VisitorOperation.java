package operations;

import customExceptions.ApplicationException;
import managers.BusManager;
import managers.SeatManager;

public class VisitorOperation {

  /**
   * The class is for Visitors who do not have an existing account and want general information
   * about the bus service.
   * Visitors can View Details for Available Routes and Buses, or they can see the current seat
   * availability for all the routes.
   * **/

  public boolean showMenu() throws ApplicationException {

    boolean exitCode = false;
    String choice = "";

    while (!exitCode) {

      System.out.println("\n 1. View Details for Available Routes and buses \n" +
              "2. Display Seat Availability\n" +
              "0. Exit to Main Menu\n");

      choice = OperationFactory.getScannerInstance().next();

      switch (choice) {
        case "1":
          displayBusTimingsAndRoute();
          break;
        case "2":
          displaySeatAvailabilityPerRoute();
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

//   Calls BusManager to Display all Available Buses with Available Routes and Timings
  public void displayBusTimingsAndRoute() throws ApplicationException {
    BusManager.getInstance().displayAvailableBusTimingsAndRoutes();
  }

  public void displaySeatAvailabilityPerRoute() throws ApplicationException {

    // select timing as "Bus Timing", routeid,sum(availability), count(*)
    // as "No of buses buses runnning at same time" from bus group by routeid, timing;

    SeatManager.getInstance().displaySeatAvailabilityPerRoute();

  }
}
