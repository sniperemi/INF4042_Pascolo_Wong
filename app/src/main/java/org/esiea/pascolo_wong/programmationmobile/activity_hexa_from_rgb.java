package org.esiea.pascolo_wong.programmationmobile;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_hexa_from_rgb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hexa_from_rgb);
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
            case R.id.action_help:
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

    public void Process(View v)
    {
        EditText redCode = (EditText) findViewById(R.id.redCode);
        EditText greenCode = (EditText) findViewById(R.id.greenCode);
        EditText blueCode = (EditText) findViewById(R.id.blueCode);
        String rgbRed = redCode.getText().toString();
        String rgbGreen = greenCode.getText().toString();
        String rgbBlue = blueCode.getText().toString();
        TextView hexa = (TextView) findViewById(R.id.hexaCode);

        if (!rgbRed.matches("^[0-1][0-9][0-9]|2[0-5][0-5]$") || !rgbGreen.matches("^[0-1][0-9][0-9]|2[0-5][0-5]$") || !rgbBlue.matches("^[0-1][0-9][0-9]|2[0-5][0-5]$"))
        {
            Toast.makeText(getApplicationContext(),getString(R.string.enterValidRGB), Toast.LENGTH_SHORT).show();
        }
        else
        {
            String hex = String.format("#%02X%02X%02X", Integer.parseInt(rgbRed), Integer.parseInt(rgbGreen), Integer.parseInt(rgbBlue));
            hexa.setBackgroundColor(Color.parseColor(hex));
            hexa.setText(hex);
        }

    }
}
