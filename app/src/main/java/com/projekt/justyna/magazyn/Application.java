package com.projekt.justyna.magazyn;

import android.app.Activity;
        import android.content.DialogInterface;
        import android.os.Environment;
        import android.support.v7.app.AlertDialog;


/**
 * Created by Justyna on 25.08.2017.
 */

public class Application extends android.app.Application {
    private static Application instance = null;

    public static Application get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static AlertDialog createListDialog(int title, int array, DialogInterface.OnClickListener listener, Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setItems(array, listener);

        return builder.create();
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}
