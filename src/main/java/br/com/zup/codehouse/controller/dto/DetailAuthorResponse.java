package br.com.zup.codehouse.controller.dto;

import br.com.zup.codehouse.model.Author;

public class DetailAuthorResponse {

    private String name;
    private String description;

    public DetailAuthorResponse(Author author) {
        this.name = author.getName();
        this.description = author.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
