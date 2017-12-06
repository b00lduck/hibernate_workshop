package tarent.entities.Advanced;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "CUSTOMERS")
@NamedQueries({
        @NamedQuery(name="Customer.findAll", query="FROM Customer")
})
public class Customer {

    @Id
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;

    @ElementCollection
    private Set<Address> billingAddresses = new LinkedHashSet<>();

    @ElementCollection
    private Set<Address> deliveryAddresses = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Collection<Order> orders = new ArrayList<>();

    protected Customer() {}

    public Customer(final Long id, final Person person) {
        this.id = id;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public Person getPerson() {
        return person;
    }

    public Set<Address> getBillingAddresses() {
        return billingAddresses;
    }

    public Set<Address> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, person=%s]", id, person.toString());
    }

}
