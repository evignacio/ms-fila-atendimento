package br.com.fiap.msfilaatendimento.core.entity;

import java.util.*;
import java.util.stream.Collectors;

public class UpaQueueManager {
    private String upaId;
    private String upaName;
    private String lastGenerateNumber;
    private Queue<String> triageQueue;
    private Set<Queue<Patient>> serviceQueues;

    public UpaQueueManager(String upaId, String upaName, String lastGenerateNumber, Queue<String> triageQueue, Set<Queue<Patient>> serviceQueues) {
        setUpaId(upaId);
        setUpaName(upaName);
        setLastGenerateNumber(lastGenerateNumber);
        setTriageQueue(triageQueue);
        setServiceQueues(serviceQueues);
    }

    public UpaQueueManager(String upaId, String upaName, Queue<String> triageQueue, Set<Queue<Patient>> serviceQueues) {
        setUpaId(upaId);
        setUpaName(upaName);
        setLastGenerateNumber(formatQueueNumber('A', 0, 0));
        setTriageQueue(triageQueue);
        setServiceQueues(serviceQueues);
    }

    public String getUpaId() {
        return upaId;
    }

    private void setUpaId(String upaId) {
        if (upaId == null || upaId.isEmpty()) {
            throw new IllegalArgumentException("UPA ID cannot be null or empty");
        }
        this.upaId = upaId;
    }

    public String getUpaName() {
        return upaName;
    }

    private void setUpaName(String upaName) {
        if (upaName == null || upaName.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.upaName = upaName;
    }

    public String getLastGenerateNumber() {
        return lastGenerateNumber;
    }

    private void setLastGenerateNumber(String lastGenerateNumber) {
        if (lastGenerateNumber == null || lastGenerateNumber.isEmpty()) {
            throw new IllegalArgumentException("Last number cannot be null or empty");
        }
        this.lastGenerateNumber = lastGenerateNumber;
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

    public String generateNextTriageNumber() {
        var delimiter = "-";
        var lastNumberSplit = lastGenerateNumber.split(delimiter);
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

        nextNumber = formatQueueNumber(letterPrefix, numberPrefix, number);
        triageQueue.addElement(nextNumber);
        lastGenerateNumber = nextNumber;
        return nextNumber;
    }

    private String formatQueueNumber(Character letterPrefix, int numberPrefix, int number) {
        return String.format("%c%d-%04d", letterPrefix, numberPrefix, number);
    }

    public void addPatientToQueue(EmergencyCategory emergencyCategory, Patient patient) {
        Queue<Patient> queue = serviceQueues.stream()
                .filter(q -> q.getEmergencyCategory() == emergencyCategory)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No queue found for emergency category: " + emergencyCategory));

        triageQueue.removeElement(patient.getQueueNumber());
        queue.addElement(patient);
    }

    public String getNextNumberFromTriageQueue() {
        var nextPatientNumberOpt = triageQueue.getNextElement();

        if (nextPatientNumberOpt.isEmpty()) {
            throw new IllegalStateException("No patients in the triage queue");
        }

        return nextPatientNumberOpt.get();
    }

    public Patient getPatientFromServiceQueue() {
        Optional<Patient> nextPatientOpt;
        Patient patient = null;

        var serviceQueuesSortedByEmergencyLevel = serviceQueues.stream()
                .sorted((Comparator.comparing(Queue::getLevelPriority)))
                .iterator();

        while (serviceQueuesSortedByEmergencyLevel.hasNext()) {
            var currentQueue = serviceQueuesSortedByEmergencyLevel.next();
            nextPatientOpt = currentQueue.getNextElement();
            if (nextPatientOpt.isPresent()) {
                patient = nextPatientOpt.get();
                break;
            }
        }

        if (patient == null) {
            throw new IllegalStateException("No patients in the service queues");
        }

        return patient;
    }


    public QueuesDetails toQueuesDetails() {
        var triageQueueDetail = triageQueue.toQueueDetail();
        var serviceQueuesDetails = serviceQueues.stream()
                .map(Queue::toQueueDetail)
                .sorted(Comparator.comparing(q -> q.getEmergencyCategory().getLevelPriority()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<QueueDetail> queuesDetails = new LinkedHashSet<>();
        queuesDetails.add(triageQueueDetail);
        queuesDetails.addAll(serviceQueuesDetails);

        int totalPatientsAtUnit = queuesDetails.stream()
                .mapToInt(QueueDetail::getSize).
                sum();

        return new QueuesDetails(upaId, queuesDetails, totalPatientsAtUnit, getTotalPatientTreated());
    }

    private int getTotalPatientTreated() {
        final int totalLetter = 26;
        final int totalNumberPerLetter = 9999;

        var delimiter = "-";
        var lastNumberSplit = lastGenerateNumber.split(delimiter);
        var number = Integer.parseInt(lastNumberSplit[1]);
        var letterPrefix = lastNumberSplit[0].charAt(0);
        var numberPrefix = Integer.parseInt(lastNumberSplit[0].substring(1));

        int prefixCount = letterPrefix - 'A' ;
        return (numberPrefix * totalLetter) + (prefixCount * totalNumberPerLetter) + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpaQueueManager that)) return false;
        return Objects.equals(upaId, that.upaId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(upaId);
    }
}
