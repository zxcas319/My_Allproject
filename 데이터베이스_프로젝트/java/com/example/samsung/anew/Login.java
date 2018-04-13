package com.example.samsung.anew;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 어플 처음 시작시 가장 먼저 나타나는 화면
 * 시작시 Info를 통해 Database가 open이 되고 Profile, Recipe, Food, Course, Preference table들을 생성한다.
 */
public class Login extends Info {
    EditText loginid;
    EditText loginpassword;
    String Id;
    String Pw;
    String Cid = null;
    private static String DATABASE_NAME = "db";
    private static int DATABASE_VERSION = 1;
    public DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        dbHelper.onCreate(db);

        loginid = (EditText) findViewById(R.id.loginid);
        loginpassword = (EditText) findViewById(R.id.loginpassword);
/**
 * 회원가입 버튼을 누르면 회원가입화면으로 넘어간다.
 */
        Button signupbutton = (Button) findViewById(R.id.signupbutton);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });
/**
 * 로그인 버튼을 누르면 일치시 메인화면으로 넘어가고
 * 불일치시 오류 message를 띄운다.
 */
        Button loginbutton = (Button) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Database의 Profile에서 입력된 정보와 값을 비교해 로그인 한다.
                 */
                if (db != null) {
                    Id = loginid.getText().toString();
                    Pw = loginpassword.getText().toString();
                    Cursor cursor = db.rawQuery("select Id from Profile where(Pw = '" + Pw + "' AND Id = '" + Id + "')", null);
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        cursor.moveToNext();

                        Cid = cursor.getString(0);
                    }
                    if (Id.equals(Cid)) {
                        Intent main = new Intent(getApplication(), MainActivity.class);
                        startActivity(main);
                        Toast.makeText(getApplicationContext(), "" + loginid.getText().toString() + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "정확한 정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                }
            }
        });
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
                public DatabaseHelper(Context context) {
                    super(context, DATABASE_NAME, null, DATABASE_VERSION);
                }

                /**
                 * 각각의 table 생성
                 */
                public void onCreate(SQLiteDatabase db) {
                    /**
                     * Preference table create
                     */
                    Toast.makeText(getApplicationContext(), "creating table [Preference].", Toast.LENGTH_SHORT).show();
/**
 * Preference table 존재시 table 전체를 없애준다.
 */
            try {
                String DROP_SQL = "drop table if exists Preference";
                db.execSQL(DROP_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }
/**
 * Preference table을 새로 생성해준다.
 */
            String CREATE_SQL = "create table Preference ("
                    + " Age int, "
                    + " Sex char, "
                    + " PreferenceFood int)";
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }

            println("inserting records.");
/**
 * Preference table에 정보를 넣어준다.
 */
            try {
                db.execSQL("insert into Preference values (10, '남', 11);");
                db.execSQL("insert into Preference values (20, '남', 2);");
                db.execSQL("insert into Preference values (30, '남', 3);");
                db.execSQL("insert into Preference values (40, '남', 1);");
                db.execSQL("insert into Preference values (50, '남', 1);");
                db.execSQL("insert into Preference values (60, '남', 2);");
                db.execSQL("insert into Preference values (10, '여', 12);");
                db.execSQL("insert into Preference values (20, '여', 4);");
                db.execSQL("insert into Preference values (30, '여', 3);");
                db.execSQL("insert into Preference values (40, '여', 9);");
                db.execSQL("insert into Preference values (50, '여', 1);");
                db.execSQL("insert into Preference values (60, '여', 2);");
            } catch (Exception ex) {
                Log.e(TAG, "Exception in insert SQL", ex);
            }
/**
 * Course table create
 */
            Toast.makeText(getApplicationContext(), "creating table [Course].", Toast.LENGTH_SHORT).show();
/**
 * Course table 존재시 table을 없애준다.
 */
            try {
                String DROP_SQL = "drop table if exists Course";
                db.execSQL(DROP_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }
/**
 * Coyrse table create
 */
            CREATE_SQL = "create table Course ("
                    + " Fname int, "
                    + " SetMenu int)";

            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }

            println("inserting records.");
/**
 * Course table에 정보를 넣어준다.
 */
            try {
                db.execSQL("insert into Course values (1, 2);");
                db.execSQL("insert into Course values (3, 10);");
                db.execSQL("insert into Course values (4, 9);");
                db.execSQL("insert into Course values (5, 6);");
                db.execSQL("insert into Course values (7, 8);");
                db.execSQL("insert into Course values (11, 12);");
            } catch (Exception ex) {
                Log.e(TAG, "Exception in insert SQL", ex);
            }
/**
 * Recipe table create
 */
            Toast.makeText(getApplicationContext(), "creating table [Recipe].", Toast.LENGTH_SHORT).show();
/**
 * Recipe table이 존재하면 table을 없애준다.
 */
            try {
                String DROP_SQL = "drop table if exists Recipe";
                db.execSQL(DROP_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }
/**
 * Recipe table을 생성한다
 */
            CREATE_SQL = "create table Recipe ("
                    + " Id int, "
                    + " Recipe char)";

            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }
/**
 * Recipe table에 정보를 넣어준다.
 */
            println("inserting records.");

            try {

                db.execSQL("insert into Recipe values(1, '1. 무는 살짝 도톰하게 채썰어서 준비 고기는 얇게 채 썰어주세요.')");
                db.execSQL("insert into Recipe values(2, '2. 대파와 고추는 송송 썰어서 준비합니다.')");
                db.execSQL("insert into Recipe values(3, '3. 뚝배기에 채썬 고기 넣고 달달 볶다가 사진처럼 고기가 익어서 기름이 나올때 까지 볶다가 무를 넣고 함꼐 볶아 줍니다.')");
                db.execSQL("insert into Recipe values(4, '4. 무가 나른하게 숨이 죽으면 쌀뜨물을 부어주세요. 건더기가 잠기겠금 자작하게 부어주면 됩니다.')");
                db.execSQL("insert into Recipe values(5, '5. 그리고 바로 된장과 고추장을 넣어주고 한번 끓여주세요')");
                db.execSQL("insert into Recipe values(6, '6. 한번 보글보글 끓어오르면 간마늘, 설탕을 넣고 다시 끓여줍니다. 된장의 텁텁함을 설탕이 잡아줍니다.')");
                db.execSQL("insert into Recipe values(7, '1. 소고기는 핏기가 갈 수 있도록 물에 담궈서 준비해주세요')");
                db.execSQL("insert into Recipe values(8, '2. 채소들은 먹기좋게 썰어주세요')");
                db.execSQL("insert into Recipe values(9, '3. 양념장을 계랑대로 섞어서 만들어주세요')");
                db.execSQL("insert into Recipe values(10, '4. 양념장에 고기를 넣고 조물조물~ 재워주는게 좋아요!')");
                db.execSQL("insert into Recipe values(11, '5. 준비한 체소를 우선 밑에 깔고, 채소위에 소고기를 올려서 끓여줍니다.')");
                db.execSQL("insert into Recipe values(12, '1. 닭은 소금, 후추, 맛술로 밑간해두고 감자와 양파를 두툼하게 썰어 준비')");
                db.execSQL("insert into Recipe values(13, '2. 달궈진 팬에 밑간 해두었던 닭을 올려준다. 닭껍질이 팬에 직접 닿도록 올려줘야 한다.')");
                db.execSQL("insert into Recipe values(14, '3. 뚜껑 덮고 닭살이 하얗게 변하기 시작하면, 위에 감자, 양파, 마늘을 넣고 좀 더 익혀준다')");
                db.execSQL("insert into Recipe values(15, '4. 감자, 양파가 익으면 팬 바닥에 생긴 수분을 조금만 남긴채 따라내버리고 버터를 팬 구석구석 녹여준다.')");
                db.execSQL("insert into Recipe values(16, '5. 닭을 앞뒤로 노릇하게 익혀준다. 자주 뒤집어주세요')");
                db.execSQL("insert into Recipe values(17, '6. 튀긴후, 버터기름이 남아있는 팬에 다진마늘 잔뜩과 간장3, 식초2, 올리고당2, 생수2/3을 넣고 바글바글')");
                db.execSQL("insert into Recipe values(18, '7. 닭도 양념과 함께 바글바글~ 끝.')");
                db.execSQL("insert into Recipe values(19, '1. 냄비에 물을 올리고 소금 약간')");
                db.execSQL("insert into Recipe values(20, '2. 닭가슴살 넣고 끓이면서 맛술로 잡내를 잡아준다')");
                db.execSQL("insert into Recipe values(21, '3. 오이를 먹기 좋은 크기로 얇게 썰어서 소금에 버무려 절여둔다.')");
                db.execSQL("insert into Recipe values(22, '4. 닭가슴살이 속까지 익으면 건져내서 찢어준다.')");
                db.execSQL("insert into Recipe values(23, '5. 절인 오이는 물을 짜서 놓고 간을 봐서 짜면 한번 행궈준다.')");
                db.execSQL("insert into Recipe values(24, '6. 찢어진 닭가슴살을 볼에 넣고 다진 양파 한컵, 당근 약간, 단무지 반컵, 오이, 삶은 계란 넣고 비빈다.')");
                db.execSQL("insert into Recipe values(25, '7. 후춧가루 톡톡 넣고 식초 네숟갈, 레모은 반으로 잘라 반개만 즙을 짜 넣어준다.')");
                db.execSQL("insert into Recipe values(26, '8. 만들어 놓은 타르타르소스를 넣고 비벼서 먹으면 끝!!')");
                db.execSQL("insert into Recipe values(27, '9. 양파를 다져서 한컵, 당근은 적당량, 단무지 물기짜서 다져서 반컵, 설탕 한숟갈 소금쪼금 식초 두숟갈 넣고 비비고 마요네즈 + 후추 넣어서 타르타르 소스 완성')");
                db.execSQL("insert into Recipe values(28, '1. 돼지고기는 사방 1cm로 잘라 키친타월에 핏물을 제거합니다.')");
                db.execSQL("insert into Recipe values(29, '2. 감자와 당근은 껍질을 벗기고 사방 1cm 크기로 썹니다.')");
                db.execSQL("insert into Recipe values(30, '3. 양파와 양배추는 1cm크기로 설고 오이는 가늘게 채 썰어 준비합니다.')");
                db.execSQL("insert into Recipe values(31, '4. 메추리알은 삶아 찬물에 식힌 후 껍질을 제거합니다.')");
                db.execSQL("insert into Recipe values(32, '5. 달군 팬에 식용유를 두르고 고기, 야채순으로 익히고 춘장넣고 20분간 볶습니다.')");
                db.execSQL("insert into Recipe values(33, '6. 녹말로 농도를 맞추고 생면과 메추리알, 오이를 올려담아냅니다.')");
                db.execSQL("insert into Recipe values(34, '1. 소금과 후추 약간 넣어 고기를 밑간 합니다.')");
                db.execSQL("insert into Recipe values(35, '2. 고구마 + 옥수수 녹말 or 고구마 녹말에 물 적당량을 부어줍니다.')");
                db.execSQL("insert into Recipe values(36, '3. 시간이 지나 녹말과 물이 분리되면 물을 부어서 버려줍니다.')");
                db.execSQL("insert into Recipe values(37, '4. 녹말에 식용유 4큰술을 넣어 저어줍니다.')");
                db.execSQL("insert into Recipe values(38, '5. 소스에 들어갈 당근, 양파, 목이버섯을 적당한 크기로 잘라줍니다.')");
                db.execSQL("insert into Recipe values(39, '6. 물 300ml, 간장, 식초 50ml, 설탕 7큰술, 굴소스 2작은술을 넣고 끓여줍니다.')");
                db.execSQL("insert into Recipe values(40, '7. 소스가 끓으면 채소를 넣어주고 채소가 익으면 물 녹말을 넣어가며 농도를 맞춰줍니다.')");
                db.execSQL("insert into Recipe values(41, '8. 4에 밑간을 해둔 고기를 넣어서 골고루 묻혀주고 기름에 튀겨줍니다.')");
                db.execSQL("insert into Recipe values(42, '9. 8에 7을 곁들여 주면 완성됩니다.')");
                db.execSQL("insert into Recipe values(43, '1. 소고기에 소금과 후추, 다진마늘로 밑간을 한다.')");
                db.execSQL("insert into Recipe values(44, '2. 밑간을 한 소고기에 밀가루를 고루 묻혀준다.')");
                db.execSQL("insert into Recipe values(45, '3. 계란을 풀어서 소고기에 잘 입혀준다.')");
                db.execSQL("insert into Recipe values(46, '4. 계란옷을 입힌 소고기에 빵가루를 고루 입힌다.')");
                db.execSQL("insert into Recipe values(47, '5. 160~170도의 식용유에 소고기를 1분 30초간 튀겨준다.')");
                db.execSQL("insert into Recipe values(48, '6. 팬에 버터를 두르고 채 썬 양파, 사과, 마늘을 갈색이 될 때까지 볶는다.')");
                db.execSQL("insert into Recipe values(49, '7. 적당히 썬 바나나를 함께 볶는다. 충분히 걸쭉해지면 믹서에 곱게 간다.')");
                db.execSQL("insert into Recipe values(50, '8. 냄비에 우스터 소스, 진간장, 청주, 설탕을 넣고 끓이면서 농도를 조절한다.')");
                db.execSQL("insert into Recipe values(51, '9. 규카츠에 완성된 소스를 뿌려준다.')");
                db.execSQL("insert into Recipe values(52, '1. 그릇에 식초와 설탕, 소금을 넣고 전자레인지에 30초 정도 돌려서 배합초를 만든다.')");
                db.execSQL("insert into Recipe values(53, '2. 어묵과 단무지는 비슷한 굵기로 채 썰고, 오이는 단무지와 비슷한 길이로 깎은 뒤 씨를 빼내고 채썬다.')");
                db.execSQL("insert into Recipe values(54, '3. 따뜻한 밥에 배합초를 넣고 섞어 초밥을 만든다.')");
                db.execSQL("insert into Recipe values(55, '4. 김 1/2장에 초밥을 적당량 넣어 펼친 뒤 어묵과 단무지, 오이, 날치알을 올린다.')");
                db.execSQL("insert into Recipe values(56, '5. 고깔 모양으로 돌돌 말아 마무리한다.')");
                db.execSQL("insert into Recipe values(57, '1. 단호박을 반으로 갈라 속의 씨와 타래를 깨끗하게 긁어 냅니다.')");
                db.execSQL("insert into Recipe values(58, '2. 손질한 단호박을 찜기에 넣고 푹 찐 다음 겉 초록 껍질은 벗겨서 따로 모읍니다.')");
                db.execSQL("insert into Recipe values(59, '3. 껍질을 벗긴 단호박은 적당한 크기로 잘라준 뒤 믹서에 넣습니다.')");
                db.execSQL("insert into Recipe values(60, '4. 판 젤라틴 3장을 찬물에 10분동안 불려서 물기를 꼭 짜 둡니다.')");
                db.execSQL("insert into Recipe values(61, '5. 두유 1컵 반, 물 반컵, 옥수수전분 1큰술을 잘 섞어서 단호박을 넣은 믹서에 같이 넣은뒤 곱게 갈아줍니다.')");
                db.execSQL("insert into Recipe values(62, '6. 곱게 간 재료들은 냄비에 넣고 판 젤라틴 불린 것을 넣어서 나무 주걱으로 저어가며 약불에서 한 소끔 끓이다가 젤라틴이 모두 녹으면 2분 정도 더 끓여 줍니다.')");
                db.execSQL("insert into Recipe values(63, '7. 6.에서 만 든것을 그릇에 부어 옮겨 담은 뒤 냉장고에서 1시간 가량 차갑게 굳혀 줍니다.')");
                db.execSQL("insert into Recipe values(64, '8. 믹서에 단호박 껍질을 넣고 올리고당 1/3컵, 물 1/4컵을 넣은 다음 믹서에 곱게 갈아서 소스를 만듭니다.')");
                db.execSQL("insert into Recipe values(65, '9. 1시간 정도 냉장고 안에서 굳힌 단호박 푸딩을 꺼낸 후 소스를 살짝 부어 마무리 해줍니다.')");
                db.execSQL("insert into Recipe values(66, '1. 딸기와 바나나를 1:1 분량으로 준비합니다.')");
                db.execSQL("insert into Recipe values(67, '2. 우유를 100~150ml를 넣어주고 갈아줍니다.')");
                db.execSQL("insert into Recipe values(68, '3. 이때 기호에 따라 얼음도 2 ~ 3개를 같이 갈아줍니다.')");
                db.execSQL("insert into Recipe values(69, '1. 고기를 다진후 소금과 후추, 간장을 넣어 반죽을 한다.')");
                db.execSQL("insert into Recipe values(70, '2. 만들어둔 고기패티를 후라이팬에 참기름을 살짝 두른 후 적당히 굽는다.')");
                db.execSQL("insert into Recipe values(71, '3. 토마토와 양파, 양상추를 먹기 좋은 크기로 잘라둔다.')");
                db.execSQL("insert into Recipe values(72, '4. 약한 불에서 버터를 넣고 마늘 1큰술을 타지 않게 볶아준다.')");
                db.execSQL("insert into Recipe values(73, '5. 설탕 1숟갈을 넣고 섞어준 다음 케첩 2숟갈, 진간장 1숟갈, 식초 1/2숟갈을 넣고 졸인다.')");
                db.execSQL("insert into Recipe values(74, '6. 햄버거 번 위에 마요네즈, 양상추, 패티, 슬라이스 치즈, 토마토, 양파, 피클, 소스를 넣는다.')");
                db.execSQL("insert into Recipe values(75, '1. 떡을 물에 넣어 10분 동안 불려놓는다.')");
                db.execSQL("insert into Recipe values(76, '2. 채소 및 어묵을 먹기 좋은 크기로 자른다.')");
                db.execSQL("insert into Recipe values(77, '3. 물 2컵(400ml)을 후라이팬에 붓는다.')");
                db.execSQL("insert into Recipe values(78, '4. 고추장 4큰술, 고춧가루 1큰술, 다진마늘 1큰술, 설탕 1큰술, 간장 1큰술을 넣고 끓인다.')");
                db.execSQL("insert into Recipe values(79, '5. 물이 끓기 시작하면 떡을 넣고 3분간 더 끓인다.')");
                db.execSQL("insert into Recipe values(80, '6. 당근, 어묵, 대파 등 채소를 넣어준다.')");
                db.execSQL("insert into Recipe values(81, '7. 국물이 졸여질 때까지 끓여준다.')");
            } catch (Exception ex) {
                Log.e(TAG, "Exception in insert SQL", ex);
            }
/**
 * Profile table create
 */
            Toast.makeText(getApplicationContext(), "creating table [Profile].", Toast.LENGTH_SHORT).show();

            CREATE_SQL = "create table Profile("
                    + " Id char, "
                    + " Pw char, "
                    + " Age int, "
                    + " Sex char)";

            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }
