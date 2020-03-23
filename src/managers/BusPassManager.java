package managers;

import assets.AssetFactory;
import customExceptions.ApplicationException;
import dbTools.QueryExecutor;
import java.util.Scanner;
import operations.OperationFactory;
import queryHelper.QueryBuilder;

public class BusPassManager extends BaseManager {
  private static Scanner sc = OperationFactory.getScannerInstance();

  private static BusPassManager busPassManager;

  public static BusPassManager getInstance() {
    if(busPassManager == null) {
      busPassManager = new BusPassManager();
    }
    return busPassManager;
  }

  public void createBusPass(int employeeId) throws ApplicationException {
      /*
      Create SQL String for insert
      call QueryExecutor for db insert
      * **/
    if (isValidBusPass(employeeId)) {
      System.out.println("BusPass for " + employeeId + " already exists\n");
      //showMenu();
    } else {
      System.out.println("Enter Route Id");
      int routeId = sc.nextInt();
      System.out.println("Enter Timing As 1 to 24 Hours");
      int timing = sc.nextInt();
      SeatManager seat = SeatManager.getInstance();
      if (seat.updateSeatValue(routeId, timing) == 0) {
        System.out.println("No Seats Available for the same route and the preferred time");
        //showmenu();
      } else {
        int busId = seat.updateSeatValue(routeId, timing);
        int buspassid = AssetFactory.getBusPassInstance(employeeId, routeId, busId, timing)
                        .getBusPassId();

        QueryBuilder queryBuilder = this.getInsertInstance()
                                        .onTable("buspass")
                                        .insertValue("buspassid",buspassid)
                                        .insertValue("userid", employeeId)
                                        .insertValue("busid", busId)
                                        .insertValue("routeid", routeId)
                                        .insertValue("timing", timing);
        String sqlQuery = this.buildQuery(queryBuilder);

        this.executeQuery(QueryExecutor.getInstance(), sqlQuery);

        System.out.println("Bus Pass Allocated with Bus Pass ID : " + buspassid);
      }
    }
  }

  public void update(String busPassId, String field, String newValue) {
    /*
     * Create SQL Query to update field of busPassId to newValue
     * Call QueryExecutor(sqlQuery)
     * */
  }

  public boolean isValidBusPass(int userId) throws ApplicationException {
    return this.isPresent("buspass","userid", userId);
  }
}
