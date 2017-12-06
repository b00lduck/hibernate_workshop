package tarent.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tarent.entities.Simple.ThingDetails;
import tarent.entities.Simple.Thing;
import tarent.entities.Simple.ThingAdvanced;
import tarent.entities.Simple.Scientist;

@Component
public class SimpleService {

    @Autowired
    private EntityManager em;

    @Transactional
    public void createSimple(final String title, final String details) {
        final Thing thing = new Thing(title, details);
        em.persist(thing);
    }

    @Transactional
    public void createSimpleEmbedded(final String title, final String details) {
        final ThingDetails embeddedDetails = new ThingDetails(title, details);
        final ThingAdvanced thing = new ThingAdvanced(embeddedDetails);
        em.persist(thing);
    }

    @Transactional
    public void createSimpleEmbeddedList(final String scientist, final String ...ideas) {
        final Scientist thing = new Scientist(scientist);
        for (final String idea : ideas) {
            thing.getIdeas().add(idea);
        }
        em.persist(thing);
    }

}
