package br.com.fiap.msfilaatendimento.core.entity;

import java.util.UUID;

public class Patient {
    private String id;
    private String name;
    private int queueNumber;

    public Patient(String id, String name, int queueNumber) {
        setId(id);
        setName(name);
        setQueueNumber(queueNumber);
    }

    public Patient(String name, int queueNumber) {
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

    public int getQueueNumber() {
        return queueNumber;
    }

    private void setQueueNumber(int queueNumber) {
        if (queueNumber < 0) {
            throw new IllegalArgumentException("Queue number cannot be negative");
        }
        this.queueNumber = queueNumber;
    }
}
