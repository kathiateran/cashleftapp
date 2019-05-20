package com.example.demo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    //private TextView currentUserID;
    private EditText newEmailID;
    private EditText newPassword;
    Button btn_EmailChange;
    Button btn_PassChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        openHelper = new DatabaseHelper(this);

        // currentUserID = (TextView) findViewById(R.id.txtCurrentUserID);
        newEmailID = findViewById(R.id.txtNewEmailID);
        newPassword = findViewById(R.id.txtNewPassword);


        btn_EmailChange = findViewById(R.id.btnEmailChange);
        btn_PassChange = findViewById(R.id.btnPassChange);

        //buttons
        btn_EmailChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newEmail = newEmailID.getText().toString();

                db = openHelper.getWritableDatabase();
                insertdata1(newEmail);
                Toast.makeText(getApplicationContext(), "All Set!", Toast.LENGTH_LONG).show();

            }
        });

        btn_PassChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newPass = newPassword.getText().toString();

                db = openHelper.getWritableDatabase();
                insertdata2(newPass);
                Toast.makeText(getApplicationContext(), "All Set!", Toast.LENGTH_LONG).show();


            }
        });

    }

    //database
    public void insertdata1(String newEmail) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COL_6, newEmail);

        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);


    }

    public void insertdata2(String newPass) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COL_7, newPass);

        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

    }

}