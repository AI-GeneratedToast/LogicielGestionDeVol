package Avion;

import java.sql.ResultSet;

public class ControlleurAvion {
    private ResultSet resultSet = null;
    private String msg = "";
    // ****************** PARTIE SINGLETON *********************
    private static ControlleurAvion instance = null;

    private ControlleurAvion(){}
    public static ControlleurAvion getInstance(){
        if (instance == null){
            instance =  new ControlleurAvion();
        }
        return instance;
    }
    //************************ FIN PARTIE SINGLETON ********************
    public String enregistrer(Avion avion) throws Exception {
        return DAOAvion.getInstance().enregistrerAvion(avion);
    }

    public String supprimer(int id) throws Exception {
        return  DAOAvion.getInstance().supprimerAvion(id);
    }

    public ResultSet lister() throws Exception {
        return DAOAvion.getInstance().listerAvion();
    }

    public Object listeUn(Avion avion) throws Exception {
        return DAOAvion.getInstance().listeUnAvion(avion.getId());
    }

    public String modifier(Avion avion) throws Exception {
        return DAOAvion.getInstance().modifierAvion(avion);
    }
}
