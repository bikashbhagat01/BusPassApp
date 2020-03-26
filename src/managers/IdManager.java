package managers;

import customExceptions.ApplicationException;
import queryHelper.QueryBuilder;

public class IdManager extends BaseManager {

  private static IdManager idManager;

  public static IdManager getInstance() {
    if (idManager == null) {
      idManager = new IdManager();
    }
    return idManager;
  }

  public int getNewId(String objectName) throws ApplicationException {
    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns("latestid")
            .onTable("idgenerator")
            .whereEq("objectname", objectName);

    String sqlQuery = this.buildQuery(queryBuilder);

    int newId = this.getQueryNumber(sqlQuery) + 1;

    queryBuilder = this.getUpdateInstance()
            .onTable("idgenerator")
            .updateValue("latestid", newId)
            .whereEq("objectname", objectName);

    sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(sqlQuery);

    return newId;
  }
}