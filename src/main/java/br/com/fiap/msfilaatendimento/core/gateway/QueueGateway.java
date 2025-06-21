package br.com.fiap.msfilaatendimento.core.gateway;

import br.com.fiap.msfilaatendimento.core.entity.EmergencyCategory;
import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.entity.Queue;

import java.util.Set;

public interface QueueGateway {
    Set<Queue> findAll(String ubsId);

    Integer requestNumber(String ubsId);

    void removeNumberFromTriageQueue(String ubsId, Integer number);

    void addPatientToQueue(String ubsId, EmergencyCategory emergencyCategory, Patient patient);

    void removePatientFromQueue(String ubsId, EmergencyCategory emergencyCategory, Patient patient);
}
