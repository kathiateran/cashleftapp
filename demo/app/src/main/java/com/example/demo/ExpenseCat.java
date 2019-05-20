package com.example.demo;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.utils.BasisTimesUtils;

public class ExpenseCat extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    private EditText exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, exp9, exp10;
    private EditText amount1, amount2, amount3, amount4, amount5, amount6, amount7, amount8, amount9, amount10;
    private TextView amount11;
    Button btn_ExpType;

    private final String CHANNEL_ID = "Budget Notifications";
    private final int NOTIFICATION_ID = 001;
    Button btn_displayNotification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        exp1  = findViewById(R.id.txtExpCat1);
        exp2  = findViewById(R.id.txtExpCat2);
        exp3  = findViewById(R.id.txtExpCat3);
        exp4  = findViewById(R.id.txtExpCat4);
        exp5  = findViewById(R.id.txtExpCat5);
        exp6  = findViewById(R.id.txtExpCat6);
        exp7  = findViewById(R.id.txtExpCat7);
        exp8  = findViewById(R.id.txtExpCat8);
        exp9  = findViewById(R.id.txtExpCat9);
        exp10  = findViewById(R.id.txtExpCat10);

        //
        exp1.setFocusableInTouchMode(false);
        exp2.setFocusableInTouchMode(false);
        exp3.setFocusableInTouchMode(false);
        exp4.setFocusableInTouchMode(false);
        exp5.setFocusableInTouchMode(false);
        exp6.setFocusableInTouchMode(false);
        exp7.setFocusableInTouchMode(false);
        exp8.setFocusableInTouchMode(false);
        exp9.setFocusableInTouchMode(false);
        exp10.setFocusableInTouchMode(false);
        //

        amount1 = findViewById(R.id.numExp1);
        amount2 = findViewById(R.id.numExp2);
        amount3 = findViewById(R.id.numExp3);
        amount4 = findViewById(R.id.numExp4);
        amount5 = findViewById(R.id.numExp5);
        amount6 = findViewById(R.id.numExp6);
        amount7 = findViewById(R.id.numExp7);
        amount8 = findViewById(R.id.numExp8);
        amount9 = findViewById(R.id.numExp9);
        amount10 = findViewById(R.id.numExp10);
        amount11= findViewById(R.id.numExp11);
        btn_ExpType = findViewById(R.id.btnAddExpType);


        amount11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasisTimesUtils.showDatePickerDialog(ExpenseCat.this, false, "Selection date", 2019, 5, 8, new BasisTimesUtils.OnDatePickerListener() {

                    @Override
                    public void onConfirm(int year, int month, int dayOfMonth) {


                        amount11.setText(""+year + "-" + month + "-" + dayOfMonth);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });


        btn_ExpType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String _exp1 = exp1.getText().toString();
                String _exp2 = exp2.getText().toString();
                String _exp3 = exp3.getText().toString();
                String _exp4 = exp4.getText().toString();
                String _exp5 = exp5.getText().toString();
                String _exp6 = exp6.getText().toString();
                String _exp7 = exp7.getText().toString();
                String _exp8 = exp8.getText().toString();
                String _exp9 = exp9.getText().toString();
                String _exp10 = exp10.getText().toString();

                String _amount1 = amount1.getText().toString();
                String _amount2 = amount2.getText().toString();
                String _amount3 = amount3.getText().toString();
                String _amount4 = amount4.getText().toString();
                String _amount5 = amount5.getText().toString();
                String _amount6 = amount6.getText().toString();
                String _amount7 = amount7.getText().toString();
                String _amount8 = amount8.getText().toString();
                String _amount9 = amount9.getText().toString();
                String _amount10 = amount10.getText().toString();

                //之前的方法不用了
//                insertData5(_exp1, _exp2, _exp3, _exp4, _exp5, _exp6, _exp7, _exp8, _exp9, _exp10, _amount1,
//                        _amount2,_amount3, _amount4, _amount5, _amount6, _amount7, _amount8, _amount9, _amount10);
               // Toast.makeText(getApplicationContext(), "Your category expenses have been added!", Toast.LENGTH_LONG).show();
                //现在的方法
                insertData();

            }
        });

        btn_displayNotification = findViewById(R.id.displayNotification);

        btn_displayNotification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             triggerNotification();
            }
        });

    }

    public void insertData(){

        String _amount1 = amount1.getText().toString();
        String _amount2 = amount2.getText().toString();
        String _amount3 = amount3.getText().toString();
        String _amount4 = amount4.getText().toString();
        String _amount5 = amount5.getText().toString();
        String _amount6 = amount6.getText().toString();
        String _amount7 = amount7.getText().toString();
        String _amount8 = amount8.getText().toString();
        String _amount9 = amount9.getText().toString();
        String _amount10 = amount10.getText().toString();



        if (_amount1.length()==0||_amount2.length()==0||_amount3.length()==0||_amount4.length()==0||_amount5.length()==0
                ||_amount6.length()==0||_amount7.length()==0||_amount8.length()==0||_amount9.length()==0||_amount10.length()==0){

            Toast.makeText(ExpenseCat.this,"No item can be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if (amount11.getText().toString().length()==0){
            Toast.makeText(ExpenseCat.this,"Date cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COL_25, _amount1);
        contentValues.put(DatabaseHelper.COL_26, _amount2);
        contentValues.put(DatabaseHelper.COL_27, _amount3);
        contentValues.put(DatabaseHelper.COL_28, _amount4);
        contentValues.put(DatabaseHelper.COL_29, _amount5);
        contentValues.put(DatabaseHelper.COL_30, _amount6);
        contentValues.put(DatabaseHelper.COL_31, _amount7);
        contentValues.put(DatabaseHelper.COL_32, _amount8);
        contentValues.put(DatabaseHelper.COL_33, _amount9);
        contentValues.put(DatabaseHelper.COL_34, _amount10);
        contentValues.put("date", amount11.getText().toString());

        long id = db.insert(DatabaseHelper.category_new, null, contentValues);

        if (id!=-1){
            Toast.makeText(ExpenseCat.this,"Your category expenses have been added \nNow update your Budget! ",Toast.LENGTH_SHORT).show();
            //ExpenseCat.this.finish();
        }else {
            Toast.makeText(ExpenseCat.this,"fail ",Toast.LENGTH_SHORT).show();
        }

    }

    //database
    public void insertData5(String _exp1, String _exp2, String _exp3, String _exp4, String _exp5, String _exp6, String _exp7,
                            String _exp8, String _exp9, String _exp10, String _amount1, String _amount2, String _amount3,  String _amount4,  String _amount5,
                            String _amount6, String _amount7,  String _amount8,  String _amount9, String _amount10) {

        ContentValues contentValues = new ContentValues();

//        contentValues.put(DatabaseHelper.COL_15, _exp1);
//        contentValues.put(DatabaseHelper.COL_16, _exp2);
//        contentValues.put(DatabaseHelper.COL_17, _exp3);
//        contentValues.put(DatabaseHelper.COL_18, _exp4);
//        contentValues.put(DatabaseHelper.COL_19, _exp5);
//        contentValues.put(DatabaseHelper.COL_20, _exp6);
//        contentValues.put(DatabaseHelper.COL_21, _exp7);
//        contentValues.put(DatabaseHelper.COL_22, _exp8);
//        contentValues.put(DatabaseHelper.COL_23, _exp9);
//        contentValues.put(DatabaseHelper.COL_24, _exp10);

        contentValues.put(DatabaseHelper.COL_25, _amount1);
        contentValues.put(DatabaseHelper.COL_26, _amount2);
        contentValues.put(DatabaseHelper.COL_27, _amount3);
        contentValues.put(DatabaseHelper.COL_28, _amount4);
        contentValues.put(DatabaseHelper.COL_29, _amount5);
        contentValues.put(DatabaseHelper.COL_30, _amount6);
        contentValues.put(DatabaseHelper.COL_31, _amount7);
        contentValues.put(DatabaseHelper.COL_32, _amount8);
        contentValues.put(DatabaseHelper.COL_33, _amount9);
        contentValues.put(DatabaseHelper.COL_34, _amount10);


        long id = db.insert(DatabaseHelper.category_new, null, contentValues);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //NOTIFICATIONS

    public void triggerNotification(){

        String _amount1 = amount1.getText().toString();
        String _amount2 = amount2.getText().toString();
        String _amount3 = amount3.getText().toString();
        String _amount4 = amount4.getText().toString();
        String _amount5 = amount5.getText().toString();
        String _amount6 = amount6.getText().toString();
        String _amount7 = amount7.getText().toString();
        String _amount8 = amount8.getText().toString();
        String _amount9 = amount9.getText().toString();
        String _amount10 = amount10.getText().toString();

        double catSum = categoriesSum( _amount1, _amount2, _amount3, _amount4, _amount5, _amount6, _amount7,_amount8, _amount9, _amount10);


        createNotificationChannel();
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("YOUR ADDED EXPENDITURE IS: $" + catSum);
        builder.setContentText("Make sure you have cash left!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());


    }



    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence notificationName = "Simple Notification 2";
            String description ="hello";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, notificationName, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    //ALL CATEGORIES SUM method
    public static double categoriesSum(String _amount1, String _amount2, String _amount3, String _amount4, String _amount5,
                                       String _amount6, String _amount7, String _amount8, String _amount9, String _amount10){

        double amount1= Double.parseDouble(_amount1);
        double amount2= Double.parseDouble(_amount2);
        double amount3= Double.parseDouble(_amount3);
        double amount4= Double.parseDouble(_amount4);
        double amount5= Double.parseDouble(_amount5);
        double amount6= Double.parseDouble(_amount6);
        double amount7= Double.parseDouble(_amount7);
        double amount8= Double.parseDouble(_amount8);
        double amount9= Double.parseDouble(_amount9);
        double amount10= Double.parseDouble(_amount10);

        return amount1+amount2+amount3+amount4+amount5+amount6+amount7+amount8+amount9+amount10;

    }




}