package br.com.zup.codehouse.controller;



import br.com.zup.codehouse.controller.dto.NewAuthorRequest;
import br.com.zup.codehouse.model.Author;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Author> registerAuthor(@RequestBody @Valid NewAuthorRequest request){

        Author author = request.convertToModel();
        manager.persist(author);

        return ResponseEntity.ok(author);
    }
}
