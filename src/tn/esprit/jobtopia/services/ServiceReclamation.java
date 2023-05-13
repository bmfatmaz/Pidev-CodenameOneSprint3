package tn.esprit.jobtopia.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import tn.esprit.jobtopia.entities.Reclamation;
import tn.esprit.jobtopia.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceReclamation {
    
    public static ServiceReclamation instance = null ;
    public static boolean resultOk = true;
    private ConnectionRequest req;
    
    
    public static ServiceReclamation getInstance() {
        if(instance == null )
            instance = new ServiceReclamation();
        return instance ;
    }

    public ServiceReclamation() {
        req = new ConnectionRequest();
        
    }

    //ajouter réclamation:
    public void ajouterReclamation(Reclamation reclamation) {
        
        String url ="http://127.0.0.1:8000/reclamation/ajouterJSON?titre="+reclamation.getTitre()+"&type="+reclamation.getType()
        +"&description="+reclamation.getDescription()+"&userID="+reclamation.getUserID();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });   
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    //afficher réclamations: 
    public ArrayList<Reclamation> afficherReclamations() {
        ArrayList<Reclamation> result = new ArrayList<>();
        
        String url = "http://127.0.0.1:8000/reclamation/afficherJSON";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Reclamation re = new Reclamation();
                        
                        float id = Float.parseFloat(obj.get("id").toString());  
                        String titre = obj.get("titre").toString();
                        String description = obj.get("description").toString();
                        String type = obj.get("type").toString();
                        String etat = obj.get("etat").toString();
                        
                        re.setId((int)id);
                        re.setTitre(titre);
                        re.setDescription(description);
                        re.setType(type);
                        re.setEtat(etat);
                        
                        result.add(re);
                    }   
                }catch(Exception ex) {    
                    ex.printStackTrace();
                }
            }
        }); 
        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;   
    }
    
    //afficher details réclamation:
    public Reclamation detailsRecalamation( int id , Reclamation reclamation) {
        
        String url = "http://127.0.0.1:8000/reclamation/detailsJSON?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                reclamation.setTitre(obj.get("titre").toString());
                reclamation.setType(obj.get("type").toString());
                reclamation.setDescription(obj.get("description").toString());
                reclamation.setEtat(obj.get("etat").toString());
    
            }catch(IOException ex) {
                System.out.println("message:"+ex.getMessage());
            }
     
            System.out.println("data === "+str);
  
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamation;

    }
 
    //supprimer réclamation: 
    public boolean supprimerReclamation(int id ) {
        String url = "http://127.0.0.1:8000/reclamation/supprimerJSON?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }

    //modifier réclamation: 
    public boolean modifierReclamation(Reclamation reclamation) {
        String url = "http://127.0.0.1:8000/reclamation/modifierJSON?id="+reclamation.getId()+"&titre="+reclamation.getTitre()
        +"&description="+reclamation.getDescription()+"&type="+reclamation.getType();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;
                req.removeResponseListener(this);
            }
        });    
        NetworkManager.getInstance().addToQueueAndWait(req);
    
        return resultOk; 
    }
    
}
