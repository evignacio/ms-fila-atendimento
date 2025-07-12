package br.com.fiap.msfilaatendimento.infrastructure.repository.mapper;

import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.entity.Queue;
import br.com.fiap.msfilaatendimento.core.entity.UpaQueueManager;
import br.com.fiap.msfilaatendimento.infrastructure.repository.model.PatientModel;
import br.com.fiap.msfilaatendimento.infrastructure.repository.model.QueueModel;
import br.com.fiap.msfilaatendimento.infrastructure.repository.model.UpaQueueManagerModel;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class UpaQueueManagerMapper {

    private UpaQueueManagerMapper() {
    }

    public static UpaQueueManager toEntity(UpaQueueManagerModel model) {
        Queue<String> triageQueue = QueueMapper.toEntity(model.getTriageQueue(), e -> e);

        Set<Queue<Patient>> serviceQueues = model.getServiceQueues()
                .stream()
                .map(queueModel -> QueueMapper.toEntity(queueModel, PatientMapper::toEntity))
                .collect(Collectors.toSet());

        return new UpaQueueManager(
                model.getUpaId(),
                model.getUpaName(),
                model.getLastNumber(),
                triageQueue,
                serviceQueues
        );
    }

    public static UpaQueueManagerModel toModel(UpaQueueManager entity) {
        QueueModel<String> triageQueueModel = QueueMapper.toModel(entity.getTriageQueue(), e -> e);

        Set<QueueModel<PatientModel>> serviceQueuesModel = entity.getServiceQueues()
                .stream()
                .map(queueModel -> QueueMapper.toModel(queueModel, PatientMapper::toModel))
                .collect(Collectors.toSet());

        return UpaQueueManagerModel.builder()
                .upaId(entity.getUpaId())
                .upaName(entity.getUpaName())
                .lastNumber(entity.getLastGenerateNumber())
                .triageQueue(triageQueueModel)
                .serviceQueues(serviceQueuesModel)
                .build();
    }
}
