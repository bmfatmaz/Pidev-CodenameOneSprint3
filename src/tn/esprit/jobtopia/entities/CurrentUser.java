/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.entities;

/**
 *
 * @author Administrateur
 */
public class CurrentUser {
      private static CurrentUser instance = null;
    private int id;
    

    private CurrentUser() {}

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
