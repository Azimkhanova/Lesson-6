package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

public class LoginAct extends AppCompatActivity implements View.OnClickListener {

    private StoreDatabase storeDb;
    private SQLiteDatabase sqdb;

    EditText et_email;
    EditText et_password;
    Button reg_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        storeDb = new StoreDatabase(this);
        sqdb = storeDb.getWritableDatabase();

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);


        reg_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(et_email.getText())) {
            et_email.setError("Fill again");
            return;
        }

        if (TextUtils.isEmpty(et_password.getText())) {
            et_password.setError("Fill again");
            return;
        }

        Cursor userCursor = sqdb.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_EMAIL + "=? AND " +
                COLUMN_USER_PASSWORD + "=? ", new String[]{et_email.getText().toString(), et_password.getText().toString()});


        Intent intent = new Intent(this, LoginAct.class);
        startActivity(intent);
    }
}