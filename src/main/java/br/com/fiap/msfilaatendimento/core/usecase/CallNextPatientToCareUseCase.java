package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.gateway.UbsQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CallNextPatientToCareUseCase(UbsQueueManagerGateway ubsQueueManagerGateway,
                                           FindUbsQueueManagerUseCase findUbsQueueManagerUseCase) {

    public String execute(String ubsId) {
        log.info("Calling next patient to care - ubsId: {}", ubsId);
        var ubsQueueManager = findUbsQueueManagerUseCase.execute(ubsId);
        var nextPatientNumber = ubsQueueManager.getNextNumberFromCareQueue();
        log.info("Next patient called to care - ubsId: {}, nextPatientNumber: {}", ubsId, nextPatientNumber);
        return nextPatientNumber;
    }
}
