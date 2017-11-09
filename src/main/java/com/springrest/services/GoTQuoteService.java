package com.springrest.services;

/*
created by PopoPenguin on 11/1/17
*/

import com.springrest.mappers.GoTMapper;
import com.springrest.model.GoTQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class GoTQuoteService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GoTMapper goTMapper;

    //get all users using com
    public ArrayList<GoTQuote> getAllQuotes (){
        return goTMapper.getAllQuotes();
    }

    //get user by id
    public GoTQuote getById(int id){
        return goTMapper.getByID(id);
    }

    //add new quote
    public GoTQuote addNew(GoTQuote goTQuote) {
        goTMapper.insertQuote(goTQuote);
        return goTMapper.getByCharacter(goTQuote.getQuote());
    }

    //update user by its id
    public GoTQuote updateById(GoTQuote goTQuote) {
        goTMapper.updateQuote(goTQuote);
        return goTMapper.getByCharacter(goTQuote.getQuote());
    }

    //delete
    public GoTQuote deleteById(int id) {
        goTMapper.deleteQuote(id);
        return goTMapper.getByID(id);
    }


    public GoTQuote getGoTQuote() {
            GoTQuote test = restTemplate.getForObject("https://got-quotes.herokuapp.com/quotes", GoTQuote.class);
            insertGoTQuote(test);
            return test;

    }

    public ArrayList<GoTQuote> insertMultiGoTQuote() {

        for (int i = 0; i < 5; i++) {
            GoTQuote test = restTemplate.getForObject("https://got-quotes.herokuapp.com/quotes", GoTQuote.class);
            insertGoTQuote(test);
        }
        return getAllQuotes();
    }

    public void insertGoTQuote (GoTQuote goTQuote){
        // for every result (story) in the NYT response

        String id;


        try {
            id = goTMapper.getByQuote(goTQuote.getQuote());

        } catch (Exception e) {
            id = null;
        }
        // check to see if the story already exists in our DB
        if (id == null) {
            goTMapper.insertQuote(goTQuote);
        }
    }

}
