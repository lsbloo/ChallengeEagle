package com.example.osvaldoairon.challengeeeagle.models;

import java.io.Serializable;

public class User implements Serializable{



    private String name;

    private String login;

    private String email;

    private String password;

    private boolean enabled;


    private long id;


    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public User(){
        /**
         * Default Construtor
         */
    }
    public User(String name, String login , String password , String email, boolean enabled){
        setName(name);
        setLogin(login);
        setPassword(password);
        setEmail(email);
        setEnabled(enabled);
    }
}
