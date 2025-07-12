package br.com.fiap.msfilaatendimento.infrastructure.controller;

import br.com.fiap.msfilaatendimento.core.usecase.GenerateQueuesUpaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upa")
public record UpaController(GenerateQueuesUpaUseCase generateQueuesUpaUseCase) {

    @PostMapping("/{upaId}/generate-queues")
    public ResponseEntity<Void> generateQueues(@PathVariable String upaId) {
        generateQueuesUpaUseCase.execute(upaId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
