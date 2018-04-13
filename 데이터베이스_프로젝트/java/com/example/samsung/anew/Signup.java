package com.example.samsung.anew;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends Info implements AdapterView.OnItemSelectedListener {
    EditText editid, editpassword;
    RadioButton button1, button2;
    TextView textView1;
    String Tid, Tpw, Tage, Tsex;
    Spinner spinner;
    String Cid;
    String Cpw;
    String[] items = {"10", "20", "30", "40", "50", "60"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editid = (EditText) findViewById(R.id.editid);
        editpassword = (EditText) findViewById(R.id.editpassword);
        button1 = (RadioButton) findViewById(R.id.button1);
        button2 = (RadioButton) findViewById(R.id.button2);
        textView1 = (TextView) findViewById(R.id.textView1);
        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Tid = editid.getText().toString();
                Tpw = editpassword.getText().toString();
                if (button1.isChecked()) {
                    Tsex = button1.getText().toString();
                } else if (button2.isChecked()) {
                    Tsex = button2.getText().toString();
                }
                Tage = textView1.getText().toString();
/**
 * 회원가입시 입력을 안한 부분이 있으면 오류문을 띄운다.
 */
                if (editid.getText().toString().length() == 0 || editpassword.getText().toString().length() == 0 || (!button1.isChecked() && !button2.isChecked()) || Tage == null) {
                    Toast.makeText(getApplicationContext(), "잘못된 입력이 있습니다. 다시 확인해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                /**
                 * Database의 Profile table에서 Id 값을 읽어온다
                 */
                Cursor cursor = db.rawQuery("SELECT Id FROM Profile where(Id = '" + Tid + "')", null);
                int count = cursor.getCount();
                for (int i = 0; i < count; i++) {
                    cursor.moveToNext();
                    Cid = cursor.getString(0);
                }
                /**
                 * 입력한 Id와 Profile table에서 읽어온 Id가 일치하면 등록된 Id라는 문구를 나타낸다.
                 */
                if (Tid.equals(Cid))
                    Toast.makeText(getApplicationContext(), "이미 등록된 아이디 입니다.", Toast.LENGTH_LONG).show();
                /**
                 * Profile table에 Id가 존재하지 않으면
                 * 새로 table에 Id, Password, Age, Sex를 추가해준다.
                 */
                else {
                    try {
                        if (db != null) {
                            Toast.makeText(getApplicationContext(), "insert", Toast.LENGTH_SHORT).show();
                            db.execSQL("INSERT INTO Profile VALUES" +
                                    "('" + Tid + "', '" + Tpw + "'," + Tage + ", '" + Tsex + "')");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplication(), "회원가입 되었습니다.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    /**
     * 아이템이 선택되었을 때 처리
     */
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        textView1.setText(items[position]);
    }

    /**
     * 아무것도 선택되지 않았을 때 처리
     */
    public void onNothingSelected(AdapterView<?> parent) {
        textView1.setText("");
    }
}