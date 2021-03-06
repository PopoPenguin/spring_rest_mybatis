package com.springrest.mappers;

import com.springrest.model.User;
import org.apache.ibatis.annotations.*;
import java.util.ArrayList;

/**
 * Created by tanerali on 24/07/2017.
 */

@Mapper
public interface UserMapper {

    String GET_ALL__ACTIVE_USERS = "SELECT * FROM `mybatis-test`.users where `isActive` = 1";
    String GET_BY_ID = "SELECT * FROM `mybatis-test`.users where `id` = #{id}";
    String INSERT_USER = "INSERT INTO `mybatis-test`.users (`first_name`, `last_name`, isActive) " +
            "VALUES (#{first_name}, #{last_name}, #{isActive})";
    String UPDATE_USER = "UPDATE `mybatis-test`.`users` SET `first_name`=#{first_name},"+
            "`last_name`=#{last_name}, `isActive`=#{isActive} WHERE `id`=#{id}";
    String DELETE_USER = "UPDATE `mybatis-test`.users set `isActive` = 0 WHERE `id` = #{id}";
    String GET_BY_NAME = "SELECT * FROM `mybatis-test`.users where `first_name` = #{param1} and `last_name` = #{param2}";
    String CHECK_USER_EXIST = "SELECT id FROM `mybatis-test`.users where `first_name` = #{param1} and `last_name` = #{param2}";

    @Select(GET_BY_NAME)
    public User getByName(String first_name, String last_name);

    @Select(GET_ALL__ACTIVE_USERS)
    public ArrayList<User> getAllUsers();

    @Select(GET_BY_ID)
    public User getByID(int id);

    @Select(CHECK_USER_EXIST)
    public int checkUserExist(String first_name, String last_name);

    @Insert(INSERT_USER)
    public int insertUser (User user);

    @Update(UPDATE_USER)
    public int updateUser (User user);

    @Delete(DELETE_USER)
    public int deleteUser(int id);

}