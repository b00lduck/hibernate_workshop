package tarent.demo;

import java.util.Collection;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tarent.entities.Customer;
import tarent.entities.Order;
import tarent.random.RandomDataGenerator;
import tarent.service.CustomerService;

@Component
public class DemoComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoComponent.class);

    private final RandomDataGenerator randomDataGenerator;

    private final CustomerService customerService;

    public DemoComponent(final CustomerService customerService, final RandomDataGenerator randomDataGenerator) {
        Assert.notNull(randomDataGenerator, "This component needs a RandomDataGenerator");
        this.randomDataGenerator = randomDataGenerator;

        Assert.notNull(customerService, "This component needs a PersonService");
        this.customerService = customerService;
    }

    public void createCustomers() {
        for (int i=0; i < 100; i++) {
            final Customer customer = new Customer(randomDataGenerator.getRandomPerson());
            customer.getBillingAddresses().add(randomDataGenerator.getRandomAddress());
            customer.getDeliveryAddresses().add(randomDataGenerator.getRandomAddress());
            customer.getDeliveryAddresses().add(randomDataGenerator.getRandomAddress());
            customerService.createCustomer(customer);
        }
    }

    @Transactional
    public void addOrderToFirstCustomer() {
        final Customer customerReference = customerService.getReferenceById(50L);
        customerService.addOrder(customerReference, randomDataGenerator.getRandomOrder());
    }


    public void printCustomers() {

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
