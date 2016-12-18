package org.esiea.pascolo_wong.programmationmobile;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadService extends IntentService {
    private static final String ACTION_GET_COLORS = "org.esiea.pascolo_wong.programmationmobile.action.GET_COLORS";

    public DownloadService() {
        super("DownloadService");
    }

    public static void startActionGetColors(Context context) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(ACTION_GET_COLORS);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_COLORS.equals(action)) {
                getColors();
            }
        }
    }

    private void getColors() {
        Log.i("Download", "Downloading...");
        URL url = null;
        try
        {
            url = new URL("https://gist.githubusercontent.com/jjdelc/1868136/raw/c9160b1e60bd8c10c03dbd1a61b704a8e977c46b/crayola.json");
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            connexion.connect();
            if(HttpURLConnection.HTTP_OK == connexion.getResponseCode())
            {
                copyInputStreamToFile(connexion.getInputStream(), new File(getCacheDir(), "advancedColorPack1.json"));
                Log.d("Download", "Colors downloaded");
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void copyInputStreamToFile(InputStream input, File file)
    {
        try
        {
            OutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int lenght = 0;
            while((lenght=input.read(buffer))>0)
            {
                output.write(buffer, 0, lenght);
            }
            output.close();
            input.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
