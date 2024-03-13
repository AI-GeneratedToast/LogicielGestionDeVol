package VolBasPrix;

import Utilitaire.ConnexionDB;

import java.sql.*;

public class DAOVolBasPrix {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    // ****************** PARTIE SINGLETON *********************
    private static DAOVolBasPrix instance = null;

    private DAOVolBasPrix(){}
    public static DAOVolBasPrix getInstance(){
        if (instance == null){
            instance =  new DAOVolBasPrix();
        }
        return instance;
    }
    //************************ FIN PARTIE SINGLETON ********************
    public String enregistrerVolBasPrix(VolBasPrix vol) throws Exception {
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection!=null){
                preparedStatement = connection.prepareStatement("INSERT INTO volbasprix (isRepasFourni, isSiegeReserve, " +
                        "isServiceBar,isSysDivertissement, isServicePayant,isPriseAlimentation,isWifi,idvol) " +
                        "VALUES (?, ?, ?, ?, ? ,? ,? ,?);");

                preparedStatement.setBoolean(1,vol.getIsRepasFourni());
                preparedStatement.setBoolean(2,vol.getIsSiegeReserve());
                preparedStatement.setBoolean(3,vol.getIsServiceBar());
                preparedStatement.setBoolean(4,vol.getIsSysDivertissement());
                preparedStatement.setBoolean(5,vol.getIsServicePayant());
                preparedStatement.setBoolean(6,vol.getIsPriseAlimentation());
                preparedStatement.setBoolean(7,vol.getIsWifi());
                preparedStatement.setInt(8,vol.getIdVol());

                preparedStatement.executeUpdate();

                msg = "Le vol bas prix numero "+ vol.getNumVol()+" a été ajouté";
            }else {
                msg = "LA CONNECTION N'EST PAS VALIDE!";
            }
        }catch (SQLException e){
            msg = "PROBLEME D'EXECUTION !";
        }
        fermer();
        return msg;
    }

    public ResultSet listerVolBasPrix() throws Exception {

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection!= null){
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                resultSet = statement.executeQuery("SELECT * FROM volbasprix");

                return resultSet;
            }else {
                fermer();
                throw new SQLException("Erreur de connection à la base de données.");
            }
        }catch (Exception e){
            fermer();
            throw new Exception("Problème lors de l'exécution de cette requête.");
        }
    }

    public String supprimerVolBasPrix(int id) throws Exception {
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection!=null){
                preparedStatement = connection.prepareStatement("DELETE FROM volbasprix WHERE idvolbasprix=?");

                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();

                msg = "Le vol bas prix "+ id+" a été supprimé";

                return msg;
            }else {
                fermer();
                msg="Erreur de connection à la base de données.";
            }
        } catch (SQLException e) {
            fermer();
            msg="Problème lors de l'exécution de cette requête.";
        }
        fermer();
        return msg;
    }

    public Object listeUnVolBasPrix(int id) throws Exception {
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection != null){
                preparedStatement = connection.prepareStatement("SELECT * FROM volbasprix WHERE idvolbasprix=?");
                preparedStatement.setInt(1,id);
                resultSet = preparedStatement.executeQuery();

                return resultSet;
            }else {
                fermer();
                msg="Erreur de connection à la base de données.";
            }
        }catch (Exception e){
            fermer();
            msg="Problème lors de l'exécution de cette requête.";
        }
        return msg;
     }

    public String modifierVolBasPrix(VolBasPrix vol){
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if ( connection != null){
                preparedStatement = connection.prepareStatement("UPDATE volbasprix SET isrepasfourni=?," +
                        "issiegereserve=?, isservicebar=?, issysdivertissement=?, isservicepayant=?," +
                        "isprisealimentation=?, iswifi=? WHERE idvolbasprix=?");
                preparedStatement.setBoolean(1,vol.getIsRepasFourni());
                preparedStatement.setBoolean(2,vol.getIsSiegeReserve());
                preparedStatement.setBoolean(3,vol.getIsServiceBar());
                preparedStatement.setBoolean(4,vol.getIsSysDivertissement());
                preparedStatement.setBoolean(5,vol.getIsServicePayant());
                preparedStatement.setBoolean(6,vol.getIsPriseAlimentation());
                preparedStatement.setBoolean(7,vol.getIsWifi());
                preparedStatement.setInt(8,vol.getIdVolBasPrix());

                preparedStatement.executeUpdate();

                msg = "Le vol vol bas prix" + vol.getIdVol() + "  a été modifié.";
            }else {
                fermer();
                msg="Erreur de connection à la base de données.";
            }

        }catch (Exception e){
            fermer();
            msg="Problème lors de l'exécution de cette requête.";
        }
        return msg;
    }


    //FERMER LA CONNECTION
    private void fermer() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                connection.close();
            }
            if (statement != null) {
                connection.close();
            }
            if (resultSet != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
    }
}
