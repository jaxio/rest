/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/search/SearchMappingFactory.p.vm.java
 */
package fr.jaxio.search;

import org.apache.solr.analysis.ASCIIFoldingFilterFactory;
import org.apache.solr.analysis.DoubleMetaphoneFilterFactory;
import org.apache.solr.analysis.EdgeNGramFilterFactory;
import org.apache.solr.analysis.FrenchLightStemFilterFactory;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.NGramTokenizerFactory;
import org.hibernate.search.annotations.Factory;
import org.hibernate.search.cfg.SearchMapping;

/**
 * This configuration is picked up by hibernate search using the <code>hibernate.search.model_mapping</code> code in <code>/META-INF/persistence.xml</code>
 */
public class SearchMappingFactory {
    @Factory
    public SearchMapping getSearchMapping() {
        SearchMapping mapping = new SearchMapping();
        mapping.analyzerDef("custom", NGramTokenizerFactory.class).tokenizerParam("maxGramSize", "40") //
                .filter(ASCIIFoldingFilterFactory.class) //
                .filter(LowerCaseFilterFactory.class) // 
                .filter(DoubleMetaphoneFilterFactory.class) // 
                .filter(FrenchLightStemFilterFactory.class) //
                .filter(EdgeNGramFilterFactory.class).param("maxGramSize", "40");
        return mapping;
    }
}
