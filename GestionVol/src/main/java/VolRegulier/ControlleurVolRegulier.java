package VolRegulier;

import VolBasPrix.DAOVolBasPrix;

import java.sql.ResultSet;

public class ControlleurVolRegulier {
    private ResultSet resultSet = null;
    private String msg = "";
    // ****************** PARTIE SINGLETON *********************
    private static ControlleurVolRegulier instance = null;

    private ControlleurVolRegulier(){}
    public static ControlleurVolRegulier getInstance(){
        if (instance == null){
            instance =  new ControlleurVolRegulier();
        }
        return instance;
    }
    //************************ FIN PARTIE SINGLETON ********************
    public String enregistrer(VolRegulier vol) throws Exception {
        return DAOVolRegulier.getInstance().enregistrerVolRegulier(vol);
    }

    public String supprimer(int  id) throws Exception {
        return DAOVolRegulier.getInstance().supprimerVolRegulier(id);
    }

    public ResultSet lister() throws Exception {
        return DAOVolRegulier.getInstance().listerVolRegulier();
    }

    public Object listeUn(VolRegulier vol) throws Exception {
        return DAOVolRegulier.getInstance().listeUnVolRegulier(vol.getIdVolType());
    }

    public String modifier(VolRegulier vol) throws Exception {
        return DAOVolRegulier.getInstance().modifierVolRegulier(vol);
    }
}
