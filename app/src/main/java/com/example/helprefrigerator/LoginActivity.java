package com.example.helprefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        EditText et_id = findViewById (R.id.et_id);
        EditText et_pw = findViewById (R.id.et_pw);
        Button btn_login = findViewById (R.id.btn_login);
        Button btn_register = findViewById (R.id.btn_register);

        btn_login.setOnClickListener (new View.OnClickListener () { // 입력받은 id/pw를 db내의 데이터와 비교
            @Override
            public void onClick(View view) {

            }
        });

        btn_register.setOnClickListener (new View.OnClickListener () { // 등록화면으로 이동
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (LoginActivity.this,RegisterActivity.class);
                startActivity (intent);
            }
        });
    }
}