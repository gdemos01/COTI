package com.app.coti.coti.models;

/**
 * Created by User on 28/01/2017.
 */

public class Knowledge {

    int id;
    String knowledge;
    int yes;
    int no;
    int total;
    int bans;

    public Knowledge(){
        this.id =0;
        this.yes=0;
        this.no=0;
        this.total=0;
        this.bans=0;
        this.knowledge="";
    }

    public Knowledge(int id, String knowledge, int yes, int no, int total, int bans) {
        this.id = id;
        this.knowledge = knowledge;
        this.yes = yes;
        this.no = no;
        this.total = total;
        this.bans = bans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public int getYes() {
        return yes;
    }

    public void setYes(int yes) {
        this.yes = yes;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBans() {
        return bans;
    }

    public void setBans(int bans) {
        this.bans = bans;
    }
}
