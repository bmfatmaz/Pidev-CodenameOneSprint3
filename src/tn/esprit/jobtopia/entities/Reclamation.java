package tn.esprit.jobtopia.entities;

import java.util.Date;
import java.util.Objects;

public class Reclamation {
    
    private int id,userID,isDeleted;
    private String titre,type,description,etat;
    private Date date;
    
    public Reclamation() {}
    
        public Reclamation(int id) {
        this.id = id;
    }
    
    public Reclamation(String etat) {
        this.etat = etat;
    }
    
    public Reclamation(String titre, String type, String description) {
        this.titre = titre;
        this.type = type;
        this.description = description;
    }
    
    public Reclamation(int id,String titre, String type, String description) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }
    
    public int getUserID() {
        return userID;
    }
    
    public int getIsDeleted() {
        return isDeleted;
    }

    public String getTitre() {
        return titre;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
    public String getValue1(String column) {
        switch (column) {
            case "Titre":
                return titre;
            /*    
            case "Nom de l'utilisateur":
                return username; TO DO FATMA :na7eha ki tkamel l5edma
            */
            case "Description":
                return description;         
            case "Type":
                return type; 
            case "Etat":
                return etat;  
            case "Date":
                return date.toString();                
           default:
                    return "";
           }
    }
    
    public String getValue2(String column) {
        switch (column) {
            case "Titre":
                return titre;
            case "Description":
                return description;         
            case "Type":
                return type; 
            case "Etat":
                return etat;  
            case "Date":
                return date.toString();                
           default:
                    return "";
           }
    }
    
    @Override
    public String toString() {
        return "Reclamation{" + ", titre=" + titre + ", type=" + type + "userID=" + userID + ", description=" + description + ", etat=" + etat + ", date=" + date + '}';
    }
    
    
}

