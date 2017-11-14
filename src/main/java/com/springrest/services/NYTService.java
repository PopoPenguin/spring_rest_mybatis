package com.springrest.services;

/*
created by PopoPenguin on 11/3/17
*/

import com.springrest.exceptions.InvalidRequestException;
import com.springrest.mappers.NYTMapper;
import com.springrest.model.nyt.NYTMultimedia;
import com.springrest.model.nyt.NYTResults;
import com.springrest.model.nyt.NYTTopStories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class NYTService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NYTMapper nytMapper;

    public NYTTopStories loadNYTTopStories() {

            NYTTopStories topStories =
                    restTemplate.getForObject("https://api.nytimes.com/svc/topstories/v2/home.json?apikey=c5f580e7fb094f3b87df9aadc9c00b62", NYTTopStories.class);
            insertNYTTopStories(topStories);

            return topStories;
    }

    public void insertNYTTopStories (NYTTopStories stories){
        // for every result (story) in the NYT response
        for (NYTResults result : stories.getNytResults()) {
            int id = 0;
            try {
                id = nytMapper.getStory(result.getUrl());

            } catch (Exception e) {
                id = 0;
            }
            // check to see if the story already exists in our DB
            if (id == 0) {

                int success = nytMapper.insertResult(result);
                //insertTopStory returns the number of rows inserted if that number is greater
                // than 0- that means the story was inserted - now we can insert the associated multimedia
                if (success > 0) {
                    //get the id of the newly inserted story to use for each multimedia record
                    id = nytMapper.getStory(result.getUrl());
                    //for every multimedia in the story
                    for (NYTMultimedia media : result.getMultimedia()) {
                        media.setNyt_top_story_id(id);
                        nytMapper.insertMultimedia(media);
                    }
                }
            }
        }
    }

    public ArrayList<NYTResults> getNYTTopStories(String query) {

        //search our database for that query
        if (!query.equalsIgnoreCase("null")){
            return nytMapper.searchStoryParam("%" + query + "%");
        } else {
            return nytMapper.getAllStories();
        }
    }

    public ArrayList<NYTResults> getNYTResultsBySection (String section) throws InvalidRequestException {


        try{
            int tempId = nytMapper.checkSectionExists(section);
        }catch (Exception npe){
            throw new InvalidRequestException("Unknown section: " + section, 400);
        }

        return nytMapper.searchBySection(section);

    }
}
