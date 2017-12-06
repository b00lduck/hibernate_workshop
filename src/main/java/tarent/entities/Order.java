package tarent.entities;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "ORDERS")
public final class Order {

    @Id
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_seq", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID", nullable = false)
    private Customer customer;

    private String total;

    protected Order() {}

    public Order(final String total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getTotal() {
        return total;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return String.format("order[id=%d]", id);
    }

}
