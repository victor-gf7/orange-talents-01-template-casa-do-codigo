package br.com.zup.codehouse.controller.dto;

import br.com.zup.codehouse.model.Book;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class DetailBookResponse {


    private String title;
    private String resume;
    private String summary;
    private BigDecimal bookPrice;
    private Integer numPages;
    private String isbn;
    private DetailAuthorResponse authorResponse;
    private String publishDate;

    public DetailBookResponse(Book book) {
        this.title = book.getTitle();
        this.resume = book.getResume();
        this.summary = book.getSummary();
        this.bookPrice = book.getBookPrice();
        this.numPages = book.getNumPages();
        this.isbn = book.getIsbn();
        this.authorResponse = new DetailAuthorResponse(book.getAuthor());
        this.publishDate = book.getPublishDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getTitle() {
        return title;
    }

    public String getResume() {
        return resume;
    }

    public String getSummary() {
        return summary;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public DetailAuthorResponse getAuthorResponse() {
        return authorResponse;
    }

    public String getPublishDate() {
        return publishDate;
    }
}
