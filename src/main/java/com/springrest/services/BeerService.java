package com.springrest.services;

/*
created by PopoPenguin on 11/1/17
*/


import com.springrest.mappers.BeerMapper;
import com.springrest.model.brewery.BaseBeer;
import com.springrest.model.brewery.Category;
import com.springrest.model.brewery.Data;
import com.springrest.model.brewery.Style;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class BeerService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BeerMapper mapper;

    public ArrayList<Data> searchData(String query){

        //search our database for that query
        if (!query.equalsIgnoreCase("null")){
            ArrayList<Data> ret = mapper.searchBeerParam("%" + query + "%");
            populateData(ret);
            return ret;
        } else {
            ArrayList<Data> ret = mapper.getAllData();
            populateData(ret);
            return ret;
        }
    }

    public BaseBeer getBeer(){

        BaseBeer baseBeer = restTemplate.getForObject(
                "http://api.brewerydb.com/v2/beer/random?key=8b6d28dfa988bf5e2d8342cdd6fe586c", BaseBeer.class);
        insertBaseBeer(baseBeer);
        return baseBeer;
    }

    public void loadBeer(){
        for(int i = 0; i<5; i++){
            BaseBeer baseBeer = restTemplate.getForObject(
                    "http://api.brewerydb.com/v2/beer/random?key=8b6d28dfa988bf5e2d8342cdd6fe586c", BaseBeer.class);
            insertBaseBeer(baseBeer);
        }
    }

    public ArrayList<Data> getAllBeer(){

        ArrayList<Data> ret = mapper.getAllData();
        populateData(ret);
        return ret;
    }

    public ArrayList<Data> populateData(ArrayList<Data> data){

        for (Data d : data){
            d.setStyle(mapper.getBeerStyle(d.getStyleId()));
            Category c = mapper.getBeerCategory(d.getStyle().getCategoryId());
            d.getStyle().setCategory(c);
        }
        return data;
    }

    public void insertBaseBeer (BaseBeer baseBeer){

        String id;
        int styleId;
        int categoryId;

        try {
            id = mapper.getBeerId(baseBeer.getData().getId());
        } catch (Exception e) {
            id = null;
        }
        // check to see if the beer already exists in our DB
        if (id == null) {
            int success = mapper.insertNewData(baseBeer.getData());
            if (success > 0) {
                try {
                    styleId = mapper.getBeerStyleId(baseBeer.getData().getStyleId());

                } catch (Exception e) {
                    styleId = 0;
                }
                // check to see if the beer already exists in our DB
                if (styleId == 0) {
                    success = mapper.insertNewStyle(baseBeer.getData().getStyle());
                    if (success > 0) {
                        try {
                            categoryId = mapper.getBeerCategoryId(baseBeer.getData().getStyle().getCategoryId());
                        } catch (Exception e) {
                            categoryId = 0;
                        }
                        // check to see if the beer already exists in our DB
                        if (categoryId == 0) {
                            mapper.insertNewCategory(baseBeer.getData().getStyle().getCategory());
                        }
                    }
                }
            }
        }
    }
}
