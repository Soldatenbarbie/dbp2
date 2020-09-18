package at.campus02.dbp2.dao;

import java.util.HashMap;
import java.util.Map;

public class CustomerDaoInMemory implements CustomerDao {

    private final Map<String, Customer> cache = new HashMap<>();

    @Override
    public void create(Customer customer) {
        cache.put(customer.getLastname(), new Customer(customer));
    }

    @Override
    public Customer read(String lastname) {
        Customer fromCache = cache.get(lastname);
        // längere Schreibweise, gleiche Funktionalität
//        if (fromCache == null) {
//            return  null;
//        } else {
//            return new Customer(fromCache);
//        }

//         kurze Schreibweise
        return fromCache == null ? null : new Customer(fromCache);
    }

    @Override
    public void update(Customer customer) {
        Customer fromCache = cache.get(customer.getLastname());
        if (fromCache == null) {
            throw new IllegalStateException("Customer not in database!");
        }
        fromCache.setFirstname(customer.getFirstname());
        fromCache.setAge(customer.getAge());
    }

    @Override
    public void delete(Customer customer) {
        if (!cache.containsKey(customer.getLastname())) {
            throw new IllegalStateException("Customer not in database");
        }
        cache.remove(customer.getLastname());
    }
}
