package tn.esprit.jobtopia.entities;

public class CurrentUser {
      private static CurrentUser instance = null;
    private int id;
    

    private CurrentUser() {}

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
