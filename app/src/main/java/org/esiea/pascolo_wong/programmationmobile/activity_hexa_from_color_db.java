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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
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

    public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorHolder>
    {
        private JSONArray colors = null;
        public class ColorHolder extends RecyclerView.ViewHolder
        {
            public View name;
            public ColorHolder(View itemView) {
                super(itemView);
                name = itemView;
            }

        }

        public ColorsAdapter(JSONArray jsonArray)
        {
            colors = jsonArray;
        }

        @Override
        public ColorsAdapter.ColorHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv_color_element, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ColorHolder colorH = new ColorHolder(v);
            return colorH;
        }

        @Override
        public void onBindViewHolder(ColorHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            //holder.itemView..setText(mDataset[position]);
            JSONObject jsonObject = null;
            try
            {
                jsonObject = colors.getJSONObject(position);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            //holder.name.setText(jsonObject);
        }

        @Override
        public int getItemCount() {
            return colors.length();
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

        ColorsAdapter colorsAdapter = new ColorsAdapter(getColorsFromFile());
        rv.setAdapter(colorsAdapter);
    }
}


