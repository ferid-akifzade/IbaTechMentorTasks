package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    List<T> getDatabase();
    void read() throws SQLException;
    void add(T value);
}
