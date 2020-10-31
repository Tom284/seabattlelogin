package Logic;

import Models.User;

import java.io.*;
import java.util.ArrayList;

public class FileSave {

    private static final FileSave instance = new FileSave();

    private FileSave(){}

    public static FileSave getInstance(){
        return instance;
    }

    public void writeToFile(ArrayList<User> list, String fileName){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileName);
            ArrayList<User> users = list;
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in .." + fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public ArrayList<User> retrieveFromFile(String fileName){
        ArrayList<User> users = new ArrayList<>();

        try
        {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object obj = ois.readObject();
            users = (ArrayList<User>) obj;

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        return users;
    }

    public void clearFile(ArrayList<User> list, String fileName){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileName);
            ArrayList<User> users = list;
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in ... "+ fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
