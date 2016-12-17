package org.esiea.pascolo_wong.programmationmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class activity_hexa_from_color_db extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hexa_from_color_db);
        getColorsFromFile();
    }

    public JSONArray getColorsFromFile()
    {
        try
        {
            InputStream input = new FileInputStream(getCacheDir() + "/" + "advancedColorPack1.json");
            return new JSONArray();
        }
        catch(IOException e)
        {
            emptyDatabaseDialog();
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
}
