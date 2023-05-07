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
public class Offre {

     private int id;
     private int clientId;
     private String titre;
     private String description;
     private String dateLimite;
     private String dateCreation;
     private String categorie;
     private String Etat;
     private String skill1;
     private String skill2;
     private String skill3;
     private String logoPath;

    public Offre(int clientId, String titre, String description, String dateLimite, String dateCreation, String categorie, String Etat, String skill1, String skill2, String skill3, String logoPath) {
        this.clientId = clientId;
        this.titre = titre;
        this.description = description;
        this.dateLimite = dateLimite;
        this.dateCreation = dateCreation;
        this.categorie = categorie;
        this.Etat = Etat;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.logoPath = logoPath;
    }

   
    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", clientId=" + clientId + ", titre=" + titre + ", description=" + description + ", dateLimite=" + dateLimite + ", dateCreation=" + dateCreation + ", categorie=" + categorie + ", Etat=" + Etat + ", skill1=" + skill1 + ", skill2=" + skill2 + ", skill3=" + skill3 + '}';
    }

  

    
    

    
   

    

   
   
   
   
}
