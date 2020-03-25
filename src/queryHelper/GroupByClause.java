package queryHelper;

public class GroupByClause {

  public String columnName;

  GroupByClause(String columnName) {
    this.columnName = columnName;

  }
  public String getGroupByQuery() {
    return this.columnName;
  }
//	public String getGroupByQuery() {
//
//		return null;
//	}


}