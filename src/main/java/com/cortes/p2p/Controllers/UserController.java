package com.cortes.p2p.Controllers;

import com.cortes.p2p.DTO.SignupDTO;
import com.cortes.p2p.Models.User;
import com.cortes.p2p.Services.DAO.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "signup", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity registerUser(@RequestBody SignupDTO newUser)
    {
        newUser.setRoll(newUser.getRoll().toUpperCase());
        User saved = userService.getUser(newUser.getRoll());
        if(saved!=null)
            return new ResponseEntity(HttpStatus.ALREADY_REPORTED);

        try{
            saved = userService.addNewUser(newUser);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(saved==null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity validateLogin(@RequestParam("roll")String roll, @RequestParam("password") String hash)
    {
        roll = roll.toUpperCase();
        User saved = userService.getUser(roll);
        if(saved == null)
        {
            return ResponseEntity.notFound().build();
        }
        else if(userService.validateLogin(roll, hash, saved))
        {
//            redirect to home with url
            return new ResponseEntity(saved, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }

}
