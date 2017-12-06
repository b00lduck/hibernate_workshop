package tarent.service;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
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

    public void createCustomer(final Person person) {
        em.persist(person);
        final Customer customer = new Customer(person.getId(), person);
        em.persist(customer);
    }

    public void addOrder(final Customer customer, final Order order) {
        customer.getOrders().add(order);
        order.setCustomer(customer);
        em.persist(customer);
    }

    public Customer getReferenceById(final Long id) {
        return em.getReference(Customer.class, id);
    }

    public Collection<Customer> getAllCustomers() {
        final TypedQuery<Customer> customersQuery = em.createQuery("FROM Customer", Customer.class);
        return customersQuery.getResultList();
    }

}
