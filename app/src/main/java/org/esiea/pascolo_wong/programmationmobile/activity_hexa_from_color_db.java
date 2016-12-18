package org.esiea.pascolo_wong.programmationmobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.StreamCorruptedException;

public class activity_hexa_from_color_db extends AppCompatActivity {

    public static final String COLORS_UPDATE = "org.esiea.pascolo_wong.programmationmobile.COLORS_UPDATE";
    public class ColorsUpdate extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            //Log.d("FinishedDL", getIntent().getAction());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hexa_from_color_db);
        getColorsFromFile();
        showColors();
        IntentFilter intentFilter = new IntentFilter(COLORS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new ColorsUpdate(), intentFilter);
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
        alertDialog.setTitle("Empty Database");
        alertDialog.setMessage("You need to download colors to continue");
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

    private void showColors()
    {
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_colors);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}


