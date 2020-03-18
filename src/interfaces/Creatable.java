package interfaces;

// Demo template

import java.sql.SQLException;

public interface Creatable<T> {
  public void create(T t) throws SQLException, ClassNotFoundException;
}
