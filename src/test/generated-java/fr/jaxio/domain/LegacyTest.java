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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic tests for Legacy
 */
@SuppressWarnings("unused")
public class LegacyTest {
    // test composite primary key

    @Test
    public void newInstanceHasNoPrimaryKey() {
        Legacy model = new Legacy();
        assertThat(model.getId()).isNotNull();
        assertThat(model.isIdSet()).isFalse();
    }

    @Test
    public void setEmptyCompositePrimaryKey() {
        Legacy model = new Legacy();
        LegacyPk pk = new LegacyPk();
        model.setId(pk);
        assertThat(model.isIdSet()).isFalse();
        assertThat(model.getId()).isSameAs(pk);
    }

    @Test
    public void setValidCompositePrimaryKey() {
        Legacy model = new Legacy();
        LegacyPk pk = new LegacyPk();
        pk.setCode(ValueGenerator.getUniqueString(8));
        pk.setDept(ValueGenerator.getUniqueInteger());
        pk.setName(ValueGenerator.getUniqueString(16));
        model.setId(pk);

        assertThat(model.isIdSet()).isTrue();
        assertThat(model.getId()).isSameAs(pk);
    }

    /*
     public void equalsUsingPk() {
     Legacy model1 = new Legacy();
     Legacy model2 = new Legacy();

     String name = ValueGenerator.getUniqueString(16);
     model1.setName(name);
     model2.setName(name);

     String code = ValueGenerator.getUniqueString(8);
     model1.setCode(code);
     model2.setCode(code);

     Integer dept = ValueGenerator.getUniqueInteger();
     model1.setDept(dept);
     model2.setDept(dept);

     model1.setExtraInfo("a");
     model2.setExtraInfo("a");
     assertThat(model1.isIdSet()).isTrue();
     assertThat(model2.isIdSet()).isTrue();
     assertThat(model1.hashCode()).isEqualTo(model2.hashCode());
     assertThat(model1).isEqualTo(model2);
     assertThat(model2).isEqualTo(model1);
     }
     */
}