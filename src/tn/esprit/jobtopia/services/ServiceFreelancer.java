/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import tn.esprit.jobtopia.entities.Freelancer;

/**
 *
 * @author Administrateur
 */
public class ServiceFreelancer {

    public static ServiceFreelancer instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceFreelancer() {
        req = new ConnectionRequest();
    }

    public static ServiceFreelancer getInstance() {
        if (instance == null) {
            instance = new ServiceFreelancer();
        }
        return instance;
    }

    public ArrayList<Freelancer> getAllTasks() {
        String url = "http://127.0.0.1:8000/listFreelancersJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ArrayList<Freelancer> tasks = parseFreelancer(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        ArrayList<Freelancer> tasks = parseFreelancer(new String(req.getResponseData()));

        return tasks;
    }

    public ArrayList<Freelancer> parseFreelancer(String jsonText) {
        ArrayList<Freelancer> freelancers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> freeListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(freeListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) freeListJson.get("root");
            for (Map<String, Object> obj : list) {
                Freelancer t = new Freelancer();
                t.setImagePath(obj.get("imagepath").toString());

                  float id = Float.parseFloat(obj.get("id").toString());
                // t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                t.setId((int) id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                t.setCategorie(obj.get("categorie").toString());
                

                freelancers.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return freelancers;
    }

}
