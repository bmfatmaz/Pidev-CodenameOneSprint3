/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import tn.esprit.entities.Candidature;
import tn.esprit.gui.ListCandidatureForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.gui.DetailsCandidForm;

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
        String url = "http://127.0.0.1:8000/addCandidatureJson" + "?freelancerID=" + c.getfreelancerID() + "&offreID=" + c.getOfferID() + "&lettremotivation=" + c.getLettreMotivation() + "&cv=" + c.getCv();
        System.out.println(url);
        req.setUrl(url);
        req.setPost(true);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    public ArrayList<Candidature> ShowAllCandidatures() {

        String url = "http://127.0.0.1:8000/listCandidaturesJson";
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent t) {
                ArrayList<Candidature> candid = parseCandidature(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        ArrayList<Candidature> candid = parseCandidature(new String(req.getResponseData()));
        return candid;

    }

    public ArrayList<Candidature> ShowFreelancerCandid(int freelancerid) {

        String url = "http://127.0.0.1:8000/listFreelancer/" + 25;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent t) {
                ArrayList<Candidature> candid = parseCandidature(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        ArrayList<Candidature> candid = parseCandidature(new String(req.getResponseData()));
        return candid;

    }

    public ArrayList<Candidature> ShowOffreCandid(int offreid) {

        String url = "http://127.0.0.1:8000/listOffre/2";
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        Candidature c = new Candidature();
        /*req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent t) {
                ArrayList<Candidature> candid = parseCandidature(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });*/
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(req.getResponseData());
                JSONParser parser = new JSONParser();
                try {
                    Map<String, Object> CandidatureMap = parser.parseJSON(new CharArrayReader(json.toCharArray()));
                    String LettreMotivation = (String) CandidatureMap.get("lettremotivation");
                    String etat = (String) CandidatureMap.get("etat");

                    c.setLettreMotivation(LettreMotivation);
                    c.setEtatID(etat);

                    System.out.println(c);
                    // do something with the freelancer object
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        ArrayList<Candidature> candid = parseCandidature(new String(req.getResponseData()));
        return candid;

    }

    public Candidature getOneCandidature(int id) throws IOException {

        String url = "http://127.0.0.1:8000/CandidatureJson/" + id;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        Candidature c = new Candidature();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(req.getResponseData());
                JSONParser parser = new JSONParser();
                try {
                    Map<String, Object> CandidatureMap = parser.parseJSON(new CharArrayReader(json.toCharArray()));
                    String LettreMotivation = (String) CandidatureMap.get("lettremotivation");
                    String etat = (String) CandidatureMap.get("etat");

                    c.setLettreMotivation(LettreMotivation);
                    c.setEtatID(etat);

                    System.out.println(c);
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

    public Candidature readLettre(int id) {
        String url = "http://127.0.0.1:8000/readLettreJson/" + id;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
        Candidature c = new Candidature();
        request.addResponseListener((NetworkEvent evt) -> {
            byte[] responseData = request.getResponseData();
            if (responseData != null) {
                String json = new String(responseData);
            }
        });

        NetworkManager.getInstance().addToQueue(request);
        return c;
    }
    public boolean Refuser(int id) {
        String url = "http://127.0.0.1:8000/refuser/" + id;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);
        Candidature c = new Candidature();
        request.addResponseListener((NetworkEvent evt) -> {
            byte[] responseData = request.getResponseData();
            if (responseData != null) {
                String json = new String(responseData);
            }
        });

        NetworkManager.getInstance().addToQueue(request);
        return true;
    }
    public Candidature Accepter(int id) {
        String url = "http://127.0.0.1:8000/accepter/" + id+"?etat=Accepté";
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);
        Candidature c = new Candidature();
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return c;
    }

    public boolean Del(int id) {
        String url = "http://127.0.0.1:8000/deleteCandidJSON/" + id;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
        Candidature c = new Candidature();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] responseData = request.getResponseData();
                if (responseData != null) {
                    String json = new String(responseData);
                }
                req.removeResponseListener(this);

            }
        });

        NetworkManager.getInstance().addToQueue(request);
        return true;
    }

    /*
    public Boolean Modif(Candidature c) {
        String url = "http://127.0.0.1:8000/updateCandidJSON/edit" + "?id=" + c.getId() + "&lettremotivation=" + c.getLettreMotivation() + "&CV=" + c.getCv();
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
    }*/
    public Boolean Modif(Candidature c) {
        String url = "http://127.0.0.1:8000/Candid/edit" + "?id=" + c.getId() + "&lettremotivation=" + c.getLettreMotivation();
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
        String rep = new String(req.getResponseData());

        return true;
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
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                //c.setEtatID(obj.get("etat").toString());
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
