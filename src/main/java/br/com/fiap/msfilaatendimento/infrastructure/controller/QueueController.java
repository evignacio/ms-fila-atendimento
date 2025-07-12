package br.com.fiap.msfilaatendimento.infrastructure.controller;

import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.entity.QueuesDetails;
import br.com.fiap.msfilaatendimento.core.usecase.*;
import br.com.fiap.msfilaatendimento.infrastructure.controller.mapper.PatientMapper;
import br.com.fiap.msfilaatendimento.infrastructure.controller.request.AddPatientToQueueRequest;
import br.com.fiap.msfilaatendimento.infrastructure.controller.response.PatientNumberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queues")
public record QueueController(RequestNewQueueNumberUseCase requestNewQueueNumberUseCase,
                              CallNextPatientToTriageUseCase callNextPatientToTriageUseCase,
                              CallNextPatientToCareUseCase callNextPatientToCareUseCase,
                              AddPatientToQueueUseCase addPatientToQueueUseCase,
                              GetQueuesDetailsUseCase getQueuesDetailsUseCase) {

    @GetMapping("/upa/{upaId}")
    public ResponseEntity<QueuesDetails> getQueuesDetails(@PathVariable String upaId) {
        var queuesDetails = getQueuesDetailsUseCase.execute(upaId);
        return new ResponseEntity<>(queuesDetails, HttpStatus.OK);
    }

    @PostMapping("/upa/{upaId}/new-number")
    ResponseEntity<PatientNumberResponse> requestNewNumber(@PathVariable String upaId) {
        var number = requestNewQueueNumberUseCase.execute(upaId);
        return new ResponseEntity<>(new PatientNumberResponse(number), HttpStatus.CREATED);
    }

    @PostMapping("/triage/upa/{upaId}/call-next")
    ResponseEntity<PatientNumberResponse> callNextTriage(@PathVariable String upaId) {
        var number = callNextPatientToTriageUseCase.execute(upaId);
        return new ResponseEntity<>(new PatientNumberResponse(number), HttpStatus.OK);
    }

    @PostMapping("/service/upa/{upaId}/call-next")
    ResponseEntity<Patient> callNextService(@PathVariable String upaId) {
        var patient = callNextPatientToCareUseCase.execute(upaId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/upa/{upaId}/add-patient")
    ResponseEntity<Void> addPatientToQueue(@PathVariable String upaId, @RequestBody AddPatientToQueueRequest request) {
        addPatientToQueueUseCase.execute(upaId, request.emergencyCategory(), PatientMapper.toEntity(request.patient()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
