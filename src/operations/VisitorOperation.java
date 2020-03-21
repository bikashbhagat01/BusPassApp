package operations;

import java.sql.SQLException;
import java.util.Scanner;
import managers.BusManager;

public class VisitorOperation {
  private static Scanner sc = OperationFactory.getScannerInstance();
  public void showMenu() throws SQLException, ClassNotFoundException {
    // Available Routes, SeatAvailability, Exit
    boolean exitCode = false;
    String choice = "";
    while(!exitCode){
      System.out.println( "\n1. View Details for Available Routes and buses \n" +
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
    System.out.println("Returning to Main Menu");
    OperationFactory.getAppDriverInstance().initiate();
  }

  public void displayBusTimingsAndRoute() throws SQLException, ClassNotFoundException {
    // Calls the RouteManager.read()
    BusManager.displayAvailableBusTimingsAndRoutes();
  }

  public void displaySeatAvailabilityPerRoute() {

  }
}
