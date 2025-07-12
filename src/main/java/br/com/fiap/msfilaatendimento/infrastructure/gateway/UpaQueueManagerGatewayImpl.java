package br.com.fiap.msfilaatendimento.infrastructure.gateway;

import br.com.fiap.msfilaatendimento.core.entity.UpaQueueManager;
import br.com.fiap.msfilaatendimento.core.gateway.UpaQueueManagerGateway;
import br.com.fiap.msfilaatendimento.infrastructure.repository.UpaQueueManagerRepository;
import br.com.fiap.msfilaatendimento.infrastructure.repository.mapper.UpaQueueManagerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public record UpaQueueManagerGatewayImpl(UpaQueueManagerRepository upaQueueManagerRepository)
        implements UpaQueueManagerGateway {

    @Override
    public Optional<UpaQueueManager> find(String upaId) {
        return upaQueueManagerRepository.findById(upaId)
                .map(UpaQueueManagerMapper::toEntity);
    }

    @Override
    public void save(UpaQueueManager upaQueueManager) {
        upaQueueManagerRepository.save(UpaQueueManagerMapper.toModel(upaQueueManager));
    }
}
