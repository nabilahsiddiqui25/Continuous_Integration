package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;
import com.mockcompany.webapp.model.ProductItem;
import com.mockcompany.webapp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.regex.Pattern;

//Controller must use SearchService

/**
 * Management decided it is super important that we have lots of products that match the following terms.
 * So much so, that they would like a daily report of the number of products for each term along with the total
 * product count.
 *
 * TODO: Refactor this class by rewritting the runReport method to use the SearchService
 */

@RestController
public class ReportController {

    private static final String[] importantTerms = new String[] {
            "Cool",
            "Amazing",
            "Perfect",
            "Kids"
    };

    @Autowired
    private SearchService searchService;

    /**
     * The people that wrote this code didn't know about JPA Spring Repository interfaces!
     */
    private final EntityManager entityManager;
   // private final SearchService service1;
    //un-comment above line after SearchService is completed and constructor is instantiated.

    /**
     * TODO: Declare the SearchService similar to EntityManager and add as a constructor argument
     */

    @Autowired
    public ReportController(EntityManager entityManager, SearchService serviceObj) {
        this.entityManager = entityManager;
        this.searchService = serviceObj;
    }

    //public Collection<ProductItem> search(String query) {
    //return this.searchService.search(query);

    @GetMapping("/api/products/report")
    public SearchReportResponse runReport() {

        Number count1 = (Number) this.entityManager.createQuery("SELECT count(item) FROM ProductItem item").getSingleResult();

        /** TODO: This method needs to be rewritten to use the SearchService */

        Map<String, Integer> hits = new HashMap<>();
        for (String terms: importantTerms) {
            hits.put(terms, searchService.search(terms).size());
        }

        //Transform to API response and return
        SearchReportResponse response = new SearchReportResponse();
        response.setProductCount(count1.intValue());
        response.setSearchTermHits(hits);
        return response;

    }
}
