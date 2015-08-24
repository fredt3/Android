package com.example.fred.milfinapp;

/**
 * From codinggeek.com.
 */
import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class is a utility class for performing the serialization and
 * deserialization operations provided the required information.
 *
 * @author hiteshgarg
 */
public class SerializationUtil {

    public static void saveObjectToFile(Context context, String fileName, Profile obj) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();

        } catch (FileNotFoundException e) {
            Log.e("error", "saveObjectToFile FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("error", "saveObjectToFile IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("error", "saveObjectToFile Exception: " + e.getMessage());
        }
    }
    public static Profile getObjectFromFile(Context context, String filename) {

        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Profile object = (Profile) ois.readObject();
            ois.close();
            fis.close();

            return object;

        } catch (FileNotFoundException e) {
            Log.e("error", "getObjectFromFile FileNotFoundException: " + e.getMessage());
            saveObjectToFile(context, filename, new Profile());
            return null;
        } catch (IOException e) {
            Log.e("error", "getObjectFromFile IOException: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            Log.e("error", "getObjectFromFile ClassNotFoundException: " + e.getMessage());
            return null;
        } catch (Exception e) {// Catch exception if any
            Log.e("error", "getBookmarksFromFile Exception: " + e.getMessage());
            return null;
        }
    }
}