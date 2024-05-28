package database;

import java.sql.Connection;
import java.sql.ResultSet;

public interface InterfaceForDatabase {
    public ResultSet getAll(String nameTable);
    public Connection getDbConnection();
}