/**
 * Profile table에 정보를 넣어준다.
 */
            println("inserting records.");

            try {
                db.execSQL("insert into Profile values('aaaa', 'aa1234', 20, '남')");
            } catch (Exception ex) {
                Log.e(TAG, "Exception in insert SQL", ex);
            }

            Toast.makeText(getApplicationContext(), "creating table [Food].", Toast.LENGTH_SHORT).show();
/**
 * Food table존재시 table을 없애준다.
 */
            try {
                String DROP_SQL = "drop table if exists Food";
                db.execSQL(DROP_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            CREATE_SQL = "create table Food("
                    + " Kind char, "
                    + " Fnumber int, "
                    + " Name char, "
                    + " Calorie int, "
                    + " Material int, "
                    + " Price int)";
/**
 * Food table create
 */
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }
/**
 * Food table에 정보를 넣어준다.
 */
            println("inserting records.");

            try {
                db.execSQL("insert into Food values('한식', 1, '된장찌개', 124, 1, 4500) ");
                db.execSQL("insert into Food values('한식', 1, '된장찌개', 125, 5, 4500) ");
                db.execSQL("insert into Food values('한식', 1, '된장찌개', 126, 19, 4500) ");
                db.execSQL("insert into Food values('한식', 1, '된장찌개', 127, 21, 4500) ");
                db.execSQL("insert into Food values('한식', 1, '된장찌개', 128, 33, 4500) ");
                db.execSQL("insert into Food values('한식', 1, '된장찌개', 129, 37, 4500) ");
                db.execSQL("insert into Food values('한식', 1, '된장찌개', 130, 42, 4500) ");
                db.execSQL("insert into Food values('한식', 1, '된장찌개', 131, 60, 4500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 2, 5500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 11, 5500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 12, 5500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 18, 5500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 19, 5500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 29, 5500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 37, 5500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 42, 5500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 50, 5500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 59, 5500) ");
                db.execSQL("insert into Food values('한식', 2, '뚝불고기', 318, 68, 5500) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 2, 12000) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 3, 12000) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 12, 12000) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 16, 12000) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 28, 12000) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 36, 12000) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 43, 12000) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 47, 12000) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 50, 12000) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 54, 12000) ");
                db.execSQL("insert into Food values('양식', 3, '스테이크', 897, 68, 12000) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 13, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 17, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 18, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 25, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 27, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 28, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 40, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 42, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 43, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 47, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 50, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 52, 2500) ");
                db.execSQL("insert into Food values('양식', 4, '닭가슴살 샐러드', 556, 68, 2500) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 3, 5000) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 10, 5000) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 18, 5000) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 20, 5000) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 30, 5000) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 41, 5000) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 46, 5000) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 48, 5000) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 50, 5000) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 52, 5000) ");
                db.execSQL("insert into Food values('중식', 5, '자장면', 864, 62, 5000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 2, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 4, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 7, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 10, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 18, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 20, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 31, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 42, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 43, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 46, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 47, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 50, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 53, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 55, 8000) ");
                db.execSQL("insert into Food values('중식', 6, '탕수육', 460, 68, 8000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 15, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 26, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 33, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 34, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 36, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 38, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 39, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 42, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 43, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 44, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 46, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 50, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 58, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 61, 7000) ");
                db.execSQL("insert into Food values('일식', 7, '규카츠', 370, 68, 7000) ");
                db.execSQL("insert into Food values('일식', 8, '김마끼', 202, 8, 6000) ");
                db.execSQL("insert into Food values('일식', 8, '김마끼', 202, 9, 6000) ");
                db.execSQL("insert into Food values('일식', 8, '김마끼', 202, 13, 6000) ");
                db.execSQL("insert into Food values('일식', 8, '김마끼', 202, 35, 6000) ");
                db.execSQL("insert into Food values('일식', 8, '김마끼', 202, 42, 6000) ");
                db.execSQL("insert into Food values('일식', 8, '김마끼', 202, 43, 6000) ");
                db.execSQL("insert into Food values('일식', 8, '김마끼', 202, 47, 6000) ");
                db.execSQL("insert into Food values('일식', 8, '김마끼', 202, 51, 6000) ");
                db.execSQL("insert into Food values('일식', 8, '김마끼', 202, 52, 6000) ");
                db.execSQL("insert into Food values('디저트', 9, '단호박 푸딩', 100, 14, 3400) ");
                db.execSQL("insert into Food values('디저트', 9, '단호박 푸딩', 100, 22, 3400) ");
                db.execSQL("insert into Food values('디저트', 9, '단호박 푸딩', 100, 53, 3400) ");
                db.execSQL("insert into Food values('디저트', 9, '단호박 푸딩', 100, 54, 3400) ");
                db.execSQL("insert into Food values('디저트', 9, '단호박 푸딩', 100, 65, 3400) ");
                db.execSQL("insert into Food values('디저트', 10, '딸기 바나나 생과일 주스', 100, 23, 1500) ");
                db.execSQL("insert into Food values('디저트', 10, '딸기 바나나 생과일 주스', 100, 34, 1500) ");
                db.execSQL("insert into Food values('디저트', 10, '딸기 바나나 생과일 주스', 100, 57, 1500) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 2, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 20, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 26, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 27, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 36, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 42, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 43, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 45, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 47, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 49, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 50, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 59, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 63, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 64, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 66, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 67, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 11, '햄버거', 318, 68, 4000) ");
                db.execSQL("insert into Food values('인스턴트', 12, '떡볶이', 377, 2, 3000) ");
                db.execSQL("insert into Food values('인스턴트', 12, '떡볶이', 377, 5, 3000) ");
                db.execSQL("insert into Food values('인스턴트', 12, '떡볶이', 377, 6, 3000) ");
                db.execSQL("insert into Food values('인스턴트', 12, '떡볶이', 377, 18, 3000) ");
                db.execSQL("insert into Food values('인스턴트', 12, '떡볶이', 377, 19, 3000) ");
                db.execSQL("insert into Food values('인스턴트', 12, '떡볶이', 377, 24, 3000) ");
                db.execSQL("insert into Food values('인스턴트', 12, '떡볶이', 377, 26, 3000) ");
                db.execSQL("insert into Food values('인스턴트', 12, '떡볶이', 377, 42, 3000) ");
                db.execSQL("insert into Food values('인스턴트', 12, '떡볶이', 377, 51, 3000) ");
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