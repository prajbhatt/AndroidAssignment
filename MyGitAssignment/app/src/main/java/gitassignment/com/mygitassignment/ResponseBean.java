package gitassignment.com.mygitassignment;

/**
 * Created by admin on 10/6/2015.
 *
 */
public class ResponseBean {


    public String getCommitterNAme() {
        return committerNAme;
    }

    public void setCommitterNAme(String committerNAme) {
        this.committerNAme = committerNAme;
    }

    private String committerNAme,autherNAme,message,date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAutherNAme() {
        return autherNAme;
    }

    public void setAutherNAme(String autherNAme) {
        this.autherNAme = autherNAme;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
