package com.example.helprefrigerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    RecyclerView SearchList;
    List<String> titles;
    List<String> dates;
    List<String> amounts;
    List<Integer> images;
    AdapterSearch searchAdapter;
    EditText et_search;
    String[] list_name, list_date, list_amount;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_search);

        SearchList = findViewById (R.id.rv_search);
        et_search = findViewById (R.id.et_search);
        ImageButton btn_search = findViewById (R.id.btn_search);

        titles = new ArrayList<> ();
        dates = new ArrayList<> ();
        amounts = new ArrayList<> ();
        images = new ArrayList<> ();

        searchAdapter = new AdapterSearch(this,titles,dates,amounts,images);
        searchAdapter.notifyDataSetChanged ();

        GridLayoutManager gridLayoutManager = new GridLayoutManager (this,1,GridLayoutManager.VERTICAL,false);
        SearchList.setLayoutManager (gridLayoutManager);
        SearchList.setAdapter (searchAdapter);

        Intent getIntent = getIntent ();
        id = getIntent.getStringExtra ("id");

        btn_search.setOnClickListener (new View.OnClickListener () { // 클릭된 각 아이템들의 데이터 출력
            @Override
            public void onClick(View v) {
                String txt_search = et_search.getText ().toString ();
                DbHelper dbHelper = DbHelper.getInstance (Search.this);
                Cursor cursor = dbHelper.getReadableDatabase ().rawQuery ("SELECT * FROM list WHERE name LIKE ?", new String[]{id + "%"});
                while (cursor.moveToNext ()) {
                    try {
                        list_name = cursor.getString (3).split ("_");
                        list_date = cursor.getString (4).split ("_");
                        list_amount = cursor.getString (5).split ("_");

                        if (list_name[1].equals (txt_search)) {
                            titles.add (list_name[1]);
                            dates.add (list_date[1]);
                            amounts.add (list_amount[1]);
                            switch (list_name[1]){
                                case "apple" :
                                    images.add (R.drawable.ic_apple);
                                    break;
                                case "banana" :
                                    images.add (R.drawable.ic_banana);
                                    break;
                                case "orange" :
                                    images.add (R.drawable.ic_orange);
                                    break;
                                case "milk" :
                                    images.add (R.drawable.ic_milk);
                                    break;
                                case "rice" :
                                    images.add (R.drawable.ic_rice);
                                    break;
                            }
                            searchAdapter.notifyDataSetChanged ();
                        } else { }
                    } catch (Exception e) {
                    }
                }
            }
        });
    }
}