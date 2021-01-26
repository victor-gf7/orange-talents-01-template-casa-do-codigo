package br.com.zup.codehouse.controller;


import br.com.zup.codehouse.config.StateBelongsToCountry;
import br.com.zup.codehouse.config.checksDocumentCpfCnpjValidator;
import br.com.zup.codehouse.controller.dto.NewPurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/buy")
public class ClosesPurchase {

    @Autowired
    private StateBelongsToCountry stateBelongsToCountry;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(new checksDocumentCpfCnpjValidator(), stateBelongsToCountry);
    }

    @PostMapping
    public String create(@RequestBody @Valid NewPurchaseRequest request){

        return request.toString();

    }
}
