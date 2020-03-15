package operations;

// All operations that can be used by the Admin

public class AdminOperation {

  public void showMenu() {
    // Switch Case menu which calls other small functions in this class
    /* 1. Add Or remove Bus
       2. Add Or remove Route
       3. Change type of a Bus
       4. Display Number of Buses of each Type
       5. Display timings and route for each bus
    */
  }

    // implementations of Cases
  private void addBus() {
    /* create a Bus object by taking details from Console
      How will the admin enter the Primary ID
      Bus busObj = getBusObject(busType,....)

       send the bus object to BusManager.create()
    */
  }

  private void removeBus() {
    /*  Get the busId from Console
        send the busId to BusManager.delete()
    */
  }

  private void addRoute() {
 /* create a Route object by taking details from Console
       send the route object to RouteManager.create()
    */
  }

  private void removeRoute() {
  /*  Get the routeId from Console
      send the routeId to RouteManager.delete()
    */
  }

  private void changeBusType() {
  /* Get the busId and new Type from Console
  * send the busId, field as [Type] and newValue as String to BusManager.update(busId, field, newValue)
  * */
  }

  private void vechicleCount() {
    /* Create String array of fields required  - busType, busCountForEachType
    * Call the BusManager.read(fields[])
    * */
  }

  private void timingInfo() {
    /* Create String Array of all fields required from bus- busId, timing, routeId
        Stops is present in Route Table, route must be included as Dependent Table of the Query
        Create String Array of fields required from DependentTable - [stops]
    * Call the BusManager.read(fields[], dependentTable, dependentTableFields[])
    */
  }
}
