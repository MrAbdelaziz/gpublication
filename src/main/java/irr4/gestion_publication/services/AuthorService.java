package irr4.gestion_publication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import irr4.gestion_publication.models.Author;
import irr4.gestion_publication.models.Publication;
import irr4.gestion_publication.repositories.AuthorRepository;
import irr4.gestion_publication.repositories.PublicationRepository;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublicationRepository publicationRepository;

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    public Author getAuthor(String id){
        return authorRepository.findById(id).get();
    }

    public Author deleteAuthor(String id){
        Author author = getAuthor(id);
        if(author != null){
            authorRepository.deleteById(id);
            return author;
        }
        return  null;
    }

    public List<Author> getPublicationAuthors(String id){
        return publicationRepository.findById(id).get().getAuthors();
    }

    public Author addAuthorToPost(String pubId, String authorId){
        Publication publication = publicationRepository.findById(pubId).get();
        Author author = authorRepository.findById(authorId).get();
        if(publication != null && author != null){
            publication.getAuthors().add(author);
            publicationRepository.save(publication);
            return author;
        }

        return null;
    }

    public Author deleteAuthorFormPublication(String pubId, String auhtorId){
        Publication publication = publicationRepository.findById(pubId).get();
        Author author = authorRepository.findById(auhtorId).get();
        if(publication != null && author != null){
            List<Author> authors = publication.getAuthors();
            for (int i = 0; i < authors.size(); i++){
                if(authors.get(i).getId().equals(auhtorId)){
                    System.out.println(i);
                    publication.getAuthors().remove(i);
                }
            }
            publicationRepository.save(publication);
            return author;
        }
        return null;
    }
}
