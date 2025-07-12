package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.gateway.UpaQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record RequestNewQueueNumberUseCase(UpaQueueManagerGateway upaQueueManagerGateway,
                                           FindUpaQueueManagerUseCase findUpaQueueManagerUseCase) {

    public String execute(String upaId) {
        log.info("Requesting number for patient - upaId: {}", upaId);
        var upaQueueManager = findUpaQueueManagerUseCase.execute(upaId);
        String number = upaQueueManager.generateNextTriageNumber();
        upaQueueManagerGateway.save(upaQueueManager);
        log.info("Number patient requested - number : {}, upaId {}", number, upaId);
        return number;
    }
}
