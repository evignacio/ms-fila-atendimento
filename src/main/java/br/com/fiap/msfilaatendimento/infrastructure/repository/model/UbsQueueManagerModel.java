package br.com.fiap.msfilaatendimento.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ubsQueueManagers")
public class UbsQueueManagerModel {
    @Id
    private String ubsId;
    private String ubsName;
    private String lastNumber;
    private QueueModel<String> triageQueue;
    private Set<QueueModel<PatientModel>> serviceQueues;
}
