package com.restlogin.seabattlelogin;

import Logic.FileSave;
import Models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("register")
@RestController
public class RegisterController {

    FileSave fileSave = FileSave.getInstance();
    String fileText = "users.txt";
    public void setFileText(String text){
        fileText = text;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public HttpStatus register(@RequestBody User user) {
        ArrayList<User> previousUsers;
        if (user.getName() != null && user.getPassword() != null) {
            previousUsers = fileSave.retrieveFromFile(fileText);
            if(previousUsers == null){
                previousUsers = new ArrayList<User>();
            }
            for (User oldUser: previousUsers) {
                String name = oldUser.getName();
                String newName = user.getName();
                if(name.equals(newName)){
                    System.out.println("WAAAAT??");
                    return HttpStatus.NOT_FOUND;
                }
            }
            previousUsers.add(user);
            fileSave.writeToFile(previousUsers, fileText);
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
        fileSave.clearFile(startUsers, fileText);
        return HttpStatus.OK;
    }
}
