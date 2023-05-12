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

public class Message {
    private int id, receiverId, senderId;
    private String contenu;
    private String date;

    public Message() {
    }

    public Message(int id, int receiverId, int senderId, String contenu, String date) {
        this.id = id;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.contenu = contenu;
        this.date = date;
    }
    


    
    public int getId() {
        return id;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getContenu() {
        return contenu;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", receiverId=" + receiverId + ", senderId=" + senderId + ", contenu=" + contenu + ", date=" + date + '}';
    }

    
    
    
}
