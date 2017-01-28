package com.app.coti.coti.models;

import java.util.ArrayList;

/**
 * Created by User on 28/01/2017.
 */

public class PositiveResponse {

    private ArrayList<String> responses;
    private ArrayList<String> agreement;
    private int amount;

    public  PositiveResponse(){
        responses = new ArrayList<>();
        agreement = new ArrayList<>();
        populateResponses();
        populateAgreement();
        amount = responses.size();
    }

    public void populateAgreement(){
        agreement.add("I knew it!");
        agreement.add("Nice!");
        agreement.add("Cool");
    }

    public void populateResponses(){
        responses.add("Yes!");
        responses.add("Absolutely!");
        responses.add("I agree");
    }

    public int getAmount() {
        return amount;
    }

    public String getResponse(int i){
        return responses.get(i);
    }

    public String getAgreement(int i){return agreement.get(i);}
}
