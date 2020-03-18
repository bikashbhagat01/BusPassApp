package managers;

import java.sql.SQLException;
import java.util.ArrayList;

import assets.Route;
import dbTools.QueryExecutor;
import interfaces.Creatable;

public class RouteManager implements Creatable<Route> {
  String sql;
  public void create(Route route) throws SQLException, ClassNotFoundException {

    // Creates sqlQuery from Route Object and calls QueryExecutor.executeQuery(sqlQuery)
    // Creates sqlQuery to write routeId and stopIds into route - stop table
    for(int i=0;i<route.getStops().length;i++)
    {
      sql="insert into routestoplookup routeid="+route.getRouteId()+"stopid="+route.getStops()[i];
      QueryExecutor.getInstance().executeSQL(sql);
      System.out.println("RouteID and Stops updated\n");
    }
  }

  public ArrayList<Integer> searchForRoute(int start, int end) throws Exception, SQLException{
    String startsql,endsql;
    /*
     * Find Lists of Route IDs for each start StopId and EndStopUId from
     * Route-Stop lookUp Table
     * Find Common RouteIds from the table
     * return the result list of routeIds
     * common elements btw 2 arrays
     * */
//	int[] result = {21,23};
//	return result;
    ArrayList<Integer> startResult=new ArrayList<Integer>();
    ArrayList<Integer> endResult=new ArrayList<Integer>();
    ArrayList<Integer> finalResult=new ArrayList<Integer>();
    startsql="select routeid from routestoplookup where stopid="+start;
    endsql="select routeid from routestoplookup where stopid="+end;
    startResult= (ArrayList<Integer>) QueryExecutor.getInstance().getQueryNumberList(startsql);
    endResult= (ArrayList<Integer>) QueryExecutor.getInstance().getQueryNumberList(endsql);
    for(int i=0;i<startResult.size();i++)
    {
      if(endResult.contains(startResult.get(i)))
        finalResult.add(startResult.get(i));
    }
    return finalResult;
  }

  public ArrayList<Integer> searchForRoute(int stopId) throws ClassNotFoundException, SQLException {
    ArrayList<Integer> result=new ArrayList<Integer>();
    /*
     * Returns an array of RouteIds when searched against the provided stopId
     * from Route-Stop LookUp Table
     * */
    sql="select routeid from routestoplookup where stopid="+stopId;
    result= (ArrayList<Integer>) QueryExecutor.getInstance().getQueryNumberList(sql);
    return result;
  }
}

