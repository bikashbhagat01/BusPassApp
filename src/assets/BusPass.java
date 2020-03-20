package assets;

// POJO, Only holds data

public class BusPass {
  private int busPassId;
  private int userId;
  private int busId;
  private int routeId;
  private int timing;

  public BusPass(int busPassId, int userId, int routeId, int busId, int timing) {
    this.busPassId = busPassId;
    this.userId = userId;
    this.busId = busId;
    this.routeId = routeId;
    this.timing = timing;
  }

  public int getBusPassId() {
    return busPassId;
  }

  public String getTableName() {
    // For a Given Attribute Name, Returns the Column name from Table
    return "buspass";
  }

  public String getColumnName(String attribute) {
    if(attribute.trim().equalsIgnoreCase("userid")){
      return "userid";
    }
    return "";
  }
//  Setters and Getters to be added
  //  BusPass can only be created with a valid key, user and busId
}
