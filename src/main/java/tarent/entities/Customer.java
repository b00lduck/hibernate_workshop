package tarent.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_seq", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @Column(name="ID")
    private Long id;

    @Embedded
    private Person person;

    @ElementCollection
    private Set<Address> billingAddresses = new LinkedHashSet<>();

    @ElementCollection
    private Set<Address> deliveryAddresses = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Collection<Order> orders = new ArrayList<>();

    protected Customer() {}

    public Customer(final Person person) {
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
