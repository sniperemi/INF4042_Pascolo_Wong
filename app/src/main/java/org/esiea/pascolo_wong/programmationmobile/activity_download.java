package org.esiea.pascolo_wong.programmationmobile;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class activity_download extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
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
                Intent iHelp = new Intent(this, help_download.class);
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

    public void onDownload (View v)
    {
        Toast.makeText(getApplicationContext(),getString(R.string.downloadToast), Toast.LENGTH_SHORT).show();
        downloadNotification();
        downloadJSONFile();
    }

    private void downloadNotification ()
    {
        NotificationCompat.Builder notifBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.download)
                .setContentTitle(getString(R.string.colorPack))
                .setContentText(getString(R.string.downloading));
        NotificationManager notifMnger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifMnger.notify(0, notifBuilder.build());
    }

    private void downloadJSONFile()
    {
        DownloadService.startActionGetColors(this);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
