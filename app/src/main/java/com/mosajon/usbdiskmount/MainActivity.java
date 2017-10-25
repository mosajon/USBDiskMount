package com.mosajon.usbdiskmount;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mosajon.usbdiskmount.ShellUtils.CommandResult;

public class MainActivity extends AppCompatActivity {

    Button button,button2;
    TextView textView,textView2;
    String txtTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://weibo.com/mosajon");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent2=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent2);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Btn_Click(View view)
    {
        String[] commands1 = new String[] { "mount -t vfat /dev/block/sda4 /mnt/removable/dongle_disk" };
        CommandResult result1 = ShellUtils.execCommand(commands1, true);
        txtTemp = "\nU盘挂载成功！位于：\n/mnt/removable/dongle_disk\n/dongle_disk";
        textView2.setText(txtTemp);
    }

    public void Btn2_Click(View view)
    {
        String[] commands2 = new String[] { "umount /mnt/removable/dongle_disk" };
        CommandResult result2 = ShellUtils.execCommand(commands2, true);
        txtTemp = "U盘卸载成功！";
        textView2.setText(txtTemp);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(txtTemp != null)
        {
            if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
                textView2.setText(txtTemp);
            }else{
                textView2.setText(txtTemp);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
