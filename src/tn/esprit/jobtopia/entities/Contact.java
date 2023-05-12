/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author saddo
 */
public class Contact {
    private int id;
    private String username;
    private String imagePath;
    private String email;

    public Contact() {
    }

    
    public Contact(int id, String username, String imagePath, String email) {
        this.id = id;
        this.username = username;
        this.imagePath = imagePath;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", username=" + username + ", imagePath=" + imagePath + ", email=" + email + '}';
    }
   
    
}
