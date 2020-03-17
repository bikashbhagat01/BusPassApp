import java.sql.SQLException;
import operations.AppDriver;

public class Driver {
  public static void main(String[] args) throws SQLException, ClassNotFoundException {

    /*MAIN CLASS ::
    * System is Initiated by calling initiate function
    * */
    AppDriver appDriver = new AppDriver();
    appDriver.initiate();
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


