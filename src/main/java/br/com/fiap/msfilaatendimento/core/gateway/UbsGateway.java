package br.com.fiap.msfilaatendimento.core.gateway;

import java.util.Optional;

public interface UbsGateway {
    Optional<String> consultUbsName(String ubsId);
}
