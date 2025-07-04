package br.com.fiap.msfilaatendimento.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueModel<T> {
    private String id;
    private String title;
    private String description;
    private String emergencyCategory;
    private LinkedHashSet<T> elementsQueue;
}
