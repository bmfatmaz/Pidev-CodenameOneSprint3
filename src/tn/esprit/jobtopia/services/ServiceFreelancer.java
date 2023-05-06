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
//import java.util.logging.Level;
//import java.util.logging.Logger;
import tn.esprit.jobtopia.entities.Freelancer;
import tn.esprit.jobtopia.gui.ListFreelancerForm;

/**
 *
 * @author Administrateur
 */
public class ServiceFreelancer {

    public static ServiceFreelancer instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFreelancer() {
        req = new ConnectionRequest();
    }

    public static ServiceFreelancer getInstance() {
        if (instance == null) {
            instance = new ServiceFreelancer();
        }
        return instance;
    }

    public Freelancer getFreelancer(int id) throws IOException {
        String url = "http://127.0.0.1:8000/UserJson/" + id;
        req.setUrl(url);
        req.setPost(false);
        Freelancer fr = new Freelancer();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(req.getResponseData());
                JSONParser parser = new JSONParser();
                try {
                    Map<String, Object> freelancerMap = parser.parseJSON(new CharArrayReader(json.toCharArray()));
                    String nom = (String) freelancerMap.get("nom");
                    String prenom = (String) freelancerMap.get("prenom");
                    String email = (String) freelancerMap.get("email");
                    String description = (String) freelancerMap.get("description");
                    String categorie = (String) freelancerMap.get("categorie");
                    String Telephone = (String) freelancerMap.get("telephone");

                    //  float salaire = Float.parseFloat((String) freelancerMap.get("salaire"));
                    String photoData = (String) freelancerMap.get("imagepath");

                    fr.setNom(nom);
                    fr.setPrenom(prenom);
                    fr.setEmail(email);
                    fr.setDescription(description);
                    fr.setImagePath(photoData);
                    fr.setCategorie(categorie);
                    fr.setTelephone(Telephone);
                    //  fr.setSalaire(salaire);

                    System.out.println(fr);
                    // do something with the freelancer object
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return fr;
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
    
    public Boolean Modif(Freelancer f) {
        String url = "http://127.0.0.1:8000/FreelancerJson/edit"+"?id="+f.getId()+ "&nom="+f.getNom()+"&prenom="+f.getPrenom()+"&telephone="+f.getTelephone()+"&email="+f.getEmail()+"&description="+f.getDescription()+"&categorie="+f.getCategorie();
       System.out.println(url);
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // ArrayList<Freelancer> tasks = parseFreelancer(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    String rep= new String(req.getResponseData());

        return true;
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
