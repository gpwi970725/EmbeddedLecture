package com.cookandroid.project10_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNewActivity = (Button) findViewById(R.id.btnNewActivity);
        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.i("ActivityTest", "MainActivity onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        android.util.Log.i("ActivityTest", "MainActivity onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.i("ActivityTest", "MainActivity onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        android.util.Log.i("ActivityTest", "MainActivity onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.i("ActivityTest", "MainActivity onDestroy()");
    }
}
