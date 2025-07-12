package br.com.fiap.msfilaatendimento.core.entity;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Queue<T> {
    private String id;
    private String title;
    private String description;
    private EmergencyCategory emergencyCategory;
    private java.util.Queue<T> elementsQueue;

    public Queue(String id, String title, String description, EmergencyCategory emergencyCategory, java.util.Queue<T> elementsQueue) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setEmergencyCategory(emergencyCategory);
        setElementsQueue(elementsQueue);
    }

    public Queue(String title, String description) {
        setId(UUID.randomUUID().toString());
        setTitle(title);
        setDescription(description);
        setEmergencyCategory(emergencyCategory);
        setElementsQueue(new ArrayDeque<>());
    }

    public Queue(String title, String description, EmergencyCategory emergencyCategory) {
        this(title, description);
        setEmergencyCategory(emergencyCategory);
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

    public EmergencyCategory getEmergencyCategory() {
        return emergencyCategory;
    }

    private void setEmergencyCategory(EmergencyCategory emergencyCategory) {
        this.emergencyCategory = emergencyCategory;
    }

    public java.util.Queue<T> getElementsQueue() {
        return elementsQueue;
    }

    private void setElementsQueue(java.util.Queue<T> patients) {
        if (patients == null) {
            throw new IllegalArgumentException("Elements queue cannot be null");
        }
        this.elementsQueue = patients;
    }

    public int getLevelPriority() {
        return emergencyCategory != null ? emergencyCategory.getLevelPriority() : 0;
    }

    public Optional<T> getNextElement() {
        return Optional.ofNullable(elementsQueue.poll());
    }

    public void addElement(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        elementsQueue.add(element);
    }

    public void removeElement(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        elementsQueue.remove(element);
    }

    public String getEmergencyCategoryName() {
        return emergencyCategory != null ? emergencyCategory.name() : null;
    }

    public QueueDetail toQueueDetail() {
        return new QueueDetail(title, elementsQueue.size(), emergencyCategory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Queue<?> queue)) return false;
        return Objects.equals(id, queue.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Queue{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", emergencyCategory=" + emergencyCategory +
                ", elementsQueue=" + elementsQueue +
                '}';
    }
}
