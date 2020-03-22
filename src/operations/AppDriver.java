package operations;

import java.sql.SQLException;
import java.util.Scanner;

public class AppDriver {
  public void initiate() throws SQLException, ClassNotFoundException {

    /* System Initiate class ::
    * SWitch Case Routine to as for User type
    * 1. Admin --> takes to Admin LogIn
    * 2. User --> takes to User LogIn
    * 3. Visitor --> takes to user operation
    *

    * Notes - No Admin and Visitor DB implementation decided yet
    *
    * */

    Scanner sc = OperationFactory.getScannerInstance();
    boolean exitCode = false;

    while (!exitCode) {
      System.out.println("Welcome to Amazon's Employee BusPass Management Application");
      System.out.println("\nSelect User Type Option :");
      System.out.println("\n1. Admin \n2. User\n3. Visitor \n4. Exit \n");

      String choice = sc.next();

      switch (choice) {
        case "1":
          System.out.println("Welcome Administrator!\n");
          try {
            OperationFactory.getAdminLoginInstance().showMenu();
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "2":
          System.out.println("Welcome User!\n");
          OperationFactory.getUserLoginInstance().showMenu();
          break;
        case "3":
          System.out.println("\nWelcome Visitor!\n");
          OperationFactory.getVisitorOperationInstance().showMenu();
          break;
        case "4":
          exitCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option");
      }
    }

    System.out.println("Thank You For Using Amazon's Employee BusPass Management Application\n");
  }
}




















    /*
    String[] fields = {"Id", "Name", "Age"};
    QueryExecutor.getInstance().executeSQL("select * from emp; ", fields);

    System.out.println("End Of Query \n");

    Connection conn = ConnectDatabase.getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("show tables;");
    System.out.println(rs.getRow() + " what");
    // Using QueryExecutor
    String[] str = {"just Something"};
    QueryExecutor.getInstance().executeSQL("show tables",str);

    // Creating Unsigned ID in long using UUID
    long uniqueID = UUID.randomUUID().getLeastSignificantBits()& 0x00000000ffffffffL;
    System.out.println(uniqueID);
    // Creating String ID  using UUID
    UUID uniqueID2 = UUID.randomUUID();
    System.out.println(uniqueID2);
    */


