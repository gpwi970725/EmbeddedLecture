package com.cookandroid.project10_1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Embedded on 2019-05-28.
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.i("ActivityTest", "SecondActivity onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.i("ActivityTest", "SecondActivity onPause()");
    }
}
