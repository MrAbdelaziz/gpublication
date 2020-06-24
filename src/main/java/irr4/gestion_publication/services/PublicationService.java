package irr4.gestion_publication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import irr4.gestion_publication.models.Publication;
import irr4.gestion_publication.repositories.PublicationRepository;

import java.util.List;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    public List<Publication> getPublications(){
        return publicationRepository.findAll();
    }

    public Publication addPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    public Publication getPublication(String id){
        return publicationRepository.findById(id).get();
    }

    public Publication deletePublication(String id){
        Publication publication = getPublication(id);
        if(publication != null){
            publicationRepository.deleteById(id);
            return publication;
        }
        return  null;
    }


}
