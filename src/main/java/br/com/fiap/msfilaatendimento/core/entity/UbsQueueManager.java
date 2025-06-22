package br.com.fiap.msfilaatendimento.core.entity;

import java.util.Set;

public class UbsQueueManager {
    private String ubsId;
    private String title;
    private Queue<Integer> triageQueue;
    private Set<Queue<Patient>> serviceQueues;

    public UbsQueueManager(String ubsId, String title, Queue<Integer> triageQueue, Set<Queue<Patient>> serviceQueues) {
        setUbsId(ubsId);
        setTitle(title);
        setTriageQueue(triageQueue);
        setServiceQueues(serviceQueues);
    }

    public String getUbsId() {
        return ubsId;
    }

    private void setUbsId(String ubsId) {
        if (ubsId == null || ubsId.isEmpty()) {
            throw new IllegalArgumentException("UBS ID cannot be null or empty");
        }
        this.ubsId = ubsId;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public Queue<Integer> getTriageQueue() {
        return triageQueue;
    }

    private void setTriageQueue(Queue<Integer> triageQueue) {
        if (triageQueue == null) {
            throw new IllegalArgumentException("Triage queue cannot be null");
        }
        this.triageQueue = triageQueue;
    }

    public Set<Queue<Patient>> getServiceQueues() {
        return serviceQueues;
    }

    private void setServiceQueues(Set<Queue<Patient>> serviceQueues) {
        if (serviceQueues == null || serviceQueues.isEmpty()) {
            throw new IllegalArgumentException("Category queues cannot be null or empty");
        }

        if (serviceQueues.size() != EmergencyCategory.values().length) {
            throw new IllegalArgumentException("Service queues must contain one queue for each emergency category");
        }
        this.serviceQueues = serviceQueues;
    }

    public Integer getNextTriageNumber() {
        return triageQueue.getElementsQueue().size() + 1;
    }

    public void addPatientToQueue(EmergencyCategory emergencyCategory, Patient patient) {
        Queue<Patient> queue = serviceQueues.stream()
                .filter(q -> q.getEmergencyCategory() == emergencyCategory)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No queue found for emergency category: " + emergencyCategory));

        queue.getElementsQueue()
                .add(patient);
    }
}
