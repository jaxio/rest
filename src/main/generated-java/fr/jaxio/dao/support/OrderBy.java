/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/dao/support/OrderBy.p.vm.java
 */
package fr.jaxio.dao.support;

import java.io.Serializable;

import javax.persistence.metamodel.SingularAttribute;

import static com.google.common.base.Preconditions.checkNotNull;
import static fr.jaxio.dao.support.OrderByDirection.ASC;
import static fr.jaxio.dao.support.OrderByDirection.DESC;

/**
 * Holder class for search ordering used by the {@link SearchParameters}.
 * When used with {@link NamedQueryUtil}, you pass column name. For other usage, pass the property name.
 */
public class OrderBy implements Serializable {
    private static final long serialVersionUID = 1L;
    private String columnOrProperty;
    private OrderByDirection direction = ASC;

    public OrderBy(String columnOrProperty, OrderByDirection direction) {
        this.columnOrProperty = checkNotNull(columnOrProperty);
        this.direction = checkNotNull(direction);
    }

    public OrderBy(String columnOrProperty) {
        this(columnOrProperty, ASC);
    }

    public OrderBy(SingularAttribute<?, ?> attribute, OrderByDirection direction) {
        this.columnOrProperty = checkNotNull(attribute).getName();
        this.direction = checkNotNull(direction);
    }

    public OrderBy(SingularAttribute<?, ?> attribute) {
        this(attribute, ASC);
    }

    public String getColumn() {
        return columnOrProperty;
    }

    public String getProperty() {
        return columnOrProperty;
    }

    public OrderByDirection getDirection() {
        return direction;
    }

    public boolean isOrderDesc() {
        return DESC == direction;
    }
}