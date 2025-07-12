package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.gateway.UpaQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CallNextPatientToTriageUseCase(UpaQueueManagerGateway upaQueueManagerGateway,
                                             FindUpaQueueManagerUseCase findUpaQueueManagerUseCase) {

    public String execute(String upaId) {
        log.info("Calling next patient to triage - upaId: {}", upaId);
        var upaQueueManager = findUpaQueueManagerUseCase.execute(upaId);
        var nextPatientNumber = upaQueueManager.getNextNumberFromTriageQueue();
        upaQueueManagerGateway.save(upaQueueManager);
        log.info("Next patient called to triage - upaId: {}, nextPatientNumber: {}", upaId, nextPatientNumber);
        return nextPatientNumber;
    }
}
