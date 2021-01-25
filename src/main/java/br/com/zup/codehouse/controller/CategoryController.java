package br.com.zup.codehouse.controller;


import br.com.zup.codehouse.controller.dto.NewCategoryRequest;
import br.com.zup.codehouse.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Category> registerCategory(@RequestBody @Valid NewCategoryRequest request){

        Category category = request.converterToModel();
        manager.persist(category);


        return ResponseEntity.ok(category);
    }
}
