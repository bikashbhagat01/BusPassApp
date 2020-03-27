package managers;

import assets.BusPass;
import customExceptions.ApplicationException;
import java.util.Scanner;
import operations.OperationFactory;
import queryHelper.QueryBuilder;

public class BusPassManager extends BaseManager {
  private static Scanner sc = OperationFactory.getScannerInstance();

  private static BusPassManager busPassManager;

  public static BusPassManager getInstance() {
    if (busPassManager == null) {
      busPassManager = new BusPassManager();
    }
    return busPassManager;
  }

  public void create(BusPass busPass) throws ApplicationException {

    QueryBuilder queryBuilder = this.getInsertInstance()
            .onTable("buspass")
            .insertValue("buspassid", busPass.getBusPassId())
            .insertValue("userid", busPass.getUserId())
            .insertValue("busid", busPass.getBusId())
            .insertValue("routeid", busPass.getRouteId())
            .insertValue("timing", busPass.getTiming());

    String sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(sqlQuery);
  }

  public boolean isValidBusPass(int userId) throws ApplicationException {
    return this.isPresent("buspass", "userid", userId);
  }
}
