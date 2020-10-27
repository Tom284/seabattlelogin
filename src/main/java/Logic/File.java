package Logic;

import Models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File {

    private static final File instance = new File();

    private File(){}

    public static File getInstance(){
        return instance;
    }

    public void writeToFile(ArrayList<User> list){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("users.txt");
            ArrayList<User> users = list;
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in users.txt");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public ArrayList<User> retrieveFromFile(){
        ArrayList<User> users = new ArrayList<>();

        try
        {
            FileInputStream fis = new FileInputStream("users.txt");
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

    public void clearFile(ArrayList<User> list){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("users.txt");
            ArrayList<User> users = list;
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in users.txt");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
