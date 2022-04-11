package com.example.helprefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText et_id;
    private EditText et_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        et_id = findViewById (R.id.et_id);
        et_pw = findViewById (R.id.et_pw);
        Button btn_login = findViewById (R.id.btn_login);
        Button btn_register = findViewById (R.id.btn_register);

        btn_login.setOnClickListener (new View.OnClickListener () { // 로그인 버튼 누르면 입력받은 id/pw를 db내의 데이터와 비교
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = DbHelper.getInstance (Login.this);
                Cursor cursor = dbHelper.getReadableDatabase ().rawQuery ("SELECT * FROM list WHERE id = ? ", new String[] {et_id.getText ().toString ()}); // 입력받은 id값으로 찾기
                if(cursor.getCount () != 1) { // id가 존재하지 않을 경우
                    Toast.makeText (Login.this, "존재하지 않는 ID입니다", Toast.LENGTH_SHORT).show ();
                } else { // id가 존재할 경우 입력받은 pw값과 db내의 데이터와 비교
                    while(cursor.moveToNext ()) {
                        if(cursor.getString (2).equals (et_pw.getText ().toString ())) { // pw가 일치하는 경우 로그인 성공
                            Toast.makeText (Login.this, "로그인 되었습니다", Toast.LENGTH_SHORT).show ();
                            Intent intent = new Intent (Login.this, Refrigerator.class);
                            intent.putExtra ("id_check", et_id.getText ().toString ());
                            intent.putExtra ("pw_check", et_pw.getText ().toString ());
                            startActivity (intent);
                        } else { // pw가 일치하지 않는 경우
                            Toast.makeText (Login.this, "다시 입력해주세요", Toast.LENGTH_SHORT).show ();
                        }
                    }
                }
                cursor.close ();
            }
        });

        btn_register.setOnClickListener (new View.OnClickListener () { // 등록화면으로 이동
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Login.this, Register.class);
                startActivity (intent);
            }
        });
    }
}