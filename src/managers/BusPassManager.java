package managers;

import assets.AssetFactory;
import dbTools.ConnectDatabase;
import dbTools.Validate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BusPassManager {
  public void create() throws ClassNotFoundException, SQLException {
      /*
      Create SQL String for insert
      call QueryExecutor for db insert
      * **/
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter Employee Id");
    int employeeId = myObj.nextInt();
    if (Validate.isValidBusPass(employeeId)) {
      System.out.println("BusPass for " + employeeId + " already exists\n");
      //showMenu();
    } else {
      System.out.println("Enter Route Id");
      int routeId = myObj.nextInt();
      System.out.println("Enter Timing As 1 to 24 Hours");
      int timing = myObj.nextInt();
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
