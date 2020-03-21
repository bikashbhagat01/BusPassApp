package interfaces;

// Demo template, Please AVOID

import java.sql.SQLException;

public interface Creatable<T> {
  public void create(T t) throws SQLException, ClassNotFoundException;
}
