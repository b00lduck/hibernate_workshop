package tarent.service;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tarent.entities.Address;
import tarent.entities.Person;

@Transactional
@Component
public class PersonService {

    private final EntityManager em;

    public PersonService(final EntityManager em) {
        Assert.notNull(em, "This component needs an EntityManager");
        this.em = em;
    }

     public void createPerson(final Person p) {
        em.persist(p);
    }

    public void createPersonWithAddresses(final Person p, final Collection<Address> addresses) {
        p.getAddresses().addAll(addresses);
        for(final Address address : addresses) {
            address.setPerson(p);
        }
        em.persist(p);
    }

    public Collection<Person> getAllPersons() {
        final TypedQuery<Person> personsQuery = em.createQuery("FROM Person", Person.class);
        return personsQuery.getResultList();
    }

}
