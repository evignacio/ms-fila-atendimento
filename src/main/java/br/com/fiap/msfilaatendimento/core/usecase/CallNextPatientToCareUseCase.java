package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.gateway.UpaQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CallNextPatientToCareUseCase(UpaQueueManagerGateway upaQueueManagerGateway,
                                           FindUpaQueueManagerUseCase findUpaQueueManagerUseCase) {

    public Patient execute(String upaId) {
        log.info("Calling next patient to care - upaId: {}", upaId);
        var upaQueueManager = findUpaQueueManagerUseCase.execute(upaId);
        var nextPatient = upaQueueManager.getPatientFromServiceQueue();
        upaQueueManagerGateway.save(upaQueueManager);
        log.info("Next patient called to care - upaId: {}, nextPatient: {}", upaId, nextPatient);
        return nextPatient;
    }
}
