/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import entities.Contact;
import entities.Message;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author saddo
 */
public class MessageService {

    ArrayList<Contact> contacts;
    ArrayList<Message> messages;
    public static MessageService instance = null;

    public ArrayList<Contact> parseContacts(String jsonText) {
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> MessageListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) MessageListJson.get("contacts");
            for (Map<String, Object> obj : list) {
                Contact c = new Contact();
                float id = Float.parseFloat(obj.get("contactId").toString());
                c.setId((int) id);
                c.setUsername(obj.get("username").toString());
                c.setImagePath(obj.get("imagepath").toString());
                c.setEmail(obj.get("email").toString());
                boolean contactExists = false;
                for (Contact existingContact : contacts) {
                    if (existingContact.getId() == c.getId()) {
                        contactExists = true;
                        break;
                    }
                }
                if (!contactExists) {
                    contacts.add(c);
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return contacts;
    }

    public ArrayList<Contact> getAllContacts() {
        String url = "http://127.0.0.1:8000/messagesJson";
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                contacts = parseContacts(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return contacts;
    }

}
