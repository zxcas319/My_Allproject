package com.example.samsung.anew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.samsung.anew.R.id.radioButton;
import static com.example.samsung.anew.R.id.radioButton2;
import static com.example.samsung.anew.R.id.radioButton3;
import static com.example.samsung.anew.R.id.radioButton4;
import static com.example.samsung.anew.R.id.radioButton5;
import static com.example.samsung.anew.R.id.radioButton6;

public class MaintoFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainto_food);

/**
 *      음식을 RadioButton을 통해 선택하고 foodchoosebutton1을 클릭한 후의 동작을 설정해준다.
 */
        final RadioGroup rg1 = (RadioGroup)findViewById(R.id.radioGroup1);
        Button b1 = (Button)findViewById(R.id.foodchoosebutton1);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id1 = rg1.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(id1);
/*
 *      선택된 radioButton을 확인하고 해당 Button이 체크되었는지까지 확인하여
  *     각 버튼에 맞는 레시피 화면으로 넘어간다.
 */
                if (id1==radioButton && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _1Gyukacheu.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(id1==radioButton2 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _2Gimmakki.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(id1==radioButton3 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _3ChickenSalad.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(id1==radioButton4 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _4BeanPasteStew.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(id1==radioButton5 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _5RiceCake.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(id1==radioButton6 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _6Ddukbulgogi.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Choose food!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

/**
 *  해당 함수를 가진 버튼을 클릭할 때 Food 2번 페이지로 넘어간다.
 */
    public void foodnextButtonClicked1(View v){
        Intent intent = new Intent(getApplicationContext(), MaintoFood2.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Next page.", Toast.LENGTH_SHORT).show();
    }

/**
 *  해당 함수를 가진 버튼을 클릭할때 메인 화면으로 돌아가며 현재 화면을 종료시킨다.
 */
    public void foodmenuButtonClicked1(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Main menu.", Toast.LENGTH_SHORT).show();
        finish();
    }


}
