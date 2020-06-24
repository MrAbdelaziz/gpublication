package irr4.gestion_publication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import irr4.gestion_publication.models.Organisme;
import irr4.gestion_publication.repositories.OrganismeRepository;

import java.util.List;

@Service
public class OrganismeService {

    @Autowired
    private OrganismeRepository organismeRepository;

    public List<Organisme> getOrganismes(){
        return organismeRepository.findAll();
    }

    public Organisme addOrganisme(Organisme organisme){
        return organismeRepository.save(organisme);
    }

    public Organisme getOrganisme(String id){
        return organismeRepository.findById(id).get();
    }

    public Organisme deleteOrganisme(String id){
        Organisme organisme = getOrganisme(id);
        if(organisme != null){
            organismeRepository.deleteById(id);
            return organisme;
        }
        return  null;
    }


}
