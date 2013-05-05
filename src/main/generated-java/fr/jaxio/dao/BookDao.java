/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/dao/DAOImpl.e.vm.java
 */
package fr.jaxio.dao;

import static fr.jaxio.domain.Book_.title;
import javax.inject.Named;
import javax.inject.Singleton;
import fr.jaxio.dao.BookDao;
import fr.jaxio.dao.support.GenericDao;
import fr.jaxio.domain.Book;

/**
 * JPA 2 Data Access Object for {@link Book}.
 * Note: do not use @Transactional in the DAO layer.
 */
@Named
@Singleton
public class BookDao extends GenericDao<Book, Integer> {
    public BookDao() {
        super(Book.class, title);
    }
}