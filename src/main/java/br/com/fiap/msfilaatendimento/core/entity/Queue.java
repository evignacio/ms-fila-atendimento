package br.com.fiap.msfilaatendimento.core.entity;

import java.util.ArrayDeque;
import java.util.UUID;

public class Queue {
    private String id;
    private String title;
    private String description;
    private String ubsId;
    private EmergencyCategory emergencyCategory;
    private java.util.Queue<Patient> patients;

    public Queue(String id, String title, String description, String ubsId, EmergencyCategory emergencyCategory, java.util.Queue<Patient> patients) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setUbsId(ubsId);
        setEmergencyCategory(emergencyCategory);
        setPatients(patients);
    }

    public Queue(String title, String description, String ubsId, EmergencyCategory emergencyCategory) {
        setId(UUID.randomUUID().toString());
        setTitle(title);
        setDescription(description);
        setUbsId(ubsId);
        setEmergencyCategory(emergencyCategory);
        setPatients(new ArrayDeque<>());
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

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
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

    public EmergencyCategory getEmergencyCategory() {
        return emergencyCategory;
    }

    private void setEmergencyCategory(EmergencyCategory emergencyCategory) {
        if (emergencyCategory == null) {
            throw new IllegalArgumentException("Emergency category cannot be null");
        }
        this.emergencyCategory = emergencyCategory;
    }

    public java.util.Queue<Patient> getPatients() {
        return patients;
    }

    private void setPatients(java.util.Queue<Patient> patients) {
        if (patients == null) {
            throw new IllegalArgumentException("Patients queue cannot be null");
        }
        this.patients = patients;
    }
}
