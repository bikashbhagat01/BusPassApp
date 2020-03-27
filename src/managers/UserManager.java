package managers;
import assets.User;
import customExceptions.ApplicationException;
import queryHelper.QueryBuilder;

public class UserManager extends BaseManager {

  private static UserManager userManager;

  public static UserManager getInstance() {
    if(userManager == null) {
      userManager = new UserManager();
    }
    return userManager;
  }

  public void create(User user) throws ApplicationException {

    QueryBuilder queryBuilder = this.getInsertInstance()
                                    .onTable("user")
                                    .insertValue("userid", user.getEmployeeId())
                                    .insertValue("fname", user.getFirstName())
                                    .insertValue("lname", user.getLastName())
                                    .insertValue("email", user.getEmail())
                                    .insertValue("contactno", user.getContactNo())
                                    .insertValue("emergencycontactno", user.getEmergencyContactNo())
                                    .insertValue("emergencycontactname", user.getEmergencyContactName())
                                    .insertValue("bloodgroup", user.getBloodGroup())
                                    .insertValue("password", user.getPassword());

    String sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(sqlQuery);

  }

  public void update(int employeeId, String field, String newValue) throws ApplicationException {

    QueryBuilder queryBuilder = this.getUpdateInstance()
                                    .updateValue(field,newValue)
                                    .whereEq("userid",employeeId)
                                    .onTable("user");
    String sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(sqlQuery);
  }

  public boolean isValidUserPassword(int userId, String password) throws ApplicationException {

    QueryBuilder queryBuilder = this.getSelectInstance()
                                    .selectColumns("userid")
                                    .onTable("user")
                                    .whereEq("userid",userId)
                                    .whereEq("password", password);

    String sqlQuery = this.buildQuery(queryBuilder);


    return this.hasResult(sqlQuery);
  }

  public boolean isValidUser(int userId) throws ApplicationException {

    QueryBuilder queryBuilder = this.getSelectInstance()
            .selectColumns("userid")
            .onTable("user")
            .whereEq("userid",userId);

    String sqlQuery = this.buildQuery(queryBuilder);

    return this.hasResult(sqlQuery);
  }
}
