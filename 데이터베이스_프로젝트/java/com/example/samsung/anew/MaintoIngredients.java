package com.example.samsung.anew;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MaintoIngredients extends Info {
    CheckBox checkbox, checkbox2, checkbox3, checkbox4, checkbox5, checkbox6, checkbox7, checkbox8, checkbox9, checkbox10, checkbox11, checkbox12, checkbox13;
    private static String DATABASE_NAME = "db";
    private static int DATABASE_VERSION = 1;
    public DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainto_ingredients);

        dbHelper = new DatabaseHelper(this);
        dbHelper.onCreate(db);

        checkbox = (CheckBox) findViewById(R.id.checkbox);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkbox5 = (CheckBox) findViewById(R.id.checkbox5);
        checkbox6 = (CheckBox) findViewById(R.id.checkbox6);
        checkbox7 = (CheckBox) findViewById(R.id.checkbox7);
        checkbox8 = (CheckBox) findViewById(R.id.checkbox8);
        checkbox9 = (CheckBox) findViewById(R.id.checkbox9);
        checkbox10 = (CheckBox) findViewById(R.id.checkbox10);
        checkbox11 = (CheckBox) findViewById(R.id.checkbox11);
        checkbox12 = (CheckBox) findViewById(R.id.checkbox12);
        checkbox13 = (CheckBox) findViewById(R.id.checkbox13);

        Button button = (Button) findViewById(R.id.ingre);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *  checkbox 중 체크된 항목에 대해서 Material table 내의 Own attribute를
                 *  기존 'X'에서 'O'로 변경 시켜준다.
                 */
                if (checkbox.isChecked()) {
                    Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 16)");
                }
                if (checkbox2.isChecked()) {
                    Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 17)");
                }
                if (checkbox3.isChecked()) {
                    Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 20)");
                }
                if (checkbox4.isChecked()) {
                    Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 21)");
                }
                if (checkbox5.isChecked()) {
                    Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 62)");
                }
                if (checkbox6.isChecked()) {
                    Toast.makeText(getApplicationContext(), "6", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 23)");
                }
                if (checkbox7.isChecked()) {
                    Toast.makeText(getApplicationContext(), "7", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 24)");
                }
                if (checkbox8.isChecked()) {
                    Toast.makeText(getApplicationContext(), "8", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 8)");
                }
                if (checkbox9.isChecked()) {
                    Toast.makeText(getApplicationContext(), "9", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 34)");
                }
                if (checkbox10.isChecked()) {
                    Toast.makeText(getApplicationContext(), "10", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 37)");
                }
                if (checkbox11.isChecked()) {
                    Toast.makeText(getApplicationContext(), "11", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 41)");
                }
                if (checkbox12.isChecked()) {
                    Toast.makeText(getApplicationContext(), "12", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 44)");
                }
                if (checkbox13.isChecked()) {
                    Toast.makeText(getApplicationContext(), "13", Toast.LENGTH_LONG).show();
                    db.execSQL("update Material set Own = 'O' where(Pnumber = 67)");
                }
                /**
                 *  다음 화면으로 넘어가면서 현재 화면을 종료시킨다.
                 */
                Intent intent = new Intent(getApplicationContext(), MaintoIngredients2.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void ingrebackButtonClicked(View v) {
        Toast.makeText(getApplicationContext(), "Back to main page.", Toast.LENGTH_LONG).show();
        finish();
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            Toast.makeText(getApplicationContext(), "creating table [Material].", Toast.LENGTH_SHORT).show();

            try {
                String DROP_SQL = "drop table if exists Material";
                db.execSQL(DROP_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            String CREATE_SQL = "create table Material("
                    + " Name char, "
                    + " Own varchar(1), "
                    + " Pnumber int)";

            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }

            println("inserting records.");

            try {
                db.execSQL("insert into Material values('간마늘', 'X', 1)");
                db.execSQL("insert into Material values('간장', 'X', 2)");
                db.execSQL("insert into Material values('감자', 'X', 3)");
                db.execSQL("insert into Material values('고구마 녹말', 'X', 4)");
                db.execSQL("insert into Material values('고추장', 'X', 5)");
                db.execSQL("insert into Material values('고춧가루', 'X', 6)");
                db.execSQL("insert into Material values('굴 소스', 'X', 7)");
                db.execSQL("insert into Material values('김밥용 김', 'X', 8)");
                db.execSQL("insert into Material values('날치알', 'X', 9)");
                db.execSQL("insert into Material values('녹말', 'X', 10)");
                db.execSQL("insert into Material values('느타리버섯', 'X', 11)");
                db.execSQL("insert into Material values('다진마늘', 'X', 12)");
                db.execSQL("insert into Material values('단무지', 'X', 13)");
                db.execSQL("insert into Material values('단호박', 'X', 14) ");
                db.execSQL("insert into Material values('달걀', 'X', 15) ");
                db.execSQL("insert into Material values('닭', 'X', 16) ");
                db.execSQL("insert into Material values('닭 가슴살', 'X', 17) ");
                db.execSQL("insert into Material values('당근', 'X', 18) ");
                db.execSQL("insert into Material values('대파', 'X', 19) ");
                db.execSQL("insert into Material values('돼지고기 안심', 'X', 20) ");
                db.execSQL("insert into Material values('된장', 'X', 21) ");
                db.execSQL("insert into Material values('두유', 'X', 22) ");
                db.execSQL("insert into Material values('딸기', 'X', 23) ");
                db.execSQL("insert into Material values('떡볶이 떡', 'X', 24) ");
                db.execSQL("insert into Material values('레몬', 'X', 25) ");
                db.execSQL("insert into Material values('마늘', 'X', 26) ");
                db.execSQL("insert into Material values('마요네즈', 'X', 27) ");
                db.execSQL("insert into Material values('맛술', 'X', 28) ");
                db.execSQL("insert into Material values('매실액', 'X', 29) ");
                db.execSQL("insert into Material values('메추리알', 'X', 30) ");
                db.execSQL("insert into Material values('목이버섯', 'X', 31) ");
                db.execSQL("insert into Material values('무', 'X', 32) ");
                db.execSQL("insert into Material values('밀가루', 'X', 33) ");
                db.execSQL("insert into Material values('바나나', 'X', 34) ");
                db.execSQL("insert into Material values('밥', 'X', 35) ");
                db.execSQL("insert into Material values('버터', 'X', 36) ");
                db.execSQL("insert into Material values('불고기용 소고기', 'X', 37) ");
                db.execSQL("insert into Material values('빵가루', 'X', 38) ");
                db.execSQL("insert into Material values('사과', 'X', 39) ");
                db.execSQL("insert into Material values('삶은 달걀', 'X', 40) ");
                db.execSQL("insert into Material values('생면', 'X', 41) ");
                db.execSQL("insert into Material values('설탕', 'X', 42) ");
                db.execSQL("insert into Material values('소금', 'X', 43) ");
                db.execSQL("insert into Material values('스테이크용 소고기', 'X', 44) ");
                db.execSQL("insert into Material values('슬라이스 치즈', 'X', 45) ");
                db.execSQL("insert into Material values('식용유', 'X', 46) ");
                db.execSQL("insert into Material values('식초', 'X', 47) ");
                db.execSQL("insert into Material values('양배추', 'X', 48) ");
                db.execSQL("insert into Material values('양상추', 'X', 49) ");
                db.execSQL("insert into Material values('양파', 'X', 50) ");
                db.execSQL("insert into Material values('어묵', 'X', 51) ");
                db.execSQL("insert into Material values('오이', 'X', 52) ");
                db.execSQL("insert into Material values('옥수수 전분', 'X', 53) ");
                db.execSQL("insert into Material values('올리고당', 'X', 54) ");
                db.execSQL("insert into Material values('완두콩', 'X', 55) ");
                db.execSQL("insert into Material values('우스터 소스', 'X', 56) ");
                db.execSQL("insert into Material values('우유', 'X', 57) ");
                db.execSQL("insert into Material values('진간장', 'X', 58) ");
                db.execSQL("insert into Material values('참기름', 'X', 59) ");
                db.execSQL("insert into Material values('청양고추', 'X', 60) ");
                db.execSQL("insert into Material values('청주', 'X', 61) ");
                db.execSQL("insert into Material values('춘장', 'X', 62) ");
                db.execSQL("insert into Material values('케첩', 'X', 63) ");
                db.execSQL("insert into Material values('토마토', 'X', 64) ");
                db.execSQL("insert into Material values('판젤라틴', 'X', 65) ");
                db.execSQL("insert into Material values('피클', 'X', 66); ");
                db.execSQL("insert into Material values('햄버거 번', 'X', 67) ");
                db.execSQL("insert into Material values('후추가루', 'X', 68)  ");
            } catch (Exception ex) {
                Log.e(TAG, "Exception in insert SQL", ex);
            }
        }
        public void onOpen(SQLiteDatabase db) {
            println("opened database [" + DATABASE_NAME + "].");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ".");
        }
    }
}
/*

 */
