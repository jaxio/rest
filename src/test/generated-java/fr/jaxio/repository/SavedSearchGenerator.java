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
import fr.jaxio.domain.SavedSearch;
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
public class SavedSearchGenerator {

    /**
     * Returns a new SavedSearch instance filled with random values.
     */
    public SavedSearch getSavedSearch() {
        SavedSearch savedSearch = new SavedSearch();

        // simple attributes follows
        savedSearch.setName("a");
        savedSearch.setFormClassname("a");
        savedSearch.setFormContent("d".getBytes());
        // mandatory relation
        Account account = accountGenerator.getAccount();
        accountRepository.save(account);
        savedSearch.setAccount(account);
        return savedSearch;
    }

    @Inject
    private AccountRepository accountRepository;
    @Inject
    private AccountGenerator accountGenerator;
}