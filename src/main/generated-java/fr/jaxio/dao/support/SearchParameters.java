/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/dao/support/SearchParameters.p.vm.java
 */
package fr.jaxio.dao.support;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import fr.jaxio.domain.Identifiable;

/**
 * The SearchParameters is used to pass search parameters to the DAO layer.
 * 
 * Its usage keeps 'find' method signatures in the DAO/Service layer simple.
 * 
 * A SearchParameters helps you drive your search in the following areas:
 * <ul>
 * <li>Configure the search mode (EQUALS, LIKE, ...)</li>
 * <li>Pagination: it allows you to limit your search results to a specific range.</li>
 * <li>Allow you to specify ORDER BY and ASC/DESC</li>
 * <li>Enable/disable case sensitivity</li>
 * <li>Enable/disable 2d level cache</li>
 * <li>LIKE search against all string values: simply set the searchPattern property</li>
 * <li>Named query: if you set a named query it will be executed. Named queries can be defined in annotation or src/main/resources/META-INF/orm.xml</li>
 * <li>FullTextSearch: simply set the term property (requires Hibernate Search)</li>
 * </ul>
 * 
 * Note : All requests are limited to a maximum number of elements to prevent resource exhaustion.
 * 
 * @see GenericDao
 * @see SearchMode
 * @see OrderBy
 * @see Range
 * @see NamedQueryUtil
 * @see PropertySelector
 * @see EntitySelector
 */
public class SearchParameters implements Serializable {
    private static final long serialVersionUID = 1L;

    private SearchMode searchMode = SearchMode.EQUALS;

    // named query related
    private String namedQuery;
    private Map<String, Object> parameters = newHashMap();

    private List<OrderBy> orders = newArrayList();

    // technical parameters
    private boolean caseSensitive = false;

    // Pagination
    private int maxResults = 500;
    private int firstResult = 0;

    // Joins
    private List<SingularAttribute<?, ?>> leftJoins = newArrayList();

    // ranges
    private List<Range<?, ?>> ranges = newArrayList();

    // property selectors
    private List<PropertySelector<?, ?>> properties = newArrayList();

    // entity selectors
    private List<EntitySelector<?, ? extends Identifiable<?>, ?>> entities = newArrayList();

    // pattern to match against all strings.
    private String searchPattern;

    // Warn: before enabling cache for queries,
    // check this: https://hibernate.atlassian.net/browse/HHH-1523
    private Boolean cacheable = false;
    private String cacheRegion;

    // extra parameters
    private Map<String, Object> extraParameters = newHashMap();

    private boolean useANDInManyToMany = true;

    private boolean useDistinct = false;

    // -----------------------------------
    // SearchMode
    // -----------------------------------

