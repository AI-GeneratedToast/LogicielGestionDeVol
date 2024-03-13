package VolCharter;

import Vol.Vol;

public class VolCharter extends Vol {
    //Attrubuts
    private int idVolType;
    private boolean isRepasFourni;
    private boolean isSiegeReserve;
    private boolean isServiceBar;
    private boolean isSysDivertissement;
    private boolean isServicePayant;
    private boolean isPriseAlimentation;
    private boolean isWifi;


    public VolCharter(int id,String numVol, String destination, String dateDepart, int nbReservations, int idAvion,
                      boolean isRepasFournis,boolean isPriseAlimentation,boolean isServiceBar,boolean isServicePayant,
                      boolean isWifi,boolean isSysDivertissement,boolean isSiegeReserve,int idVolType) {
        super(id,numVol, destination, dateDepart, idAvion, nbReservations);
        this.isRepasFourni = isRepasFournis;
        this.isPriseAlimentation =  isPriseAlimentation;
        this.isServiceBar = isServiceBar;
        this.isWifi= isWifi;
        this.isSysDivertissement = isSysDivertissement;
        this.isSiegeReserve = isSiegeReserve;
        this.isServicePayant = isServicePayant;
        this.idVolType =idVolType;
    }

    public int getIdVolType() {
        return idVolType;
    }
    public void setIdVolType(int idVolType) {
        this.idVolType = idVolType;
    }

    public boolean getIsRepasFourni() {
        return isRepasFourni;
    }
    public boolean getIsSiegeReserve() {
        return isSiegeReserve;
    }
    public boolean getIsServiceBar() {
        return isServiceBar;
    }
    public boolean getIsSysDivertissement() {
        return isSysDivertissement;
    }
    public boolean getIsServicePayant() {
        return isServicePayant;
    }
    public boolean getIsPriseAlimentation() {
        return isPriseAlimentation;
    }
    public boolean getIsWifi() {
        return isWifi;
    }



}
