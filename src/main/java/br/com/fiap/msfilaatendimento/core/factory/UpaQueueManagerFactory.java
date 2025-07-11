package br.com.fiap.msfilaatendimento.core.factory;

import br.com.fiap.msfilaatendimento.core.entity.EmergencyCategory;
import br.com.fiap.msfilaatendimento.core.entity.Patient;
import br.com.fiap.msfilaatendimento.core.entity.Queue;
import br.com.fiap.msfilaatendimento.core.entity.UpaQueueManager;

import java.util.Set;

public abstract class UpaQueueManagerFactory {

    private UpaQueueManagerFactory() {
    }

    public static UpaQueueManager create(String upaId, String upaName) {
        return new UpaQueueManager(upaId, upaName, createTriageQueue(), createCategoryQueues());
    }

    private static Queue<String> createTriageQueue() {
        return new Queue<>("Triage Queue", "Queue for triaging patients");
    }

    private static Set<Queue<Patient>> createCategoryQueues() {
        return Set.of(
                new Queue<>("Not Urgent", "Patients with non-urgent conditions", EmergencyCategory.NOT_URGENT),
                new Queue<>("Little Urgent", "Patients with minor urgent conditions", EmergencyCategory.LITTLE_URGENT),
                new Queue<>("Urgent", "Patients with urgent conditions", EmergencyCategory.URGENT),
                new Queue<>("Very Urgent", "Patients with very urgent conditions", EmergencyCategory.VERY_URGENT),
                new Queue<>("Emergency", "Patients requiring immediate attention", EmergencyCategory.EMERGENCY)
        );
    }
}
