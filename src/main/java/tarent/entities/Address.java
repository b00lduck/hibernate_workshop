package tarent.entities;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Address {

    @Id
    @GenericGenerator(
            name = "address_id_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_name", value = "address_seq")}
    )
    // depecated:
    // @SequenceGenerator(name = "address_id_generator", sequenceName = "address_seq", allocationSize=1)
    @GeneratedValue(generator = "address_id_generator")
    private Long id;

    private String address;

    private String zip;

    private String city;

    @ManyToOne
    @JoinColumn(name="person_id", nullable=false)
    private Person person;

    protected Address() {}

    public Address(final String address, final String zip, final String city) {
        this.address = address;
        this.zip = zip;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(final String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return String.format(
                "Address[id=%d, address='%s', zip='%s', city='%s']",
                id, address, zip, city);
    }

}
