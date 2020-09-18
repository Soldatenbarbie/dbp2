package at.campus02.dbp2.dao;

public class Application {

    public static void log(String msg) {
        System.out.println("Application:  --> " + msg);
    }

    public static void main(String[] args) {

        // Customer vorbereiten
        Customer customer1 = new Customer();
        customer1.setLastname("Fuchs");
        customer1.setFirstname("Fritz");
        customer1.setAge(30);

        // Data Access Object holen

        // In memory Implementierung
        // CustomerDao dao = new CustomerDaoInMemory();

        // JDBC Implementierung
        CustomerDao dao = new CustomerDaoJdbc("jdbc:derby:database;create=true");

        // 1) create
        dao.create(customer1);
        log("Created customer1.");

        // 2) read
        Customer fromDB = dao.read(customer1.getLastname());
        log("Read customer from database: " + fromDB);

        // 3) update, dann noch einmal aus DB holen
        customer1.setFirstname("Friedrich");
        log("Kontrolle: " + dao.read(customer1.getLastname()));
        dao.update(customer1);
        fromDB = dao.read(customer1.getLastname());
        log("Updated customer1 in database: " + fromDB);

        // 4) delete, dann versuchen, aus DB zu holen
        dao.delete(customer1);
        fromDB = dao.read(customer1.getLastname());
        log("Deleted customer1 from database: " + fromDB);



    }

}
