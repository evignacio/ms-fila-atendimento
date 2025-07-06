package br.com.fiap.msfilaatendimento.infrastructure.controller;

import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.usecase.AddPatientToQueueUseCase;
import br.com.fiap.msfilaatendimento.core.usecase.CallNextPatientToCareUseCase;
import br.com.fiap.msfilaatendimento.core.usecase.CallNextPatientToTriageUseCase;
import br.com.fiap.msfilaatendimento.core.usecase.RequestNewQueueNumberUseCase;
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
                              AddPatientToQueueUseCase addPatientToQueueUseCase) {

    @PostMapping("/ubs/{ubsId}/new-number")
    ResponseEntity<PatientNumberResponse> requestNewNumber(@PathVariable String ubsId) {
        var number = requestNewQueueNumberUseCase.execute(ubsId);
        return new ResponseEntity<>(new PatientNumberResponse(number), HttpStatus.CREATED);
    }

    @PostMapping("/triage/ubs/{ubsId}/call-next")
    ResponseEntity<PatientNumberResponse> callNextTriage(@PathVariable String ubsId) {
        var number = callNextPatientToTriageUseCase.execute(ubsId);
        return new ResponseEntity<>(new PatientNumberResponse(number), HttpStatus.OK);
    }

    @PostMapping("/service/ubs/{ubsId}/call-next")
    ResponseEntity<Patient> callNextService(@PathVariable String ubsId) {
        var patient = callNextPatientToCareUseCase.execute(ubsId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/ubs/{ubsId}/add-patient")
    ResponseEntity<Void> addPatientToQueue(@PathVariable String ubsId, @RequestBody AddPatientToQueueRequest request) {
        addPatientToQueueUseCase.execute(ubsId, request.emergencyCategory(), PatientMapper.toEntity(request.patient()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
