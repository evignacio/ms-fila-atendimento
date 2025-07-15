package br.com.fiap.msfilaatendimento.infrastructure.gateway;

import br.com.fiap.msfilaatendimento.core.gateway.UpaGateway;
import br.com.fiap.msfilaatendimento.infrastructure.integration.UpaRestClient;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public record UpaGatewayImpl(UpaRestClient upaRestClient) implements UpaGateway {

    @Override
    public Optional<String> consultUpaName(String upaId) {
        try {
            var response = upaRestClient.findUpa(upaId).getBody();
            if (response == null || response.nickName() == null) {
                return Optional.empty();
            }
            return Optional.of(response.nickName());
        } catch (FeignException exception) {
            if (exception.status() == HttpStatus.NOT_FOUND.value() || exception.status() == HttpStatus.NO_CONTENT.value()) {
                return Optional.empty();
            }
            throw new IllegalArgumentException("Unable to consult the UPA in the external service  ", exception);
        }
    }
}
