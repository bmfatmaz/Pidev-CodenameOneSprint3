/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author HP
 */
public class Candidature {

    private int id, offerID, freelancerID;

    public Candidature(int offerID, int freelancerID, String lettreMotivation, String cv) {
        this.offerID = offerID;
        this.freelancerID = freelancerID;
        this.lettreMotivation = lettreMotivation;
        this.cv = cv;
    }

    public Candidature(String LettreMotivation, String cv) {
        
        this.lettreMotivation= LettreMotivation;
        this.cv = cv;
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    public int getOfferID() {
        return offerID;
    }
    private String etatID, offreTitle, offreDescription, freelancerLName, freelancerEmail, freelancerProfession, offreCategorie, lettreMotivation,cv,score;

    public Candidature() {

    }

    public Candidature(int id) {
        this.id = id;
    }

    public Candidature(String offreTitle) {
        this.offreTitle = offreTitle;
    }

    public Candidature(int id, String etatID, String offreTitle, String offreDescription,int freelancerID) {

        this.id = id;
        this.etatID = etatID;
        this.offreTitle = offreTitle;
        this.offreDescription = offreDescription;
        this.freelancerID = freelancerID;

    }

    public void setOffreCategorie(String offreCategorie) {
        this.offreCategorie = offreCategorie;
    }

    public String getOffreCategorie() {
        return offreCategorie;
    }

    public Candidature(int id, String etatID, String offreTitle, String offreDescription, int freelancerID, String freelancerLName, String freelancerEmail, String freelancerProfession,String score) {
        this.id = id;
        this.etatID = etatID;
        this.offreTitle = offreTitle;
        this.offreDescription = offreDescription;
        this.freelancerID = freelancerID;
        this.freelancerLName = freelancerLName;
        this.freelancerEmail = freelancerEmail;
        this.freelancerProfession = freelancerProfession;
        this.score=score;
    }

    public Candidature(String etatID, int offreId, int freelancerID, String lettreMotivation, String cv, String score) {
        this.etatID = etatID;
        this.offerID = offreId;
        this.freelancerID = freelancerID;
        this.lettreMotivation = lettreMotivation;
        this.cv = cv;
        this.score = score;
        
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getCv() {
        return cv;
    }

    public Candidature(String etatID, String offreTitle, int freelancerID) {
        this.etatID = etatID;
        this.offreTitle = offreTitle;
        this.freelancerID = freelancerID;

    }

    public void setLettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public int getId() {
        return id;
    }

    public String getEtatID() {
        return etatID;
    }

    public String getOffreTitle() {
        return offreTitle;
    }

    public String getOffreDescription() {
        return offreDescription;
    }

    public int getfreelancerID() {
        return freelancerID;
    }

    public String getFreelancerLName() {
        return freelancerLName;
    }

    public String getFreelancerEmail() {
        return freelancerEmail;
    }

    public String getFreelancerProfession() {
        return freelancerProfession;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtatID(String etatID) {
        this.etatID = etatID;
    }

    public void setOffreTitle(String offreTitle) {
        this.offreTitle = offreTitle;
    }

    public void setOffreDescription(String offreDescription) {
        this.offreDescription = offreDescription;
    }

    public void setfreelancerID(int freelancerID) {
        this.freelancerID = freelancerID;
    }

    public void setFreelancerLName(String freelancerLName) {
        this.freelancerLName = freelancerLName;
    }

    public void setFreelancerEmail(String freelancerEmail) {
        this.freelancerEmail = freelancerEmail;
    }

    public void setFreelancerProfession(String freelancerProfession) {
        this.freelancerProfession = freelancerProfession;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    

    @Override
    public String toString() {
        return "Candidature{" + "id=" + id + ", etat=" + etatID + ", offreDescription=" + offreTitle + ", freeelancer=" + freelancerID + '}';
    }

    public String showID() {
        return "Candidature numero: " + id;
    }

}
