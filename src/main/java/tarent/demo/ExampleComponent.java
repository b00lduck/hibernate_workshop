package tarent.demo;

import java.util.Collection;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tarent.entities.Customer;
import tarent.entities.Order;
import tarent.random.RandomDataGenerator;
import tarent.service.CustomerService;
import tarent.service.ThingService;

@Component
public class ExampleComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleComponent.class);

    @Autowired
    private RandomDataGenerator randomDataGenerator;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ThingService thingService;

    public void Example1() {
        // In this Example we show how SQL logging ca be enabled
        createCustomers();
    }

    public void Example2() {
        // In this Example we show how to persist a most simple entity
        thingService.createThing("Book", "something you can read");
        thingService.createThing("Banana", "something you can eat");
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
