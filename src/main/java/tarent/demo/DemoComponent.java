package tarent.demo;

import java.util.Collection;
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
import tarent.random.RandomDataGenerator;
import tarent.service.PersonService;

@Component
public class DemoComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoComponent.class);

    private final RandomDataGenerator randomDataGenerator;

    private final PersonService personService;

    public DemoComponent(final PersonService personService, final RandomDataGenerator randomDataGenerator) {
        Assert.notNull(randomDataGenerator, "This component needs a RandomDataGenerator");
        this.randomDataGenerator = randomDataGenerator;

        Assert.notNull(personService, "This component needs a PersonService");
        this.personService = personService;
    }

    public void createPersons() {
        for (int i=0; i < 5000; i++) {
            personService.createPerson(randomDataGenerator.getRandomPerson());
        }
    }

    public void printPersons() {

        final Collection<Person> allPersons = personService.getAllPersons();

        LOGGER.info("Persons found:");
        for (final Person person : allPersons) {
            LOGGER.info(person.toString());
        }
    }
}
