package at.campus02.dbp2.dao;

public class Customer {

    private String lastname;
    private String firstname;
    private Integer age;

    // Für JPA brauchen wir einen default Konstruktor, also behalten wir den....
    public Customer() {
    }

    // Hilfskonstruktur zum Klonen (Auflösen der Referenz)
    public Customer(Customer toClone) {
        lastname = toClone.getLastname();
        firstname = toClone.getFirstname();
        age = toClone.getAge();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", age=" + age +
                '}';
    }
}
