package com.app.coti.coti.models;

import java.util.ArrayList;

/**
 * Created by User on 28/01/2017.
 */

public class NegativeResponse {

    private ArrayList<String> responses;
    private ArrayList<String> disagreement;
    private int amount;

    public  NegativeResponse(){
        responses = new ArrayList<>();
        disagreement = new ArrayList<>();
        populateDisagreement();
        populateResponses();
        amount = responses.size();
    }

    public void populateDisagreement(){
        disagreement.add("Really?");
        disagreement.add("Really?");
        disagreement.add("hm...");
        disagreement.add("Correcting...");
        disagreement.add("Learning...");
    }

    public void populateResponses(){
        responses.add("NO");
        responses.add("Not really!");
        responses.add("I don't believe that");
        responses.add("I don't think so");
        responses.add("Wrong!");
    }

    public int getAmount() {
        return amount;
    }

    public String getResponse(int i){
        return responses.get(i);
    }

    public String getDisagreement(int i){return disagreement.get(i);}
}
