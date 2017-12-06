package tarent.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tarent.entities.Thing;

@Component
public class ThingService {

    private final EntityManager em;

    public ThingService(final EntityManager em) {
        Assert.notNull(em, "This component needs an EntityManager");
        this.em = em;
    }

    @Transactional
    public void createThing(final String title, final String details) {
        final Thing thing = new Thing(title, details);
        em.persist(thing);
    }

}
