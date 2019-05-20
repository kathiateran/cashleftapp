package com.example.demo;
import com.example.demo.DatabaseHelper;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{
    SQLiteDatabase db;
    Button btnClickMe, btnClickMe2;
    EditText emailid,password;
    Button forgotpass;


    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailid = (EditText)findViewById(R.id.loginEmail);
        password = (EditText)findViewById(R.id.loginPsswrd);


        openHelper=new DatabaseHelper(this);
        db=openHelper.getWritableDatabase();

        btnClickMe = (Button) findViewById(R.id.btnSignUp);
        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openSignUp();
            }
        });

        forgotpass = (Button)findViewById(R.id.btn_forgotPass);


        forgotpass.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              Intent launchForgotPass = new Intent(MainActivity.this, UserProfile.class);
                                              startActivity(launchForgotPass);

                                          }
                                      });
        btnClickMe = (Button) findViewById(R.id.btnLogin);
        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignIn();
            }


        });


    }

    public void openSignUp() {
        Intent launchActivity2 = new Intent(this, SignUpActivity.class);
        startActivity(launchActivity2);

    }

    public void openSignIn() {


        String email= emailid.getText().toString();
        String psswrd= password.getText().toString();
        //这是你之前的方法 为什么登录的时候 要给数据库表 signin插入数据呢
        // insertUser(email,psswrd);
        //登录应该 是从注册表里面查收 一下 用户输入的 内容 是否有这个 账号
        //先判断用户输入是否为空 ，为空 不能进行数据库查询
        if (email.length()==0){
            Toast.makeText(MainActivity.this,"Email  Not  Null！",Toast.LENGTH_SHORT).show();
            return;
        }

        if (psswrd.length()==0){
            Toast.makeText(MainActivity.this,"Password Not  Null！",Toast.LENGTH_SHORT).show();
            return;
        }
        //
        int i = 0;

        Cursor cursor = db.query(DatabaseHelper.signup, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String email_ids = cursor.getString(cursor.getColumnIndex("email_id"));
                String passwords = cursor.getString(cursor.getColumnIndex("password"));
                if ((emailid.getText().toString().equals(email_ids)) && (password.getText().toString().equals(passwords))) {
                    i = 1;
                    break;
                }
            } while (cursor.moveToNext());
        }
        if (i == 1) {
            Toast.makeText(MainActivity.this,"Account check is correct! Entering",Toast.LENGTH_SHORT).show();

            Intent launchActivity2 = new Intent(this, FrontActivity.class);
            startActivity(launchActivity2);
            cursor.close();
            db.close();
            MainActivity.this.finish();
        }else {
            Toast.makeText(MainActivity.this,"Errors in Mail and Password Entry Please Check！",Toast.LENGTH_SHORT).show();

        }



    }



    //插入语句
    public void insertUser(String emailid,String password)
    {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.signin_col1,emailid);
        values.put(DatabaseHelper.signin_col2,password);


        long result = db.insert(DatabaseHelper.signin, null, values);

        Toast.makeText(MainActivity.this,"result"+result,Toast.LENGTH_SHORT).show();

        db.close();
    }



}