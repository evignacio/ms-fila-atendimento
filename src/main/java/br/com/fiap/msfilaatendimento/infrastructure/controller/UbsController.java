package br.com.fiap.msfilaatendimento.infrastructure.controller;

import br.com.fiap.msfilaatendimento.core.usecase.GenerateQueuesUbsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ubs")
public record UbsController(GenerateQueuesUbsUseCase generateQueuesUbsUseCase) {

    @PostMapping("/{ubsId}/generate-queues")
    public ResponseEntity<Void> generateQueues(@PathVariable String ubsId) {
        generateQueuesUbsUseCase.execute(ubsId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
