package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.entity.EmergencyCategory;
import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.gateway.QueueGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record AddPatientToQueueUseCase(QueueGateway queueGateway) {

    public void execute(String ubsId, EmergencyCategory emergencyCategory, Patient patient) {
        log.info("Adding  patient to queue - ubsId: {},  queue category: {}, patient name: {}", ubsId, emergencyCategory.name(), patient.getName());
        queueGateway.removeNumberFromTriageQueue(ubsId, patient.getQueueNumber());
        queueGateway.addPatientToQueue(ubsId, emergencyCategory, patient);
        log.info("Patient added to queue - ubsId: {}, queue category: {},  patient name: {}", ubsId, emergencyCategory.name(), patient.getName());
    }
}
