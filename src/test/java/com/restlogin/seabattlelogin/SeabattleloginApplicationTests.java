package com.restlogin.seabattlelogin;

import Logic.FileSave;
import Models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SeabattleloginApplicationTests {

    FileSave fileSave = FileSave.getInstance();
    RegisterController controller = new RegisterController();
    String fileText = "TestFile.txt";

    @BeforeEach
    void setUp() throws IOException {
        ArrayList<User> list = new ArrayList<>();
        User user = new User();
        user.setName("Henk");
        user.setPassword("ABC");
        list.add(user);
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileText);
            ArrayList<User> users = list;
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in TestFile.txt");
        } catch (IOException i) {
            i.printStackTrace();
        }
        controller.setFileText(fileText);
    }
    @AfterEach
    void tearDown() {
        File text = new File(fileText);
        text.delete();
    }
    @Test
    void contextLoads() {
    }

    @Test
    void RetrievefromFile() {
        ArrayList<User> previousUsers;
        previousUsers = fileSave.retrieveFromFile(fileText);
        assertEquals(1,previousUsers.size(), "Wrong number of people are in the list");
    }

    @Test
    void WriteToFile() {
        ArrayList<User> list = new ArrayList<>();
        User user = new User();
        user.setName("Henk");
        user.setPassword("ABC");
        list.add(user);
        fileSave.writeToFile(list, fileText);

        assertEquals(1,fileSave.retrieveFromFile(fileText).size(), "Wrong number of people are in the list");
    }

    @Test
    void WriteToFileController() {
        User user = new User();
        user.setName("Menk");
        user.setPassword("ABC");
        controller.register(user);

        assertEquals(2,fileSave.retrieveFromFile(fileText).size(), "Wrong number of people are in the list");
    }

    @Test
    void WriteToFileControllerHTTP() {
        User user = new User();
        user.setName("Menk");
        user.setPassword("ABC");
        HttpStatus status = controller.register(user);

        assertEquals(HttpStatus.OK,status, "Wrong number of people are in the list");
    }

    @Test
    void WriteToFileControllerNameExists() {
        User user = new User();
        user.setName("Henk");
        user.setPassword("ABC");
        controller.register(user);

        assertEquals(1,fileSave.retrieveFromFile(fileText).size(), "Wrong number of people are in the list");
    }

    @Test
    void WriteToFileControllerNameExistsHTTP() {
        User user = new User();
        user.setName("Henk");
        user.setPassword("ABC");
        HttpStatus status = controller.register(user);

        assertEquals(HttpStatus.NOT_FOUND,status, "Wrong number of people are in the list");
    }

}
