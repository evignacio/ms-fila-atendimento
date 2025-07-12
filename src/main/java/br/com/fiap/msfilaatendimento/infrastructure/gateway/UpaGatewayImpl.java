package br.com.fiap.msfilaatendimento.infrastructure.gateway;

import br.com.fiap.msfilaatendimento.core.gateway.UpaGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public record UpaGatewayImpl() implements UpaGateway {

    @Override
    public Optional<String> consultUpaName(String upaId) {
        return Optional.of("UPA legal");
    }
}
