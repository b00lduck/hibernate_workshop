package tarent.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Transactional
@Component
public class OrderService {

    private final EntityManager em;

    public OrderService(final EntityManager em) {
        Assert.notNull(em, "This component needs an EntityManager");
        this.em = em;
    }

}
