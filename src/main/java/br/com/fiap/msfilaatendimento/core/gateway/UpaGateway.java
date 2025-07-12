package br.com.fiap.msfilaatendimento.core.gateway;

import java.util.Optional;

public interface UpaGateway {
    Optional<String> consultUpaName(String upaId);
}
