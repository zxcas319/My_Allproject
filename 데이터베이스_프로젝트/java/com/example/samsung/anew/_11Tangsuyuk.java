package com.example.samsung.anew;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class _11Tangsuyuk extends Info {
    String Re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__11_tangsuyuk);

        ArrayList<String> arDessert = new ArrayList<String>();
        ArrayList<String> arraylist = new ArrayList<String>();
/**
 * Query를 통해서 Course table에 등록되어 있는 하나의 음식을 검색하여 다른 attribute의 음식 이름을 불러온다.
 **/
        Cursor cursor = db.rawQuery("select distinct Name from Food, Course where((Fnumber = SetMenu AND Fname = 6) OR (Fnumber = Fname AND SetMenu = 6))", null);
        int count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            Re = cursor.getString(0);
        }
        arraylist.add("궁합이 맞는 음식은 " + Re + " 입니다.");
/**
 * Query를 통해 선택 되어진 음식에 맞는 recipe를 받아온다.
 **/
        cursor = db.rawQuery("select Recipe from Recipe where(id == 34)", null);
        count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            Re = cursor.getString(0);
        }
        arDessert.add("" + Re);//받아온 recipe를 listview에 넣는다.

        cursor = db.rawQuery("select Recipe from Recipe where(id == 35)", null);
        count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            Re = cursor.getString(0);
        }
        arDessert.add("" + Re);

        cursor = db.rawQuery("select Recipe from Recipe where(id == 36)", null);
        count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            Re = cursor.getString(0);
        }
        arDessert.add("" + Re);

        cursor = db.rawQuery("select Recipe from Recipe where(id == 37)", null);
        count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            Re = cursor.getString(0);
        }
        arDessert.add("" + Re);

        cursor = db.rawQuery("select Recipe from Recipe where(id == 38)", null);
        count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            Re = cursor.getString(0);
        }
        arDessert.add("" + Re);

        cursor = db.rawQuery("select Recipe from Recipe where(id == 39)", null);
        count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            Re = cursor.getString(0);
        }
        arDessert.add("" + Re);

        cursor = db.rawQuery("select Recipe from Recipe where(id == 40)", null);
        count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            Re = cursor.getString(0);
        }
        arDessert.add("" + Re);

        cursor = db.rawQuery("select Recipe from Recipe where(id == 41)", null);
        count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            Re = cursor.getString(0);
        }
        arDessert.add("" + Re);

        cursor = db.rawQuery("select Recipe from Recipe where(id == 42)", null);
        count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            Re = cursor.getString(0);
        }
        arDessert.add("" + Re);

        ArrayAdapter<String> Adapter;
        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, arDessert);

        ListView list = (ListView) findViewById(R.id.List11);
        list.setAdapter(Adapter);
        list.setDivider(new ColorDrawable(Color.DKGRAY));
        list.setDividerHeight(2);

        ArrayAdapter<String> array;
        array = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, arraylist);

        ListView listview = (ListView) findViewById(R.id.Listview);
        listview.setAdapter(array);
        listview.setDivider(new ColorDrawable(Color.DKGRAY));
        listview.setDividerHeight(2);
    }

    /**
     * 메인으로 가는 버튼을 눌렀을 경우 메인으로 돌아가고 현재 page는 finish를 통해 종료시켜준다.
     **/
    public void gomenuButtonClicked11(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Main menu.", Toast.LENGTH_LONG).show();
        finish();
    }

    /**
     * 뒤로가기를 눌렀을 경우 현재페이지를 종료시켜다.
     */
    public void gobackButtonClicked11(View v) {
        finish();
    }
}