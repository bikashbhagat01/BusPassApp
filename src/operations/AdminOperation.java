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
      After Creating Bus Object, Confirm if route and timing is to be added or not
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
    stopName(s) taken from user is searched via StopManager.search
    to get their stopIds, if no stopId found we call create StopManager(Stop Object via factory)
    to create a Stop
    pass these to factoryFor route to get a routeObject
    Ask Admin if Route should be attached to a Bus or not
    Send the route object to RouteManager.create()
    */
  }

  private void removeRoute() {
  /*  Get the routeId from Console
      send the routeId to RouteManager.delete()
    */
  }

  public void approveRoute() {

    /*if route requests table empty, display no request found
    * if route requests exist, show details to Admin
    * if routeExists flag is true, assign routeId and timing to a bus
    * Search for Stops using StopManager.seacrh() - if found assign
    * Start and end as relevant StopIds
    * If stops not found,  ask Admin if they want to create a Stop using StopManager and get a stopId
    * or they want to deny
    * If they deny, delete the routeRequest
    * RouteManager.searchRoute(start,end), which searches for the set of Stops and returns a
    * set of routeIds where the Stops fall in
    * if route exists for stops mentioned but time doesn't, then admin assigns a Bus to the route
    * if route doesn't exist then admin creates route and creates/updates bus for the timing
    * */
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
    /*
        Fields to Display - RouteId, Stops, Time
        Get RouteIds and timings from Buses. Find Stops for each routeId
        Display all 3 attributes on Console
    */
  }
}
