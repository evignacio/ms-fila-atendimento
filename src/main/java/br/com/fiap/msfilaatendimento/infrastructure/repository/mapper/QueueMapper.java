package br.com.fiap.msfilaatendimento.infrastructure.repository.mapper;

import br.com.fiap.msfilaatendimento.core.entity.EmergencyCategory;
import br.com.fiap.msfilaatendimento.core.entity.Queue;
import br.com.fiap.msfilaatendimento.infrastructure.repository.model.QueueModel;

import java.util.ArrayDeque;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class QueueMapper {

    private QueueMapper() {
    }

    public static <S, T> Queue<T> toEntity(QueueModel<S> model, Function<S, T> elementMapper) {
        java.util.Queue<T> elementsQueue = model.getElementsQueue()
                .stream()
                .map(elementMapper)
                .collect(Collectors.toCollection(ArrayDeque::new));

        return new Queue<>(
                model.getId(),
                model.getTitle(),
                model.getDescription(),
                EmergencyCategory.valueOf(model.getEmergencyCategory()),
                elementsQueue
        );
    }

    public static <T> QueueModel<T> toModel(Queue<T> entity) {
        return QueueModel.<T>builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .emergencyCategory(entity.getEmergencyCategory().name())
                .elementsQueue(entity.getElementsQueue())
                .build();
    }
}
