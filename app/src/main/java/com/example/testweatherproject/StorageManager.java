package com.example.testweatherproject;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StorageManager {

    public void writeOnMemory(Context context, String fileDirectory, String fileName, String fileBody) {
        try {
            File root = new File(fileDirectory);
            if (!root.exists()) {
                root.mkdirs();
            }
            File jsonFile = new File(root, fileName);
            FileWriter writer = new FileWriter(jsonFile);
            writer.append(fileBody);
            writer.flush();
            writer.close();
            Log.i("SaveFileCompletedTag", "file saved");
//            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
//            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            Log.i("SaveFileErrorTag", e.toString());
        }
    }


    public String readFromMemory(Context context,String fileDirectory, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;
        try {
            File jsonFile = new File(fileDirectory, fileName);
            FileReader fileReader = new FileReader(jsonFile);

            in = new BufferedReader(fileReader);
            while ((line = in.readLine()) != null) stringBuilder.append(line);

            Log.i("ReadFileCompletedTag", "reading file completed successfully");

        } catch (FileNotFoundException e) {
            Log.i("ReadFileErrorTag", e.toString());
        } catch (IOException e) {
            Log.i("ReadFileErrorTag", e.toString());
        }
        return stringBuilder.toString();
    }
}
