package br.com.fiap.msfilaatendimento.infrastructure.repository.mapper;

import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.infrastructure.repository.model.PatientModel;

public abstract class PatientMapper {

    private PatientMapper() {
    }

    public static Patient toEntity(PatientModel model) {
        return new Patient(model.getId(), model.getName(), model.getQueueNumber());
    }

    public static PatientModel toModel(Patient entity) {
        return PatientModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .queueNumber(entity.getQueueNumber())
                .build();
    }
}
