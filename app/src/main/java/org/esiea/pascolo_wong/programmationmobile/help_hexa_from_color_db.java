package org.esiea.pascolo_wong.programmationmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class help_hexa_from_color_db extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_hexa_from_color_db);
    }

    public void Return(View v)
    {
        Intent i = new Intent(this, activity_color_from_hexa.class);
        startActivity(i);
    }
}
