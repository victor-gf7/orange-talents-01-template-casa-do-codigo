package br.com.zup.codehouse.controller;

import br.com.zup.codehouse.controller.dto.NewCountryRequest;
import br.com.zup.codehouse.model.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/country")
public class CountryController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Country> registerCountry(@RequestBody @Valid NewCountryRequest request){

        Country country = request.converterToModel();

        manager.persist(country);

        return ResponseEntity.ok(country);
    }
}
