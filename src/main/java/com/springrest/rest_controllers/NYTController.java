package com.springrest.rest_controllers;

/*
created by PopoPenguin on 11/3/17
*/

import com.springrest.exceptions.InvalidRequestException;
import com.springrest.model.CustomResponseObject;
import com.springrest.model.nyt.NYTResults;
import com.springrest.services.NYTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity getNYTTopStories(){

        return new ResponseEntity(service.loadNYTTopStories(), HttpStatus.OK);
    }

    // addition of ":.+" in section allows . to be included in the query
    @RequestMapping("/topstories/{section:.+}")
    public ResponseEntity<CustomResponseObject> getNYTResultsBySection(@PathVariable("section") String section)
        throws InvalidRequestException {

        ArrayList<NYTResults> nyt = null;
        CustomResponseObject response = new CustomResponseObject();
        try {
            nyt = service.getNYTResultsBySection(section);
            response.setMessage("success");
            response.setData(nyt);
            response.setStatus_code(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (InvalidRequestException down) {
            throw down;
        }
    }


    @RequestMapping("/topstories")
    public ResponseEntity<NYTResults> getNYTTopStories(@RequestParam(value = "query",required = false, defaultValue = "null") String query){
        return new ResponseEntity(service.getNYTTopStories(query), HttpStatus.OK);
    }

    @RequestMapping("/topstories/query")
    public ArrayList<NYTResults> getNYTTopStories1(@RequestParam(value = "query",required = false, defaultValue = "null") String query){
        return service.getNYTTopStories(query);
    }

}
