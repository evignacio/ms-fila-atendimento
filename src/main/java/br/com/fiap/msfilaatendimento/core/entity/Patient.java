package br.com.fiap.msfilaatendimento.core.entity;

import java.util.Objects;
import java.util.UUID;

public class Patient {
    private String id;
    private String name;
    private String queueNumber;

    public Patient(String id, String name, String queueNumber) {
        setId(id);
        setName(name);
        setQueueNumber(queueNumber);
    }

    public Patient(String name, String queueNumber) {
        setId(UUID.randomUUID().toString());
        setName(name);
        setQueueNumber(queueNumber);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getQueueNumber() {
        return queueNumber;
    }

    private void setQueueNumber(String queueNumber) {
        if (queueNumber == null || queueNumber.isEmpty()) {
            throw new IllegalArgumentException("Queue number cannot be null or empty");
        }
        this.queueNumber = queueNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient patient)) return false;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", queueNumber='" + queueNumber + '\'' +
                '}';
    }
}
