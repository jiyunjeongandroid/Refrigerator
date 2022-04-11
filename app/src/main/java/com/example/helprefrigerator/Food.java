package com.example.helprefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Food extends AppCompatActivity {

    private String str_foodname, str_fooddate, str_foodamount;
    private EditText et_foodname, et_fooddate, et_foodamount;
    String food_plus,selected;
    String foodname, fooddate, foodamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_food);

        et_foodname = findViewById (R.id.et_foodname);
        et_fooddate = findViewById (R.id.et_fooddate);
        et_foodamount = findViewById (R.id.et_foodamount);

        Intent getIntent = getIntent (); // Refrigerator로부터 id값 받기
        food_plus = getIntent.getStringExtra ("food_id");

        Button btn_foodcreate = findViewById (R.id.btn_foodcreate);
        btn_foodcreate.setOnClickListener (new View.OnClickListener () { // ok버튼 클릭시 입력받은 값들을 저장
            @Override
            public void onClick(View view) {
                str_foodname = et_foodname.getText ().toString ();
                str_fooddate = et_fooddate.getText ().toString ();
                str_foodamount = et_foodamount.getText ().toString ();

                foodname = food_plus + "_" + str_foodname;
                fooddate = food_plus + "_" + str_fooddate;
                foodamount = food_plus + "_" + str_foodamount;

                ContentValues contentValues = new ContentValues ();
                contentValues.put (FoodList.Entry.COLUMN_NAME_NAME, foodname);
                contentValues.put (FoodList.Entry.COLUMN_NAME_DATE, fooddate);
                contentValues.put (FoodList.Entry.COLUMN_NAME_AMOUNT, foodamount);

                SQLiteDatabase db = DbHelper.getInstance (Food.this).getWritableDatabase ();
                long newRowId = db.insert (FoodList.Entry.TABLE_NAME,null,contentValues);
                if(newRowId == -1) {
                    Toast.makeText (Food.this, "문제가 발생하였습니다", Toast.LENGTH_SHORT).show ();
                } else {
                    Toast.makeText (Food.this, "저장 완료", Toast.LENGTH_SHORT).show ();
                    Log.v ("name", "is" + foodname);
                }
            }
        });
    }
}