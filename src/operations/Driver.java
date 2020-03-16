package operations;

import java.sql.SQLException;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) throws SQLException, ClassNotFoundException {


    /*MAIN CLASS ::
    * SWitch Case Routine to as for User type
    * 1. Admin --> takes to Admin LogIn
    * 2. User --> takes to User LogIn
    * 3. Visitor --> takes to user operation
    *

    * Notes - No Admin and Visitor DB implementation decided yet
    *
    * */


    Scanner in = new Scanner(System.in);

    while (true) {
      System.out.println("1. Admin operations.Login\n2. User operations.Login\n3. Visitor\n4. Exit \n");
      String choice = in.next();
      switch (choice) {
        case "1":
          System.out.println("Enter Admin Credentials");
          AdminLogin.getInstance().setLoginDetails();
      }
    }
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


