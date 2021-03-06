package org.esiea.pascolo_wong.programmationmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.action_help:
                Intent iHelp = new Intent(this, help_main.class);
                startActivity(iHelp);
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

    public void launchHexaFromColor(View v)
    {
        Intent i = new Intent(this, activity_hexa_from_color.class);
        startActivity(i);
    }

    public void launchColorFromHexa(View v)
    {
        Intent i = new Intent(this, activity_color_from_hexa.class);
        startActivity(i);
    }

    public void launchHexaFromRGB(View v)
    {
        Intent i = new Intent(this, activity_hexa_from_rgb.class);
        startActivity(i);
    }

    public void launchRGBFromHexa(View v)
    {
        Intent i = new Intent(this, activity_rgb_from_hexa.class);
        startActivity(i);
    }
}
