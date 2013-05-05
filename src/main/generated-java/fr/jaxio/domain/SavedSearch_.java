/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/domain/EntityMeta_.e.vm.java
 */
package fr.jaxio.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import fr.jaxio.domain.Account;

@StaticMetamodel(SavedSearch.class)
public abstract class SavedSearch_ {

    // Raw attributes
    public static volatile SingularAttribute<SavedSearch, Integer> id;
    public static volatile SingularAttribute<SavedSearch, String> name;
    public static volatile SingularAttribute<SavedSearch, String> formClassname;
    public static volatile SingularAttribute<SavedSearch, byte[]> formContent;

    // Many to one
    public static volatile SingularAttribute<SavedSearch, Account> account;
}