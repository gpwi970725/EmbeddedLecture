package com.cookandroid.finaltest;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;
    EditText edtName, edtKorean, edtMath, edtNameResult, edtKoreanResult, edtMathResult;
    Button btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("201612735 김혜지");

        edtName = (EditText) findViewById(R.id.edtName);
        edtKorean = (EditText) findViewById(R.id.edtKorean);
        edtMath = (EditText) findViewById(R.id.edtMath);
        edtNameResult = (EditText) findViewById(R.id.edtNameResult);
        edtKoreanResult = (EditText) findViewById(R.id.edtKoreanResult);
        edtMathResult = (EditText) findViewById(R.id.edtMathResult);

        btnInit = (Button) findViewById(R.id.btnInit);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnSelect = (Button) findViewById(R.id.btnSelect);

        myHelper = new myDBHelper(this);
        btnInit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2); // 인수는 아무거나 입력하면 됨.
                sqlDB.close();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();

                String name = edtName.getText().toString();
                String korean = edtKorean.getText().toString();
                String math = edtMath.getText().toString();
                if (name.length()==0 || (korean.length()==0 || math.length()==0)) {
                    Toast.makeText(getApplicationContext(), "빈칸 존재",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    sqlDB.execSQL("INSERT INTO scoreTable VALUES ( '"
                            + edtName.getText().toString() + "' , "
                            + edtKorean.getText().toString() + " , "
                            + edtMath.getText().toString() + ");");
                    sqlDB.close();
                    edtName.setText("");
                    edtKorean.setText("");
                    edtMath.setText("");
                    Toast.makeText(getApplicationContext(), "입력됨",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;

                String name = edtName.getText().toString();
                String korean = edtKorean.getText().toString();
                String math = edtMath.getText().toString();
                int condition=0;
                if (name.length() != 0) {
                    name = "name='" + name + "'";
                    condition ++;
                }
                else {
                    name = "";
                }
                if (korean.length() != 0) {
                    if (condition > 0) {
                        korean = " AND korean=" + korean;
                    }
                    else {
                        korean = "korean=" + korean;
                    }
                    condition ++;
                }
                else {
                    korean = "";
                }
                if (math.length() != 0) {
                    if (condition > 0) {
                        math = " AND math=" + math;
                    }
                    else {
                        math = "math=" + math;
                    }
                    condition ++;
                }
                else {
                    math = "";
                }
                if (condition > 0) {
                    cursor = sqlDB.rawQuery("SELECT * FROM scoreTable where " + name + korean + math + ";", null);
                }
                else {
                    cursor = sqlDB.rawQuery("SELECT * FROM scoreTable;", null);
                }

                String strNames = "이름" + "\r\n" + "--------" + "\r\n";
                String strKorean = "국어점수" + "\r\n" + "--------" + "\r\n";
                String strMath = "수학점수" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strKorean += cursor.getString(1) + "\r\n";
                    strMath += cursor.getString(2) + "\r\n";
                }

                edtNameResult.setText(strNames);
                edtKoreanResult.setText(strKorean);
                edtMathResult.setText(strMath);

                cursor.close();
                sqlDB.close();
            }
        });

    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "scoreDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE  scoreTable ( name CHAR(20) PRIMARY KEY, korean INTEGER, math INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS scoreTable");
            onCreate(db);
        }
    }
}
