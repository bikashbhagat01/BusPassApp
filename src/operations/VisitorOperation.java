package operations;

import customExceptions.ApplicationException;
import java.util.Scanner;
import managers.BusManager;

public class VisitorOperation {

  private static Scanner sc = OperationFactory.getScannerInstance();

  public boolean showMenu() throws ApplicationException {

    boolean exitCode = false;
    String choice = "";

    while (!exitCode) {

      System.out.println("1. View Details for Available Routes and buses \n" +
              "2. Display Seat Availability\n" +
              "3. Exit to Main Menu\n");

      choice = sc.nextLine();

      switch (choice) {
        case "1":
          displayBusTimingsAndRoute();
          break;
        case "2":
          displaySeatAvailabilityPerRoute();
          break;
        case "3":
          exitCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option\n");
      }
    }

    System.out.println("Returning to Previous Menu");

    return true;
  }

  // Calls BusManager to Display all Available Buses with Available Routes and Timings
  public void displayBusTimingsAndRoute() throws ApplicationException {
    BusManager.getInstance().displayAvailableBusTimingsAndRoutes();
  }

  public void displaySeatAvailabilityPerRoute() {

  }
}
