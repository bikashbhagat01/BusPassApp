package managers;

import assets.AssetFactory;
import dbTools.ConnectDatabase;
import dbTools.Validator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import operations.OperationFactory;

public class BusPassManager {
  private static Scanner sc = OperationFactory.getScannerInstance();
  public static void createBusPass(int employeeId) throws ClassNotFoundException, SQLException {
      /*
      Create SQL String for insert
      call QueryExecutor for db insert
      * **/
    if (Validator.isValidBusPass(employeeId)) {
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
        Connection conn = ConnectDatabase.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into buspass values(?,?,?,?,?)");
        pstmt.setInt(1, buspassid);
        pstmt.setInt(2, employeeId);
        pstmt.setInt(3, busId);
        pstmt.setInt(4, routeId);
        pstmt.setInt(4, timing);
        int result = pstmt.executeUpdate();
      }
    }
  }

  public void update(String busPassId, String field, String newValue) {
    /*
     * Create SQL Query to update field of busPassId to newValue
     * Call QueryExecutor(sqlQuery)
     * */
  }
}
