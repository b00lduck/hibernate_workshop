package tarent.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Transactional
@Component
public class OrderService {

    @Autowired
    private EntityManager em;

}
