/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;


import java.util.Date;


/**
 *
 * @author Salma Majeri
 */
public class Offre {

     private int id;
     private int clientId;
     private String titre;
     private String description;
     private String dateLimite;
     private Date dateCreation;
     private String categorie;
     private String skill1;
     private String skill2;
     private String skill3;

    public Offre(int clientId, String titre, String description, String dateLimite, Date dateCreation, String categorie, String skill1, String skill2, String skill3) {
        this.clientId = clientId;
        this.titre = titre;
        this.description = description;
        this.dateLimite = dateLimite;
        this.dateCreation = dateCreation;
        this.categorie = categorie;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
    }

     
     
     
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }  

    public Offre() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(String dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", dateLimite=" + dateLimite + ", dateCreation=" + dateCreation + ", categorie=" + categorie + ", skill1=" + skill1 + ", skill2=" + skill2 + ", skill3=" + skill3 + '}';
    }

    
    

    
   

    

   
   
   
   
}
