package com.example.demo;

import android.arch.lifecycle.OnLifecycleEvent;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class Accounts extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DatabaseHelper db1;

    private EditText cardDebit;
    private EditText _dateDebit;
    private EditText _cvvDebit;
    private EditText cardCredit;
    private EditText _dateCredit;
    private EditText _cvvCredit;
    private EditText cashLimit;
    private EditText debitLimit;
    private EditText credLimit;

    Button btn_AddCredCard;
    Button btn_AddDebCard;
    Button btn_SetCashLim;
    Button btn_SetDebLim;
    Button btn_SetCredLim;

    private String uuId;
    private String Tag="1"; //0 updata  1 add
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_accounts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();


        cardDebit = findViewById(R.id.numDebitCard);
        _dateDebit = findViewById(R.id.dateDebit);
        _cvvDebit = findViewById(R.id.cvvDebit);
        cardCredit = findViewById(R.id.numCreditCard);
        _dateCredit = findViewById(R.id.dateCredit);
        _cvvCredit = findViewById(R.id.cvvCredit);
        cashLimit = findViewById(R.id.numCashLim);
        debitLimit = findViewById(R.id.numDebitLim);
        credLimit = findViewById(R.id.numCredLim);

        btn_AddDebCard = findViewById(R.id.btnAddDebCard);
        btn_AddCredCard = findViewById(R.id.btnAddCredCard);
        btn_SetCashLim = findViewById(R.id.btnSetCashLim);
        btn_SetDebLim = findViewById(R.id.btnSetDebLim);
        btn_SetCredLim = findViewById(R.id.btnSetCredLim);

        //

        //0 updata  1 add
        Cursor cursor =  db.rawQuery("select * from accounts", null);

        if (cursor.moveToFirst() == false) {
            uuId=UUID.randomUUID().toString().replaceAll("-","");
            Tag="1";
        }else {
                String _ID = cursor.getString(cursor.getColumnIndex("_ID"));
            String Cash = cursor.getString(cursor.getColumnIndex("Cash"));
            String Debit = cursor.getString(cursor.getColumnIndex("Debit"));
            String Credit = cursor.getString(cursor.getColumnIndex("Credit"));
                if (_ID.length()!=0){
                    uuId=_ID;
                    Tag="0";
                }else {
                    uuId=UUID.randomUUID().toString().replaceAll("-","");
                    Tag="1";
                }

                if (Cash.length()!=0){
                    cashLimit.setText(Cash);
                }

            if (Debit.length()!=0){
                debitLimit.setText(Debit);
            }

            if (Credit.length()!=0){
                credLimit.setText(Credit);
            }
        }


        cursor.close();

        //

        //buttons
        btn_AddDebCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num_DebitCard = cardDebit.getText().toString();
                String date_Debit = _dateDebit.getText().toString();
                String cvv_Debit = _cvvDebit.getText().toString();

//                db = openHelper.getWritableDatabase();
//                insertDebitCard(num_DebitCard, date_Debit, cvv_Debit);
                Toast.makeText(getApplicationContext(), "Under development!", Toast.LENGTH_LONG).show();
            }
        });

        btn_AddCredCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num_CreditCard = cardCredit.getText().toString();
                String date_Credit = _dateCredit.getText().toString();
                String cvv_Credit = _cvvCredit.getText().toString();

//                db = openHelper.getWritableDatabase();
//                insertCreditCard(num_CreditCard, date_Credit, cvv_Credit);
                Toast.makeText(getApplicationContext(), "Under development!", Toast.LENGTH_LONG).show();
            }
        });

        btn_SetCashLim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cash_limit = cashLimit.getText().toString();


                insertCashLimit(cash_limit);
               // Toast.makeText(getApplicationContext(), "All Set!", Toast.LENGTH_LONG).show();
            }
        });

        btn_SetDebLim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String debit_limit = debitLimit.getText().toString();
                ;
                insertDebitLimit(debit_limit);
               // Toast.makeText(getApplicationContext(), "All Set!", Toast.LENGTH_LONG).show();
            }
        });

        btn_SetCredLim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cred_limit = credLimit.getText().toString();


                insertCreditLimit(cred_limit);
               // Toast.makeText(getApplicationContext(), "All Set!", Toast.LENGTH_LONG).show();
            }
        });
    }

    //DATABASE
    //Cards
    public void insertDebitCard(String num_DebitCard, String date_Debit, String cvv_Debit){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COL_8, num_DebitCard);
        contentValues.put(DatabaseHelper.COL_9, date_Debit);
        contentValues.put(DatabaseHelper.COL_10, cvv_Debit);

      //  long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public void insertCreditCard(String num_CreditCard, String date_Credit, String cvv_Credit){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COL_11, num_CreditCard);
        contentValues.put(DatabaseHelper.COL_12, date_Credit);
        contentValues.put(DatabaseHelper.COL_13, cvv_Credit);

        //
        //long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    //Budget Limits
    public void insertCashLimit(String cash_limit) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.Cash, cash_limit);

        // 0 updata  1 add
        if ("0".equals(Tag)){
            db.update(DatabaseHelper.accounts,contentValues,"_ID=?",new String []{uuId});
            Toast.makeText(Accounts.this,"All Set!",Toast.LENGTH_LONG).show();
        }else {
            contentValues.put("_ID",uuId);
            long id = db.insert(DatabaseHelper.accounts, null, contentValues);

            if (id!=-1){
                Toast.makeText(Accounts.this,"All good",Toast.LENGTH_LONG).show();
                Tag="0";
            }
        }

    }

    public void insertDebitLimit(String debit_limit) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.Debit, debit_limit);


        // 0 updata  1 add
        if ("0".equals(Tag)){
            db.update(DatabaseHelper.accounts,contentValues,"_ID=?",new String []{uuId});
            Toast.makeText(Accounts.this,"All set!",Toast.LENGTH_LONG).show();
        }else {
            contentValues.put("_ID",uuId);
            long id = db.insert(DatabaseHelper.accounts, null, contentValues);

            if (id!=-1){
                Toast.makeText(Accounts.this,"All good",Toast.LENGTH_LONG).show();
                Tag="0";
            }
        }



    }

    public void insertCreditLimit(String cred_limit) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.Credit, cred_limit);

        // 0 updata  1 add
        if ("0".equals(Tag)){
            db.update(DatabaseHelper.accounts,contentValues,"_ID=?",new String []{uuId});
            Toast.makeText(Accounts.this,"All Set!",Toast.LENGTH_LONG).show();
        }else {
            contentValues.put("_ID",uuId);
            long id = db.insert(DatabaseHelper.accounts, null, contentValues);

            if (id!=-1){
                Toast.makeText(Accounts.this,"All Set!",Toast.LENGTH_LONG).show();
                Tag="0";
            }
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}