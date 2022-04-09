package com.example.helprefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class Refrigerator extends AppCompatActivity implements Dialog.DialogListener {

    String str_name;
    String str_id, str_pw, id_name, id_date, id_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_refrigerator);

        ImageButton btn_plus = findViewById (R.id.btn_plus);
        ImageButton btn_search = findViewById (R.id.btn_search);

        Intent getIntent = getIntent (); // 로그인 액티비티로부터 id, pw값 받기
        str_id = getIntent.getStringExtra ("id_check");
        str_pw = getIntent.getStringExtra ("pw_check");

        btn_plus.setOnClickListener (new View.OnClickListener () { // 추가 버튼 클릭시 다이얼로그 열기
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private void openDialog() { // 다이얼로그
        Dialog dialog = new Dialog ();
        dialog.show (getSupportFragmentManager (),"dialog");
    }

    @Override
    public void applyTexts(String name_str, String date_str, String amount_str) { // 음식 name, date, amount을 저장
        str_name = name_str;
        id_name = str_id + "_" + name_str; // id + name
        id_date = str_id + "_" + date_str; // id+ date
        id_amount = str_id + "_" + amount_str; // id + amount

        ContentValues contentValues = new ContentValues ();
        contentValues.put (FoodList.Entry.COLUMN_NAME_NAME, id_name);
        contentValues.put (FoodList.Entry.COLUMN_NAME_DATE, id_date);
        contentValues.put (FoodList.Entry.COLUMN_NAME_AMOUNT,id_amount);
        SQLiteDatabase db = DbHelper.getInstance (Refrigerator.this).getWritableDatabase ();
        long newRowId = db.insert (FoodList.Entry.TABLE_NAME,null,contentValues);
        if(newRowId == -1) {
            Toast.makeText (Refrigerator.this, "문제가 발생하였습니다", Toast.LENGTH_SHORT).show ();
        } else {
            Toast.makeText (Refrigerator.this, "저장 완료", Toast.LENGTH_SHORT).show ();
        }
    }
}