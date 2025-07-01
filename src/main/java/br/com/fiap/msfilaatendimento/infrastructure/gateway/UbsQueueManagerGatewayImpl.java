package br.com.fiap.msfilaatendimento.infrastructure.gateway;

import br.com.fiap.msfilaatendimento.core.entity.UbsQueueManager;
import br.com.fiap.msfilaatendimento.core.gateway.UbsQueueManagerGateway;
import br.com.fiap.msfilaatendimento.infrastructure.repository.UbsQueueManagerRepository;
import br.com.fiap.msfilaatendimento.infrastructure.repository.mapper.UbsQueueManagerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public record UbsQueueManagerGatewayImpl(UbsQueueManagerRepository ubsQueueManagerRepository)
        implements UbsQueueManagerGateway {

    @Override
    public Optional<UbsQueueManager> find(String ubsId) {
        return ubsQueueManagerRepository.findById(ubsId)
                .map(UbsQueueManagerMapper::toEntity);
    }

    @Override
    public void save(UbsQueueManager ubsQueueManager) {
        ubsQueueManagerRepository.save(UbsQueueManagerMapper.toModel(ubsQueueManager));
    }
}
