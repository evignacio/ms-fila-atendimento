package br.com.fiap.msfilaatendimento.infrastructure.controller;

import br.com.fiap.msfilaatendimento.core.usecase.CallNextPatientToTriageUseCase;
import br.com.fiap.msfilaatendimento.core.usecase.RequestNewQueueNumberUseCase;
import br.com.fiap.msfilaatendimento.infrastructure.controller.response.PatientNumberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queue")
public record QueueController(RequestNewQueueNumberUseCase requestNewQueueNumberUseCase,
                              CallNextPatientToTriageUseCase callNextPatientToTriageUseCase) {

    @PostMapping("/ubs/{ubsId}/new-number")
    ResponseEntity<PatientNumberResponse> requestNewNumber(@PathVariable String ubsId) {
        var number = requestNewQueueNumberUseCase.execute(ubsId);
        return new ResponseEntity<>(new PatientNumberResponse(number), HttpStatus.CREATED);
    }

    @PostMapping("/ubs/{ubsId}/call-next-triage")
    ResponseEntity<PatientNumberResponse> callNextTriage(@PathVariable String ubsId) {
        var number = callNextPatientToTriageUseCase.execute(ubsId);
        return new ResponseEntity<>(new PatientNumberResponse(number), HttpStatus.OK);
    }
}
