/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Map;
import tn.esprit.jobtopia.entities.Client;
import tn.esprit.jobtopia.entities.Freelancer;

/**
 *
 * @author Administrateur
 */
public class ServiceClient {
     public static ServiceClient instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceClient() {
        req = new ConnectionRequest();
    }

    public static ServiceClient getInstance() {
        if (instance == null) {
            instance = new ServiceClient();
        }
        return instance;
    }
     public Client getClient(int id) throws IOException {
        String url = "http://127.0.0.1:8000/UserJson/" + id;
        req.setUrl(url);
        req.setPost(false);
        Client c = new Client();
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
                    String prof = (String) freelancerMap.get("profession");
                    //  float salaire = Float.parseFloat((String) freelancerMap.get("salaire"));
                    String photoData = (String) freelancerMap.get("imagepath");

                    c.setNom(nom);
                    c.setPrenom(prenom);
                    c.setEmail(email);
                   // c.setDescription(description);
                    c.setImagePath(photoData);
                    //c.setCategorie(categorie);
                    c.setTelephone(Telephone);
                    c.setProfession(prof);
                    //  fr.setSalaire(salaire);

                   // System.out.println(fr);
                    // do something with the freelancer object
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return c;
    }
       public Boolean Ajout(Client f) {
        String url = "http://127.0.0.1:8000/ClientJson/add"+ "?nom="+f.getNom()+"&prenom="+f.getPrenom()+"&telephone="+f.getTelephone()+"&username="+f.getUsername()+"&email="+f.getEmail()+"&profession="+f.getProfession()+"&password="+f.getPassword()+ "&file=" + f.getImagePath();
         MultipartRequest cr = new MultipartRequest();
        cr.setFilename("file", "Item.jpg");
        System.out.println(url);
        req.setUrl(url);
        req.setPost(true);
        cr.setHttpMethod("POST");
        cr.setUrl(url);
        try {
            cr.addData("file", f.getImagePath(), "image/png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        cr.addArgumentNoEncoding("nom", f.getNom());
        cr.addArgumentNoEncoding("prenom", f.getPrenom());
        cr.addArgumentNoEncoding("telephone", f.getTelephone());
        cr.addArgumentNoEncoding("username", f.getUsername());
        cr.addArgumentNoEncoding("email", f.getEmail());
        cr.addArgumentNoEncoding("password", f.getPassword());
        cr.addArgumentNoEncoding("image", f.getImagePath());
        cr.addArgumentNoEncoding("profession", f.getProfession());

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cr.getResponseCode() == 200; //Code HTTP 200 OK
                cr.removeResponseListener(this);
                // Handle the response from the server
            }
        });
        cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return resultOK;
    }
    
      public Boolean Modif(Client f) {
        String url = "http://127.0.0.1:8000/ClientJson/edit"+"?id="+f.getId()+ "&nom="+f.getNom()+"&prenom="+f.getPrenom()+"&telephone="+f.getTelephone()+"&email="+f.getEmail()+"&profession="+f.getProfession();
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
    
}
