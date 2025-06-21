package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.gateway.QueueGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record RequestNumberUseCase(QueueGateway queueGateway) {

    public Integer execute(String ubsId) {
        log.info("Requesting number for patient at triage queue - ubsId: {}", ubsId);
        Integer number = queueGateway.requestNumber(ubsId);
        log.info("Number patient requested - number : {}, ubsId {}", number, ubsId);
        return number;
    }
}
