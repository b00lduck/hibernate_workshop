package tarent.entities.Advanced;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public final class Person {

    @GenericGenerator(
            name = "person_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PERSON_SEQ")}
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    @Column(name="ID")
    private Long id;

    private String firstName;

    private String lastName;

    protected Person() {}

    public Person(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Person[firstName='%s', lastName='%s']", firstName, lastName);
    }

}
