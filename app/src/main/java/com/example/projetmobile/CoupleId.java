package com.example.projetmobile;

/**
 * This class defines the CoupleId object
 */
public class CoupleId {
    private String user;
    private String password;

    /**
     * Constructor
     * @param user is the user
     * @param password is the user's password
     */
    public CoupleId (String user, String password){
        setUser(user);
        setPassword(password);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    }
