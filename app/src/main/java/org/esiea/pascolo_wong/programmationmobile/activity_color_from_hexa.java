package org.esiea.pascolo_wong.programmationmobile;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_color_from_hexa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_from_hexa);
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
                Intent iHelp = new Intent(this, help_color_from_hexa.class);
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

    public void Process(View v)
    {
        EditText editText = (EditText) findViewById(R.id.hexaCode);
        String hexaCode = editText.getText().toString();
        TextView color = (TextView) findViewById(R.id.Color);
        if (!hexaCode.matches("^#([A-Fa-f0-9]{6})$"))
        {
            Toast.makeText(getApplicationContext(),getString(R.string.enterValidHexa), Toast.LENGTH_SHORT).show();
        }
        else
        {
            color.setBackgroundColor(Color.parseColor(hexaCode));
        }


    }
}
