package irr4.gestion_publication.models;

import org.springframework.data.mongodb.core.mapping.Document;

import irr4.gestion_publication.utils.TimeStamp;

@Document(collection = "organismes")
public class Organisme extends TimeStamp {

    private String id;
    private String name;
    private String type;

    public Organisme() {
    }

    public Organisme(String id, String name) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
