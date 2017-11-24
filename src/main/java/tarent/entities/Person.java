package tarent.entities;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Person {

    @Id
    @SequenceGenerator(name = "person_id_seq", sequenceName = "person_seq", allocationSize=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany
    private Collection<Address> addresses = new ArrayList<>();

    protected Person() {}

    public Person(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Person[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
