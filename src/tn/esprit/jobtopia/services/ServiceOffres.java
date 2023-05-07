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
import com.codename1.l10n.SimpleDateFormat;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import tn.esprit.jobtopia.entities.Offre;
import tn.esprit.jobtopia.gui.ListOffreForm;
import tn.esprit.jobtopia.gui.OffreClientForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import tn.esprit.jobtopia.entities.CurrentUser;


/**
 *
 * @author Salma Majeri
 */
public class ServiceOffres {

    public static ServiceOffres instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceOffres() {
        req = new ConnectionRequest();
    }

    public static ServiceOffres getInstance() {
        if (instance == null) {
            instance = new ServiceOffres();
        }
        return instance;
    }
  public Offre getOffre() throws IOException {
      String url = "http://127.0.0.1:8000/users/offres/" + ListOffreForm.offreid;
      System.out.println(ListOffreForm.offreid);
    req.setUrl(url);
    req.setPost(false);
    Offre fr = new Offre();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
        String json = new String(req.getResponseData());
        JSONParser parser = new JSONParser();
        try {
            Map<String, Object> offreMap = parser.parseJSON(new CharArrayReader(json.toCharArray()));
            String titre = (String) offreMap.get("titre");
            String description = (String) offreMap.get("description");
            String skill1 = (String) offreMap.get("skill1");
            String skill2 = (String) offreMap.get("skill2");
            String skill3 = (String) offreMap.get("skill3");
            String categorie = (String) offreMap.get("categorie");
         
          
            fr.setTitre(titre);
            fr.setDescription(description);
            fr.setSkill1(skill1);
            fr.setSkill2(skill2);
            fr.setSkill3(skill3);
            fr.setCategorie(categorie);


            System.out.println(fr);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        req.removeResponseListener(this);
    }
});
NetworkManager.getInstance().addToQueueAndWait(req);

        return fr;
    }

    public ArrayList<Offre> getAllTasks() {
        String url = "http://127.0.0.1:8000/users/offres";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ArrayList<Offre> tasks = parseOffres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return parseOffres(new String(req.getResponseData()));
    }

    public ArrayList<Offre> parseOffres(String jsonText) {
        ArrayList<Offre> offres = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> offreListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(offreListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) offreListJson.get("root");

            if (list != null) {
                for (Map<String, Object> obj : list) {
                    Offre o = new Offre();
                    float id = Float.parseFloat(obj.get("id").toString());
                    o.setId((int)id);
                  //  o.setLogoPath(obj.get("logoPath").toString());
                    o.setTitre(obj.get("titre").toString());
                    o.setDescription(obj.get("description").toString());
                    o.setCategorie(obj.get("categorie").toString());   
                                     offres.add(o);
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }
  public ArrayList<Offre> getTasks() {
    String url = "http://127.0.0.1:8000/users/offresClient/"+CurrentUser.getInstance().getId();
    req.setUrl(url);
    req.setPost(false);

    ArrayList<Offre> offres = new ArrayList<>();
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            ArrayList<Offre> tasks = parseOffre(new String(req.getResponseData()));
            offres.addAll(tasks);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return offres;
}

public ArrayList<Offre> parseOffre(String jsonText) {
    ArrayList<Offre> offres = new ArrayList<>();

    try {
        JSONParser j = new JSONParser();
        Map<String, Object> offreListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) offreListJson.get("root");

        if (list != null) {
            for (Map<String, Object> obj : list) {
                Offre o = new Offre();
                float id = Float.parseFloat(obj.get("id").toString());
               o.setId((int)id);
                o.setTitre(obj.get("titre").toString());
                o.setDescription(obj.get("description").toString());
                o.setEtat(obj.get("etat").toString());   
                o.setDateCreation(obj.get("dc").toString());   
                offres.add(o);
            }
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }

    return offres;
}

    
    
     public boolean Ajout(Offre o) {
        String url = "http://127.0.0.1:8000/OffreJson/add"+ "?titre="+o.getTitre()+"&description="+o.getDescription()+"&categorie="+o.getCategorie()+"&clientId="+o.getClientId();
       System.out.println(url);
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    //String rep= new String(req.getResponseData());

        return true;
    }
       public Boolean Modif(Offre o) {
        String url = "http://127.0.0.1:8000/OffreJson/edit/"+"?id="+o.getId()+ "&titre="+o.getTitre()+"&description="+o.getDescription()+"&categorie="+o.getCategorie();
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
      public Offre getOffreBYid() throws IOException {
      String url = "http://127.0.0.1:8000/users/offres/" + OffreClientForm.offreid;
      System.out.println(OffreClientForm.offreid);
    req.setUrl(url);
    req.setPost(false);
    Offre fr = new Offre();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
        String json = new String(req.getResponseData());
        JSONParser parser = new JSONParser();
        try {
            Map<String, Object> offreMap = parser.parseJSON(new CharArrayReader(json.toCharArray()));
            float id = Float.parseFloat(offreMap.get("id").toString());
            String titre = (String) offreMap.get("titre");
            String description = (String) offreMap.get("description");
            String categorie = (String) offreMap.get("categorie");
         System.out.println(id);
          fr.setId((int)id);
            fr.setTitre(titre);
            fr.setDescription(description);
            fr.setCategorie(categorie);


            System.out.println(fr);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        req.removeResponseListener(this);
    }
});
NetworkManager.getInstance().addToQueueAndWait(req);

        return fr;
    }
    }
