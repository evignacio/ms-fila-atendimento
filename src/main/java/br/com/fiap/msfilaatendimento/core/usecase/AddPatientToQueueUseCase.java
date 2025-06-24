package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.entity.EmergencyCategory;
import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.gateway.UbsQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record AddPatientToQueueUseCase(UbsQueueManagerGateway ubsQueueManagerGateway,
                                       FindUbsQueueManagerUseCase findUbsQueueManagerUseCase) {

    public void execute(String ubsId, EmergencyCategory emergencyCategory, Patient patient) {
        log.info("Adding  patient to queue - ubsId: {},  emergency category: {}, patient name: {}", ubsId, emergencyCategory.name(), patient.getName());
        var ubsQueueManager = findUbsQueueManagerUseCase.execute(ubsId);
        ubsQueueManager.addPatientToQueue(emergencyCategory, patient);
        ubsQueueManagerGateway.save(ubsQueueManager);
        log.info("Patient added to queue - ubsId: {}, emergency category: {},  patient name: {}", ubsId, emergencyCategory.name(), patient.getName());
    }
}
