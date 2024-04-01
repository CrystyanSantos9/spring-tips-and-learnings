package com.cryss.tipsandlearnings.controller;

import com.cryss.tipsandlearnings.component.ResponseIterableComponent;
import com.cryss.tipsandlearnings.model.recursiveentity.Category;
import com.cryss.tipsandlearnings.model.request.CategoryRequest;
import com.cryss.tipsandlearnings.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController 
@RequestMapping(value = "api/v1/categories")
public class CategoriesController {

    // Other @Autowired injections
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ResponseIterableComponent responseIterable;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseIterableComponent> getCategories(@PathVariable Long id) {

        try {

            // FormVersion
            Collection<Category> categories = new ArrayList<> ();
            categories.add (categoryRepository.findById (id).get ());


            // Forms with it's children
//            Collection<Category> categoriesChildren = categories.;

//            // Send Forms data
            this.responseIterable.data = categories;
            this.responseIterable.status = 200;
            this.responseIterable.info = "success";
            this.responseIterable.message = "Success on getting experimental form metadata data by Publication Type Code: " + id + ".";
//            this.commonSvc.setLogger(INFO, this.responseIterable.message);

        } catch (Exception e) {
            this.responseIterable.status = 400;
            this.responseIterable.info = "error";
            this.responseIterable.message = "Error on getting experimental form metadata data by Publication Type Code: " + id + ".";
//            this.commonSvc.setLogger(ERROR, this responseIterable.message, e);
        }

        return ResponseEntity.status (this.responseIterable.status).body (this.responseIterable);

//        return ResponseEntity.status(this.responseIterable.status).body(this.responseIterable);
    }

    // Other API endpoints


    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getAllCategories()  {


        return categoryRepository.findAllCategory ();

// Other API endpoints
    }

    @PutMapping(value = "/{id}/parent/{parentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category saveParentCategory(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "parentId") Long parentId

 )  {

        if(Objects.nonNull (id) && Objects.nonNull (parentId)) {

          var parent =  categoryRepository.findById (parentId).orElseThrow (()-> new RuntimeException ("parent id nao encontrado"));

          var chield = categoryRepository.findById (id).orElseThrow (()-> new RuntimeException ("parent id nao encontrado"));

          chield.setParentCategory (parent);

        return   categoryRepository.save (chield);

        }else{
            throw new RuntimeException ("OCORREU UM ERRO");
        }



// Other API endpoints
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category saveCategory(@RequestBody CategoryRequest category)  {

        if(Objects.nonNull (category)) {
            return categoryRepository.save (

                    Category.
                            builder ()
                            .id (category.id ())
                            .name (category.name ())
                            .build ()

            );
        }else{
            throw new RuntimeException ("OCORREU UM ERRO");
        }



// Other API endpoints
    }



}




