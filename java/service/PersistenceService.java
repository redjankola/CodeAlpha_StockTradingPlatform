package service;

import model.User;

import java.io.*;

public class PersistenceService {

    private static final String FILE_NAME = "portfolio.dat";

    public static void saveUser(User user) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(FILE_NAME))) {

            out.writeObject(user);

            System.out.println("Portfolio saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User loadUser() {

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(FILE_NAME))) {

            return (User) in.readObject();

        } catch (Exception e) {
            return null;
        }
    }
}