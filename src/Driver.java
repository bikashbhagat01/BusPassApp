import dbTools.ConnectDatabase;
import dbTools.QueryExecutor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class Driver {
  public static void main(String[] args) throws SQLException, ClassNotFoundException {


    /*MAIN CLASS ::
    * SWitch Case Routine to as for User type
    * 1. Admin --> takes to Admin LogIn
    * 2. User --> takes to User LogIn
    * 3. Visitor --> takes to user operation
    *
    *
    * Notes - No Admin and Visitor DB implementation decided yet
    *
    * */


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
  }
}
