package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.gateway.UbsQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CallNextPatientToCareUseCase(UbsQueueManagerGateway ubsQueueManagerGateway,
                                           FindUbsQueueManagerUseCase findUbsQueueManagerUseCase) {

    public Patient execute(String ubsId) {
        log.info("Calling next patient to care - ubsId: {}", ubsId);
        var ubsQueueManager = findUbsQueueManagerUseCase.execute(ubsId);
        var nextPatient = ubsQueueManager.getPatientFromServiceQueue();
        ubsQueueManagerGateway.save(ubsQueueManager);
        log.info("Next patient called to care - ubsId: {}, nextPatient: {}", ubsId, nextPatient);
        return nextPatient;
    }
}
