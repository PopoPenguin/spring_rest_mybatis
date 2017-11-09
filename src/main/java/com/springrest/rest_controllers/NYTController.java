package com.springrest.rest_controllers;

/*
created by PopoPenguin on 11/3/17
*/

import com.springrest.model.nyt.NYTResults;
import com.springrest.model.nyt.NYTTopStories;
import com.springrest.services.NYTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequestMapping("/NYT")
@RestController
public class NYTController {

    @Autowired
    NYTService service;

    @RequestMapping("/topstories/load")
    public NYTTopStories getNYTTopStories(){

        return service.loadNYTTopStories();
    }


    @RequestMapping("/topstories")
    public ArrayList<NYTResults> getNYTTopStories(@RequestParam(value = "query",required = false, defaultValue = "null") String query){
        return service.getNYTTopStories(query);
    }

}
