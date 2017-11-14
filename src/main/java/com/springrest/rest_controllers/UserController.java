package com.springrest.rest_controllers;

import java.util.ArrayList;

import com.springrest.exceptions.InvalidRequestException;
import com.springrest.model.CustomResponseObject;
import com.springrest.model.User;
import com.springrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    //Get
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ArrayList<User> getUsers() {

        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CustomResponseObject> getById(@PathVariable(value="id")int id) throws InvalidRequestException {
        CustomResponseObject response = new CustomResponseObject();

        try {
            userService.getById(id);
            response.setMessage("success");
            response.setData(userService.getById(id));
            response.setStatus_code(200);
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (InvalidRequestException ie){
            throw ie;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/manual")
    public ArrayList<User> getUsersManually() {

        return userService.getAllUsersManually();
    }

    //Create
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<CustomResponseObject> addNew(@RequestBody User user) throws InvalidRequestException{

        CustomResponseObject response = new CustomResponseObject();
        try{
            userService.addNew(user);
            response.setMessage("success");
            response.setData(user);
            response.setStatus_code(200);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch(InvalidRequestException npe){
            throw npe;
        }
    }

    //Update
    @RequestMapping(method = RequestMethod.PATCH, value = "/")
    public ResponseEntity<CustomResponseObject> updateById(@RequestBody User user) throws InvalidRequestException {
        CustomResponseObject response = new CustomResponseObject();
        try{
            userService.updateById(user);
            response.setMessage("success");
            response.setData(user);
            response.setStatus_code(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (InvalidRequestException npe){
            throw npe;
        }

    }

    //Delete
    @RequestMapping(method= RequestMethod.DELETE, value="/")
    public User deleteById(@RequestBody User user){


        return userService.deleteById(user.getId());
    }
}
