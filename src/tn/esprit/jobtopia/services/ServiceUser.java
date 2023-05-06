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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.jobtopia.entities.Freelancer;
import tn.esprit.jobtopia.entities.User;
import tn.esprit.jobtopia.gui.ListFreelancerForm;

/**
 *
 * @author Administrateur
 */
public class ServiceUser {
     public static ServiceUser instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public User login(String username,String password) throws IOException {
      String url = "http://127.0.0.1:8000/UserJson/" +username+ "/" +password;
                  System.out.println(url);

req.setUrl(url);
req.setPost(false);
 User user = new User();
req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
        String json = new String(req.getResponseData());
       
     if(json.equals("invalid")){
         User user= new User();
          System.out.println("invalid");
     }
     else{
        JSONParser parser = new JSONParser();
        try {
            Map<String, Object> userMap = parser.parseJSON(new CharArrayReader(json.toCharArray()));
            String nom = (String) userMap.get("nom");
            String prenom = (String) userMap.get("prenom");
            String email = (String) userMap.get("email");
            String role = (String) userMap.get("role");
           
         float id = Float.parseFloat(userMap.get("id").toString());
            String photoData = (String) userMap.get("imagepath");
            user.setId((int)id);
            user.setRole(role);
           user.setNom(nom);
           user.setPrenom(prenom);
           user.setEmail(email);

            System.out.println(user);
          //  float salaire = Float.parseFloat((String) freelancerMap.get("salaire"));

            
            // do something with the freelancer object
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     }
        req.removeResponseListener(this);
    }
});
NetworkManager.getInstance().addToQueueAndWait(req);

        return user;
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
