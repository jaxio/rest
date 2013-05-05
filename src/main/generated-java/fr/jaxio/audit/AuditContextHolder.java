/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/audit/AuditContextHolder.p.vm.java
 */
package fr.jaxio.audit;

import static org.apache.commons.lang.StringUtils.trimToNull;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import fr.jaxio.context.UserContext;

/**
 * Enable/disable {@link PreUpdate} and {@link PrePersist} actions and/or force the username to be used.
 * Please note that you are in charge of reseting the context properties if you use them directly.
 */
public class AuditContextHolder {
    private static final ThreadLocal<Boolean> audit = new ThreadLocal<Boolean>();
    private static final ThreadLocal<String> username = new ThreadLocal<String>();

    public static void setAudit(boolean auditing) {
        audit.set(auditing);
    }

    public static void setUsername(String user) {
        username.set(trimToNull(user));
    }

    public static boolean audit() {
        return audit.get() == null || audit.get();
    }

    public static String username() {
        return username.get() == null ? UserContext.getUsername() : username.get();
    }
}