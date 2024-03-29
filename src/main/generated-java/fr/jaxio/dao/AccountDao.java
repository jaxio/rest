/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/dao/DAOImpl.e.vm.java
 */
package fr.jaxio.dao;

import static fr.jaxio.domain.Account_.email;
import static fr.jaxio.domain.Account_.firstName;
import static fr.jaxio.domain.Account_.lastName;
import static fr.jaxio.domain.Account_.username;
import javax.inject.Named;
import javax.inject.Singleton;
import fr.jaxio.dao.AccountDao;
import fr.jaxio.dao.support.GenericDao;
import fr.jaxio.domain.Account;

/**
 * JPA 2 Data Access Object for {@link Account}.
 * Note: do not use @Transactional in the DAO layer.
 */
@Named
@Singleton
public class AccountDao extends GenericDao<Account, String> {
    public AccountDao() {
        super(Account.class, username, email, firstName, lastName);
    }
}