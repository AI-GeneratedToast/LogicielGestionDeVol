package Vol;

import Utilitaire.ConnexionDB;

import java.sql.*;

public class DAOVol {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    // ****************** PARTIE SINGLETON *********************
    private static DAOVol instance = null;

    private DAOVol(){}
    public static DAOVol getInstance(){
        if (instance == null){
            instance =  new DAOVol();
        }
        return instance;
    }
    //************************ FIN PARTIE SINGLETON ********************
    public String enregistrerVol(Vol vol) throws Exception {
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection!=null){
                preparedStatement = connection.prepareStatement("INSERT INTO vol (numvol, destination, idavion," +
                        "nbreservations, datedepart) VALUES (?, ?, ?, ?, ?);");

                preparedStatement.setString(1, vol.getNumVol());
                preparedStatement.setString(2, vol.getDestination());
                preparedStatement.setInt(3, vol.getIdAvion());
                preparedStatement.setInt(4, vol.getNbReservations());
                preparedStatement.setString(5, vol.getDateDepart());


                preparedStatement.executeUpdate();

                msg = "Le vol numero "+ vol.getNumVol()+" a été ajouté";
            }else {
                msg = "LA CONNECTION N'EST PAS VALIDE!";
            }
        }catch (SQLException e){
            msg = "PROBLEME D'EXECUTION !";
        }
        fermer();
        return msg;
    }

    public ResultSet listerVol() throws Exception {

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection!= null){
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                resultSet = statement.executeQuery("SELECT * FROM vol");

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

    public String supprimerVol(int id) throws Exception {
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection!=null){
                preparedStatement = connection.prepareStatement("DELETE FROM vol WHERE idvol=?");

                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();

                msg = "Le vol "+ id+" a été supprimé";

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

    public Object listeUnVol(int id) throws Exception {
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection != null){
                preparedStatement = connection.prepareStatement("SELECT * FROM vol WHERE idvol=?");
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

    public String modifierVol(Vol vol){
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if ( connection != null){
                preparedStatement = connection.prepareStatement("UPDATE vol SET numvol=?," +
                        "destination=?, datedepart=?, idavion=?, nbreservations=? WHERE idvol=?");
                preparedStatement.setString(1, vol.getNumVol());
                preparedStatement.setString(2, vol.getDestination());
                preparedStatement.setString(3, vol.getDateDepart());
                preparedStatement.setInt(4, vol.getIdAvion());
                preparedStatement.setInt(5, vol.getNbReservations());
                preparedStatement.setInt(6, vol.getIdVol());

                preparedStatement.executeUpdate();

                msg = "Le vol " + vol.getIdVol() + "  a été modifié.";
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
