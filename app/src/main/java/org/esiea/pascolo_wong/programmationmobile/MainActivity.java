package org.esiea.pascolo_wong.programmationmobile;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*TextView tv_helloworld = (TextView)findViewById(R.id.tv_welcome);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_list);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));*/
    }

    /*public void showToast(View v)
    {
        Toast.makeText(getApplicationContext(),getString(R.string.msg), Toast.LENGTH_SHORT).show();
    }*/

    public void onDownload (View v)
    {
        Toast.makeText(getApplicationContext(),getString(R.string.downloadToast), Toast.LENGTH_SHORT).show();
        downloadNotification();
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
}
