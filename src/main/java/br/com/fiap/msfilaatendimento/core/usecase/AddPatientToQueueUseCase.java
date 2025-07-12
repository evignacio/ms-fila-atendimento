package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.entity.EmergencyCategory;
import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.gateway.UpaQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record AddPatientToQueueUseCase(UpaQueueManagerGateway upaQueueManagerGateway,
                                       FindUpaQueueManagerUseCase findUpaQueueManagerUseCase) {

    public void execute(String upaId, EmergencyCategory emergencyCategory, Patient patient) {
        log.info("Adding  patient to queue - upaId: {},  emergency category: {}, patient name: {}", upaId, emergencyCategory.name(), patient.getName());
        var upaQueueManager = findUpaQueueManagerUseCase.execute(upaId);
        upaQueueManager.addPatientToQueue(emergencyCategory, patient);
        upaQueueManagerGateway.save(upaQueueManager);
        log.info("Patient added to queue - upaId: {}, emergency category: {},  patient name: {}", upaId, emergencyCategory.name(), patient.getName());
    }
}
