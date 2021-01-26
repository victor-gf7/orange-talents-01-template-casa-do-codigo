package br.com.zup.codehouse.config;

import br.com.zup.codehouse.controller.dto.NewPurchaseRequest;
import br.com.zup.codehouse.model.Country;
import br.com.zup.codehouse.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class StateBelongsToCountry implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return NewPurchaseRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors())
            return;

        NewPurchaseRequest request = (NewPurchaseRequest) target;

        Country country = manager.find(Country.class, request.getIdCountry());
        State state = manager.find(State.class, request.getIdState());

        if(!state.belongs(country)){
            errors.rejectValue("idState", null, "Este estado não é do país selecionado");
        }

    }
}
