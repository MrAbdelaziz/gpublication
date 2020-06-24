package irr4.gestion_publication.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import irr4.gestion_publication.models.Publication;

import java.util.List;

@Repository
public interface PublicationRepository extends MongoRepository<Publication, String> {
    public List<Publication> findAll();
    public List<Publication> findAllByCategoryId(String id);
    public List<Publication> findAllByDeciplineId(String id);
    @Query("{title: { '$regex': ?0, '$options' : 'i' }}")
    public List<Publication> findAllByTitle(String title);
    public     List<Publication> findByauthors(String auteur);
    
}
