package br.com.fiap.msfilaatendimento.infrastructure.controller.mapper;

import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.infrastructure.controller.request.PatientRequest;

public abstract class PatientMapper {

    private PatientMapper() {
    }

    public static Patient toEntity(PatientRequest request) {
        return new Patient(request.name(), request.queueNumber());
    }
}
