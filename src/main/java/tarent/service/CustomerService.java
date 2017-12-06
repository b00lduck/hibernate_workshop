package tarent.service;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tarent.entities.Advanced.Address;
import tarent.entities.Advanced.Customer;
import tarent.entities.Advanced.Order;
import tarent.entities.Advanced.Person;

@Transactional
@Component
public class CustomerService {

    private final EntityManager em;

    public CustomerService(final EntityManager em) {
        Assert.notNull(em, "This component needs an EntityManager");
        this.em = em;
    }

    public Customer createCustomer(final Person person) {
        em.persist(person);
        final Customer customer = new Customer(person.getId(), person);
        em.persist(customer);
        return customer;
    }

    public void addBillingAddress(final Customer customer, final Address address) {
        final Customer attachedCustomer = em.merge(customer);
        attachedCustomer.getBillingAddresses().add(address);
    }

    public void addDeliveryAddress(final Customer customer, final Address address) {
        final Customer attachedCustomer = em.merge(customer);
        attachedCustomer.getDeliveryAddresses().add(address);
    }

    public void addOrder(final Customer customer, final Order order) {
        final Customer attachedCustomer = em.merge(customer);
        attachedCustomer.getOrders().add(order);
        order.setCustomer(attachedCustomer);
    }

    public Collection<Customer> getAllCustomers() {
        final TypedQuery<Customer> customersQuery =
                em.createQuery("FROM Customer", Customer.class);
        return customersQuery.getResultList();
    }

    public Collection<Customer> getAllCustomersNamed() {
        final TypedQuery<Customer> customersQuery =
                em.createNamedQuery("Customer.findAll", Customer.class);
        return customersQuery.getResultList();
    }

}
