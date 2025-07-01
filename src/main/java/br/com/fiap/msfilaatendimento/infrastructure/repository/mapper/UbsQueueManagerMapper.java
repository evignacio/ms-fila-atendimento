package br.com.fiap.msfilaatendimento.infrastructure.repository.mapper;

import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.entity.Queue;
import br.com.fiap.msfilaatendimento.core.entity.UbsQueueManager;
import br.com.fiap.msfilaatendimento.infrastructure.repository.model.UbsQueueManagerModel;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class UbsQueueManagerMapper {

    private UbsQueueManagerMapper() {
    }

  public static UbsQueueManager toEntity(UbsQueueManagerModel model) {
      Set<Queue<Patient>> serviceQueues = model.getServiceQueues()
              .stream()
              .map(queueModel -> QueueMapper.toEntity(queueModel, PatientMapper::toEntity))
              .collect(Collectors.toSet());

      Queue<String> triageQueue = QueueMapper.toEntity(model.getTriageQueue(), e -> e );

      return new UbsQueueManager(
              model.getUbsId(),
              model.getUbsName(),
              model.getLastNumber(),
              triageQueue,
              serviceQueues
      );
  }

    public static UbsQueueManagerModel toModel(UbsQueueManager entity) {
        return UbsQueueManagerModel.builder()
                .ubsId(entity.getUbsId())
                .ubsName(entity.getUbsName())
                .lastNumber(entity.getLastNumber())
                .build();
    }
}
