/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/test/java/service/ModelGenerator.e.vm.java
 */
package fr.jaxio.repository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import fr.jaxio.domain.Account;
import fr.jaxio.domain.Document;
import fr.jaxio.repository.AccountGenerator;
import fr.jaxio.repository.AccountRepository;
import fr.jaxio.util.ValueGenerator;

/**
 * Helper class to create transient entities instance for testing purposes.
 * Simple properties are pre-filled with random values.
 */
@SuppressWarnings("unused")
@Named
@Singleton
public class DocumentGenerator {

    /**
     * Returns a new Document instance filled with random values.
     */
    public Document getDocument() {
        Document document = new Document();

        // simple attributes follows
        document.setDocumentBinary("d".getBytes());
        document.setDocumentFileName("a");
        document.setDocumentContentType("a");
        document.setDocumentSize(1);
        // mandatory relation
        Account owner = accountGenerator.getAccount();
        accountRepository.save(owner);
        document.setOwner(owner);
        return document;
    }

    @Inject
    private AccountRepository accountRepository;
    @Inject
    private AccountGenerator accountGenerator;
}