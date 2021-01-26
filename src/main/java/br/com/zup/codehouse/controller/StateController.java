package br.com.zup.codehouse.controller;


import br.com.zup.codehouse.controller.dto.NewStateRequest;
import br.com.zup.codehouse.model.State;
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
@RequestMapping("/state")
public class StateController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<State> registerState(@RequestBody @Valid NewStateRequest request){

        State state = request.converterToModel(manager);

        manager.persist(state);

        return ResponseEntity.ok(state);
    }
}
