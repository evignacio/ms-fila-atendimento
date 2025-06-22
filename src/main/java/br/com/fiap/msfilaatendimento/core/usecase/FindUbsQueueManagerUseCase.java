package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.entity.UbsQueueManager;
import br.com.fiap.msfilaatendimento.core.gateway.UbsQueueManagerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record FindUbsQueueManagerUseCase(UbsQueueManagerGateway ubsQueueManagerGateway) {

    public UbsQueueManager execute(String ubsId) {
        log.info("Finding UBS Queue Manager - ubsId: {}", ubsId);
        var ubsQueueManager = ubsQueueManagerGateway.find(ubsId)
                .orElseThrow(() -> new IllegalArgumentException("UBS not found with id: " + ubsId));
        log.info("UBS Queue Manager found - ubsId: {}", ubsId);
        return ubsQueueManager;
    }
}
