package Avion;

public class Avion {
    //Attributs
    private int id;
    private String numAvion;
    private String typeAvion;
    private int nbPlaces;
    private String categorie;
    private static int nbCatCC=0;
    private static int nbCatMC=0;
    private static int nbCatLC=0;

    //Constructeur
    public Avion(int id, String numAvion, int nbPlaces, String typeAvion, String categorie) {
        this.id = id;
        this.numAvion = numAvion;
        this.nbPlaces = nbPlaces;
        this.typeAvion = typeAvion;
        this.categorie = categorie;
    }

    public String getNumAvion() {
        return numAvion;
    }

    public String getTypeAvion() {
        return typeAvion;
    }

    public int getNbPlaces() {
        return this.nbPlaces;
    }

    public void setTypeAvion(String typeAvion) {
        typeAvion = this.typeAvion;
    }

    public void setNumAvion(String numAvion) {
        numAvion = numAvion;
    }

    public void setNbPlaces(int nbPlaces) {
        nbPlaces = nbPlaces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategorie(String categorie) {
        switch (categorie.toLowerCase()){
            case "court-courrier":
                this.categorie = categorie;
                nbCatCC++;
                break;
            case "moyen-courrier":
                this.categorie = categorie;
                nbCatMC++;
                break;
            case "long-courrier":
                this.categorie = categorie;
                nbCatLC++;
                break;
        }
    }



    public String getCategorie() {
        return categorie;
    }
}
