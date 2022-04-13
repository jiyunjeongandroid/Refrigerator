package com.example.helprefrigerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Refrigerator extends AppCompatActivity implements Adapter.OnFoodListener {

    private String str_name;
    private String str_id, str_pw, id_name, id_date, id_amount;
    public RecyclerView rv_refrigerator;
    private List<String> titles;
    private List<Integer> images;
    public static Adapter list_adapter;
    private String[] list_name;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_refrigerator);

        rv_refrigerator = findViewById (R.id.rv_refrigerator);
        ImageButton btn_plus = findViewById (R.id.btn_plus);
        ImageButton btn_search = findViewById (R.id.btn_search);

        Intent getIntent = getIntent (); // 로그인 액티비티로부터 id, pw값 받기
        str_id = getIntent.getStringExtra ("id_check");
        str_pw = getIntent.getStringExtra ("pw_check");

        titles = new ArrayList<> ();
        images = new ArrayList<> ();

        list_adapter = new Adapter (this,titles,images,this::onFoodClick);
        list_adapter.notifyDataSetChanged ();

        GridLayoutManager gridLayoutManager = new GridLayoutManager (this,3,GridLayoutManager.VERTICAL, false); // 한 줄에 3개씩 뿌려주기
        rv_refrigerator.setLayoutManager (gridLayoutManager);
        rv_refrigerator.setAdapter (list_adapter);

        DbHelper dbHelper = DbHelper.getInstance (Refrigerator.this); // db연결
        cursor = dbHelper.getReadableDatabase ().rawQuery ("SELECT * FROM list WHERE name LIKE ?", new String[] {str_id+"%"});
        while(cursor.moveToNext ()) {
            try {
                String id = cursor.getString (1);
                list_name = cursor.getString (3).split ("_");
                switch (list_name[1]) {
                    case "apple" :
                        titles.add ("APPLE");
                        images.add (R.drawable.ic_apple);
                        break;
                    case "banana" :
                        titles.add ("BANANA");
                        images.add (R.drawable.ic_banana);
                        break;
                    case "orange" :
                        titles.add ("ORANGE");
                        images.add (R.drawable.ic_orange);
                        break;
                    case "milk" :
                        titles.add ("MILK");
                        images.add (R.drawable.ic_milk);
                        break;
                    case "rice" :
                        titles.add ("RICE");
                        images.add (R.drawable.ic_rice);
                        break;
                }list_adapter.notifyDataSetChanged ();
            } catch (Exception e) { }
        }

        btn_search.setOnClickListener (new View.OnClickListener () { // 찾기 버튼 클릭시 Search액티비티로 전환
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Refrigerator.this,Search.class);
                intent.putExtra ("id", str_id);
                startActivity (intent);
            }
        });

        btn_plus.setOnClickListener (new View.OnClickListener () { // 추가 버튼 클릭시 Food액티비티로 전환
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Refrigerator.this, Food.class);
                intent.putExtra ("user_id", str_id);
                startActivity (intent);
            }
        });
    }

    @Override
    public void onFoodClick(int position) {
        String send = titles.get (position);
        Intent intent = new Intent (Refrigerator.this, Food.class);
        intent.putExtra ("selected_id",str_id);
        intent.putExtra ("selectors", send);
        startActivity (intent);
    }
}
