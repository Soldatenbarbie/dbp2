package at.campus02.dbp2.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerDaoJpa implements CustomerDao {

    private EntityManagerFactory factory;
    private EntityManager manager;

    public CustomerDaoJpa() {
        factory = Persistence.createEntityManagerFactory("nameOfJpaPersistenceUnit");
        manager = factory.createEntityManager();
    }

    @Override
    public void create(Customer customer) {
        manager.getTransaction().begin();
        manager.persist(customer);
        manager.getTransaction().commit();
    }

    @Override
    public Customer read(String lastname) {
        return manager.find(Customer.class, lastname);
    }

    @Override
    public void update(Customer customer) {
        manager.getTransaction().begin();
        manager.merge(customer);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(Customer customer) {
        manager.getTransaction().begin();
        Customer managed = manager.merge(customer);
        manager.remove(managed);
        manager.getTransaction().commit();
    }
}
