package tarent.demo;

import java.util.Collection;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tarent.entities.Advanced.Customer;
import tarent.entities.Advanced.Order;
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
        simpleDataService.createSimple("Book", "something you can read");
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
        simpleDataService.createSimpleEmbeddedList("Newton", "falling Apple", "9.81m/s²", "gravity", "gravity");
    }

    public void Example5() {
        // In this example we show the OneToOne association with PK sharing
        customerService.createCustomer(randomDataGenerator.getRandomPerson());
    }


    public void Example6() {
        // In this example we show a unidirectional OneToMany association with foreign key
        for(int i=0; i<10; i++) {
            final Customer newCustomer = customerService.createCustomer(randomDataGenerator.getRandomPerson());
            customerService.addBillingAddress(newCustomer, randomDataGenerator.getRandomAddress());
            customerService.addDeliveryAddress(newCustomer, randomDataGenerator.getRandomAddress());
            customerService.addDeliveryAddress(newCustomer, randomDataGenerator.getRandomAddress());
        }
    }

    public void Example7() {
        for(int i=0; i<10; i++) {
            final Customer newCustomer = customerService.createCustomer(randomDataGenerator.getRandomPerson());
            customerService.addBillingAddress(newCustomer, randomDataGenerator.getRandomAddress());
            customerService.addOrder(newCustomer, randomDataGenerator.getRandomOrder());
        }
    }

    public void Example8() {
        for(int i=0; i<10; i++) {
            final Customer newCustomer = customerService.createCustomer(randomDataGenerator.getRandomPerson());

            // Add one billing address to the customer
            customerService.addBillingAddress(newCustomer, randomDataGenerator.getRandomAddress());

            // Add a random number of delivery addresses to the customer
            final int numDeliveryAddresses = randomDataGenerator.getRandomInt(3);
            for (int j=0; j<numDeliveryAddresses; j++) {
                customerService.addDeliveryAddress(newCustomer, randomDataGenerator.getRandomAddress());
            }

            // Add a random number of orders to the customer
            final int numOrders = randomDataGenerator.getRandomInt(10);
            for (int j=0; j<numOrders; j++) {
                customerService.addOrder(newCustomer, randomDataGenerator.getRandomOrder());
            }
        }

        printAllCustomerNames();

    }

    private void printAllCustomerNames() {

        final Collection<Customer> customers = customerService.getAllCustomersNamed();

        System.out.format("\n\n%4s | %20s | %20s\n", "ID", "Firstname", "Lastname");
        System.out.format("--------------------------------------------------------\n");
        for (final Customer customer : customers) {

            System.out.format("%4d | %20s | %20s\n",
                    customer.getPerson().getId(),
                    customer.getPerson().getFirstName(),
                    customer.getPerson().getLastName());

        }

        System.out.format("--------------------------------------------------------\n\n");
    }

    private void createOrder(final Customer customer) {
        final Order order = randomDataGenerator.getRandomOrder();
        customerService.addOrder(customer, order);
    }



}
