package br.com.fiap.msfilaatendimento.infrastructure.repository.mapper;

import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.entity.Queue;
import br.com.fiap.msfilaatendimento.core.entity.UbsQueueManager;
import br.com.fiap.msfilaatendimento.infrastructure.repository.model.PatientModel;
import br.com.fiap.msfilaatendimento.infrastructure.repository.model.QueueModel;
import br.com.fiap.msfilaatendimento.infrastructure.repository.model.UbsQueueManagerModel;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class UbsQueueManagerMapper {

    private UbsQueueManagerMapper() {
    }

    public static UbsQueueManager toEntity(UbsQueueManagerModel model) {
        Queue<String> triageQueue = QueueMapper.toEntity(model.getTriageQueue(), e -> e);

        Set<Queue<Patient>> serviceQueues = model.getServiceQueues()
                .stream()
                .map(queueModel -> QueueMapper.toEntity(queueModel, PatientMapper::toEntity))
                .collect(Collectors.toSet());

        return new UbsQueueManager(
                model.getUbsId(),
                model.getUbsName(),
                model.getLastNumber(),
                triageQueue,
                serviceQueues
        );
    }

    public static UbsQueueManagerModel toModel(UbsQueueManager entity) {
        QueueModel<String> triageQueueModel = QueueMapper.toModel(entity.getTriageQueue(), e -> e);

        Set<QueueModel<PatientModel>> serviceQueuesModel = entity.getServiceQueues()
                .stream()
                .map(queueModel -> QueueMapper.toModel(queueModel, PatientMapper::toModel))
                .collect(Collectors.toSet());

        return UbsQueueManagerModel.builder()
                .ubsId(entity.getUbsId())
                .ubsName(entity.getUbsName())
                .lastNumber(entity.getLastGenerateNumber())
                .triageQueue(triageQueueModel)
                .serviceQueues(serviceQueuesModel)
                .build();
    }
}
