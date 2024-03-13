package Vol;

import java.io.Serializable;

public abstract class Vol implements Serializable {
    //Attributs
    private int idVol;
    private String numVol;
    private String destination;
    private String dateDepart;
    private int idAvion;
    private int nbReservations;

    //Constructeur
    public Vol (int idVol,String numVol,String destination,String dateDepart,int idAvion,int nbReservations){
        this.destination=destination;
        this.dateDepart = dateDepart;
        this.nbReservations = nbReservations;
        this.numVol =numVol;
        this.idAvion=idAvion;
        this.idVol =idVol;
    }


    public int getIdAvion() {
        return idAvion;
    }

    public int getIdVol() {
        return idVol;
    }

    public int getNbReservations() {
        return nbReservations;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public String getDestination() {
        return destination;
    }

    public String getNumVol() {
        return numVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public void setNumVol(String numVol) {
        this.numVol = numVol;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public void setNbReservations(int nbReservations) {
        this.nbReservations = nbReservations;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    //Autre Methode
    @Override
    public String toString() {
        return "Vol{" +
                "numeroVol=" + numVol +
                ", \tdestination='" + destination +
                ", \tdateDepart=" + dateDepart +
                ", \tnumAvion=" + idAvion +
                ", \tnbReservations=" + nbReservations +
                '}';
    }
}
