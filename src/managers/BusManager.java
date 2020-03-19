package managers;

import assets.Bus;
import dbTools.QueryExecutor;
import java.sql.SQLException;

public class BusManager {
  private static String sqlQuery;
  public static void create(Bus bus) {

  }

  public static void update(int busId, String field, int newValue)
          throws SQLException, ClassNotFoundException {
    /* Updates field with new value */
    sqlQuery = "update bus set " + field + "= " + newValue + " where busid = " + busId;
    QueryExecutor.getInstance().executeSQL(sqlQuery);
  }

  public void read() {
    /*
    * Create SQL Query to read all info. of table
    * Create Array of all field names
    * Call QueryExecutor(sqlQuery, fields[])
    * */
  }

  public void read(String[] fields) {
    /*
     * Create SQL Query to read only mentioned fields of table
     * Call QueryExecutor(sqlQuery, fields[])
     * */
  }

  public static void delete(int busId) {
  /*create SQL Query to delete the BusId from bus table
    * */
  }
}
