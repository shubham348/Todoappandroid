package com.example.shubham.todoapp;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class filehelper {
    public static final String FILENAME="listinfo.dat";
    public static void writedata(ArrayList<String> items, Context context){
        try  {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(items);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();

        }

    }




    public static ArrayList<String> readdata(Context context){
        ArrayList<String> itemlist=null;
        try  {
            FileInputStream fis = context.openFileInput(FILENAME);

                try  {
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    itemlist= (ArrayList<String>) ois.readObject();


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


        } catch (FileNotFoundException e) {
            itemlist=new ArrayList<>();
            e.printStackTrace();
        }

   return itemlist;
    }

}