package Env;

public class Env {

    //ATTRUBUTS POUR LA CONNEXION DE LA BD
    public static final String SERVEUR="localhost";
    public static final String BD_NAME="bdvols";
    public static final String USERNAME="postgres";
    public static  final String PASSWORD="postgres";

    // ****************** PARTIE SINGLETON *********************
    // La seule instance de cette classe

    private static Env instance = null;

    private Env(){}

    public static Env getInstance(){
        if (instance == null){
            instance = new Env();
        }
        return instance;
    }
}
