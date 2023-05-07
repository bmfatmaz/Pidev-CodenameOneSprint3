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
import com.codename1.ui.events.ActionListener;
import entities.Candidature;
import gui.ListCandidatureForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ServiceCandidature {

    public ConnectionRequest req;
    public boolean resultOK;
    private static ServiceCandidature instance;
    ArrayList<Candidature> candid = new ArrayList<>();

    public ServiceCandidature() {
        req = new ConnectionRequest();
    }

    public static ServiceCandidature getInstance() {
        if (instance == null) {
            instance = new ServiceCandidature();
        }
        return instance;
    }

    
    public boolean AjouterCandidature(Candidature c) {
        String url = "http://127.0.0.1:8000/addCandidJSON/" + "new"+"?freelancerID=" + c.getfreelancerID()+ "&offreID=" + c.getOfferID()+ "&etat=EnAttente" +"&lettremotivation="+ c.getLettreMotivation()+"&cv=myfile";
        System.out.println(url);
        req.setUrl(url);
        
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
    /*
    public boolean AjouterCandidature(Candidature o) {
        String url = "http://127.0.0.1:8000/addCandidJSON/new" + "?freelancerID=" + o.getfreelancerID()+ "&offreID=" + o.getOfferID()+ "&etat=EnAttente" +"&lettremotivation="+ o.getLettreMotivation();
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
    }*/

    public Candidature getOffre() throws IOException {
        String url = "http://127.0.0.1:8000/listCandidaturesJson" + ListCandidatureForm.cadidid;
        req.setUrl(url);
        req.setPost(false);
        Candidature fr = new Candidature();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(req.getResponseData());
                JSONParser parser = new JSONParser();
                try {
                    Map<String, Object> offreMap = parser.parseJSON(new CharArrayReader(json.toCharArray()));
                    String titre = (String) offreMap.get("lettremotivation");
                    String description = (String) offreMap.get("cv");
                    String skill1 = (String) offreMap.get("etat");

                    fr.setLettreMotivation(titre);
                    fr.setCv(description);
                    fr.setEtatID(skill1);;

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

    public ArrayList<Candidature> ShowAllCandidatures() {

        String url = "http://127.0.0.1:8000/listCandidaturesJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent t) {
                candid = parseCandidature(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return candid;

    }

    private ArrayList<Candidature> parseCandidature(String jsonText) {
        ArrayList<Candidature> candid = new ArrayList<>();
        try {

            JSONParser j = new JSONParser();
            Map<String, Object> candidListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(candidListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) candidListJson.get("root");
            for (Map<String, Object> obj : list) {
                Candidature c = new Candidature();
                c.setCv(obj.get("cv").toString());
                c.setLettreMotivation(obj.get("lettremotivation").toString());
                //String LettreMotivation = "null";
                //String cv = obj.get("cv").toString();
                //String Score = obj.get("score").toString();
                candid.add(c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return candid;
    }

}