    /**
     * Fluently set the @{link SearchMode}. It defaults to EQUALS.
     * @see SearchMode#EQUALS
     */
    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = checkNotNull(searchMode);
    }

    /**
     * Fluently set the @{link SearchMode}. It defaults to EQUALS.
     * @see SearchMode#EQUALS
     */
    public SearchParameters searchMode(SearchMode searchMode) {
        setSearchMode(searchMode);
        return this;
    }

    /**
     * Use the EQUALS @{link SearchMode}.
     * @see SearchMode#EQUALS
     */
    public SearchParameters equals() {
        return searchMode(SearchMode.EQUALS);
    }

    /**
     * Use the ANYWHERE @{link SearchMode}.
     * @see SearchMode#ANYWHERE
     */
    public SearchParameters anywhere() {
        return searchMode(SearchMode.ANYWHERE);
    }

    /**
     * Use the STARTING_LIKE @{link SearchMode}.
     * @see SearchMode#STARTING_LIKE
     */
    public SearchParameters startingLike() {
        return searchMode(SearchMode.STARTING_LIKE);
    }

    /**
     * Use the LIKE @{link SearchMode}.
     * @see SearchMode#LIKE
     */
    public SearchParameters like() {
        return searchMode(SearchMode.LIKE);
    }

    /**
     * Use the ENDING_LIKE @{link SearchMode}.
     * @see SearchMode#ENDING_LIKE
     */
    public SearchParameters endingLike() {
        return searchMode(SearchMode.ENDING_LIKE);
    }

    /**
     * Return the @{link SearchMode}. It defaults to EQUALS.
     * @see SearchMode#EQUALS
     */
    public SearchMode getSearchMode() {
        return searchMode;
    }

    public boolean is(SearchMode searchMode) {
        return getSearchMode() == searchMode;
    }

    // -----------------------------------
    // Named query support
    // -----------------------------------

    /**
     * Returns true if a named query has been set, false otherwise. When it returns true, the DAO layer will call the namedQuery.
     */
    public boolean hasNamedQuery() {
        return isNotBlank(namedQuery);
    }

    /**
     * Set the named query to be used by the DAO layer. Null by default.
     */
    public void setNamedQuery(String namedQuery) {
        this.namedQuery = namedQuery;
    }

    /**
     * Fluently set the named query to be used by the DAO layer. Null by default.
     */
    public SearchParameters namedQuery(String namedQuery) {
        setNamedQuery(namedQuery);
        return this;
    }

    /**
     * Return the name of the named query to be used by the DAO layer.
     */
    public String getNamedQuery() {
        return namedQuery;
    }

    /**
     * Set the parameters for the named query.
     */
    public void setNamedQueryParameters(Map<String, Object> parameters) {
        this.parameters = checkNotNull(parameters);
    }

    /**
     * Set the parameters for the named query.
     */
    public void addNamedQueryParameter(String name, Object value) {
        parameters.put(checkNotNull(name), checkNotNull(value));
    }

    /**
     * Fluently set the parameters for the named query.
     */
    public SearchParameters namedQueryParameters(Map<String, Object> parameters) {
        setNamedQueryParameters(parameters);
        return this;
    }

    /**
     * Fluently set the parameters for the named query.
     */
    public SearchParameters namedQueryParameter(String name, Object value) {
        addNamedQueryParameter(name, value);
        return this;
    }

    /**
     * The parameters associated with the named query, if any.
     */
    public Map<String, Object> getNamedQueryParameters() {
        return parameters;
    }

    /**
     * Return the value of the passed parameter name.
     */
    public Object getNamedQueryParameter(String parameterName) {
        return parameters.get(checkNotNull(parameterName));
    }

    // -----------------------------------
    // Search pattern support
    // -----------------------------------

    /**
     * When it returns true, it indicates to the DAO layer to use the passed searchPattern on all string properties.
     */
    public boolean hasSearchPattern() {
        return isNotBlank(searchPattern);
    }

    /**
     * Set the pattern which may contains wildcards (ex: "e%r%ka" ). The passed searchPattern is used by the DAO layer on all string properties. Null by
     * default.
     */
    public void setSearchPattern(String searchPattern) {
        this.searchPattern = searchPattern;
    }

    /**
     * Fluently set the pattern which may contains wildcards (ex: "e%r%ka" ). The passed searchPattern is used by the DAO layer on all string properties. Null
     * by default.
     */
    public SearchParameters searchPattern(String searchPattern) {
        setSearchPattern(searchPattern);
        return this;
    }

    /**
     * Returns the search pattern to be used by the DAO layer.
     */
    public String getSearchPattern() {
        return searchPattern;
    }

    // -----------------------------------
    // Terms support (hibernate search)
    // -----------------------------------

    private List<String> termsOnDefault = newArrayList();
    private ArrayListMultimap<String, String> termsOnAny = ArrayListMultimap.create();
    private ArrayListMultimap<String, String> termsOnAll = ArrayListMultimap.create();

    public boolean hasTerms() {
        return !(termsOnAny.isEmpty() && termsOnAll.isEmpty() && termsOnDefault.isEmpty());
    }

    public List<String> getTermsOnDefault() {
        return termsOnDefault;
    }

    public ArrayListMultimap<String, String> getTermsOnAny() {
        return termsOnAny;
    }

    public ArrayListMultimap<String, String> getTermsOnAll() {
        return termsOnAll;
    }

    public SearchParameters term(String term) {
        if (isNotBlank(term)) {
            termsOnDefault.add(term);
        }
        return this;
    }

    public SearchParameters termOn(String term, String props) {
        return addTerm(termsOnAll, term, props);
    }

    public SearchParameters termOn(String term, SingularAttribute<?, ?> attr) {
        return addTerm(termsOnAll, term, attr.getName());
    }

    public SearchParameters termOnAll(String term, SingularAttribute<?, ?>... attrs) {
        return addTerm(termsOnAll, term, JpaUtil.toNames(attrs));
    }

    public SearchParameters termOnAny(String term, String... props) {
        return addTerm(termsOnAny, term, props);
    }

    public SearchParameters termOnAny(String term, SingularAttribute<?, ?>... attrs) {
        return addTerm(termsOnAny, term, JpaUtil.toNames(attrs));
    }

    public SearchParameters term(String term, SingularAttribute<?, ?>... attrs) {
        return addTerm(termsOnAny, term, JpaUtil.toNames(attrs));
    }

    private SearchParameters addTerm(Multimap<String, String> termsMap, String term, String... props) {
        if (isBlank(term) || props == null || props.length == 0) {
            return this;
        }
        for (String prop : props) {
            termsMap.put(term, prop);
        }
        return this;
    }

    // -----------------------------------
    // Case sensitiveness support
    // -----------------------------------

    /**
     * Set the case sensitiveness. Defaults to false.
     * 
     * @param caseSensitive
     */
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    /**
     * Fluently set the case sensitiveness. Defaults to false.
     * 
     * @param caseSensitive
     */
    public SearchParameters caseSensitive(boolean caseSensitive) {
        setCaseSensitive(caseSensitive);
        return this;
    }

    /**
     * Fluently set the case sensitiveness to true.
     */
    public SearchParameters caseSensitive() {
        setCaseSensitive(true);
        return this;
    }

    /**
     * Fluently set the case sensitiveness to false.
     */
    public SearchParameters caseInsensitive() {
        setCaseSensitive(false);
        return this;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public boolean isCaseInsensitive() {
        return !caseSensitive;
    }

    // -----------------------------------
    // Order by support
    // -----------------------------------

    public List<OrderBy> getOrders() {
        return orders;
    }

    public void addOrderBy(String fieldName) {
        orders.add(new OrderBy(checkNotNull(fieldName)));
    }

    public void addOrderBy(String fieldName, OrderByDirection direction) {
        orders.add(new OrderBy(checkNotNull(fieldName), checkNotNull(direction)));
    }

    public void addOrderBy(SingularAttribute<?, ?> attribute) {
        orders.add(new OrderBy(checkNotNull(attribute)));
    }

    public void addOrderBy(SingularAttribute<?, ?> attribute, OrderByDirection direction) {
        orders.add(new OrderBy(checkNotNull(attribute), checkNotNull(direction)));
    }

    public void addOrderBy(OrderBy orderBy) {
        orders.add(checkNotNull(orderBy));
    }

    public SearchParameters orderBy(OrderBy orderBy) {
        addOrderBy(orderBy);
        return this;
    }

    public SearchParameters orderBy(String fieldName) {
        addOrderBy(fieldName);
        return this;
    }

    public SearchParameters orderBy(String fieldName, OrderByDirection direction) {
        addOrderBy(fieldName, direction);
        return this;
    }

    public SearchParameters orderBy(SingularAttribute<?, ?> attribute) {
        addOrderBy(attribute);
        return this;
    }

    public SearchParameters orderBy(SingularAttribute<?, ?> attribute, OrderByDirection direction) {
        addOrderBy(attribute, direction);
        return this;
    }

    public boolean hasOrders() {
        return !orders.isEmpty();
    }

    public void clearOrders() {
        orders.clear();
    }

    // -----------------------------------
    // Search by range support
    // -----------------------------------

    public List<Range<?, ?>> getRanges() {
        return ranges;
    }

    public void addRange(Range<?, ?> range) {
        ranges.add(range);
    }

    /**
     * Add the passed {@link Range} in order to create a 'range' predicate on the corresponding property. 
     */
    public SearchParameters range(Range<?, ?> range) {
        addRange(range);
        return this;
    }

    public boolean hasRanges() {
        return !ranges.isEmpty();
    }

    public void clearRanges() {
        ranges.clear();
    }

    // -----------------------------------
    // Search by property selector support
    // -----------------------------------

    public List<PropertySelector<?, ?>> getProperties() {
        return properties;
    }

    public void addProperty(PropertySelector<?, ?> propertySelector) {
        properties.add(propertySelector);
    }

    /**
     * Add the passed {@link PropertySelector} in order to construct an OR predicate for the corresponding property. 
     */
    public SearchParameters property(PropertySelector<?, ?> propertySelector) {
        addProperty(propertySelector);
        return this;
    }

    public boolean hasProperties() {
        return !properties.isEmpty();
    }

    public void clearProperties() {
        properties.clear();
    }

    // -----------------------------------
    // Search by entity selector support
    // -----------------------------------

    public List<EntitySelector<?, ? extends Identifiable<?>, ?>> getEntities() {
        return entities;
    }

    public void addEntity(EntitySelector<?, ? extends Identifiable<?>, ?> entitySelector) {
        entities.add(entitySelector);
    }

    /**
     * Add the passed {@link EntitySelector} in order to construct an OR predicate for the underlying foreign key. 
     */
    public SearchParameters entity(EntitySelector<?, ? extends Identifiable<?>, ?> entitySelector) {
        addEntity(entitySelector);
        return this;
    }

    public boolean hasEntities() {
        return !entities.isEmpty();
    }

    public void clearEntities() {
        entities.clear();
    }

    // -----------------------------------
    // Pagination support
    // -----------------------------------

    /**
     * Set the maximum number of results to retrieve. Pass -1 for no limits.
     */
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public SearchParameters maxResults(int maxResults) {
        setMaxResults(maxResults);
        return this;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public SearchParameters noLimit() {
        setMaxResults(-1);
        return this;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public SearchParameters firstResult(int firstResult) {
        setFirstResult(firstResult);
        return this;
    }

    public int getFirstResult() {
        return firstResult;
    }

    // -----------------------------------------
    // Fetch associated entity using a LEFT Join 
    // -----------------------------------------

    /**
     * Returns the attribute (x-to-one association) which must be fetched with a left join.
     */
    public List<SingularAttribute<?, ?>> getLeftJoins() {
        return leftJoins;
    }

    public boolean hasLeftJoins() {
        return !leftJoins.isEmpty();
    }

    /**
     * The passed attribute (x-to-one association) will be fetched with a left join.
     */
    public void addLeftJoin(SingularAttribute<?, ?> xToOneAttribute) {
        leftJoins.add(xToOneAttribute);
    }

    /**
     * Fluently set the join attribute
     */
    public SearchParameters leftJoin(SingularAttribute<?, ?> xToOneAttribute) {
        addLeftJoin(xToOneAttribute);
        return this;
    }

    // -----------------------------------
    // Caching support
    // -----------------------------------

    /**
     * Default to false.
     * Please read https://hibernate.atlassian.net/browse/HHH-1523
     * before using cache.
     */
    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public SearchParameters cacheable(boolean cacheable) {
        setCacheable(cacheable);
        return this;
    }

    public SearchParameters enableCache() {
        setCacheable(true);
        return this;
    }

    public SearchParameters disableCache() {
        setCacheable(false);
        return this;
    }

    public boolean isCacheable() {
        return cacheable;
    }

    public boolean hasCacheRegion() {
        return isNotBlank(cacheRegion);
    }

    public void setCacheRegion(String cacheRegion) {
        this.cacheRegion = cacheRegion;
    }

    public SearchParameters cacheRegion(String cacheRegion) {
        setCacheRegion(cacheRegion);
        return this;
    }

    public String getCacheRegion() {
        return cacheRegion;
    }

    // -----------------------------------
    // Extra parameters
    // -----------------------------------

    /**
     * Set additionnal parameters.
     */
    public void setExtraParameters(Map<String, Object> extraParameters) {
        this.extraParameters = extraParameters;
    }

    public Map<String, Object> getExtraParameters() {
        return extraParameters;
    }

    /**
     * add additionnal parameter.
     */
    public SearchParameters addExtraParameter(String key, Object o) {
        extraParameters.put(key, o);
        return this;
    }

    /**
     * get additionnal parameter.
     */
    @SuppressWarnings("unchecked")
    public <T> T getExtraParameter(String key) {
        return (T) extraParameters.get(key);
    }

    // -----------------------------------
    // Use and in NN Search 
    // -----------------------------------

    public SearchParameters useORInManyToMany() {
        return useANDInManyToMany(false);
    }

    public SearchParameters useANDInManyToMany() {
        return useANDInManyToMany(true);
    }

    public SearchParameters useANDInManyToMany(boolean useANDInNNSearch) {
        this.useANDInManyToMany = useANDInNNSearch;
        return this;
    }

    public boolean getUseANDInManyToMany() {
        return useANDInManyToMany;
    }

    // -----------------------------------
    // Distinct
    // -----------------------------------

    public void setDistinct(boolean useDistinct) {
        this.useDistinct = useDistinct;
    }

    public boolean getDistinct() {
        return useDistinct;
    }

    public SearchParameters distinct(boolean useDistinct) {
        setDistinct(useDistinct);
        return this;
    }

    public SearchParameters useDistinct() {
        setDistinct(true);
        return this;
    }

    public SearchParameters disableDistinct() {
        setDistinct(false);
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}