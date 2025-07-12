package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.factory.UpaQueueManagerFactory;
import br.com.fiap.msfilaatendimento.core.gateway.UpaGateway;
import br.com.fiap.msfilaatendimento.core.gateway.UpaQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record GenerateQueuesUpaUseCase(UpaQueueManagerGateway upaQueueManagerGateway,
                                       UpaGateway upaGateway) {

    public void execute(String upaId) {
        log.info("Generating queues for UPA - upaId: {}", upaId);
        var upaName = upaGateway.consultUpaName(upaId)
                .orElseThrow(() -> new IllegalArgumentException("Upa not found in external service -  id: " + upaId));

        var upaQueueManagerOpt = upaQueueManagerGateway.find(upaId);

        if (upaQueueManagerOpt.isPresent()) {
            throw new IllegalArgumentException("Queues already generated for UPA - upaId: " + upaId);
        }

        var upaQueueManager = UpaQueueManagerFactory.create(upaId, upaName);
        upaQueueManagerGateway.save(upaQueueManager);
        log.info("Queues generated for UPA - upaId: {}", upaId);
    }
}
