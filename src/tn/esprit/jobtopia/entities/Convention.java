/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Convention {
    
    private int id, clientID, freelancerID ;
    private Etat etat ;
    private String projectName;
    private Date startDate, endDate ;
    private Double paymentAmount ;
    private String freelancerUsername;
    
    public Convention(){ 
        
    }
    
    public Convention(int clientID, int freelancerID, Etat etat, String projectName, Date startDate, Date endDate,Double paymentAmount) {
        this.clientID = clientID;
        this.freelancerID=freelancerID;
        this.etat = etat;
        this.projectName=projectName ;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentAmount=paymentAmount;
        
    }
    
    public Convention(int id) {
        this.id=id ;
    }
    
    public  Convention(String projectName, Date startDate, Date endDate,Double paymentAmount){
        this.projectName=projectName;
        this.startDate= startDate;
        this.endDate= endDate;
        this.paymentAmount=paymentAmount ;
        
    }
    
        public  Convention(int id , Etat etat, String projectName, Date startDate, Date endDate, Double paymentAmount){
        this.id=id;
        this.etat=etat;
        this.projectName=projectName;
        this.startDate= startDate;
        this.endDate= endDate;
        this.paymentAmount=paymentAmount ;
        
    }
        
        public Convention(int id, Etat etat){
            this.id=id;
            this.etat=etat;
        }
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getFreelancerID() {
        return freelancerID;
    }

    public void setFreelancerID(int freelancerID) {
        this.freelancerID = freelancerID;
    }

   

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getFreelancerUsername() {
        return freelancerUsername;
    }

    public void setFreelancerUsername(String freelancerUsername) {
        this.freelancerUsername = freelancerUsername;
    }

    @Override
    public String toString() {
        return "Convention{" + "id=" + id + ", clientID=" + clientID + ", freelancerID=" + freelancerID + ", etat=" + etat + ", projectName=" + projectName + ", startDate=" + startDate + ", endDate=" + endDate + ", paymentAmount=" + paymentAmount + ", freelancerUsername=" + freelancerUsername + '}';
    }
    

    
    



}

    
    
    

