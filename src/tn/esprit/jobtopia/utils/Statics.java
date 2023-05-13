
package tn.esprit.jobtopia.utils;

public class Statics {
    public static final String BASE_URL="http://127.0.0.1:8000/";
}

//package tn.esprit.jobtopia.services;
//
//import java.util.HashMap;
//import com.codename1.io.CharArrayReader;
//import com.codename1.io.JSONParser;
//import com.codename1.io.NetworkEvent;
//import com.codename1.io.NetworkManager;
//import com.codename1.ui.events.ActionListener;
//import tn.esprit.jobtopia.entities.Reclamation;
//import tn.esprit.jobtopia.entities.User;
//import tn.esprit.jobtopia.gui.AjouterReclamationForm;
//import tn.esprit.jobtopia.gui.ModifierReclamationForm;
//import tn.esprit.jobtopia.gui.AfficherReclamationForm;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Map;
//import com.codename1.io.ConnectionRequest;
//import java.util.List;
//
//public class ServiceReclamation {
//    
//    public boolean resultOK;
//    public ArrayList<Reclamation> reclamations;
//    public ConnectionRequest req;
//    
//    public static ServiceReclamation instance = null ;
//    
//    public static boolean resultOk = true;
//
//    public ServiceReclamation() {
//        req = new ConnectionRequest();
//    }
//
//    public static ServiceReclamation getInstance() {
//        if(instance == null )
//            instance = new ServiceReclamation();
//        return instance ;
//    }
//    
//    //ajouter réclamation
//    public boolean ajouterReclamation(Reclamation r) {
//        
//        String url ="http://127.0.0.1:8000/reclamation/ajouterJSON?titre="+r.getTitre()+"&description="+r.getDescription()+"&type="+r.getType();
//        
//        req.setUrl(url);
//        req.setPost(false);
//        
//        req.addArgument("titre",r.getTitre());
//        // req.addArgument("userID",Integer.toString(d.getUserID()));                     §§§§§  current userID   §§§§§
//        req.addArgument("description",r.getDescription());
//        req.addArgument("type",r.getType());
//        
//
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent ne) {
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//
////    //affichage
////     public ArrayList<Reclamation> parseReclamations(String jsonText) {
////        try {
////            reclamations = new ArrayList<>();
////            JSONParser j = new JSONParser();
////            Map<String, Object> reclamationsListJson= j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
////
////            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsListJson.get("root");
////            for (Map<String, Object> obj : list) {
////                Reclamation r = new Reclamation();
////                if (obj.get("id") == null)
////                    r.setId(0);
////                else {
////                float id = Float.parseFloat(obj.get("id").toString());
////                
////                r.setId((int) id);
////                }
////         
////                if (obj.get("titre") == null) {
////                    r.setTitre("null");
////                } else {
////                    r.setTitre(obj.get("titre").toString());
////                } 
////                
////                if (obj.get("type") == null) {
////                    r.setType("null");
////                } else {
////                    r.setType(obj.get("type").toString());
////                }
////                if (obj.get("description") == null) {
////                    r.setDescription("null");
////                } else {
////                    r.setDescription(obj.get("description").toString());
////                }
////                if (obj.get("etat") == null) {
////                    r.setEtat("null");
////                } else {
////                    r.setEtat(obj.get("etat").toString());
////                }
////                
////                reclamations.add(r);
////            }
////
////        } catch (IOException ex) {
////            System.out.println(ex.getMessage());
////        }
////        return reclamations;
////    }
////
////    public ArrayList<Reclamation> afficherReclamations() {
////      String url = "http://127.0.0.1:8000/reclamation/afficherJSON";
////       
////        req.setUrl(url);
////        req.setPost(false);
////        req.addResponseListener(new ActionListener<NetworkEvent>() {
////            @Override
////            public void actionPerformed(NetworkEvent evt) {
////                reclamations = parseReclamations(new String(req.getResponseData()));
////                req.removeResponseListener(this);
////            }
////        });
////        NetworkManager.getInstance().addToQueueAndWait(req);
////        return reclamations;
////        
////    }
//    public ArrayList<Reclamation> afficherReclamations() {
//    String url = "http://127.0.0.1:8000/reclamation/afficherJSON";
//       
//    req.setUrl(url);
//    req.setPost(false);
//    
//    req.addResponseListener(new ActionListener<NetworkEvent>() {
//        @Override
//        public void actionPerformed(NetworkEvent evt) {
//            reclamations = parseReclamations(new String(req.getResponseData()));
//            req.removeResponseListener(this);
//        }
//    });
//    
//    NetworkManager.getInstance().addToQueueAndWait(req);
//    return reclamations;
//}
//
//    // Modify the parseReclamations method to handle JSON directly
//    public ArrayList<Reclamation> parseReclamations(String jsonText) {
//        try {
//            reclamations = new ArrayList<>();
//
//            JSONParser j = new JSONParser();
//            List<Map<String, Object>> list = (List<Map<String, Object>>) j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//            for (Map<String, Object> obj : list) {
//                Reclamation r = new Reclamation();
//
//                if (obj.get("id") == null)
//                    r.setId(0);
//                else {
//                    float id = Float.parseFloat(obj.get("id").toString());
//                    r.setId((int) id);
//                }
//
//                if (obj.get("titre") == null)
//                    r.setTitre("null");
//                else
//                    r.setTitre(obj.get("titre").toString());
//
//                if (obj.get("type") == null)
//                    r.setType("null");
//                else
//                    r.setType(obj.get("type").toString());
//
//                if (obj.get("description") == null)
//                    r.setDescription("null");
//                else
//                    r.setDescription(obj.get("description").toString());
//
//                if (obj.get("etat") == null)
//                    r.setEtat("null");
//                else
//                    r.setEtat(obj.get("etat").toString());
//
//                reclamations.add(r);
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return reclamations;
//    }
//
//    public boolean SupprimerReclamation(int id) {
//        String url = "http://127.0.0.1:8000/reclamation/supprimerJSON/"+Integer.toString(id);
//        
//
//        System.out.print(url);
//        req.setUrl(url);
//        req.setPost(false);
//
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200;
//                req.removeResponseListener(this);
//            }
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//
//    //modifier réclamation
//    public boolean modifierReclamation(int id , Reclamation d) {
//        String url = "http://127.0.0.1:8000/reclamation/modifierJSON/"+Integer.toString(id);
//        
//        System.out.print(url);
//        req.setUrl(url);
//        req.setPost(false);
//        
//        req.addArgument("titre",d.getTitre());
//        req.addArgument("description",d.getDescription());
//        req.addArgument("type",d.getType());
//
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent ne) {
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//                
//        return resultOK;
//    }
//    
//        //rechercher réclamation
////    public Reclamation rechercherReclamation(int id){
////        reclamations = new ArrayList<>();
////    Reclamation d = new Reclamation();
////     String url = "http://127.0.0.1:8000/reclamation/rechercherJSON"+Integer.toString(id);
////        req.setUrl(url);
////        req.setPost(false);
////        
////    req.setUrl(url);
////    req.setPost(false);
////    
////        req.addResponseListener(new ActionListener<NetworkEvent>() {
////            @Override
////     public void actionPerformed(NetworkEvent evt) {
////                 reclamations=parseUser(new String(req.getResponseData()));
////                req.removeResponseListener(this);
////            }
////        });
////        NetworkManager.getInstance().addToQueueAndWait(req);
////        
////        d.toString();
////        return reclamations.get(0);
////    
////    }
////      public ArrayList<Reclamation> parseUser(String jsonText) {
////        try{
////            reclamations = new ArrayList<>();
////            JSONParser j = new JSONParser();
////            Map<String, Object> reclamationsListJson= new HashMap<>();
////                  reclamationsListJson.put("key",j.parseJSON(new CharArrayReader(jsonText.toCharArray())));
////
////           Map<String, Object> userJson = (Map<String, Object>) reclamationsListJson.get("key");
////             
////        Reclamation t = new Reclamation();
////        float id = Float.parseFloat(userJson.get("id").toString());
////        t.setId((int) id);
////
////        if (userJson.get("titre") == null) {
////            t.setTitre("null");
////        } else {
////            t.setTitre(userJson.get("titre").toString());
////        }
////        
////        if (userJson.get("description") == null) {
////            t.setDescription("null");
////        } else {
////            t.setDescription(userJson.get("description").toString());
////        }
////
////        if (userJson.get("type") == null) {
////            t.setType("null");
////        } else {
////            t.setType(userJson.get("type").toString());
////        }
////
////        reclamations.add(t);
////
////    } catch (IOException ex) {
////        System.out.println(ex.getMessage());
////    }
////     
////    return reclamations;
////}
//    
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package tn.esprit.jobtopia.services;
////
////import com.codename1.io.CharArrayReader;
////import com.codename1.io.JSONParser;
////import com.codename1.io.NetworkEvent;
////import com.codename1.io.NetworkManager;
////import com.codename1.l10n.SimpleDateFormat;
////import com.codename1.ui.events.ActionListener;
////import tn.esprit.jobtopia.entities.Reclamation;
////import tn.esprit.jobtopia.gui.AjouterReclamationForm;
////import java.io.IOException;
////import java.util.ArrayList;
////import java.util.Date;
////import java.util.Map;
////import com.codename1.io.ConnectionRequest;
////import java.util.List;
////
////public class ServiceReclamation {
////    
////    public ArrayList<Reclamation> reclamations;
////    
////    public static ServiceReclamation instance = null;
////    public boolean resultOK;
////    public ConnectionRequest req;
////
////    public ServiceReclamation() {
////        req = new ConnectionRequest();
////    }
////
////    public static ServiceReclamation getInstance() {
////        if (instance == null) {
////            instance = new ServiceReclamation();
////        }
////        return instance;
////    }
////    
////    public ArrayList<Reclamation> getReclamations(String jsonText) {
////        try {
////            reclamations = new ArrayList<>();
////            JSONParser j = new JSONParser();
////            Map<String, Object> reclamationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
////
////            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsListJson.get("root");
////            for (Map<String, Object> obj : list) {
////                
////                Reclamation r = new Reclamation();
////                
////                r.setId((int) Float.parseFloat(obj.get("id").toString()));
////                r.setTitre(obj.get("titre").toString());
////                r.setDescription(obj.get("description").toString());
////                r.setType(obj.get("type").toString());
////                r.setEtat(obj.get("etat").toString());
////                r.setDate((Date) obj.get("date"));
////                reclamations.add(r);
////            }
////
////        } catch (IOException ex) {
////            System.out.println(ex.getMessage());
////        }
////        return reclamations;
////    }
////    
////    //afficher toutes les réclamations de l'utilisateur:
////    public ArrayList<Reclamation> AfficherReclamation() {
////    String url = "http://127.0.0.1:8000/";
////    req.setUrl(url);
////    req.setPost(false);
////    req.addResponseListener(new ActionListener<NetworkEvent>() {
////        @Override
////        public void actionPerformed(NetworkEvent evt) {
////            reclamations = getReclamations(new String(req.getResponseData()));
////            req.removeResponseListener(this);
////        }
////    });
////    NetworkManager.getInstance().addToQueueAndWait(req);
////    return reclamations;
////    }
////    
////    //ajouter réclamation:
////     public boolean AjouterReclamation(Reclamation r) {
////         
////        String url = "http://127.0.0.1:8000/frontreclamations/ajouter"+ "?titre="+r.getTitre()+"&description="+r.getDescription()
////        +"&type="+r.getType();
////        
////        System.out.println(url);
////        req.setUrl(url);
////        req.setPost(true);
////        
////        req.addResponseListener(new ActionListener<NetworkEvent>() {
////            @Override
////            public void actionPerformed(NetworkEvent evt) {
////                req.removeResponseListener(this);
////            }
////        });
////        
////        NetworkManager.getInstance().addToQueueAndWait(req);
////        return true;
////    }
////     
////         public void ajoutReclamation(Reclamation reclamation) {
////        
////        String url ="http://127.0.0.1:8000/addReclamation?description="+reclamation.getDescription()+"&titre="+reclamation.getTitre(); 
////        
////        req.setUrl(url);
////        req.addResponseListener((e) -> {
////            
////            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
////            System.out.println("data == "+str);
////        });
////        
////        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
////        
////    }
////    
////    //modifier réclamation:
////    public Boolean ModifierReclamation(Reclamation r) {
////        
////        String url = "http://127.0.0.1:8000/frontreclamations/modifier"+"?id="+r.getId()+ "&titre="+r.getTitre()
////        +"&description="+r.getDescription()+"&type="+r.getType();
////        
////        System.out.println(url);
////        req.setUrl(url);
////        req.setPost(true);
////        
////        req.addResponseListener(new ActionListener<NetworkEvent>() {
////            @Override
////            public void actionPerformed(NetworkEvent evt) {
////                req.removeResponseListener(this);
////            }
////        });
////        
////        NetworkManager.getInstance().addToQueueAndWait(req);
////        String rep= new String(req.getResponseData());
////
////        return true;
////    }
////    
////}
