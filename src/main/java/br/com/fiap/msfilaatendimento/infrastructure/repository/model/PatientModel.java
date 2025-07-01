package br.com.fiap.msfilaatendimento.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientModel {
    private String id;
    private String name;
    private String queueNumber;
}
