package org.example;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T extends Model> {
    void create(T object) throws SQLException;
    List<T> findAll() throws SQLException;
    Integer findByName(String name) throws SQLException;
    String findById(int id) throws SQLException;
}
