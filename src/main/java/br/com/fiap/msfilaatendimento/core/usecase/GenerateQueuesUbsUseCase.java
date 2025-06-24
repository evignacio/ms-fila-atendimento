package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.factory.UbsQueueManagerFactory;
import br.com.fiap.msfilaatendimento.core.gateway.UbsGateway;
import br.com.fiap.msfilaatendimento.core.gateway.UbsQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record GenerateQueuesUbsUseCase(UbsQueueManagerGateway ubsQueueManagerGateway,
                                       UbsGateway ubsGateway) {

    public void execute(String ubsId) {
        log.info("Generating queues for UBS - ubsId: {}", ubsId);
        var ubsName = ubsGateway.consultUbsName(ubsId)
                .orElseThrow(() -> new IllegalArgumentException("Ubs not found in external service -  id: " + ubsId));

        var ubsQueueManagerOpt = ubsQueueManagerGateway.find(ubsId);

        if (ubsQueueManagerOpt.isPresent()) {
            throw new IllegalArgumentException("Queues already generated for UBS - ubsId: " + ubsId);
        }

        var ubsQueueManager = UbsQueueManagerFactory.create(ubsId, ubsName);
        ubsQueueManagerGateway.save(ubsQueueManager);
        log.info("Queues generated for UBS - ubsId: {}", ubsId);
    }
}
