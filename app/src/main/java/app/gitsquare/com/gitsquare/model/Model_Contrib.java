package app.gitsquare.com.gitsquare.model;

/**
 * Created by admin on 08-03-2018.
 */

public class Model_Contrib {
    String login="";
    String imagePath="";
    String respons_url="";
    int contribution=0;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getRespons_url() {
        return respons_url;
    }

    public void setRespons_url(String respons_url) {
        this.respons_url = respons_url;
    }

    public int getContribution() {
        return contribution;
    }

    public void setContribution(int contribution) {
        this.contribution = contribution;
    }
}
