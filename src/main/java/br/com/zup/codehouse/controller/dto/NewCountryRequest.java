package br.com.zup.codehouse.controller.dto;

import br.com.zup.codehouse.config.UniqueValue;
import br.com.zup.codehouse.model.Country;

import javax.validation.constraints.NotBlank;

public class NewCountryRequest {

    @NotBlank
    @UniqueValue(domainClass = Country.class, fieldName = "name", message = "Já existe um país cadastrado com esse nome")
    private String name;

    public String getName() {
        return name;
    }

    public Country converterToModel() {
        return new Country(this.name);
    }
}
