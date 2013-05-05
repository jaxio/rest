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
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic tests for Address
 */
@SuppressWarnings("unused")
public class AddressTest {

    // test unique primary key
    @Test
    public void newInstanceHasNoPrimaryKey() {
        Address model = new Address();
        assertThat(model.isIdSet()).isFalse();
    }

    @Test
    public void isIdSetReturnsTrue() {
        Address model = new Address();
        model.setId(ValueGenerator.getUniqueInteger());
        assertThat(model.getId()).isNotNull();
        assertThat(model.isIdSet()).isTrue();
    }

    /*
     public void equalsUsingPk() {
     Address model1 = new Address();
     Address model2 = new Address();

     Integer id = ValueGenerator.getUniqueInteger();
     model1.setId(id);
     model2.setId(id);

     model1.setStreetName("a");
     model2.setStreetName("a");

     model1.setCity("a");
     model2.setCity("a");

     model1.setVersion(1);
     model2.setVersion(1);
     assertThat(model1.isIdSet()).isTrue();
     assertThat(model2.isIdSet()).isTrue();
     assertThat(model1.hashCode()).isEqualTo(model2.hashCode());
     assertThat(model1).isEqualTo(model2);
     assertThat(model2).isEqualTo(model1);
     }
     */
}