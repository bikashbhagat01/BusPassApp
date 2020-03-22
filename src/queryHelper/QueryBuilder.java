package queryHelper;

import java.util.ArrayList;

public class QueryBuilder {

  private static final String INSERT = "INSERT";
  private static final String SELECT = "SELECT";
  private static final String UPDATE = "UPDATE";

  private String state;
  private String[] columns;
  private String table;

  // where name = "" and id = 45;
  private ArrayList<WhereOperation> whereOperations;
  private ArrayList<InsertOperation> insertOpertions;

  private boolean isSelectAllColumns;

  public QueryBuilder() {
    this.isSelectAllColumns = false;
    this.whereOperations = new ArrayList<WhereOperation>();
    this.insertOpertions = new ArrayList<InsertOperation>();
  }

  private void checkState() throws Exception {
    if (this.state != null) {
      throw new Exception("State already set");
    }
  }

  public QueryBuilder Select() throws Exception {
    this.checkState();
    this.state = SELECT;
    return this;
  }

  public QueryBuilder Insert() throws Exception {
    this.checkState();
    this.state = INSERT;
    return this;
  }

  public QueryBuilder Update() throws Exception {
    this.checkState();
    this.state = UPDATE;
    return this;
  }


  public QueryBuilder SelectColums(String[] columns) {
    this.columns = columns;
    return this;
  }

  public QueryBuilder SelectAllColumns() {
    this.isSelectAllColumns = true;
    return this;
  }

  public QueryBuilder WhereEq(String key, int val) {
    this.whereOperations.add(new WhereOperation("eq", key, val));
    return this;
  }

  public QueryBuilder WhereEq(String key, String val) {
    this.whereOperations.add(new WhereOperation("eq", key, val));
    return this;
  }

  public QueryBuilder WhereLte(String key, int val) {
    this.whereOperations.add(new WhereOperation("lte", key, val));
    return this;
  }

  public QueryBuilder WhereGte(String key, int val) {
    this.whereOperations.add(new WhereOperation("gte", key, val));
    return this;
  }

  public QueryBuilder WhereLt(String key, int val) {
    this.whereOperations.add(new WhereOperation("lt", key, val));
    return this;
  }

  public QueryBuilder WhereGt(String key, int val) {
    this.whereOperations.add(new WhereOperation("gt", key, val));
    return this;
  }

  public QueryBuilder WhereNeq(String key, int val) {
    this.whereOperations.add(new WhereOperation("neq", key, val));
    return this;
  }

  public QueryBuilder WhereNeq(String key, String val) {
    this.whereOperations.add(new WhereOperation("neq", key, val));
    return this;
  }

  public QueryBuilder InsertValue(String key, int val) {
    this.insertOpertions.add(new InsertOperation(key, val));
    return this;
  }

  public QueryBuilder InsertValue(String key, String val) {
    this.insertOpertions.add(new InsertOperation(key, val));
    return this;
  }

  public QueryBuilder UpdateValue(String key, String val) {
    this.InsertValue(key, val);
    return this;
  }

  public QueryBuilder UpdateValue(String key, int val) {
    this.InsertValue(key, val);
    return this;
  }

  public QueryBuilder FromTable(String table) {
    this.table = table;
    return this;
  }

  public String build() throws Exception {
    switch (this.state) {
      case QueryBuilder.SELECT:
        return this.buildSelect();
      case QueryBuilder.INSERT:
        return this.buildInsert();
      case QueryBuilder.UPDATE:
        return this.buildUpdate();
    }
    return "";
  }


  private String buildSelect() {
    String query = "SELECT ";

    if (this.isSelectAllColumns) {
      query += " * ";
    } else {
      for (int i = 0; i < this.columns.length; i++) {
        query += this.columns[i] + (i < this.columns.length - 1 ? ", " : "");
      }
    }

    query += " FROM " + this.table;

    if (this.whereOperations.size() > 0) {
      query += " WHERE ";

      if (this.whereOperations.size() == 1) {
        query += this.whereOperations.get(0).getWhereQuery() + " ";
      } else {
        for (int i = 0; i < this.whereOperations.size(); i++) {
          query += this.whereOperations.get(i).getWhereQuery() + " ";

          if (i != this.whereOperations.size() - 1) {
            query += "AND ";
          }
        }
      }
    }

    query += ";";

    return query;
  }


  private String buildInsert() throws Exception {
    String query = "INSERT INTO " + this.table + " ";

    if (this.insertOpertions.size() == 0) {
      throw new Exception("Nothing to insert");
    }

    String values = "";
    String actualVals = "";

    for (int i = 0; i < insertOpertions.size(); i++) {
      InsertOperation operation = insertOpertions.get(i);
      values += operation.key;
      actualVals += operation.type == "string" ? "\'" + operation.value + "\'" : operation.value;

      if (i != insertOpertions.size() - 1) {
        values += ",";
        actualVals += ",";
      }
    }

    query += "(" + values + ")";
    query += " VALUES (" + actualVals + " )";
    query += ";";
    return query;
  }

  private String buildUpdate() throws Exception {
    String query = "UPDATE " + this.table + " ";

    String sets = "";

    if (this.insertOpertions.size() == 0) {
      throw new Exception("No inserts");
    }

    for (int i = 0; i < this.insertOpertions.size(); i++) {
      sets += this.insertOpertions.get(i).getUpdateQuery();

      if (i != this.insertOpertions.size() - 1) {
        sets += " , ";
      }
    }

    if (this.whereOperations.size() == 0) {
      throw new Exception("No where");
    }

    String wheres = "";

    for (int i = 0; i < this.whereOperations.size(); i++) {
      wheres += this.whereOperations.get(i).getWhereQuery();

      if (i != this.whereOperations.size() - 1) {
        wheres += " AND ";
      }
    }

    query += " SET " + sets + " WHERE " + wheres;
    query += ";";

    return query;
  }
}