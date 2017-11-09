package com.springrest.rest_controllers;

/*
created by PopoPenguin on 11/1/17
*/


import com.springrest.model.brewery.BaseBeer;
import com.springrest.model.brewery.Data;
import com.springrest.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RequestMapping("/beer")
@RestController
public class BeerController {

    @Autowired
    BeerService service;

    @RequestMapping("/get")
    public BaseBeer getBeer(){

        return service.getBeer();
    }

    @RequestMapping("/load")
    public void loadBeer(){
        service.loadBeer();
        return ;
    }

    @RequestMapping("/get/all")
    public ArrayList<Data> getAllBeer(){

        return service.getAllBeer();
    }

    @RequestMapping("/search")
    public ArrayList<Data> searchData(@RequestParam(value = "query",required = false, defaultValue = "null") String query){

        return service.searchData(query);
    }


}
