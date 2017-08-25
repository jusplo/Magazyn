package com.projekt.justyna.magazyn.data.stores.files;

        import android.os.Build;

        import java.io.BufferedReader;
        import java.io.IOException;


public class FilesHelper {
    public static String readAllContent(BufferedReader br) throws IOException {
        String text = "";

        String temp;

        do {
            temp = br.readLine();

            if(temp != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    text += temp + System.lineSeparator();
                } else {
                    text += temp + '\n';
                }
            }
        } while(temp != null);

        return text;
    }
}

