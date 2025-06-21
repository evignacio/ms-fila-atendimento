package br.com.fiap.msfilaatendimento.core.usecase;

import br.com.fiap.msfilaatendimento.core.gateway.QueueGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CallPatientsUseCase(QueueGateway queueGateway) {

    public void execute(String ubsId, Integer number) {
        queueGateway.removeNumberFromTriageQueue(ubsId, number);
        queueGateway.requestNumber(ubsId);
    }
}
