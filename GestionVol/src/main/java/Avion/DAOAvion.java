package Avion;

import Utilitaire.ConnexionDB;

import java.sql.*;

public class DAOAvion {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    // ****************** PARTIE SINGLETON *********************
    private static DAOAvion instance = null;

    private DAOAvion(){}
    public static DAOAvion getInstance(){
        if (instance == null){
            instance =  new DAOAvion();
        }
        return instance;
    }
    //************************ FIN PARTIE SINGLETON ********************

        public String enregistrerAvion(Avion  avion) throws Exception {
            String msg ="";

            try{
                connection = ConnexionDB.getInstance().getConnexion();
                if (connection!=null){
                    preparedStatement = connection.prepareStatement("INSERT INTO avion (numavion,"+
                            "typeavion, nbplaces, categorie) VALUES (?,?,?,?) ;");

                    preparedStatement.setString(1, avion.getNumAvion());
                    preparedStatement.setString(2, avion.getTypeAvion());
                    preparedStatement.setInt(3, avion.getNbPlaces());
                    preparedStatement.setString(4, avion.getCategorie());

                    preparedStatement.executeUpdate();

                    msg = "L'avion numero "+ avion.getNumAvion()+" a été ajouté";
                }else {
                    msg = "LA CONNECTION N'EST PAS VALIDE!";
                }
            }catch (SQLException e){
                msg = "PROBLEME D'EXECUTION !";
            }
            fermer();
            return msg;
        }

    public ResultSet listerAvion() throws Exception {

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection!= null){
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                resultSet = statement.executeQuery("SELECT * FROM avion");

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

    public String supprimerAvion(int id) throws Exception {
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection!=null){
                preparedStatement = connection.prepareStatement("DELETE FROM avion WHERE idavion=?");

                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();

                msg = "L'avion "+ id+" a été supprimé";

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

    public Object listeUnAvion(int id) throws Exception {
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if (connection != null){
                preparedStatement = connection.prepareStatement("SELECT * FROM avion WHERE idavion=?");
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
        fermer();
        return msg;
    }

    public String modifierAvion(Avion  avion) throws Exception{
        String msg ="";

        try{
            connection = ConnexionDB.getInstance().getConnexion();
            if ( connection != null){
                preparedStatement = connection.prepareStatement("UPDATE avion SET numavion=?," +
                        "typeavion=?, nbplaces=?, categorie=? WHERE idavion=?");
                preparedStatement.setString(1, avion.getNumAvion());
                preparedStatement.setString(2, avion.getTypeAvion());
                preparedStatement.setInt(3, avion.getNbPlaces());
                preparedStatement.setString(4, avion.getCategorie());
                preparedStatement.setInt(5, avion.getId());

                preparedStatement.executeUpdate();

                msg = "L'avion " + avion.getId() + "  a été modifié.";
            }else {
                fermer();
                msg="Erreur de connection à la base de données.";
            }

        }catch (Exception e){
            fermer();
            msg="Problème lors de l'exécution de cette requête.";
        }
        fermer();
        return msg;
    }


    //FERMER LA CONNECTION
    private void fermer(){
        try{
            if (connection != null){
                connection.close();
            }
            if (preparedStatement != null){
                connection.close();
            }
            if (statement != null){
                connection.close();
            }
            if (resultSet != null){
                connection.close();
            }
        }catch (Exception e){

        }
    }
}
