package beans;

public class Star {
    private static int comp;
    private int id;
    private String name;
    private String prenom;
    private String ville;
    private String sexe;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    private int img;
    private float star;

    public Star(String name,String prenom,String ville,String sexe, int img, float star) {
        this.id = ++comp;
        this.name = name;
        this.img = img;
        this.star = 5;
        this.ville = ville;
        this.prenom= prenom;
        this.sexe = sexe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = 5;
    }
}


