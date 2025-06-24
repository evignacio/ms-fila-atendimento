package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.gateway.UbsQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CallNextPatientToTriageUseCase(UbsQueueManagerGateway ubsQueueManagerGateway,
                                             FindUbsQueueManagerUseCase findUbsQueueManagerUseCase) {

    public String execute(String ubsId) {
        log.info("Calling next patient to triage - ubsId: {}", ubsId);
        var ubsQueueManager = findUbsQueueManagerUseCase.execute(ubsId);
        var nextPatientNumber = ubsQueueManager.getTriageQueue()
                .getElementsQueue()
                .poll();

        if (nextPatientNumber == null) {
            log.warn("No patients in the triage queue for UBS - ubsId: {}", ubsId);
            throw new IllegalStateException("The ubs request, has no patient in the triage queue - ubsId: " + ubsId);
        }
        log.info("Next patient called to triage - ubsId: {}, nextPatientNumber: {}", ubsId, nextPatientNumber);
        return nextPatientNumber;
    }
}
