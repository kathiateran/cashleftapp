package com.example.demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.demo.Adapter.listAdapter;
import com.example.demo.Bean.listBean;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FrontActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SQLiteDatabase mydb;
    SQLiteOpenHelper openHelper;
    private ListView listview;
    private listAdapter listAdapter;
    private ArrayList<listBean> listBeanArrayList;
    private TextView Cash,Debit,Credit,bal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        openHelper=new DatabaseHelper(this);
        mydb =openHelper.getWritableDatabase();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
               this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       drawer.addDrawerListener(toggle);
       toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //add
        listview=findViewById(R.id.listview);
        listBeanArrayList=new ArrayList<>();
        setListView();

        Cash=(TextView) findViewById(R.id.Cash);
        Debit=(TextView) findViewById(R.id.Debit);
        Credit=(TextView) findViewById(R.id.Credit);
        bal = (TextView) findViewById(R.id.textView2);
        setCastData();
        //end
    }

    private void setCastData() {

        //0 updata  1 add
        Cursor cursor =  mydb.rawQuery("select * from accounts", null);

        if (cursor.moveToFirst() == false) {

        }else {

            String Cashs = cursor.getString(cursor.getColumnIndex("Cash"));
            String Debits = cursor.getString(cursor.getColumnIndex("Debit"));
            String Credits = cursor.getString(cursor.getColumnIndex("Credit"));

            int cash1 = Integer.parseInt(Cashs);
            int debit1 = Integer.parseInt(Debits);
            int credit1 = Integer.parseInt(Credits);

            int balance = cash1+debit1+credit1;


            bal.setText(""+balance);

            if (Cashs.length()!=0){
                Cash.setText("Cash \n"+Cashs);

            }else {
                Cash.setText("Cash:0");
            }

            if (Debits.length()!=0){
                Debit.setText("Debit \n"+Debits);
            }else {
                Debit.setText("Debit:0");
            }

            if (Credits.length()!=0){
                Credit.setText("Credit \n"+Credits);
            }else {
                Credit.setText("Credit:0");
            }
        }
    }

    private void setListView() {


        try {
            Cursor cursor = mydb.query(DatabaseHelper.category_new, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String transportation = cursor.getString(cursor.getColumnIndex("transportation"));
                    String Groceries = cursor.getString(cursor.getColumnIndex("Groceries"));
                    String Restaurants = cursor.getString(cursor.getColumnIndex("Restaurants"));
                    String Take_Out_Food = cursor.getString(cursor.getColumnIndex("Take_Out_Food"));
                    String Take_Out_Coffee = cursor.getString(cursor.getColumnIndex("Take_Out_Coffee"));
                    String Household = cursor.getString(cursor.getColumnIndex("Household"));
                    String Clothes = cursor.getString(cursor.getColumnIndex("Clothes"));
                    String Entertainment = cursor.getString(cursor.getColumnIndex("Entertainment"));
                    String Sports = cursor.getString(cursor.getColumnIndex("Sports"));
                    String Other = cursor.getString(cursor.getColumnIndex("Other"));
                    String date = cursor.getString(cursor.getColumnIndex("date"));
                    listBean listBean=new listBean();
                    listBean.setCEntertainment(Entertainment);
                    listBean.setClothes(Clothes);
                    listBean.setDate(date);
                    listBean.setGroceries(Groceries);
                    listBean.setHousehold(Household);
                    listBean.setOther(Other);
                    listBean.setSports(Sports);
                    listBean.setTake_Out_Coffee(Take_Out_Coffee);
                    listBean.setTake_Out_Food(Take_Out_Food);
                    listBean.setRestaurants(Restaurants);
                    listBean.setTransportation(transportation);
                    listBeanArrayList.add(listBean);

                } while (cursor.moveToNext());
            }

            listAdapter=new listAdapter(listBeanArrayList,FrontActivity.this);
            listview.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();

        }catch ( Exception e){

        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
            return true;
        }

        if (id == R.id.update) {
            Toast.makeText(getApplicationContext(),"Latest version updated",Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("CommitTransaction")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_report) {
            Intent account = new Intent(this,Report.class);
            startActivity(account);
        } else if (id == R.id.nav_accounts) {
            Intent account = new Intent(this,Accounts.class );
            startActivity(account);

        } else if (id == R.id.nav_categories) {
            Intent account = new Intent(this,ExpenseCat.class );
            startActivity(account);
        }
        else if (id == R.id.help){
            Intent account = new Intent(this,Help.class );
            startActivity(account);
        }
        else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();

        try {
            setCastData();
            listBeanArrayList.clear();
            setListView();
        }catch (Exception e){

        }
    }
}
