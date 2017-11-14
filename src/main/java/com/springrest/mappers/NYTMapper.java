package com.springrest.mappers;

/*
created by PopoPenguin on 11/3/17
*/

import com.springrest.model.nyt.NYTMultimedia;
import com.springrest.model.nyt.NYTResults;
import com.springrest.model.nyt.NYTTopStories;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface NYTMapper {

    //a query to check if  topstory already exists in db -using url
    String GET_STORY_ID = "Select id from `mybatis-test`.nyt_top_stories_results where url = #{url}";

    //a query to insert a new top story
    String INSERT_TOP_STORY_RESULT = "INSERT INTO `mybatis-test`.`nyt_top_stories_results` ( " +
            "section, subsection, title, url, byline, item_type, updated_date, created_date, " +
            "published_date, material_type_facet, kicker, short_url) VALUES ( #{section}, "
            + "#{subsection}, #{title}, #{url}, #{byline}, #{item_type}, #{updated_date}, #{created_date}, " +
            "#{published_date}, #{material_type_facet}, #{kicker}, #{short_url})";

    String INSERT_MULTIMEDIA = "INSERT INTO `mybatis-test`.`multimedia` (id, " +
            "nyt_top_story_id, url, format, height, width, type, subtype, caption, copyright)" +
            "VALUES (#{id}, #{nyt_top_story_id}, #{url}, #{format}, #{height}, #{width}, #{type}, "
            + "#{subtype}, #{caption}, #{copyright})";

    String SELECT_ALL_TOP_STORIES = "select * from `mybatis-test`.`nyt_top_stories_results`";

    String SELECT_WITH_SEARCH_PARAM = "select * from `mybatis-test`.`nyt_top_stories_results` " +
                                        "where title like #{query}";

    String SELECT_RESULTS_BY_SECTIONS = "select * from `mybatis-test`.`nyt_top_stories_results` " +
            "where section = #{section}" + "order by updated_date desc limit 10";

    String CHECK_SECTION_EXISTS = "select id from `mybatis-test`.`nyt_top_stories_results` where section = #{section}"
            + "limit 1";


    @Select(GET_STORY_ID)
    public int getStory(String url);

    @Select(SELECT_ALL_TOP_STORIES)
    public ArrayList<NYTResults> getAllStories();

    @Select(SELECT_WITH_SEARCH_PARAM)
    public ArrayList<NYTResults> searchStoryParam(String query);

    @Select(SELECT_RESULTS_BY_SECTIONS)
    public ArrayList<NYTResults> searchBySection(String section);

    @Select(CHECK_SECTION_EXISTS)
    public int checkSectionExists(String section);

    @Insert(INSERT_TOP_STORY_RESULT)
    public int insertResult(NYTResults results);

    @Insert(INSERT_MULTIMEDIA)
    public int insertMultimedia(NYTMultimedia multimedia);

}
