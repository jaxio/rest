/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/domain/CompositePkBridge.cpk.vm.java
 */
package fr.jaxio.domain;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;

public class LegacyPkBridge implements TwoWayFieldBridge {

    @Override
    public Object get(String name, Document document) {
        LegacyPk id = new LegacyPk();
        id.setCode(document.getFieldable(name + ".code").stringValue());
        id.setDept(Integer.valueOf(document.getFieldable(name + ".dept").stringValue()));
        id.setName(document.getFieldable(name + ".name").stringValue());
        return id;
    }

    @Override
    public String objectToString(Object object) {
        LegacyPk id = (LegacyPk) object;
        return id.toString().replace(':', ' '); // TODO: is space better?
    }

    @Override
    public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
        LegacyPk id = (LegacyPk) value;
        luceneOptions.addFieldToDocument(name + ".code", id.getCode(), document);
        luceneOptions.addNumericFieldToDocument(name + ".dept", id.getDept(), document);
        luceneOptions.addFieldToDocument(name + ".name", id.getName(), document);
    }
}