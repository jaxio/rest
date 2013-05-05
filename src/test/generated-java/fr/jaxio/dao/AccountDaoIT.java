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

import fr.jaxio.domain.Account;
import fr.jaxio.repository.AccountGenerator;
import fr.jaxio.dao.AccountDao;

/**
 * Integration test on AccountDao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-test.xml" })
@Transactional
public class AccountDaoIT {
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(AccountDaoIT.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private AccountDao accountDao;

    @Inject
    private AccountGenerator accountGenerator;

    @Test
    @Rollback
    public void saveAndGet() {
        Account account = accountGenerator.getAccount();

        // add it to a Set before saving (force equals/hashcode)
        Set<Account> set = newHashSet(account, account);
        assertThat(set).hasSize(1);

        accountDao.save(account);
        entityManager.flush();

        // reload it from cache and check equality
        Account model = new Account();
        model.setId(account.getId());
        assertThat(account).isEqualTo(accountDao.get(model));

        // clear cache to force reload from db
        entityManager.clear();

        // since you use a business key, equality must be preserved.
        assertThat(account).isEqualTo(accountDao.get(model));
    }
}