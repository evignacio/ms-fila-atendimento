package br.com.fiap.msfilaatendimento.infrastructure.gateway;

import br.com.fiap.msfilaatendimento.core.gateway.UbsGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public record UbsGatewayImpl() implements UbsGateway {

    @Override
    public Optional<String> consultUbsName(String ubsId) {
        return Optional.of("UBS legal");
    }
}
