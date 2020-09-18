package at.campus02.dbp2.dao;

import java.sql.SQLException;

// CRUD operationen für die Customer-Tabelle
public interface CustomerDao {

    void create(Customer customer);
    Customer read(String lastname);
    void update(Customer customer);
    void delete(Customer customer);

}
