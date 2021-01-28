package br.com.zup.codehouse.config;

import br.com.zup.codehouse.controller.dto.NewPurchaseRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class checksDocumentCpfCnpjValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NewPurchaseRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors())
            return;

        NewPurchaseRequest request = (NewPurchaseRequest) target;
        if(!request.isValidDocument()){
            errors.rejectValue("document", null, "Documento precisa ser um CPF ou CNPJ válido");
        }
    }
}
