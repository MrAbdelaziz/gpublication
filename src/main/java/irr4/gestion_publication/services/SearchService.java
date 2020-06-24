package irr4.gestion_publication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import irr4.gestion_publication.models.Publication;
import irr4.gestion_publication.repositories.PublicationRepository;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private PublicationRepository publicationRepository;

    public List<Publication> findPublicationByTitle(String title){
        return publicationRepository.findAllByTitle(title);
    }

    public List<Publication> getPublicationsByCategory(String catId){
        return publicationRepository.findAllByCategoryId(catId);
    }

    public List<Publication> getPublicationsByDecipline(String desId){
        return publicationRepository.findAllByDeciplineId(desId);
    }
    
    
    public List<Publication> getPublicationsByAuteur(String auteur){
        return publicationRepository.findByauthors(auteur);
    }
}


