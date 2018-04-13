package com.example.samsung.anew;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MaintoIngredients2 extends Info implements AdapterView.OnItemSelectedListener {
    String[] items = {"", "", "", "", "", "", "", "", "", "", "", ""};
    Spinner spinner;
    TextView textView;
    String Re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainto_ingredients2);
        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.textView);
/**
 *      Material table의 Own attribute가 'O' 인 경우를 찾아 해당 Material을 사용하는 Food의 이름을 배열에 저장한다.
  */
        Cursor cursor = db.rawQuery("select distinct Food.Name from Food, Material where(Own = 'O' AND Material = Pnumber)", null);
        int count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();

            items[i] = cursor.getString(0);
        }
/**
 *      배열에 저장된 Food 이름들을 Spinner를 통해 출력한다.
 */
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.ingreButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Spinner에서 선택된 항목을 textview를 통해 값으로 가져오고,
                 * 그 값을 비교하여 해당하는 화면으로 넘어가는 역할을 한다.
                 */
                Re = textView.getText().toString();
                Toast.makeText(getApplicationContext(), "Select "+Re, Toast.LENGTH_SHORT).show();
                if (Re.equals("규카츠")) {
                    Intent intent = new Intent(getApplicationContext(), _1Gyukacheu.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(Re.equals("김마끼")) {
                    Intent intent = new Intent(getApplicationContext(), _2Gimmakki.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(Re.equals("닭가슴살 샐러드")) {
                    Intent intent = new Intent(getApplicationContext(), _3ChickenSalad.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(Re.equals("된장찌개")) {
                    Intent intent = new Intent(getApplicationContext(), _4BeanPasteStew.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(Re.equals("떡볶이")) {
                    Intent intent = new Intent(getApplicationContext(), _5RiceCake.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(Re.equals("뚝불고기")) {
                    Intent intent = new Intent(getApplicationContext(), _6Ddukbulgogi.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if (Re.equals("딸기 바나나 생과일 주스")) {
                    Intent intent = new Intent(getApplicationContext(), _7FruitJuice.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(Re.equals("스테이크")) {
                    Intent intent = new Intent(getApplicationContext(), _8Stake.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_LONG).show();
                }
                else if(Re.equals("단호박 푸딩")) {
                    Intent intent = new Intent(getApplicationContext(), _9Pudding.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(Re.equals("자장면")) {
                    Intent intent = new Intent(getApplicationContext(), _10Jjajangmyeon.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(Re.equals("탕수육")) {
                    Intent intent = new Intent(getApplicationContext(), _11Tangsuyuk.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
                else if(Re.equals("햄버거")) {
                    Intent intent = new Intent(getApplicationContext(), _12Hamburger.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Show recipe.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /**
         * Spinner에서 선택된 항목을 textview에 출력하며, 빈 항목을 클릭할 경우 첫 항목을 출력하는 역할을 한다.
         */
        if(items[position] == "") {
            textView.setText(items[0]);
            Toast.makeText(getApplicationContext(), "잘못된 선택이 있습니다. 다시 선택해 주세요.", Toast.LENGTH_LONG).show();
        }
        else
            textView.setText(items[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

/**
 *  해당 함수를 가진 버튼을 클릭하면 mainactivity로 돌아가며 해당 화면을 종료시킨다.
 */
    public void Backclicked(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}