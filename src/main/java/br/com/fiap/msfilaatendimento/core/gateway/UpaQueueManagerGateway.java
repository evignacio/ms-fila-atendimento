package br.com.fiap.msfilaatendimento.core.gateway;

import br.com.fiap.msfilaatendimento.core.entity.UpaQueueManager;

import java.util.Optional;

public interface UpaQueueManagerGateway {
    Optional<UpaQueueManager> find(String upaId);

    void save(UpaQueueManager upaQueueManager);
}
