package operations;

import customExceptions.ApplicationException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AppDriver {
  public void initiate()  {

    /* System Initiate class ::
    * SWitch Case Routine to as for User type
    * 1. Admin --> takes to Admin LogIn
    * 2. User --> takes to User LogIn
    * 3. Visitor --> takes to user operation
    *

    * Notes - No Admin and Visitor DB implementation decided yet
    *
    * */

    boolean exitCode = false;

    while (!exitCode) {
      System.out.println("_______________________________________________________________");
//      System.out.println("---------------------------------------------------------------");
      System.out.println("|`Welcome to Amazon's Employee BusPass Management Application`|");
      loadScreen();
      System.out.println("\nEnter your User Type :");
      System.out.println("\n1. Admin \n2. User\n3. Visitor \n0. Exit \n");

      String choice = OperationFactory.getScannerInstance().next();

      switch (choice) {
        case "1":
          System.out.println("Welcome Administrator!\n");

          try {
            OperationFactory.getAdminLoginInstance().showMenu();
          } catch (ApplicationException e) {
            e.printStackTrace();
          }
          break;

        case "2":
          System.out.println("Welcome User!\n");

          try {
            OperationFactory.getUserLoginInstance().showMenu();
          } catch (ApplicationException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
          }
          break;

        case "3":
          System.out.println("Welcome Visitor!\n");

          try {
            OperationFactory.getVisitorOperationInstance().showMenu();
          } catch (ApplicationException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
          }
          break;

        case "0":
          exitCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option");
      }
    }

    System.out.println("Thank You For Using Amazon's Employee BusPass Management Application\n");
    loadScreen();
  }

  private void loadScreen(){
    System.out.println("_______________________________________________________________");
    System.out.println("---------------------------------------------------------------");

    int del = 0;
    while(del !=6){
      try {
        TimeUnit.MILLISECONDS.sleep(1000/(del+1*2));
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      int k = del;
      while(k!=0){
        System.out.print("_~`~");
        k--;
      }
      del++;
    }
    System.out.print("_~`~");
    System.out.println("\n---------------------------------------------------------------");
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


