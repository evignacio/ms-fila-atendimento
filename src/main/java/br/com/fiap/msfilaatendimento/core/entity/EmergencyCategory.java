package br.com.fiap.msfilaatendimento.core.entity;

public enum EmergencyCategory {
    NOT_URGENT(5),
    LITTLE_URGENT(4),
    URGENT(3),
    VERY_URGENT(2),
    EMERGENCY(1);

    private final int levelPriority;

    EmergencyCategory(int levelPriority) {
        this.levelPriority = levelPriority;
    }

    public int getLevelPriority() {
        return levelPriority;
    }
}
