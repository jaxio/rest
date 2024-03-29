/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/dao/support/ByFullTextUtil.p.vm.java
 */
package fr.jaxio.dao.support;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.SingularAttribute;

import fr.jaxio.domain.Identifiable;

@Named
@Singleton
public class ByFullTextUtil {
    @PersistenceContext
    private EntityManager em;

    @Inject
    HibernateSearchUtil hibernateSearchUtil;

    public <T extends Identifiable<?>> Predicate byFullText(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder, final SearchParameters sp, T entity,
            List<SingularAttribute<?, ?>> indexedAttributes) {
        if (!sp.hasTerms()) {
            return null;
        }

        List<String> properties = JpaUtil.toNamesList(indexedAttributes);
        if (JpaUtil.hasSimplePk(entity)) {
            return onSimplePrimaryKey(root, builder, sp, properties);
        } else {
            return onCompositePrimaryKeys(root, builder, sp, properties);
        }
    }

    private <T extends Identifiable<?>> Predicate onCompositePrimaryKeys(Root<T> root, CriteriaBuilder builder, final SearchParameters sp,
            List<String> properties) {
        List<? extends T> found = hibernateSearchUtil.find(root.getJavaType(), sp, properties);
        if (found.isEmpty()) {
            return builder.disjunction();
        }

        List<Predicate> predicates = newArrayList();
        for (T t : found) {
            predicates.add(byExampleOnEntity(root, t, sp, builder));
        }
        return JpaUtil.andPredicate(builder, JpaUtil.orPredicate(builder, predicates));
    }

    private <T> Predicate onSimplePrimaryKey(Root<T> root, CriteriaBuilder builder, final SearchParameters sp, List<String> properties) {
        List<Serializable> ids = hibernateSearchUtil.findId(root.getJavaType(), sp, properties);
        if (ids.isEmpty()) {
            return builder.disjunction();
        }

        return JpaUtil.andPredicate(builder, root.get("id").in(ids));
    }

    public <T extends Identifiable<?>> Predicate byExampleOnEntity(Root<T> rootPath, final T entityValue, SearchParameters sp, CriteriaBuilder builder) {
        if (entityValue == null) {
            return null;
        }

        Class<T> type = rootPath.getModel().getBindableJavaType();
        ManagedType<T> mt = em.getMetamodel().entity(type);

        List<Predicate> predicates = newArrayList();
        predicates.addAll(byExample(mt, rootPath, entityValue, sp, builder));
        predicates.addAll(byExampleOnCompositePk(rootPath, entityValue, sp, builder));
        return JpaUtil.orPredicate(builder, predicates);
    }

    protected <T extends Identifiable<?>> List<Predicate> byExampleOnCompositePk(Root<T> root, T entity, SearchParameters sp, CriteriaBuilder builder) {
        String compositePropertyName = JpaUtil.compositePkPropertyName(entity);
        if (compositePropertyName == null) {
            return emptyList();
        } else {
            return newArrayList(byExampleOnEmbeddable(root.get(compositePropertyName), entity.getId(), sp, builder));
        }
    }

    public <E> Predicate byExampleOnEmbeddable(Path<E> embeddablePath, final E embeddableValue, SearchParameters sp, CriteriaBuilder builder) {
        if (embeddableValue == null) {
            return null;
        }

        Class<E> type = embeddablePath.getModel().getBindableJavaType();
        ManagedType<E> mt = em.getMetamodel().embeddable(type); // note: calling .managedType() does not work
        return JpaUtil.orPredicate(builder, byExample(mt, embeddablePath, embeddableValue, sp, builder));
    }

    /**
     * Add a predicate for each simple property whose value is not null.
     */
    public <T> List<Predicate> byExample(ManagedType<T> mt, Path<T> mtPath, final T mtValue, SearchParameters sp, CriteriaBuilder builder) {
        List<Predicate> predicates = newArrayList();
        for (SingularAttribute<? super T, ?> attr : mt.getSingularAttributes()) {
            if (!isPrimaryKey(mt, attr)) {
                continue;
            }

            Object attrValue = JpaUtil.getValue(mtValue, attr);
            if (attrValue != null) {
                predicates.add(builder.equal(mtPath.get(JpaUtil.attribute(mt, attr)), attrValue));
            }
        }
        return predicates;
    }

    private <T> boolean isPrimaryKey(ManagedType<T> mt, SingularAttribute<? super T, ?> attr) {
        if (mt.getJavaType().getAnnotation(Embeddable.class) != null) {
            return true;
        }
        return JpaUtil.isPk(mt, attr);
    }
}