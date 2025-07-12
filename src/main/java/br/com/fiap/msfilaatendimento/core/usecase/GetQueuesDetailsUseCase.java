package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.entity.QueuesDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record GetQueuesDetailsUseCase(FindUpaQueueManagerUseCase findUpaQueueManagerUseCase) {

    public QueuesDetails execute(String upaId) {
        log.info("Retrieving queues details for UPA - upaId: {}", upaId);
        var upaQueueManager = findUpaQueueManagerUseCase.execute(upaId);
        var queuesDetails = upaQueueManager.toQueuesDetails();
        log.info("Queues details retrieved for UPA - upaId: {}", upaId);
        return queuesDetails;
    }
}
