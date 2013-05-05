/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/test/java/dao/DaoIT.e.vm.java
 */
package fr.jaxio.dao;

import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Sets.newHashSet;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.jaxio.domain.Address;
import fr.jaxio.repository.AddressGenerator;
import fr.jaxio.dao.AddressDao;

/**
 * Integration test on AddressDao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-test.xml" })
@Transactional
public class AddressDaoIT {
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(AddressDaoIT.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private AddressDao addressDao;

    @Inject
    private AddressGenerator addressGenerator;

    @Test
    @Rollback
    public void saveAndGet() {
        Address address = addressGenerator.getAddress();

        // add it to a Set before saving (force equals/hashcode)
        Set<Address> set = newHashSet(address, address);
        assertThat(set).hasSize(1);

        addressDao.save(address);
        entityManager.flush();

        // reload it from cache and check equality
        Address model = new Address();
        model.setId(address.getId());
        assertThat(address).isEqualTo(addressDao.get(model));

        // clear cache to force reload from db
        entityManager.clear();

        // pk are equals...
        assertThat(address.getId()).isEqualTo(addressDao.get(model).getId());

        // but since you do not use a business key, equality is lost.
        assertThat(address).isNotEqualTo(addressDao.get(model));
    }
}