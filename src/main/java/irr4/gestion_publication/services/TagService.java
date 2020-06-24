package irr4.gestion_publication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import irr4.gestion_publication.models.Publication;
import irr4.gestion_publication.models.Tag;
import irr4.gestion_publication.repositories.PublicationRepository;
import irr4.gestion_publication.repositories.TagRepository;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getPostTags(String id){
        Publication publication = publicationRepository.findById(id).get();
        if(publication != null){
            return publication.getTags();
        }
        return null;
    }

    public Tag addTagToPost(String id, Tag tag){
        Publication publication = publicationRepository.findById(id).get();
        if(publication != null){
            Tag tag1 = new Tag();
            tag1.setName(tag.getName());
            Tag tag2 = tagRepository.save(tag1);

            publication.getTags().add(tag2);
            publicationRepository.save(publication);
            return tag2;
        }
        return null;
    }

    public Tag deleteTagfromPublication(String id){
        Tag tag = tagRepository.findById(id).get();
        if(tag != null){
            tagRepository.deleteById(id);
            return tag;
        }
        return null;
    }

}
