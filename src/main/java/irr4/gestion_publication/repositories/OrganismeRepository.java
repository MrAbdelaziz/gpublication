package irr4.gestion_publication.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import irr4.gestion_publication.models.Organisme;

@Repository
public interface OrganismeRepository extends MongoRepository<Organisme, String> {
}
