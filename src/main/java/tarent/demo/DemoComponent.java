package tarent.demo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tarent.Application;
import tarent.entities.Person;

@Component
public class DemoComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final EntityManager em;

    public DemoComponent(final EntityManager em) {
        Assert.notNull(em, "This component needs an entity manager");
        this.em = em;
    }

    @Transactional
    public void createPersons() {
        // create a couple of persons
        em.persist(new Person("Jack", "Bauer"));
        em.persist(new Person("Chloe", "O'Brian"));
        em.persist(new Person("Michelle", "Dessler"));
    }

    @Transactional
    public void printPersons() {

        final TypedQuery<Person> personsQuery = em.createQuery("FROM Person", Person.class);
        final List<Person> allPersons = personsQuery.getResultList();

        // fetch all persons
        LOGGER.info("Persons found:");
        for (final Person person : allPersons) {
            LOGGER.info(person.toString());
        }

        /*
        // fetch an individual customer by ID
        Customer customer = repository.findOne(1L);
        LOGGER.info("Customer found with findOne(1L):");
        LOGGER.info("--------------------------------");
        LOGGER.info(customer.toString());
        LOGGER.info("");

        // fetch customers by last name
        LOGGER.info("Customer found with findByLastName('Bauer'):");
        LOGGER.info("--------------------------------------------");
        for (Customer bauer : repository.findByLastName("Bauer")) {
            LOGGER.info(bauer.toString());
        }
        LOGGER.info("");
        */
    }

}
