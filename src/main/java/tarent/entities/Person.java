package tarent.entities;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Person {

    private org.hibernate.id.enhanced.SequenceStyleGenerator x;

    @Id
    @GenericGenerator(
            name = "person_id_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "person_seq")}
    )
    @GeneratedValue(generator = "person_id_generator")
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<Address> addresses = new ArrayList<>();

    protected Person() {}

    public Person(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(final Collection<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return String.format(
                "Person[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
