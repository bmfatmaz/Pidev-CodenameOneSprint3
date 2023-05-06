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
public class Freelancer extends User {

    private String categorie;
    private String description, cv;
    private float salaire, note;

    public Freelancer() {
        super();
    }

    public Freelancer(String categorie, String description, String cv, float salaire, float note, String role, String nom, String prenom, String username, String password, String email) {
        super(Role.FREELANCER.toString(), nom, prenom, username, password, email);
        this.categorie = categorie;
        this.description = description;
        this.cv = cv;
        this.salaire = salaire;
        this.note = note;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public String getCv() {
        return cv;
    }

    public float getSalaire() {
        return salaire;
    }

    public float getNote() {
        return note;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public String toString() {return this.getUsername();
    }
}