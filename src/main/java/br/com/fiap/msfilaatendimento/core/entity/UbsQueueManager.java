package br.com.fiap.msfilaatendimento.core.entity;

import java.util.Set;

public class UbsQueueManager {
    private String ubsId;
    private String ubsName;
    private String lastNumber;
    private Queue<String> triageQueue;
    private Set<Queue<Patient>> serviceQueues;

    public UbsQueueManager(String ubsId, String ubsName, String lastNumber, Queue<String> triageQueue, Set<Queue<Patient>> serviceQueues) {
        setUbsId(ubsId);
        setUbsName(ubsName);
        setLastNumber(lastNumber);
        setTriageQueue(triageQueue);
        setServiceQueues(serviceQueues);
    }

    public UbsQueueManager(String ubsId, String ubsName, Queue<String> triageQueue, Set<Queue<Patient>> serviceQueues) {
        setUbsId(ubsId);
        setUbsName(ubsName);
        setLastNumber(createQueueNumber('A', 0, 0));
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

    public String getUbsName() {
        return ubsName;
    }

    private void setUbsName(String ubsName) {
        if (ubsName == null || ubsName.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.ubsName = ubsName;
    }

    public String getLastNumber() {
        return lastNumber;
    }

    private void setLastNumber(String lastNumber) {
        if (lastNumber == null || lastNumber.isEmpty()) {
            throw new IllegalArgumentException("Last number cannot be null or empty");
        }
        this.lastNumber = lastNumber;
    }

    public Queue<String> getTriageQueue() {
        return triageQueue;
    }

    private void setTriageQueue(Queue<String> triageQueue) {
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

    public String getNextTriageNumber() {
        var delimiter = "-";
        var lastNumberSplit = lastNumber.split(delimiter);
        var number = Integer.parseInt(lastNumberSplit[1]);
        var letterPrefix = lastNumberSplit[0].charAt(0);
        var numberPrefix = Integer.parseInt(lastNumberSplit[0].substring(1));
        var nextNumber = "";

        switch (number) {
            case 0 -> number = 1;
            case 9999 -> {
                letterPrefix++;
                number = 1;
                numberPrefix = letterPrefix == 'A' ? numberPrefix + 1 : numberPrefix;
            }
            default -> number++;
        }

        nextNumber = createQueueNumber(letterPrefix, numberPrefix, number);
        triageQueue.getElementsQueue()
                .add(nextNumber);
        lastNumber = nextNumber;
        return nextNumber;
    }

    private String createQueueNumber(Character letterPrefix, int numberPrefix, int number) {
        return String.format("%c%d-%04d", letterPrefix, numberPrefix, number);
    }

    public void addPatientToQueue(EmergencyCategory emergencyCategory, Patient patient) {
        Queue<Patient> queue = serviceQueues.stream()
                .filter(q -> q.getEmergencyCategory() == emergencyCategory)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No queue found for emergency category: " + emergencyCategory));

        triageQueue.getElementsQueue()
                .remove(patient.getQueueNumber());
        queue.getElementsQueue()
                .add(patient);
    }
}
