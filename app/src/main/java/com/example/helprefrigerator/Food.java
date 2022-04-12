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
    String user_id,selected,selected_id;
    String foodname, fooddate, foodamount;
    private String[] selected_date,selected_amount;
    String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_food);

        et_foodname = findViewById (R.id.et_foodname);
        et_fooddate = findViewById (R.id.et_fooddate);
        et_foodamount = findViewById (R.id.et_foodamount);

        Intent getIntent = getIntent (); // Refrigerator로부터 id값 받기

        user_id = getIntent.getStringExtra ("user_id"); // userId 값 받기
        selected_id = getIntent.getStringExtra ("selected_id"); // userId 값 받기
        selected = getIntent.getStringExtra ("selectors"); // title값 받기

        if(selected != null) {
            DbHelper dbHelper = DbHelper.getInstance (Food.this);
            Cursor selected_cursor = dbHelper.getReadableDatabase ().rawQuery ("SELECT * FROM list WHERE name LIKE ?", new String[] {"%" + selected});
            while(selected_cursor.moveToNext ()) {
                selected_date = selected_cursor.getString (4).split ("_");
                selected_amount = selected_cursor.getString (5).split ("_");

                et_foodname.setText (selected);
                et_fooddate.setText (selected_date[1]);
                et_foodamount.setText (selected_amount[1]);

                _id = selected_cursor.getString (selected_cursor.getColumnIndexOrThrow (FoodList.Entry._ID));
                Log.v ("id","id" + _id);
            }
        }

        SQLiteDatabase db = DbHelper.getInstance (this).getWritableDatabase ();


        Button btn_foodcreate = findViewById (R.id.btn_foodcreate);
        btn_foodcreate.setOnClickListener (new View.OnClickListener () { // ok버튼 클릭시 입력받은 값들을 저장
            @Override
            public void onClick(View view) {
                str_foodname = et_foodname.getText ().toString ();
                str_fooddate = et_fooddate.getText ().toString ();
                str_foodamount = et_foodamount.getText ().toString ();

                foodname = user_id + "_" + str_foodname;
                fooddate = user_id + "_" + str_fooddate;
                foodamount = user_id + "_" + str_foodamount;

                ContentValues contentValues = new ContentValues ();
                contentValues.put (FoodList.Entry.COLUMN_NAME_NAME, foodname);
                contentValues.put (FoodList.Entry.COLUMN_NAME_DATE, fooddate);
                contentValues.put (FoodList.Entry.COLUMN_NAME_AMOUNT, foodamount);

                SQLiteDatabase db = DbHelper.getInstance (Food.this).getWritableDatabase ();
                long newRowId = db.insert (FoodList.Entry.TABLE_NAME,null,contentValues);
                if(newRowId == -1) {
                    Toast.makeText (Food.this, "문제가 발생하였습니다", Toast.LENGTH_SHORT).show ();
                } else {
                    Toast.makeText (Food.this, "저장 되었습니다", Toast.LENGTH_SHORT).show ();
                }
            }
        });
    }
}