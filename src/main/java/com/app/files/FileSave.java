package com.app.files;

import com.app.wisesaying.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSave {
    public static void save() throws IOException {
        File file = new File("src/main/resources/save.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fw);

        Repository.toArrayList().iterator().forEachRemaining(
                ws -> {
                    try {
                        writer.write(ws.toString() + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        writer.close();
    }
}
