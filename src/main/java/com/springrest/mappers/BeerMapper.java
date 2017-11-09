package com.springrest.mappers;


import com.springrest.model.brewery.BaseBeer;
import com.springrest.model.brewery.Category;
import com.springrest.model.brewery.Data;
import com.springrest.model.brewery.Style;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface BeerMapper {

    String INSERT_NEW_BASE_BEER = "insert into `beerbrewery`.`BaseBeer` (id, message, status)"
            + " values (#{id}, #{message}, #{status})";

    String INSERT_NEW_DATA = "insert into `beerbrewery`.`data` (id, name, nanoDisplay, description, abv, " +
            "styleId, isOrganic, status, statusDisplay, createDate, updateDate) " +
            "values (#{id}, #{name}, #{nanoDisplay}, #{description}, #{abv}, "+
            "#{styleId}, #{isOrganic}, #{status}, #{statusDisplay}, #{createDate}, #{updateDate})";

    String INSERT_NEW_STYLE = "insert into `beerbrewery`.`style` (id, categoryId, name, shortName, description, ibuMin,"+
            " ibuMax, abvMin, abvMax, srmMin, srmMax, ogMin, ogMax, fgMin, fgMax, createDate, updateDate)"+
            " values (#{id}, #{categoryId}, #{name}, #{shortName}, #{description}, #{ibuMin}, "+
            "#{ibuMax}, #{abvMin}, #{abvMax}, #{srmMin}, #{srmMax}, #{ogMin}, #{ogMax}, #{fgMin}, #{fgMax}, "+
            "#{createDate}, #{updateDate})";

    String INSERT_NEW_CATEGORY = "insert into `beerbrewery`.`category` (id, name, createDate)" +
            " values (#{id}, #{name}, #{createDate})";

    String GET_BEER_DATA = "select id from `beerbrewery`.`data` where id = #{id}";

    String GET_BEER_STYLE = "select * from `beerbrewery`.`style` where id = #{id}";

    String GET_BEER_STYLE_ID = "select id from `beerbrewery`.`style` where id = #{id}";

    String GET_BEER_CATEGORY = "select * from `beerbrewery`.`category` where id = #{id}";

    String GET_BEER_CATEGORY_ID = "select id from `beerbrewery`.`category` where id = #{id}";

    String SELECT_WITH_SEARCH_PARAM = "select * from `beerbrewery`.`data` where name like #{query}";


    String SELECT_ALL_DATA = "select * from `beerbrewery`.`data`";

    @Select(GET_BEER_DATA)
    public String getBeerId (String id);

    @Select(GET_BEER_STYLE)
    public Style getBeerStyle (int id);

    @Select(GET_BEER_STYLE_ID)
    public int getBeerStyleId (int id);

    @Select(GET_BEER_CATEGORY)
    public Category getBeerCategory (int id);

    @Select(GET_BEER_CATEGORY_ID)
    public int getBeerCategoryId (int id);

    @Select(SELECT_ALL_DATA)
    public ArrayList<Data> getAllData();

    @Select(SELECT_WITH_SEARCH_PARAM)
    public ArrayList<Data> searchBeerParam(String query);

    @Insert(INSERT_NEW_BASE_BEER)
    public int insertBaseBeer(BaseBeer baseBeer);

    @Insert(INSERT_NEW_DATA)
    public int insertNewData(Data data);

    @Insert(INSERT_NEW_STYLE)
    public int insertNewStyle(Style style);

    @Insert(INSERT_NEW_CATEGORY)
    public int insertNewCategory(Category category);


}
