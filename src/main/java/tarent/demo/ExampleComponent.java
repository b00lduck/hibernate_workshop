package tarent.demo;

import java.util.Collection;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tarent.entities.Customer;
import tarent.entities.Order;
import tarent.random.RandomDataGenerator;
import tarent.service.CustomerService;
import tarent.service.SimpleService;

@Component
public class ExampleComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleComponent.class);

    @Autowired
    private RandomDataGenerator randomDataGenerator;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SimpleService simpleDataService;

    public void Example1() {
        // In this Example we show how SQL logging ca be enabled
        createCustomers();
    }

    public void Example2() {
        // In this Example we show how to persist a most simple entity
        simpleDataService.createSimple("Book", "something you can read");
        simpleDataService.createSimple("Banana", "something you can eat");
    }

    public void Example3() {
        // In this Example we show how to persist a simple entity with an embedded field
        simpleDataService.createSimpleEmbedded("Movie", "something you can watch");
        simpleDataService.createSimpleEmbedded("Water", "something you can drink");
    }

    public void Example4() {
        // In this Example we show how to persist a simple entity with an embedded list of strings
        simpleDataService.createSimpleEmbeddedList("Heisenberg", "uncertainty principle");
        simpleDataService.createSimpleEmbeddedList("Newton", "falling Apple", "9.81m/s²");
    }

    private void createCustomers() {
        for (int i=0; i < 10; i++) {
            final Customer customer = new Customer(randomDataGenerator.getRandomPerson());
            customer.getBillingAddresses().add(randomDataGenerator.getRandomAddress());
            customer.getDeliveryAddresses().add(randomDataGenerator.getRandomAddress());
            customer.getDeliveryAddresses().add(randomDataGenerator.getRandomAddress());
            customerService.createCustomer(customer);
        }
    }

    @Transactional
    protected void addOrderToFirstCustomer() {
        final Customer customerReference = customerService.getReferenceById(50L);
        customerService.addOrder(customerReference, randomDataGenerator.getRandomOrder());
    }


    private void printCustomers() {

        final Collection<Customer> customers = customerService.getAllCustomers();

        LOGGER.info("Customers found:");
        for (final Customer customer : customers) {
            LOGGER.info(customer.toString());
        }
    }

    private void createOrder(final Customer customer) {
        final Order order = randomDataGenerator.getRandomOrder();
        customerService.addOrder(customer, order);
    }



}
