package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.gateway.UbsQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record RequestNewQueueNumberUseCase(UbsQueueManagerGateway ubsQueueManagerGateway,
                                           FindUbsQueueManagerUseCase findUbsQueueManagerUseCase) {

    public String execute(String ubsId) {
        log.info("Requesting number for patient - ubsId: {}", ubsId);
        var ubsQueueManager = findUbsQueueManagerUseCase.execute(ubsId);
        String number = ubsQueueManager.getNextTriageNumber();
        ubsQueueManagerGateway.save(ubsQueueManager);
        log.info("Number patient requested - number : {}, ubsId {}", number, ubsId);
        return number;
    }
}
