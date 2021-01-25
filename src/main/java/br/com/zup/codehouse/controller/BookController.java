package br.com.zup.codehouse.controller;

import br.com.zup.codehouse.controller.dto.NewBookRequest;
import br.com.zup.codehouse.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Book> registerBook(@RequestBody @Valid NewBookRequest request){

        Book book = request.converterToModel(manager);

        manager.persist(book);

        return ResponseEntity.ok(book);
    }
}
