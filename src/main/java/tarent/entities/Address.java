package tarent.entities;

import java.util.Objects;
import javax.persistence.*;

@Embeddable
public final class Address {

    private String address;

    private String zip;

    private String city;

    protected Address() {}

    public Address(final String address, final String zip, final String city) {
        this.address = address;
        this.zip = zip;
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("Address[address='%s', zip='%s', city='%s']", address, zip, city);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        final Address address1 = (Address) o;
        return Objects.equals(address, address1.address) &&
                Objects.equals(zip, address1.zip) &&
                Objects.equals(city, address1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, zip, city);
    }
}
