package managers;

import customExceptions.ApplicationException;
import dbTools.QueryExecutor;
import java.sql.ResultSet;
import queryHelper.QueryBuilder;

import java.sql.SQLException;

// Catches System Exceptions from child classes, and throws custom ApplicationException

public class BaseManager {

  protected String tableName;

  // Catch Exceptions for QueryBuilder Update, and throw ApplicationException
  protected QueryBuilder getUpdateInstance() throws ApplicationException {
    try {
      return  QueryBuilder.getInstance().update();
    } catch (Exception e) {
      throw new ApplicationException("Cannot create Update instance");
    }
  }

  // Catch Exceptions for QueryBuilder Select, and throw ApplicationException
  protected QueryBuilder getSelectInstance() throws ApplicationException {
    try {
      return QueryBuilder.getInstance().select();
    } catch (Exception e) {
      throw new ApplicationException("Cannot create Select instance");
    }
  }

  // Catch Exceptions for QueryBuilder Insert, and throw ApplicationException
  protected QueryBuilder getInsertInstance() throws ApplicationException {
    try {
      return QueryBuilder.getInstance().insert();
    } catch (Exception e) {
      throw new ApplicationException("Cannot create update instance");
    }
  }

  // Catch Exceptions for QueryBuilder Delete, and throw ApplicationException
  protected QueryBuilder getDeleteInstance() throws ApplicationException {
    try {
      return QueryBuilder.getInstance().delete();
    } catch (Exception e) {
      throw new ApplicationException("Cannot create update instance");
    }
  }

  // Catch Exceptions for QueryBuilder Build, and throw ApplicationException
  protected String buildQuery(QueryBuilder queryBuilder) throws ApplicationException {
    try {
      return queryBuilder.build();
    } catch (Exception e) {
      throw new ApplicationException("Could not build query");
    }

  }
  // Catch Exceptions for QueryExecutor executeSql, and throw ApplicationException
  protected void executeQuery(QueryExecutor queryExecutor, String query) throws ApplicationException {
    try {
      System.out.println("Executing query: " + query);
      queryExecutor.executeSQL(query);
    } catch (SQLException e) {
      throw new ApplicationException("SQL exception");
    } catch (ClassNotFoundException e) {
      throw new ApplicationException("Class not found");
    }
  }

  protected boolean hasResult(QueryExecutor queryExecutor, String query) throws ApplicationException {
    try {
      System.out.println("Checking query for valid results: " + query);
      return queryExecutor.isValidQuery(query);
    } catch (SQLException e) {
      throw new ApplicationException("SQL exception");
    } catch (ClassNotFoundException e) {
      throw new ApplicationException("Class not found");
    }
  }

  // Catch Exceptions for QueryExecutor executeSql, and throw ApplicationException
  protected void executeQuery(QueryExecutor queryExecutor, String query, String[] fields) throws ApplicationException {
    try {
      System.out.println("Executing query: " + query);
      queryExecutor.executeSQL(query,fields);
    } catch (SQLException e) {
      throw new ApplicationException("SQL exception");
    } catch (ClassNotFoundException e) {
      throw new ApplicationException("Class not found");
    }
  }

  protected ResultSet getResultSet(QueryExecutor queryExecutor, String query) throws ApplicationException {
    try {
      System.out.println("Getting ResultSet from query: " + query);
      return queryExecutor.getResultSet(query);
    } catch (SQLException e) {
      throw new ApplicationException("SQL exception");
    } catch (ClassNotFoundException e) {
      throw new ApplicationException("Class not found");
    }
  }

  protected int getQueryNumber(QueryExecutor queryExecutor, String query) throws ApplicationException {
    try {
      System.out.println("Getting Integer field value from query: " + query);
      return queryExecutor.getQueryNumber(query);
    } catch (SQLException e) {
      throw new ApplicationException("SQL exception");
    } catch (ClassNotFoundException e) {
      throw new ApplicationException("Class not found");
    }
  }

  protected int getInt(ResultSet resultSet, int columnIndex) throws ApplicationException {
    try {
      System.out.println("Getting next int in ResultSet for columnIndex : " + columnIndex);
      return resultSet.getInt(columnIndex);
    } catch (SQLException e) {
      throw new ApplicationException("SQL exception");
    }
  }

  protected String getString(ResultSet resultSet, int columnIndex) throws ApplicationException {
    try {
      System.out.println("Getting next String in ResultSet for columnIndex : " + columnIndex);
      return resultSet.getString(columnIndex);
    } catch (SQLException e) {
      throw new ApplicationException("SQL exception");
    }
  }
  // Catch Exception for incorrect Manager passed, throws ApplicationException
  public static BaseManager getInstance() throws ApplicationException {
    throw new ApplicationException("Class not implemented");
  }

  public boolean isPresent(String field, String value) throws ApplicationException{
    QueryBuilder queryBuilder = this.getSelectInstance();

    queryBuilder
            .selectAllColumns()
            .whereEq(field, value)
            .onTable(this.tableName); // Ensure Child class has tableName

    String query = this.buildQuery(queryBuilder);

    try {
      return QueryExecutor.getInstance().isValidQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new ApplicationException("Invalid SQL");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new ApplicationException("SQL Class not found");
    }
  }

  public boolean isPresent(String tableName, String field, String value) throws ApplicationException{
    QueryBuilder queryBuilder = this.getSelectInstance();

    queryBuilder
            .selectAllColumns()
            .whereEq(field, value)
            .onTable(tableName); // Ensure Child class has tableName

    String query = this.buildQuery(queryBuilder);

    try {
      return QueryExecutor.getInstance().isValidQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new ApplicationException("Invalid SQL");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new ApplicationException("SQL Class not found");
    }
  }

  public boolean isPresent(String tableName, String field, int value) throws ApplicationException{
    QueryBuilder queryBuilder = this.getSelectInstance();

    queryBuilder
            .selectAllColumns()
            .whereEq(field, value)
            .onTable(tableName); // Ensure Child class has tableName

    String query = this.buildQuery(queryBuilder);

    try {
      return QueryExecutor.getInstance().isValidQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new ApplicationException("Invalid SQL");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new ApplicationException("SQL Class not found");
    }
  }

  protected boolean isNextPresent(ResultSet resultSet) throws ApplicationException {
    try {
      System.out.println("Checking for next row in ResultSet");
      return resultSet.next();
    } catch (SQLException e) {
      throw new ApplicationException("SQL exception");
    }
  }

}
