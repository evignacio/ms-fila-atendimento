package br.com.fiap.msfilaatendimento.core.entity;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class QueuesDetails implements Iterable<QueueDetail> {
    private String upaId;
    private Set<QueueDetail> queuesDetails;
    private int totalPatientsAtUnit;
    private int totalPatientsTreated;

    public QueuesDetails(String upaId, Set<QueueDetail> queuesDetails, int totalPatientsAtUnit, int totalPatientsTreated) {
        setUpaId(upaId);
        setQueuesDetails(queuesDetails);
        setTotalPatientsAtUnit(totalPatientsAtUnit);
        setTotalPatientsTreated(totalPatientsTreated);
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

    public Iterable<QueueDetail> getQueuesDetails() {
        return queuesDetails;
    }

    public void setQueuesDetails(Set<QueueDetail> queuesDetails) {
        if (queuesDetails == null || queuesDetails.isEmpty()) {
            throw new IllegalArgumentException("Queue details cannot be null or empty");
        }
        this.queuesDetails = queuesDetails;
    }

    public int getTotalPatientsAtUnit() {
        return totalPatientsAtUnit;
    }

    public void setTotalPatientsAtUnit(int totalPatientsAtUnit) {
        if (totalPatientsAtUnit < 0) {
            throw new IllegalArgumentException("Total patients at unit cannot be negative");
        }
        this.totalPatientsAtUnit = totalPatientsAtUnit;
    }

    public int getTotalPatientsTreated() {
        return totalPatientsTreated;
    }

    public void setTotalPatientsTreated(int totalPatientsTreated) {
        if (totalPatientsTreated < 0) {
            throw new IllegalArgumentException("Total patients treated cannot be negative");
        }
        this.totalPatientsTreated = totalPatientsTreated;
    }

    @Override
    public Iterator<QueueDetail> iterator() {
        return this.queuesDetails == null ? Collections.emptyIterator() : this.queuesDetails.iterator();
    }
}
