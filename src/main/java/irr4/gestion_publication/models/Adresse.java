package irr4.gestion_publication.models;

public class Adresse {
    private String ville;
    private String pays;
    private Long zipCode;

    public Adresse() {
    }

    public Adresse(String ville, String pays, Long zipCode) {
        this.ville = ville;
        this.pays = pays;
        this.zipCode = zipCode;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }
}

