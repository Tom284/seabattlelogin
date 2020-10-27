package com.restlogin.seabattlelogin;

import Logic.File;
import Models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("register")
@RestController
public class RegisterController {

    File file = File.getInstance();


    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public HttpStatus register(@RequestBody User user) {
        ArrayList<User> previousUsers;
        if (user.getName() != null && user.getPassword() != null) {
            previousUsers = file.retrieveFromFile();
            for (User oldUser: previousUsers) {
                String name = oldUser.getName();
                String newName = user.getName();
                if(name.equals(newName)){
                    System.out.println("WAAAAT??");
                    return HttpStatus.NOT_FOUND;
                }
            }
            previousUsers.add(user);
            file.writeToFile(previousUsers);
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }
    }

    @DeleteMapping()
    public HttpStatus h(){
        ArrayList<User> startUsers;
        startUsers = new ArrayList<>();
        User jeff = new User();
        jeff.setName("jeff");
        jeff.setPassword("jeff");
        startUsers.add(jeff);
        file.clearFile(startUsers);
        return HttpStatus.OK;
    }
}
