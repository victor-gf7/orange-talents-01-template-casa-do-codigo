package br.com.zup.codehouse.controller.dto;

import br.com.zup.codehouse.config.UniqueValue;
import br.com.zup.codehouse.model.Category;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;

    public String getName() {
        return name;
    }

    public Category converterToModel() {
        return new Category(this.name);
    }
}
