package com.springrest.model.nyt;

/*
created by PopoPenguin on 11/3/17
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class NYTTopStories {

    int id;
    String status;
    String copyright;
    String section;
    String last_updated;
    int num_results;
    NYTResults[] nytResults;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNytResults(NYTResults[] nytResults) {
        this.nytResults = nytResults;
    }

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getSection() {
        return section;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public int getNum_results() {
        return num_results;
    }

    public NYTResults[] getNytResults() {
        return nytResults;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public void setResults(NYTResults[] results) {
        this.nytResults = results;
    }
}
