package com.springrest.mappers;

/*
created by PopoPenguin on 11/3/17
*/

import com.springrest.model.GoTQuote;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface GoTMapper {

    String GET_ALL_QUOTES = "SELECT * FROM `gotquote`.got_quote where isActive = 1";
    String GET_BY_ID = "SELECT * FROM `gotquote`.got_quote where id = #{id}";
    String GET_BY_CHARACTER = "SELECT * FROM `gotquote`.got_quote where character = #{character}";
    String GET_BY_QUOTE = "SELECT * FROM `gotquote`.got_quote where quote = #{quote}";
    String INSERT_QUOTE = "INSERT INTO `gotquote`.`got_quote` (`quote`, `character`) VALUES (#{quote}, #{character})";



//            "INSERT INTO `gotquote`.`got_quote` (quote, character) " +
//            "VALUES (#{quote}, #{character});";
    String UPDATE_QUOTE = "UPDATE `gotquote`.`got_quote` SET `quote`= " +
            "#{quote}, " +
            "`character`= #{character}, `isActive`= #{isActive} WHERE `id`= #{id};";
    String DELETE_QUOTE = "UPDATE `gotquote`.got_quote set isActive = 0 WHERE id = #{id}";

    @Select(GET_BY_CHARACTER)
    public GoTQuote getByCharacter(String character);

    @Select(GET_BY_QUOTE)
    public String getByQuote(String quote);

    @Select(GET_ALL_QUOTES)
    public ArrayList<GoTQuote> getAllQuotes();

    @Select(GET_BY_ID)
    public GoTQuote getByID(int id);

    @Insert(INSERT_QUOTE)
    public int insertQuote (GoTQuote gotQuote);

    @Update(UPDATE_QUOTE)
    public int updateQuote (GoTQuote gotQuote);

    @Delete(DELETE_QUOTE)
    public int deleteQuote(int id);


}
