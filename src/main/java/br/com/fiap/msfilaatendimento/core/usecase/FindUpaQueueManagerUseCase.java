package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.entity.UpaQueueManager;
import br.com.fiap.msfilaatendimento.core.gateway.UpaQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record FindUpaQueueManagerUseCase(UpaQueueManagerGateway upaQueueManagerGateway) {

    public UpaQueueManager execute(String upaId) {
        log.info("Finding UPA Queue Manager - upaId: {}", upaId);
        var upaQueueManager = upaQueueManagerGateway.find(upaId)
                .orElseThrow(() -> new IllegalArgumentException("UPA not found with id: " + upaId));
        log.info("UPA Queue Manager found - upaId: {}", upaId);
        return upaQueueManager;
    }
}
