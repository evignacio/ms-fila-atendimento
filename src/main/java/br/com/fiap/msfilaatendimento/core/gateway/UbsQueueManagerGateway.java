package br.com.fiap.msfilaatendimento.core.gateway;

import br.com.fiap.msfilaatendimento.core.entity.UbsQueueManager;

import java.util.Optional;

public interface UbsQueueManagerGateway {
    Optional<UbsQueueManager> find(String ubsId);

    void save(UbsQueueManager ubsQueueManager);
}
