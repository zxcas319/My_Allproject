package com.example.samsung.anew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.samsung.anew.R.id.radioButton10;
import static com.example.samsung.anew.R.id.radioButton11;
import static com.example.samsung.anew.R.id.radioButton12;
import static com.example.samsung.anew.R.id.radioButton7;
import static com.example.samsung.anew.R.id.radioButton8;
import static com.example.samsung.anew.R.id.radioButton9;

public class MaintoFood2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainto_food2);

/**
 *      음식을 RadioButton을 통해 선택하고 foodchoosebutton2을 클릭한 후의 동작을 설정해준다.
 */
        final RadioGroup rg2 = (RadioGroup)findViewById(R.id.radioGroup2);
        Button b2 = (Button)findViewById(R.id.foodchoosebutton2);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id2 = rg2.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(id2);
/*
 *      선택된 radioButton을 확인하고 해당 Button이 체크되었는지까지 확인하여
 *      각 버튼에 맞는 레시피 화면으로 넘어간다.
 */
                if (id2==radioButton7 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _7FruitJuice.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(id2==radioButton8 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _8Stake.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_LONG).show();
                }
                else if(id2==radioButton9 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _9Pudding.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(id2==radioButton10 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _10Jjajangmyeon.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(id2==radioButton11 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _11Tangsuyuk.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(id2==radioButton12 && rb.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), _12Hamburger.class);
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
    public void foodagoButtonClicked1(View v){
        Intent intent = new Intent(getApplicationContext(), MaintoFood.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Previous page.", Toast.LENGTH_SHORT).show();
    }

/**
 *  해당 함수를 가진 버튼을 클릭할때 메인 화면으로 돌아가며 현재 화면을 종료시킨다.
 */
    public void foodmenuButtonClicked2(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Main menu.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
