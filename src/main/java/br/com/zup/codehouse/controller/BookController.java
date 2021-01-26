package br.com.zup.codehouse.controller;

import br.com.zup.codehouse.controller.dto.BookResponse;
import br.com.zup.codehouse.controller.dto.DetailBookResponse;
import br.com.zup.codehouse.controller.dto.NewBookRequest;
import br.com.zup.codehouse.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public List<BookResponse> listBooks(){

        Query query = manager.createQuery("select b from Book b");
        List<Book> books = query.getResultList();

        List<BookResponse> responseList = new ArrayList<>();

        books.forEach(book -> responseList.add(new BookResponse(book)));

        return responseList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailBookResponse> detailBook(@PathVariable Long id){
        Book book = manager.find(Book.class, id);

        if (book ==  null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DetailBookResponse(book));
    }
}
