package com.example.tcptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Environment;
import java.io.File;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client c = new client();

        /*Log.d("STATE", Environment.getExternalStorageState());
        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "work_data");
        Log.d("PATH", f.getAbsolutePath());
        if (!f.exists()) {
            Log.d("MAKE DIR", f.mkdirs() + "");
        }*/
    }
}
