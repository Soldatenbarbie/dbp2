package at.campus02.dbp2.dao;

import java.sql.*;

public class CustomerDaoJdbc implements CustomerDao {

    // was brauchen wir, um eine Connection zu kriegen?
    // - Treiber (in unserem Fall für Derby Embedded)
    // - den "Pfad"/die URL zur Datenbank (jdbc URL)
    private Connection connection;

    public CustomerDaoJdbc(String jdbcUrl) {

        try {
            // Treiberklasse laden
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            // Connection erzeugen lassen
            connection = DriverManager.getConnection(jdbcUrl);
            // Sicherstellen, dass die Tabelle existiert
            ensureTable();
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

    }

    private void ensureTable() throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "create table CUSTOMER (" +
                            "lastname varchar(50) primary key, " +
                            "firstname varchar(50), " +
                            "age int)"
            );
            statement.execute();
        } catch (SQLException e) {
            // Falls die Tabelle schon existiert, kommt ein spezieller
            // SQLState mit der Exception mit -> diesen können wir ignorieren,
            // andere Exceptions schmeißen wir einfach weiter
            if ("X0Y32".equalsIgnoreCase(e.getSQLState())) {
                return;
            }
            throw e;
        }
    }

    @Override
    public void create(Customer customer) {
        try {
            PreparedStatement insert = connection.prepareStatement(
                    "insert into CUSTOMER values(?,?,?)"
            );
            insert.setString(1, customer.getLastname());
            insert.setString(2, customer.getFirstname());
            insert.setInt(3, customer.getAge());
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Could not insert customer.", e);
        }
    }

    @Override
    public Customer read(String lastname) {
        try {
            PreparedStatement query = connection.prepareStatement(
                    "select * from CUSTOMER where lastname = ?"
            );
            query.setString(1, lastname);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Customer fromDb = new Customer();
                fromDb.setLastname(rs.getString(1));
                fromDb.setFirstname(rs.getString(2));
                fromDb.setAge(rs.getInt(3));
                return fromDb;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            PreparedStatement update = connection.prepareStatement(
                    "update CUSTOMER set age = ?, firstname = ?" +
                            " where lastname = ?"
            );
            update.setInt(1, customer.getAge());
            update.setString(2, customer.getFirstname());
            update.setString(3, customer.getLastname());
            update.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            PreparedStatement delete = connection.prepareStatement(
                    "delete from CUSTOMER where lastname = ?"
            );
            delete.setString(1, customer.getLastname());
            delete.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
