package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//already declared as RestController

@RestController
public class ProductsController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductItemRepository productItemRepository;

    @Autowired
    public ProductsController(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    @GetMapping("/api/products/list")
    public Iterable<ProductItem> getAllProducts() {
        return this.productItemRepository.findAll();
    }
}
