package br.com.fiap.msfilaatendimento.infrastructure.repository;

import br.com.fiap.msfilaatendimento.infrastructure.repository.model.UpaQueueManagerModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpaQueueManagerRepository extends MongoRepository<UpaQueueManagerModel, String> {
}
