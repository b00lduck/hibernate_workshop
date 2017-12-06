package tarent.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class Person {

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
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

    @Override
    public String toString() {
        return String.format("Person[firstName='%s', lastName='%s']", firstName, lastName);
    }

}
