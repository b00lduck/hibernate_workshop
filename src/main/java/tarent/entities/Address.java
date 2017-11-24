package tarent.entities;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    private Long id;

    private String address;

    private String zip;

    private String city;

    protected Address() {}

    public Address(final String address, final String zip, final String city) {
        this.address = address;
        this.zip = zip;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format(
                "Address[id=%d, address='%s', zip='%s', city='%s']",
                id, address, zip, city);
    }

}
