package irr4.gestion_publication.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import irr4.gestion_publication.utils.TimeStamp;

@Document(collection = "deciplines")
public class Decipline extends TimeStamp {

    @Id
    private String id;
    private String name;

    public Decipline() {
    }

    public Decipline(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
