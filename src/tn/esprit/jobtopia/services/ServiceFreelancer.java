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

                     float salaire = (float) Math.floor((double) freelancerMap.get("salaire"));
                    
                    String photoData = (String) freelancerMap.get("imagepath");

                    fr.setNom(nom);
                    fr.setPrenom(prenom);
                    fr.setEmail(email);
                    fr.setDescription(description);
                    fr.setImagePath(photoData);
                    fr.setCategorie(categorie);
                    fr.setTelephone(Telephone);
                    fr.setSalaire(salaire);

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
      public ArrayList<Freelancer> Search(String search) {
        String url = "http://127.0.0.1:8000/searchJson?search="+ search ;
        System.out.println(url);
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
        ArrayList<Freelancer> fr = parseFreelancer(new String(req.getResponseData()));

        return fr;
    }

    public Boolean Modif(Freelancer f) {
        String url = "http://127.0.0.1:8000/FreelancerJson/edit" + "?id=" + f.getId() + "&nom=" + f.getNom() + "&prenom=" + f.getPrenom() + "&telephone=" + f.getTelephone() + "&email=" + f.getEmail() + "&description=" + f.getDescription() + "&categorie=" + f.getCategorie();
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
        String rep = new String(req.getResponseData());

        return true;
    }
    
    public Boolean Noter(int id,int value) {
        String url = "http://127.0.0.1:8000/freelancerRate?value="+value+"&id="+id;
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
        String rep = new String(req.getResponseData());

        return true;
    }
//      public Boolean Ajout(Freelancer f) {
//        String url = "http://127.0.0.1:8000/FreelancerJson/add"+ "?nom="+f.getNom()+"&prenom="+f.getPrenom()+"&telephone="+f.getTelephone()+"&username="+f.getUsername()+"&email="+f.getEmail()+"&description="+f.getDescription()+"&password="+f.getPassword()+"&categorie="+f.getCategorie();
//       System.out.println(url);
//        req.setUrl(url);
//        req.setPost(true);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//               // ArrayList<Freelancer> tasks = parseFreelancer(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//    String rep= new String(req.getResponseData());
//
//        return true;
//    }

    public boolean Ajout(Freelancer f) {
        MultipartRequest cr = new MultipartRequest();
        cr.setFilename("file", "Item.jpg");

        String url = "http://127.0.0.1:8000/FreelancerJson/add" + "?nom=" + f.getNom() + "&prenom=" + f.getPrenom() + "&telephone=" + f.getTelephone() + "&username=" + f.getUsername() + "&email=" + f.getEmail() + "&description=" + f.getDescription() + "&password=" + f.getPassword() + "&categorie=" + f.getCategorie() + "&file=" + f.getImagePath();

        //String url = Constant.API_URL + "items/addItem" + "?description=" + description + "&name=" + name + "&location=" + location + "&file=" + file;
        System.out.println(url);
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
        cr.addArgumentNoEncoding("description", f.getDescription());
        cr.addArgumentNoEncoding("password", f.getPassword());
        cr.addArgumentNoEncoding("categorie", f.getCategorie());
        cr.addArgumentNoEncoding("image", f.getImagePath());

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
                 float note = Float.parseFloat(obj.get("note").toString());
                  float nbAvis = Float.parseFloat(obj.get("nbavis").toString());
                  t.setNote(note/nbAvis);
                // t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                t.setId((int) id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                t.setCategorie(obj.get("categorie").toString());
              // System.out.println(id);
                freelancers.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return freelancers;
    }

}
