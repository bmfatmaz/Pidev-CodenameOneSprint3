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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;

import tn.esprit.jobtopia.entities.Convention;
import tn.esprit.jobtopia.entities.Etat;

/**
 *
 * @author ASUS
 */
public class ServiceConvention {

    public ConnectionRequest req;
    public boolean resultOK;
    private static ServiceConvention instance;
    ArrayList<Convention> conventions = new ArrayList<>();

    public ServiceConvention() {
        req = new ConnectionRequest();
    }

    public static ServiceConvention getInstance() {
        if (instance == null) {
            instance = new ServiceConvention();
        }
        return instance;
    }

    public ArrayList<Convention> searchByProjectName(int clientId, String projectName) {
        ArrayList<Convention> matchingConventions = new ArrayList<>();

        ArrayList<Convention> allConventions = getConventionsByClientId(clientId); // Pass the clientId as an argument
        for (Convention convention : allConventions) {
            if (convention.getProjectName().toLowerCase().contains(projectName.toLowerCase())) {
                matchingConventions.add(convention);
            }
        }

        return matchingConventions;
    }

    public boolean addConvention(Convention convention) {
        String projectName = convention.getProjectName();
        String startDate = formatDate(convention.getStartDate());
        String endDate = formatDate(convention.getEndDate());
        double paymentAmount = convention.getPaymentAmount();
        int freelancerId = convention.getFreelancerID();

        String url = Connection.BASE_URL + "/conventions/json/new?projectName=" + projectName
                + "&startDate=" + startDate + "&endDate=" + endDate
                + "&paymentAmount=" + paymentAmount + "&freelancerId=" + freelancerId+"&clientid="+convention.getClientID();

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // HTTP 200 OK
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    
    

    public Convention getConvention(int id) throws IOException {
        String url = "http://127.0.0.1:8000/getConventionJSON/" + id;
        req.setUrl(url);
        req.setPost(false);
        Convention c = new Convention();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(req.getResponseData());
                JSONParser parser = new JSONParser();
                try {
                    Map<String, Object> conventionMap = parser.parseJSON(new CharArrayReader(json.toCharArray()));

                    String projectName = (String) conventionMap.get("projectName");
                    String startDateStr = (String) conventionMap.get("startDate");
                    Date startDate = null;
                    try {
                        startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String endDateStr = (String) conventionMap.get("endDate");
                    Date endDate = null;
                    try {
                        endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String etat = (String) conventionMap.get("etat");
                    double paymentAmount = Double.parseDouble(conventionMap.get("paymentAmount").toString());

                    c.setProjectName(projectName);
                    c.setStartDate(startDate);
                    c.setEndDate(endDate);
                    c.setEtat(Etat.EnAttente);
                    c.setPaymentAmount(paymentAmount);

                    System.out.println(c);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return c;
    }

    public ArrayList<Convention> getConventionsByClientId(int clientId) {
        String url = "http://127.0.0.1:8000/conventions/json/client/" + clientId;
        req.setUrl(url);
        req.setPost(false);
        conventions.clear();

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> conventionsJson = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    ArrayList<Map<String, Object>> conventionsListJson = (ArrayList<Map<String, Object>>) conventionsJson.get("root");

                    for (Map<String, Object> conventionJson : conventionsListJson) {
                        Convention convention = parseConventionList(conventionJson);
                        conventions.add(convention);
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return conventions;
    }

    private Convention parseConventionList(Map<String, Object> conventionJson) {
        Convention convention = new Convention();
        convention.setId(((Double) conventionJson.get("id")).intValue());
        convention.setProjectName((String) conventionJson.get("projectName"));
        String startDateStr = (String) conventionJson.get("startDate");
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        convention.setStartDate(startDate);

        String endDateStr = (String) conventionJson.get("endDate");
        Date endDate = null;
        try {
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        convention.setEndDate(endDate);

        convention.setPaymentAmount(Double.parseDouble(conventionJson.get("paymentAmount").toString()));
//        convention.setEtat(Etat.valueOf(conventionJson.get("etat").toString()));

        Map<String, Object> freelancerJson = (Map<String, Object>) conventionJson.get("freelancerid");
        String username = (String) freelancerJson.get("username");
        convention.setFreelancerUsername(username);

        return convention;
    }

}
