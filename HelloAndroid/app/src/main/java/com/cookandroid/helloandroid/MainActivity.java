package com.cookandroid.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override       // 부모클래스를 상속받아 사용할 때 자식클래스에서 메소드를 재정의
    protected void onCreate(Bundle savedInstanceState) {    // onCreate 생성할때
        super.onCreate(savedInstanceState);         // super:부모클래스 onCreate:생성할 떄
        setContentView(R.layout.activity_main);     // recource 하위의 layout 하위의 activity_main파일을 보여줌줌    }
}
