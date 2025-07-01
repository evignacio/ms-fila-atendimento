package br.com.fiap.msfilaatendimento.infrastructure.repository;

import br.com.fiap.msfilaatendimento.infrastructure.repository.model.UbsQueueManagerModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbsQueueManagerRepository extends MongoRepository<UbsQueueManagerModel, String> {
}
