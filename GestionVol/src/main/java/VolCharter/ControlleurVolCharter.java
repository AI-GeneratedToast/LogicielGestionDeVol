package VolCharter;



import java.sql.ResultSet;

public class ControlleurVolCharter {
    private ResultSet resultSet = null;
    private String msg = "";
    // ****************** PARTIE SINGLETON *********************
    private static ControlleurVolCharter instance = null;

    private ControlleurVolCharter(){}
    public static ControlleurVolCharter getInstance(){
        if (instance == null){
            instance =  new ControlleurVolCharter();
        }
        return instance;
    }
    //************************ FIN PARTIE SINGLETON ********************
    public String enregistrer(VolCharter vol) throws Exception {
        return DAOVolCharter.getInstance().enregistrerCharter(vol);
    }

    public String supprimer(int  id) throws Exception {
        return DAOVolCharter.getInstance().supprimerVolCharter(id);
    }

    public ResultSet lister() throws Exception {
        return DAOVolCharter.getInstance().listerVolCharter();
    }

    public Object listeUn(VolCharter vol) throws Exception {
        return DAOVolCharter.getInstance().listeUnVolCharter(vol.getIdVolType());
    }

    public String modifier(VolCharter vol) throws Exception {
        return DAOVolCharter.getInstance().modifierVolCharter(vol);
    }
}
