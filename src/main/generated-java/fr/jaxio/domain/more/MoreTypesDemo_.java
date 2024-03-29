/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/domain/EntityMeta_.e.vm.java
 */
package fr.jaxio.domain.more;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

@StaticMetamodel(MoreTypesDemo.class)
public abstract class MoreTypesDemo_ {

    // Raw attributes
    public static volatile SingularAttribute<MoreTypesDemo, Integer> id;
    public static volatile SingularAttribute<MoreTypesDemo, Integer> numberInt;
    public static volatile SingularAttribute<MoreTypesDemo, Long> numberLong;
    public static volatile SingularAttribute<MoreTypesDemo, Double> numberDouble;
    public static volatile SingularAttribute<MoreTypesDemo, Float> numberFloat;
    public static volatile SingularAttribute<MoreTypesDemo, BigInteger> numberBigInteger;
    public static volatile SingularAttribute<MoreTypesDemo, BigDecimal> numberBigDecimal;
    public static volatile SingularAttribute<MoreTypesDemo, Date> dateJavaTemporalDate;
    public static volatile SingularAttribute<MoreTypesDemo, Date> dateJavaTemporalTimestamp;
    public static volatile SingularAttribute<MoreTypesDemo, LocalDate> dateJoda;
    public static volatile SingularAttribute<MoreTypesDemo, LocalDateTime> dateTimeJoda;
    public static volatile SingularAttribute<MoreTypesDemo, Integer> version;
}