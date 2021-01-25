package br.com.zup.codehouse.controller.dto;

import br.com.zup.codehouse.config.UniqueValue;
import br.com.zup.codehouse.model.Author;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewAuthorRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Author.class, fieldName = "email", message = "Ja existe um(a) autor(a) cadastrado com esse email")
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 400)
    private String description;

    public NewAuthorRequest(@NotBlank @Email String email, @NotBlank String name, @NotBlank @Size(max = 400) String description) {
        this.email = email;
        this.name = name;
        this.description = description;
    }

    public Author convertToModel() {
        return new Author(this.email, this.name, this.description);
    }

    public String getEmail() {
        return email;
    }
}
