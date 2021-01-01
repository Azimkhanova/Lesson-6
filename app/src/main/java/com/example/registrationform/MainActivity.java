package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registrationform.database.StoreDatabase;



import static com.example.registrationform.database.StoreDatabase.COLUMN_USER_EMAIL;
import static com.example.registrationform.database.StoreDatabase.COLUMN_USER_PASSWORD;
import static com.example.registrationform.database.StoreDatabase.TABLE_USERS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StoreDatabase storeDb;
    private SQLiteDatabase sqdb;

    EditText ed_enterEmail;
    EditText ed_enterPasswrd;

    Button Btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeDb = new StoreDatabase(this);
        sqdb = storeDb.getWritableDatabase();


        ed_enterEmail = findViewById(R.id.ed_enterEmail);
        ed_enterPasswrd = findViewById(R.id.ed_enterPasswrd);
        Btn_submit = findViewById(R.id.Btn_submit);



        Btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reg_button:

                if (TextUtils.isEmpty(ed_enterEmail.getText())){
                    ed_enterEmail.setError("Fill again");
                    return;
                }


                if (TextUtils.isEmpty(ed_enterPasswrd.getText())){
                    ed_enterPasswrd.setError("Fill again");
                    return;
                }

                ContentValues versionValues = new ContentValues();
                versionValues.put(COLUMN_USER_EMAIL,ed_enterEmail.getText().toString());
                versionValues.put(COLUMN_USER_PASSWORD, ed_enterPasswrd.getText().toString());


                sqdb.insert(TABLE_USERS, null, versionValues);
                Toast.makeText(this, "Info is exported to database", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, LoginAct.class);
                startActivity(intent);
                break;

            case R.id.Btn_submit:
                Intent intent2 = new Intent(this, LoginAct.class);
                startActivity(intent2);

                break;
        }



    }
}