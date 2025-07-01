package br.com.fiap.msfilaatendimento.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueModel<T> {
    private String id;
    private String title;
    private String description;
    private String emergencyCategory;
    private java.util.Queue<T> elementsQueue;
}
