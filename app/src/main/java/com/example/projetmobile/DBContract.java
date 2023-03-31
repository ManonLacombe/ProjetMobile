package com.example.projetmobile;

/**
 * This class defines the construction of the database
 */
public class DBContract {

    /**
     * This method defines the name of the table and its columns
     */
    public static class Form{
        public static final String TABLE_NAME="session";
        public static final String _ID = "id";
        public static final String COLUMN_USER = "Utilisateur";
        public static final String COLUMN_PASSWORD="Mdp";
    }
}
