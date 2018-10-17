package com.kinoroy.expo.push;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class Util {

    public static String readFromFile(String fileName) throws Exception {

        // Credit: https://www.journaldev.com/875/java-read-file-to-string
        URL path = TestExpoPushService.class.getResource(fileName);
        File f = new File(path.getFile());
        BufferedReader reader = new BufferedReader(new FileReader(f));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        return content;

    }

}
