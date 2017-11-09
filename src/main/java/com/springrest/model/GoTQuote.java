package com.springrest.model;

/*
created by PopoPenguin on 11/1/17
*/


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoTQuote {

    private int id;
    private String quote;
    private String character;
    private int isActive;



    public GoTQuote() {
    }


    public GoTQuote(String quote, String character) {
        this.quote = quote;
        this.character = character;
    }

    public GoTQuote(int id, String quote, String character, int isActive) {
        this.id = id;
        this.quote = quote;
        this.character = character;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}