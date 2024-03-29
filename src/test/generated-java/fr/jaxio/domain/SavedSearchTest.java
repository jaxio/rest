/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/test/java/domain/ModelTest.e.vm.java
 */
package fr.jaxio.domain;

import java.io.*;
import java.util.*;

import static org.fest.assertions.Assertions.assertThat;
import org.junit.Test;

import fr.jaxio.util.*;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.jaxio.domain.Account;

/**
 * Basic tests for SavedSearch
 */
@SuppressWarnings("unused")
public class SavedSearchTest {

    // test unique primary key
    @Test
    public void newInstanceHasNoPrimaryKey() {
        SavedSearch model = new SavedSearch();
        assertThat(model.isIdSet()).isFalse();
    }

    @Test
    public void isIdSetReturnsTrue() {
        SavedSearch model = new SavedSearch();
        model.setId(ValueGenerator.getUniqueInteger());
        assertThat(model.getId()).isNotNull();
        assertThat(model.isIdSet()).isTrue();
    }

    //-------------------------------------------------------------
    // Many to One:  SavedSearch.accountId ==> Account.id
    //-------------------------------------------------------------

    @Test
    public void manyToOne_setAccount() {
        SavedSearch many = new SavedSearch();

        // init
        Account one = new Account();
        one.setId(ValueGenerator.getUniqueString(36));
        many.setAccount(one);

        // make sure it is propagated properly
        assertThat(many.getAccount()).isEqualTo(one);

        // now set it to back to null
        many.setAccount(null);

        // make sure null is propagated properly
        assertThat(many.getAccount()).isNull();
    }

    /*
     public void equalsUsingPk() {
     SavedSearch model1 = new SavedSearch();
     SavedSearch model2 = new SavedSearch();

     Integer id = ValueGenerator.getUniqueInteger();
     model1.setId(id);
     model2.setId(id);

     model1.setName("a");
     model2.setName("a");

     model1.setFormClassname("a");
     model2.setFormClassname("a");

     model1.setFormContent("d".getBytes());
     model2.setFormContent("d".getBytes());
     assertThat(model1.isIdSet()).isTrue();
     assertThat(model2.isIdSet()).isTrue();
     assertThat(model1.hashCode()).isEqualTo(model2.hashCode());
     assertThat(model1).isEqualTo(model2);
     assertThat(model2).isEqualTo(model1);
     }
     */
}