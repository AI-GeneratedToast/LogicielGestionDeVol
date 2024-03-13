package VolBasPrix;

import java.sql.ResultSet;

public class ControlleurVolBasPrix {
    private ResultSet resultSet = null;
    private String msg = "";
    // ****************** PARTIE SINGLETON *********************
    private static ControlleurVolBasPrix instance = null;

    private ControlleurVolBasPrix(){}
    public static ControlleurVolBasPrix getInstance(){
        if (instance == null){
            instance =  new ControlleurVolBasPrix();
        }
        return instance;
    }
    //************************ FIN PARTIE SINGLETON ********************
    public String enregistrer(VolBasPrix vol) throws Exception {
        return DAOVolBasPrix.getInstance().enregistrerVolBasPrix(vol);
    }

    public String supprimer(int id) throws Exception {
        return DAOVolBasPrix.getInstance().supprimerVolBasPrix(id);
    }

    public ResultSet lister() throws Exception {
        return DAOVolBasPrix.getInstance().listerVolBasPrix();
    }

    public Object listeUn(VolBasPrix vol) throws Exception {
        return DAOVolBasPrix.getInstance().listeUnVolBasPrix(vol.getIdVolBasPrix());
    }

    public String modifier(VolBasPrix vol) throws Exception {
        return DAOVolBasPrix.getInstance().modifierVolBasPrix(vol);
    }
}
