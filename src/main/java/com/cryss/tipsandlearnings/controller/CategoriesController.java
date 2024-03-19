package com.cryss.tipsandlearnings.controller;

import com.cryss.tipsandlearnings.component.ResponseIterableComponent;
import com.cryss.tipsandlearnings.model.recursiveentity.Category;
import com.cryss.tipsandlearnings.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController 
@RequestMapping(value = "api/v1")
public class CategoriesController {

    // Other @Autowired injections
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ResponseIterableComponent responseIterable;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<ResponseIterableComponent> getCategories(@PathVariable String publicationTypeCode)  {

        try {

            // FormVersion
            Collection<Category> categories = categoryRepository.findAll ();


            // Forms with it's children
//            Collection<Category> categoriesChildren = categories.;

//            // Send Forms data
            this.responseIterable.data = categories;
            this.responseIterable.status = 200;
            this.responseIterable.info = "success";
            this.responseIterable.message = "Success on getting experimental form metadata data by Publication Type Code: " + publicationTypeCode + ".";
//            this.commonSvc.setLogger(INFO, this.responseIterable.message);

        } catch (Exception e) {
            this.responseIterable.status = 400;
            this.responseIterable.info = "error";
            this.responseIterable.message = "Error on getting experimental form metadata data by Publication Type Code: " + publicationTypeCode + ".";
//            this.commonSvc.setLogger(ERROR, this responseIterable.message, e);
        }

        return ResponseEntity.status(this.responseIterable.status).body(this.responseIterable);

//        return ResponseEntity.status(this.responseIterable.status).body(this.responseIterable);
    }

    // Other API endpoints
}