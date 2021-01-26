package br.com.zup.codehouse.model;



import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String title;

    @NotBlank
    @Size(max = 500)
    @Column(nullable = false)
    private String resume;

    @NotBlank
    private String summary;

    @NotNull
    @Min(20)
    @Column(nullable = false)
    private BigDecimal bookPrice;

    @NotNull
    @Min(100)
    @Column(nullable = false)
    private Integer numPages;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String isbn;

    @Future
    @NotNull
    private LocalDate publishDate;

    @Valid
    @NotNull
    @ManyToOne
    private Category category;

    @Valid
    @NotNull
    @ManyToOne
    private Author author;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (title == null) {
            return other.title == null;
        } else return title.equals(other.title);
    }

    @Deprecated
    public Book() {
    }

    public Book(@NotBlank String title, @NotBlank @Size(max = 500) String resume,
                @NotBlank String summary, @NotNull @Min(20) BigDecimal bookPrice,
                @NotNull @Min(100) Integer numPages, @NotBlank String isbn,
                @Future @NotNull LocalDate publishDate, @Valid @NotNull Category category,
                @Valid @NotNull Author author) {
        this.title = title;
        this.resume = resume;
        this.summary = summary;
        this.bookPrice = bookPrice;
        this.numPages = numPages;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.category = category;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", resume='" + resume + '\'' +
                ", summary='" + summary + '\'' +
                ", bookPrice=" + bookPrice +
                ", numPages=" + numPages +
                ", isbn='" + isbn + '\'' +
                ", publishDate=" + publishDate +
                ", category=" + category +
                ", author=" + author +
                '}';
    }

    public Long getId() {
        return id;
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

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }
}

