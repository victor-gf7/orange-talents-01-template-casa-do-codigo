package br.com.zup.codehouse.controller.dto;

import br.com.zup.codehouse.config.ExistsId;
import br.com.zup.codehouse.config.UniqueValue;
import br.com.zup.codehouse.model.Country;
import br.com.zup.codehouse.model.State;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewStateRequest {

    @NotBlank
    @UniqueValue(domainClass = State.class, fieldName = "name", message = "Ja existe um estado com este nome cadastrado")
    private String name;

    @NotNull
    @ExistsId(domainClass = Country.class, fieldName = "id", message = "Não existe país com este Id informado")
    private Long idCountry;

    public State converterToModel(EntityManager manager) {
        @NotNull Country country = manager.find(Country.class, idCountry);

        return new State(this.name, country);
    }

    public String getName() {
        return name;
    }

    public Long getIdCountry() {
        return idCountry;
    }
}
