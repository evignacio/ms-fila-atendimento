package br.com.fiap.msfilaatendimento.core.entity;

public class QueueDetail {
    private String title;
    private int size;
    private EmergencyCategory emergencyCategory;


    public QueueDetail(String title, int size, EmergencyCategory emergencyCategory) {
        setTitle(title);
        setSize(size);
        setEmergencyCategory(emergencyCategory);
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

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }
        this.size = size;
    }

    public EmergencyCategory getEmergencyCategory() {
        return emergencyCategory;
    }

    private void setEmergencyCategory(EmergencyCategory emergencyCategory) {
        this.emergencyCategory = emergencyCategory;
    }
}
