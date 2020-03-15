package managers;

import assets.Route;

public class RouteManager {
  public void create(Route route) {
    // Creates sqlQuery from Route Object and calls QueryExecutor.executeQuery(sqlQuery)
  }

  public void update() {
    // No requirements found
  }

  public void read() {
    /*
     * Create SQL Query to read all info. of table
     * Create Array of all field names
     * Call QueryExecutor(sqlQuery, fields[])
     * */
  }

  public void read(String fields[], String dependentTable, String dependentFields[]) {
    /*
     * Create SQL Query to read all fields from Route and DependentTable
     * Create new Fields with all combined results fields
     * Call QueryExecutor(sqlQuery, newFields[])
     * */
  }
  public void delete() {
    // No requirements found
  }
}
