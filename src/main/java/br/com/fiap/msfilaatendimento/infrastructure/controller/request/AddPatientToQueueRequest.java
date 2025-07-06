package br.com.fiap.msfilaatendimento.infrastructure.controller.request;

import br.com.fiap.msfilaatendimento.core.entity.EmergencyCategory;

public record AddPatientToQueueRequest(PatientRequest patient, EmergencyCategory emergencyCategory) {
}
