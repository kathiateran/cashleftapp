package com.example.demo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity{

        EditText _loginEmail, _txtFirstName,_txtLastName,_txtPassword;
        Button _btnReg;

        SQLiteOpenHelper openHelper;
        SQLiteDatabase db;
        //DatabaseHelper databaseHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up2);

            _loginEmail  = (EditText)findViewById(R.id.loginEmail);
            _txtPassword = (EditText)findViewById(R.id.txtPassword);
            _txtFirstName = (EditText)findViewById(R.id.txtFirstName);
            _txtLastName = (EditText)findViewById(R.id.txtLastName);

            _btnReg = (Button)findViewById(R.id.btnReg);

            openHelper=new DatabaseHelper(this);
            db=openHelper.getWritableDatabase();
            _btnReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String firstName= _txtFirstName.getText().toString();
                    String lastName= _txtLastName.getText().toString();
                    String email= _loginEmail.getText().toString();
                    String password= _txtPassword.getText().toString();

                    //拦截输入为空的情况 防止数据库进入空数据

                    if (firstName.length()==0){
                        Toast.makeText(SignUpActivity.this,"First Name  Not  Null！",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (lastName.length()==0){
                        Toast.makeText(SignUpActivity.this,"Last Name  Not  Null！",Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if (email.length()==0){
                        Toast.makeText(SignUpActivity.this,"Email  Not  Null！",Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if (password.length()==0){
                        Toast.makeText(SignUpActivity.this,"Password  Not  Null！",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    insertData(firstName, lastName, email, password);

                }
            });

        }



        //database数据入库的方法
        public void insertData(String firstName, String lastName, String email, String password){
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.signup_col2, firstName);
            contentValues.put(DatabaseHelper.signup_col3, lastName);
            contentValues.put(DatabaseHelper.signup_col4, email);
            contentValues.put(DatabaseHelper.signup_col5, password);
            long id = db.insert(DatabaseHelper.signup,null, contentValues);
//
            if (id!=-1){
                Toast.makeText(SignUpActivity.this,"User Registration Successful！",Toast.LENGTH_SHORT).show();

                    Intent launchActivity2 = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(launchActivity2);
                    finish();
            }else {
                Toast.makeText(SignUpActivity.this,"User registration failed！",Toast.LENGTH_SHORT).show();
            }

            db.close();
        }

}

