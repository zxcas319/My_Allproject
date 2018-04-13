package com.example.samsung.anew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * 로그인 후 나타나는 메인 화면이다.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *음식선택 화면으로 넘어가게 해준다.
     */
    public void onmainButton1Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), MaintoFood.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Food was chosen.", Toast.LENGTH_SHORT).show();
    }

    /**
     *재료선택화면으로 넘어가게 해준다.
     */
    public void onmainButton2Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), MaintoIngredients.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Ingredients was chosen.", Toast.LENGTH_SHORT).show();
    }

    /**
     *연령과 나이를 고르는 화면으로 넘어가게 해준다.
     */
    public void onmainButton3Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), MaintoRecommends.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Recommends was chosen.", Toast.LENGTH_SHORT).show();
    }
}