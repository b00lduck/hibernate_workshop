package tarent.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tarent.entities.Address;

@Transactional
@Component
public class AddressService {

    private final EntityManager em;

    public AddressService(final EntityManager em) {
        Assert.notNull(em, "This component needs an EntityManager");
        this.em = em;
    }

    @Transactional
    public void createAddress(final Address a) {
        em.persist(a);
    }

}
