package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.gateway.UbsQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record RequestNumberUseCase(UbsQueueManagerGateway ubsQueueManagerGateway,
                                   FindUbsQueueManagerUseCase findUbsQueueManagerUseCase) {

    public Integer execute(String ubsId) {
        log.info("Requesting number for patient - ubsId: {}", ubsId);
        var ubsQueueManager = findUbsQueueManagerUseCase.execute(ubsId);
        Integer number = ubsQueueManager.getNextTriageNumber();
        log.info("Number patient requested - number : {}, ubsId {}", number, ubsId);
        return number;
    }
}
