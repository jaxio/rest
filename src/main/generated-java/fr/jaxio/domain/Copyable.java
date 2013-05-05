/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/domain/Copyable.p.vm.java
 */
package fr.jaxio.domain;

public interface Copyable<T> {
    /**
     * Return a copy of the current object
     */
    T copy();

    /**
     * Copy the current properties to the given object
     */
    void copyTo(T t);
}