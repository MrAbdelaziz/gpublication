package irr4.gestion_publication.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import irr4.gestion_publication.utils.TimeStamp;

import java.util.Date;
import java.util.List;

@Document(collection = "publications")
public class Publication extends TimeStamp {

    @Id
    private String id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePublication;
    private String resume;
    private Pdf pdf;
    @DBRef
    private Decipline decipline;
    @DBRef
    private Category category;
    @DBRef
    private List<Author> authors;
    @DBRef
    private List<Tag> tags;

    public Publication() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Pdf getFile() {
        return pdf;
    }

    public void setFile(Pdf pdf) {
        this.pdf = pdf;
    }

    public Decipline getDecipline() {
        return decipline;
    }

    public void setDecipline(Decipline decipline) {
        this.decipline = decipline;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
