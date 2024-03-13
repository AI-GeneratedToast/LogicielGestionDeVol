package VolPrive;


import java.sql.ResultSet;

public class ControlleurVolPrive {
    private ResultSet resultSet = null;
    private String msg = "";
    // ****************** PARTIE SINGLETON *********************
    private static ControlleurVolPrive instance = null;

    private ControlleurVolPrive(){}
    public static ControlleurVolPrive getInstance(){
        if (instance == null){
            instance =  new ControlleurVolPrive();
        }
        return instance;
    }
    //************************ FIN PARTIE SINGLETON ********************
    public String enregistrer(VolPrive vol) throws Exception {
        return DAOVolPrive.getInstance().enregistrerVolPrive(vol);
    }

    public String supprimer(int  id) throws Exception {
        return DAOVolPrive.getInstance().supprimerVolPrive(id);
    }

    public ResultSet lister() throws Exception {
        return DAOVolPrive.getInstance().listerVolPrive();
    }

    public Object listeUn(VolPrive vol) throws Exception {
        return DAOVolPrive.getInstance().listeUnVolPrive(vol.getIdVolType());
    }

    public String modifier(VolPrive vol) throws Exception {
        return DAOVolPrive.getInstance().modifierVolPrive(vol);
    }
}
