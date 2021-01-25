package br.com.zup.codehouse.controller.dto;

import br.com.zup.codehouse.config.ExistsId;
import br.com.zup.codehouse.config.UniqueValue;
import br.com.zup.codehouse.model.Author;
import br.com.zup.codehouse.model.Book;
import br.com.zup.codehouse.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewBookRequest {

    @NotBlank
    @UniqueValue(domainClass = Book.class, fieldName = "title", message = "Já existe um livro cadastrado com esse nome")
    private String title;

    @NotBlank
    private String resume;

    @NotBlank
    private String summary;

    @NotNull
    @Min(value = 20, message = "O preço do do livro deve ser de no mínimo R$ 20,00")
    private BigDecimal bookPrice;

    @NotNull
    @Min(value = 100, message = "O livro deve possuir no mínimo 100 páginas")
    private Integer numPages;

    @NotBlank
    @UniqueValue(domainClass = Book.class, fieldName = "isbn", message = "Já existe um livro com esse ISBN cadastrado")
    private String isbn;

    @NotNull
    @Future(message = "A data de publicação deve estar no futuro")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate publishDate;

    @NotNull
    @ExistsId(domainClass = Category.class, fieldName = "id")
    private Long categoryId;

    @NotNull
    @ExistsId(domainClass = Author.class, fieldName = "id")
    private Long authorId;


    public NewBookRequest(@NotBlank String title, @NotBlank String resume, @NotBlank String summary,
                          @NotNull @Min(value = 20) BigDecimal bookPrice,
                          @NotNull @Min(value = 100) Integer numPages,
                          @NotBlank String isbn,
                          @NotNull Long categoryId, @NotNull Long authorId) {
        this.title = title;
        this.resume = resume;
        this.summary = summary;
        this.bookPrice = bookPrice;
        this.numPages = numPages;
        this.isbn = isbn;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    /*
     * Setter Cridado porque o jackson não desserealiza esse formato de string para data
     */
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "NewBookRequest{" +
                "title='" + title + '\'' +
                ", resume='" + resume + '\'' +
                ", summary='" + summary + '\'' +
                ", bookPrice=" + bookPrice +
                ", numPages=" + numPages +
                ", isbn='" + isbn + '\'' +
                ", publishDate=" + publishDate +
                ", categoryId=" + categoryId +
                ", authorId=" + authorId +
                '}';
    }

    public Book converterToModel(EntityManager manager) {
        @NotNull Category category = manager.find(Category.class, this.categoryId);
        @NotNull Author author = manager.find(Author.class, this.authorId);


        return new Book(this.title, this.resume, this.summary, this.bookPrice, numPages, this.isbn, this.publishDate, category, author);
    }
}
