package org.esiea.pascolo_wong.programmationmobile;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;

public class activity_hexa_from_color_db extends AppCompatActivity
{
    private RecyclerView rv;
    private ColorAdapter adapter;
    public static final String COLORS_UPDATE = "org.esiea.pascolo_wong.programmationmobile.COLORS_UPDATE";

    public class ColorsUpdate extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            downloadFinishedNotification();
            onDownloadFinished();
        }
    }

    private void downloadFinishedNotification ()
    {
        NotificationCompat.Builder notifBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.downloadfinished)
                .setContentTitle(getString(R.string.colorPack))
                .setContentText(getString(R.string.downloadFinished));
        NotificationManager notifMnger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifMnger.notify(0, notifBuilder.build());
    }

    public void onDownloadFinished ()
    {
        Toast.makeText(getApplicationContext(),getString(R.string.downloadFinished), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hexa_from_color_db);

        rv = (RecyclerView) findViewById(R.id.rv_colors_list);

        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ColorAdapter(this, getColorsFromFile());
        rv.setAdapter(adapter);

        IntentFilter intentFilter = new IntentFilter(COLORS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new ColorsUpdate(), intentFilter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_home:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public JSONArray getColorsFromFile()
    {
        try
        {
            InputStream input = new FileInputStream(getCacheDir() + "/" + "advancedColorPack1.json");

            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            input.close();
            return new JSONArray(new String(buffer, "UTF-8"));
        }
        catch(IOException e)
        {
            emptyDatabaseDialog();
            return new JSONArray();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    private void emptyDatabaseDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(R.string.emptyDB);
        alertDialog.setMessage(getString(R.string.emptyDBmsg));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        launchDownloadActivity();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void launchDownloadActivity()
    {
        Intent i = new Intent(this, activity_download.class);
        startActivity(i);
    }
}


