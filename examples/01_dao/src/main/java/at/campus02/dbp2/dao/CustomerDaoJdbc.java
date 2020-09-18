package at.campus02.dbp2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomerDaoJdbc implements CustomerDao {

    // was brauchen wir, um eine Connection zu kriegen?
    // - Treiber (in unserem Fall für Derby Embedded)
    // - den "Pfad"/die URL zur Datenbank (jdbc URL)
    private Connection connection;

    public CustomerDaoJdbc(String jdbcUrl) {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void create(Customer customer) {

    }

    @Override
    public Customer read(String lastname) {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }
}
