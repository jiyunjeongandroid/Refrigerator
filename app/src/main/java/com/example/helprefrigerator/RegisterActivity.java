package com.example.helprefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_pw;
    private EditText ret_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);

        et_id = findViewById (R.id.et_id);
        et_pw = findViewById (R.id.et_pw);
        ret_pw = findViewById (R.id.ret_pw);
        ImageButton btn_back = findViewById (R.id.btn_back);
        Button btn_register = findViewById (R.id.btn_register);

        btn_back.setOnClickListener (new View.OnClickListener () { // 이전 화면인 로그인 화면으로 이동
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (RegisterActivity.this,LoginActivity.class);
                startActivity (intent);
            }
        });

        btn_register.setOnClickListener (new View.OnClickListener () { // 등록 버튼 누르면 입력받은 id, pw를 db에 저장
            @Override
            public void onClick(View view) {
                String id = et_id.getText ().toString ();
                String pw = et_pw.getText ().toString ();
                String check = ret_pw.getText ().toString ();

                if(id.length () == 0 || pw.length () == 0) { // id, pw 입력값이 없는 경우
                    Toast.makeText (RegisterActivity.this, "ID/PW를 입력해주세요", Toast.LENGTH_SHORT).show ();
                } else {
                    if (pw.equals (check)) { //  et_pw와 ret_pw가 일치하는 경우
                        ContentValues contentValues = new ContentValues (); // 객체 생성
                        contentValues.put (FoodList.Entry.COLUMN_NAME_ID, id);
                        contentValues.put (FoodList.Entry.COLUMN_NAME_PW, pw);

                        SQLiteDatabase db = DbHelper.getInstance (RegisterActivity.this).getWritableDatabase ();
                        long newRowId = db.insert (FoodList.Entry.TABLE_NAME, null, contentValues);

                        if (newRowId == -1) { // 에러났을 경우
                            Toast.makeText (RegisterActivity.this, "문제가 발생하였습니다", Toast.LENGTH_SHORT).show ();
                        } else { // 성공한 경우
                            Toast.makeText (RegisterActivity.this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show ();
                            startActivity (new Intent (RegisterActivity.this, LoginActivity.class));
                        }
                    } else { // et_pw와 ret_pw가 불일치하는 경우
                        Toast.makeText (RegisterActivity.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show ();
                    }
                }
            }
        });
    }
}