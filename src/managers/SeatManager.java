package managers;

import dbTools.ConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatManager {

  // checking and updating seat capacity for a bus in a route while generating buss pass

  private static SeatManager seatManager;

  private SeatManager() {
  }

  public static SeatManager getInstance() {
    if (seatManager == null) {
      seatManager = new SeatManager();
    }
    return seatManager;
  }

  public int updateSeatValue(int routeId, int time) throws SQLException, ClassNotFoundException{
    Connection conn = ConnectDatabase.getConnection();
    // Statement statement = conn.createStatement();
    int newAvailability,busBooked;
    // Map<Integer,Integer> object = new HashMap<>();
    //where avaibility>0
    PreparedStatement pstmt = conn.prepareStatement("SELECT busid,availability," +
            "bustype from bus WHERE routeid=? AND timing=? AND avaibility>0 " +
            "ORDER BY bustype DESC");
    //SeatManager.PreparedStatement pstmt = conn.prepareStatement("SELECT busid,availability,
    // bustype from bus WHERE routeid=? AND timing=? ORDER BY bustype DESC");
    pstmt.setInt(1, routeId);
    pstmt.setInt(2, time);
    ResultSet resultSet = pstmt.executeQuery();
    // ResultSet resultSet = statement.executeQuery("SELECT busid,availability from bus
    // WHERE routeid='\" + rid + \"' AND timing='\" + time + \"' ORDER BY bustype");

    if(resultSet.next()) {
      busBooked= resultSet.getInt(1);
      newAvailability=resultSet.getInt(2)-1;
      PreparedStatement pstmt1 = conn.prepareStatement("UPDATE bus SET avaibility =?," +
              "vehicleno=? where busid=?");
      pstmt1.setInt(1, newAvailability);
      pstmt1.setInt(2, busBooked);
      pstmt1.executeUpdate();
      return busBooked;
    }
    return 0;
  }

  public boolean updateSeatType(int type,int busId,int vehicleNo) throws SQLException, ClassNotFoundException{
    //this is to update seat type in a bus based on a route
    int dbSeatCapacity,dbSeatAvailability,reducedSeatAvailability,increasedSeatAvailability;
    int newVehicleType=type;
    Connection conn = ConnectDatabase.getConnection();
    PreparedStatement pstmt = conn.prepareStatement("SELECT availability,bustype from bus WHERE busid=?");
    pstmt.setInt(1, busId);
    ResultSet resultSet = pstmt.executeQuery();
    dbSeatAvailability=resultSet.getInt(1);
    dbSeatCapacity=resultSet.getInt(2);
    reducedSeatAvailability=dbSeatAvailability-(dbSeatCapacity-newVehicleType);
    increasedSeatAvailability=dbSeatAvailability+newVehicleType-dbSeatCapacity;
    if(newVehicleType>dbSeatCapacity) {
      PreparedStatement pstmt1 = conn.prepareStatement("UPDATE bus SET availability=?,bustype=?  WHERE busid=?");
      pstmt1.setInt(1,increasedSeatAvailability);
      pstmt1.setInt(2,newVehicleType);
      pstmt1.setInt(3,busId);
      pstmt1.executeUpdate();
      return true;
    }

    if(reducedSeatAvailability>=dbSeatAvailability) {
      PreparedStatement pstmt1 = conn.prepareStatement("UPDATE bus SET availability=?,bustype=?,vehicleno=?  WHERE busid=?");
      pstmt1.setInt(1,reducedSeatAvailability);
      pstmt1.setInt(2,newVehicleType);
      pstmt1.setInt(3,vehicleNo);
      pstmt1.setInt(4,busId);
      pstmt1.executeUpdate();
      return true;
    }
    return false;
  }

		 /*  while(resultSet.next()) {

		    	object.put(resultSet.getInt(1), resultSet.getInt(2));
		    }
		    for(Map.Entry index:object.entrySet()){
		    	int check=(int) index.getValue();
		    	if(check>0)
		    	{
		    		newavail=(int) index.getValue();
		    		newavail=newavail-1;
		    		busbooked=(int) index.getKey();
		    		 PreparedStatement pstmt1 = conn.prepareStatement("UPDATE bus SET avaibility =? where busid=?");
		 		    pstmt1.setInt(1, newavail);
		 			 pstmt1.setInt(2, busbooked);
		 			pstmt1.executeUpdate();
		    		return busbooked;
		    		}
		    }
		    */
  //statement.executeUpdate("UPDATE bus SET avaibility =newavail where busid=busbooked");

  public void displaySeatAvailabilityPerRoute() {
    /*Create sqlQuery to find routeId, timing and availability per route
     * create Array for the filed names
     * send to QueryExecutor.executeQuery(sqlQuery,fields[])
     * */

    /*
     * SELECT routeid,timing, SUM(avaibility) FROM busid GROUP BY route id,timing
     *
     */
  }
}
