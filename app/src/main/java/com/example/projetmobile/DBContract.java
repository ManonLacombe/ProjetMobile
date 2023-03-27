package com.example.projetmobile;

/**
 * cette classe définit la construction de la base de donnée
 */
public class DBContract {

    /**
     * cette méthode définit le nom de la table et ses colonnes
     */
    public static class Form{
        public static final String TABLE_NAME="session";
        public static final String _ID = "id";
        public static final String COLUMN_USER = "Utilisateur";
        public static final String COLUMN_PASSWORD="Mdp";
    }
}
