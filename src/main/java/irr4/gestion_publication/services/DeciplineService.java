package irr4.gestion_publication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import irr4.gestion_publication.models.Category;
import irr4.gestion_publication.models.Decipline;
import irr4.gestion_publication.repositories.DeciplineRepostory;

import java.util.List;

@Service
public class DeciplineService {

    @Autowired
    private DeciplineRepostory deciplineRepostory;

    public List<Decipline> getDeciplines(){
        return deciplineRepostory.findAll();
    }

    public Decipline addDecipline(Decipline decipline){
        return deciplineRepostory.save(decipline);
    }

    public Decipline getDecipline(String id){
        return deciplineRepostory.findById(id).get();
    }

    public Decipline deleteDecipline(String id){
        Decipline decipline = getDecipline(id);
        if(decipline != null){
            deciplineRepostory.deleteById(id);
            return decipline;
        }
        return  null;
    }

}
