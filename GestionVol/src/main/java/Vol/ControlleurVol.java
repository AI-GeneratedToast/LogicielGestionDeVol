package Vol;

import java.sql.ResultSet;

public class ControlleurVol {
    private ResultSet resultSet = null;
    private String msg = "";
    // ****************** PARTIE SINGLETON *********************
    private static ControlleurVol instance = null;

    private ControlleurVol(){}
    public static ControlleurVol getInstance(){
        if (instance == null){
            instance =  new ControlleurVol();
        }
        return instance;
    }
    //************************ FIN PARTIE SINGLETON ********************
    public String enregistrer(Vol vol) throws Exception {
        return DAOVol.getInstance().enregistrerVol(vol);
    }

    public String supprimer(int id) throws Exception {
        return DAOVol.getInstance().supprimerVol(id);
    }

    public ResultSet lister() throws Exception {
        return DAOVol.getInstance().listerVol();
    }

    public Object listeUn(Vol vol) throws Exception {
        return DAOVol.getInstance().listeUnVol(vol.getIdVol());
    }

    public String modifier(Vol vol) throws Exception {
        return DAOVol.getInstance().modifierVol(vol);
    }

}
