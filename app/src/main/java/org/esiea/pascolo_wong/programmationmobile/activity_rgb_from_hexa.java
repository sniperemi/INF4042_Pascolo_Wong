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

public class activity_rgb_from_hexa extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgb_from_hexa);
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
        EditText editText = (EditText) findViewById(R.id.hexaCode);
        String hexaCode = editText.getText().toString();
        TextView rgb = (TextView) findViewById(R.id.RGBValues);
        if (!hexaCode.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"))
        {
            Toast.makeText(getApplicationContext(),getString(R.string.enterValidHexa), Toast.LENGTH_SHORT).show();
        }
        else
        {
            rgb.setBackgroundColor(Color.parseColor(hexaCode));
            rgb.setText(hexaToRGB(hexaCode));
        }


    }
    /*private String hexaToRGB(String hexaString)
    {
        int color = (int)Long.parseLong(hexaString, 16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        return ("(" + String.valueOf(r)+ ", " + String.valueOf(g) + ", " + String.valueOf(b) + ")");
    }*/

    public String hexaToRGB(String hexaString) {
                int red = Integer.valueOf( hexaString.substring( 1, 3 ), 16 );
                int green = Integer.valueOf( hexaString.substring( 3, 5 ), 16 );
                int blue = Integer.valueOf( hexaString.substring( 5, 7 ), 16 );
        return ("(" + String.valueOf(red)+ ", " + String.valueOf(green) + ", " + String.valueOf(blue) + ")");
    }
}
