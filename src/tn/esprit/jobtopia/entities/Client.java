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
public class Client extends User {
    private String profession;

    public Client(String profession, String role, String nom, String prenom, String username, String password, String email) {
        super(role, nom, prenom, username, password, email);
        this.profession = profession;
    }

    public Client() {
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", username=" + getUsername() +  ", email=" + getEmail() + "profession=" + profession + "}";
    }
    
    
}