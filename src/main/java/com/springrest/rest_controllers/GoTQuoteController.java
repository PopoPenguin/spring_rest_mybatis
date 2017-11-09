package com.springrest.rest_controllers;

/*
created by PopoPenguin on 11/1/17
*/


import com.springrest.model.GoTQuote;
import com.springrest.services.GoTQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/GoT")
@RestController
public class GoTQuoteController {

    @Autowired
    GoTQuoteService goTQuoteService;

    @RequestMapping("/quote")
    public GoTQuote getGoTQuote(){

        return goTQuoteService.getGoTQuote();
    }

    @RequestMapping("/quote/multi")
    public ArrayList<GoTQuote> insertMultiGotQuote(){

        return goTQuoteService.insertMultiGoTQuote();
    }

    //Get
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ArrayList<GoTQuote> getAllQuotes() {

        return goTQuoteService.getAllQuotes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public GoTQuote getById(@PathVariable(value="id")int id) {

        return goTQuoteService.getById(id);
    }


    //Create
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public GoTQuote addNew(@RequestBody GoTQuote goTQuote) {

        return goTQuoteService.addNew(goTQuote);
    }

    //Update
    @RequestMapping(method = RequestMethod.PATCH, value = "/")
    public GoTQuote updateById(@RequestBody GoTQuote goTQuote) {

        return goTQuoteService.updateById(goTQuote);
    }

    //Delete
    @RequestMapping(method= RequestMethod.DELETE, value="/")
    public GoTQuote deleteById(@RequestBody GoTQuote goTQuote){

        return goTQuoteService.deleteById(goTQuote.getId());
    }

}
