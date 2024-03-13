package Utilitaire;

import Env.Env;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionDB {
    //ATTRIBUTS
    private Connection connexion = null;
    private static ConnexionDB instance = null;

    //CONSTRUCTEUR
    private ConnexionDB(){}

    //INITIALISE INSTCE DE CETTE CLASSE
    public static ConnexionDB getInstance(){
        if(instance == null){
            instance = new ConnexionDB();
        }
        return instance;
    }

    //CONNEXION A LA BASE DE DONNEE
    public Connection getConnexion(){
        try{
            connexion = DriverManager.getConnection("jdbc:postgresql://"+Env.SERVEUR+"/"+
                    Env.BD_NAME+"?user="+Env.USERNAME+"&password="+Env.PASSWORD);
            System.out.println("La connexion marche");
        }catch (Exception e){
            System.out.println("Erreur SQL lors de la connection au serveur");
            connexion = null;
        }
        return connexion;
    }

}
