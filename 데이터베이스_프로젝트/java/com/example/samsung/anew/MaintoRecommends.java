package com.example.samsung.anew;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import static com.example.samsung.anew.R.id.radio_age_10;
import static com.example.samsung.anew.R.id.radio_age_20;
import static com.example.samsung.anew.R.id.radio_age_30;
import static com.example.samsung.anew.R.id.radio_age_40;
import static com.example.samsung.anew.R.id.radio_age_50;
import static com.example.samsung.anew.R.id.radio_age_60;
import static com.example.samsung.anew.R.id.radio_gender_female;
import static com.example.samsung.anew.R.id.radio_gender_male;


public class MaintoRecommends extends Info implements RadioGroup.OnCheckedChangeListener{
    String Re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainto_recommends);

        RadioGroup genderRadioGroup = (RadioGroup) this.findViewById(R.id.radio_group_gender);
        RadioGroup petRadioGroup = (RadioGroup) this.findViewById(R.id.radio_group_age);

        genderRadioGroup.setOnCheckedChangeListener(this);
        petRadioGroup.setOnCheckedChangeListener(this);

        final RadioGroup rg3 = (RadioGroup)findViewById(R.id.radio_group_gender);
        final RadioGroup rg4 = (RadioGroup)findViewById(R.id.radio_group_age);
        Button b3 = (Button)findViewById(R.id.recomchoosebutton);
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id3 = rg3.getCheckedRadioButtonId();
                int id4 = rg4.getCheckedRadioButtonId();
                RadioButton rb3 = (RadioButton) findViewById(id3);
                RadioButton rb4 = (RadioButton) findViewById(id4);
                /**
                 *  두 그룹의 체크된 radiobutton을 확인하여 해당하는 조건에 맞는 음식이름을 query문을 통해 찾고
                 *  그 이름에 맞는 화면으로 넘어간 후 현재 화면은 종료시킨다,
                 */
                if ((id3 == radio_gender_male && rb3.isChecked()) && (id4 == radio_age_10 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber " +
                            "AND Age = 10 AND Sex = '남')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _12Hamburger.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_male && rb3.isChecked()) && (id4 == radio_age_20 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 20 AND Sex = '남')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _6Ddukbulgogi.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_male && rb3.isChecked()) && (id4 == radio_age_30 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 30 AND Sex = '남')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _8Stake.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_male && rb3.isChecked()) && (id4 == radio_age_40 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 40 AND Sex = '남')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _4BeanPasteStew.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_male && rb3.isChecked()) && (id4 == radio_age_50 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 50 AND Sex = '남')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _4BeanPasteStew.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_male && rb3.isChecked()) && (id4 == radio_age_60 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 60 AND Sex = '남')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _6Ddukbulgogi.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_female && rb3.isChecked()) && (id4 == radio_age_10 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 10 AND Sex = '여')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _5RiceCake.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_female && rb3.isChecked()) && (id4 == radio_age_20 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 20 AND Sex = '여')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _3ChickenSalad.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_female && rb3.isChecked()) && (id4 == radio_age_30 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 30 AND Sex = '여')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _8Stake.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_female && rb3.isChecked()) && (id4 == radio_age_40 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 40 AND Sex = '여')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _9Pudding.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_female && rb3.isChecked()) && (id4 == radio_age_50 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 50 AND Sex = '여')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _4BeanPasteStew.class);
                    startActivity(intent);
                }
                else if ((id3 == radio_gender_female && rb3.isChecked()) && (id4 == radio_age_60 && rb4.isChecked())) {
                    Cursor cursor = db.rawQuery("select distinct Name from Food, Preference where(PreferenceFood = Fnumber AND Age = 60 AND Sex = '여')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Re = cursor.getString(0);
                    }
                    Toast.makeText(getApplicationContext(), ""+Re, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), _6Ddukbulgogi.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "잘못된 선택이 있습니다. 다시 선택해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

/**
 *  RadioButton이 선택될 경우 해당하는 정보를 toast를 통해 출력한다.
 */
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        StringBuilder msg = null;
        RadioButton checkedRadioButton = null;

        int radioGroupId = radioGroup.getId();

        if (radioGroupId == R.id.radio_group_gender) {
            msg = new StringBuilder("Gender: ");
        } else if (radioGroupId == R.id.radio_group_age) {
            msg = new StringBuilder("Age: ");
        }

        checkedRadioButton = (RadioButton) this.findViewById(checkedId);

        if (msg != null && checkedRadioButton != null) {
            Toast.makeText(this,
                    msg.append(checkedRadioButton.getText()),
                    Toast.LENGTH_SHORT).show();
        }
    }

/**
 *  해당 함수를 가진 버튼을 클릭하면 mainactivity 화면으로 돌아가며 해당 화면은 종료 시킨다.
 */
    public void recombackButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Back to main page.", Toast.LENGTH_SHORT).show();
        finish();
    }
}