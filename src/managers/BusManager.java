package managers;

import assets.Bus;

public class BusManager {
  public static void create(Bus bus) {


  }

  public static void update(int busId, String field, int newValue) {
    /*
    * Create SQL Query to update field of busId to newValue
    * Call QueryExecutor(sqlQuery)
    * */
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

  public void read(String[] fields, String dependentTable, String[] dependentFieldNames) {
    /*
     * Create SQL Query to read all fields from Bus and DependentTable
     * Create new Fields with all combined results fields
     * Call QueryExecutor(sqlQuery, newFields[])
     * */
  }

  public void delete(String busId) {
  /*create SQL Query to delete the BusId from bus table
    * */
  }
}
