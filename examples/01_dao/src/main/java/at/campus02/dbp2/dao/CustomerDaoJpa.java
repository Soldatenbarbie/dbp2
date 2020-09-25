package at.campus02.dbp2.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerDaoJpa implements CustomerDao {

    private EntityManagerFactory factory;

    public CustomerDaoJpa() {
        factory = Persistence.createEntityManagerFactory("nameOfJpaPersistenceUnit");
    }

    @Override
    public void create(Customer customer) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(customer);
        manager.getTransaction().commit();
    }

    @Override
    public Customer read(String lastname) {
        EntityManager manager = factory.createEntityManager();
        return manager.find(Customer.class, lastname);
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }
}
