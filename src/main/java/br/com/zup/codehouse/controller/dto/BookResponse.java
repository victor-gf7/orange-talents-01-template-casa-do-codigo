package br.com.zup.codehouse.controller.dto;

import br.com.zup.codehouse.model.Book;

public class BookResponse {

    private Long id;
    private String title;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
    }

    /*
     * Getters utilizados para serializar o objeto de retorno
     */
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
