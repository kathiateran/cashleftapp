package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{


    //Columns
    public static final String COL_6="New Email";
    public static final String COL_7="New Password";
    public static final String COL_8="Checking Account";
    public static final String COL_9="Savings Account";
    public static final String COL_10="Routing Bank";
    public static final String COL_11="Credit Card";
    public static final String COL_12="Credit card date";
    public static final String COL_13="Credit card cvv";
    public static final String COL_14="Cash Input";

    public static final String COL_15="ExpType1";
    public static final String COL_16="ExpType2";
    public static final String COL_17="ExpType3";
    public static final String COL_18="ExpType4";
    public static final String COL_19="ExpType5";
    public static final String COL_20="ExpType6";
    public static final String COL_21="ExpType7";
    public static final String COL_22="ExpType8";
    public static final String COL_23="ExpType9";
    public static final String COL_24="ExpType10";

    public static final String COL_25="transportation";
    public static final String COL_26="Groceries";
    public static final String COL_27="Restaurants";
    public static final String COL_28="Take_Out_Food";
    public static final String COL_29="Take_Out_Coffee";
    public static final String COL_30="Household";
    public static final String COL_31="Clothes";
    public static final String COL_32="Entertainment";
    public static final String COL_33="Sports";
    public static final String COL_34="Other";

    public static final String db_name = "Users";

    public static final String TABLE_NAME="register";

    //这是表名字 对应下面 对那个表操作 要传入对应的表明
    public static final String signin = "sign_in",signup="sign_up",cash = "cash",debit= "debit",credit="credit",category ="categories";
    public static final String signin_col1="emailid",signin_col2 = "password";
    public static final String signup_col2="first_name",signup_col3 = "last_name",signup_col1="username",signup_col4 = "email_id",signup_col5="password";
    public static final String cash_col1="emailid",cash_col2 = "balance",cash_col3="def_limit",cash_col4="date";
    public static final String debit_col1 ="emailid",debit_col2 = "card_no",debit_col3 = "holder_name",debit_col4="ccv",debit_col5 = "bank_name",debit_col6="def_limit";
    public static final String credit_col1 ="emailid",credit_col2 = "card_no",credit_col3="exp_date",credit_col4="ccv",credit_col5="def_limit";
    public static final String category_col1 = "emailid" , category_col2 = "category_name", category_col3 = "amount",category_col4="def_limit",category_col5="date";


    public static final String category_new = "category_new";
    public static final String accounts = "accounts";
    public static final String Cash = "Cash";
    public static final String Debit= "Debit";
    public static final String Credit= "Credit";

    public DatabaseHelper(Context context) {
        super(context, db_name, null, 1);

    }

    /**
     * 注册表信息 是 signup  获取用户 信息 查询/新增 /删除 修改 的表 名是  signup 不是 TABLE_NAME
     *
     * 表 category 和界面 category字段不符合 重新创建表 设置表明 为 category_new
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ signin+" (emailid text primary key,password text)");
        db.execSQL("create table "+ signup+" (_ID INTEGER PRIMARY KEY AUTOINCREMENT, first_name text,last_name text,email_id text,password text)");
        db.execSQL("create table "+ cash+" (emailid text primary key,balance int, def_limit int,date text)");
        db.execSQL("create table "+ debit+" (emailid text primary key,card_no int,holder_name text,ccv int,bank_name text,def_limit int)");
        db.execSQL("create table "+ credit+" (emailid text primary key,card_no int,exp_date text,ccv int,def_limit int)");
        db.execSQL("create table "+ category+" (emailid text primary key,category_name text,amount int,def_limit int,date text)");
        db.execSQL("create table "+ category_new+" (_ID INTEGER PRIMARY KEY AUTOINCREMENT,transportation float,Groceries float,Restaurants float,Take_Out_Food float," +
                "Take_Out_Coffee float,Household float,Clothes float,Entertainment float,Sports float ,Other float,date text)");
        db.execSQL("create table "+ accounts+" (_ID text PRIMARY KEY,Cash float,Debit float,Credit float)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("Drop table if exists "+signin);
       onCreate(db);
    }

    public boolean insertSignup(String emailid,String first_name,int last_name,String passoword)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(signup_col2,first_name);
        values.put(signup_col3,last_name);
        values.put(signup_col1,emailid);
        values.put(signup_col4,passoword);
        long result = db.insert(signup, null, values);
        db.close();
        if(result==-1)
            return false;
        else
            return true;

    }


    public boolean insertCash(String emailid,int balance,int limit,String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(cash_col1,emailid);
        values.put(cash_col2,balance);
        values.put(cash_col3,limit);
        values.put(cash_col4,date);
        long result = db.insert(cash, null, values);
        db.close();
        if(result==-1)
            return false;
        else
            return true;

    }

    public boolean insertDebit(String emailid,int cardno,String holder,int ccv,String bank,int limit)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(debit_col1,emailid);
        values.put(debit_col2,cardno);
        values.put(debit_col3,holder);
        values.put(debit_col4,ccv);
        values.put(debit_col5,bank);
        values.put(debit_col6,limit);
        long result = db.insert(debit, null, values);
        db.close();
        if(result==-1)
            return false;
        else
            return true;

    }

    public boolean insertCredit(String emailid,int cardno,String exp_date,int ccv,int limit)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(credit_col1,emailid);
        values.put(credit_col2,cardno);
        values.put(credit_col3,exp_date);
        values.put(credit_col4,ccv);
        values.put(credit_col5,limit);
        long result = db.insert(credit, null, values);
        db.close();
        if(result==-1)
            return false;
        else
            return true;

    }

    public boolean insertCategory(String emailid,String category,int amount,int limit,String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(category_col1,emailid);
        values.put(category_col2,category);
        values.put(category_col3,amount);
        values.put(category_col4,limit);
        values.put(category_col5,date);
        long result = db.insert(category, null, values);
        db.close();
        if(result==-1)
            return false;
        else
            return true;

    }

    public float getCash(String name,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="select amount from "+cash+" where user_name= "+name;
        float ans=0.0f;
        Cursor cr=db.rawQuery(query, null);
        if (cr.moveToFirst())
        {
            ans = cr.getFloat(2);
        }
      return ans;
    }

    public float getDebit(String name,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="select amount from "+debit+" where user_name= "+name;
        float ans=0.0f;
        Cursor cr=db.rawQuery(query, null);
        if (cr.moveToFirst())
        {
            ans = cr.getFloat(2);
        }
        return ans;
    }

    public float getCredit(String name, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="select amount from "+credit+" where user_name= "+name;
        float ans=0.0f;
        Cursor cr=db.rawQuery(query, null);
        if (cr.moveToFirst())
        {
            ans = cr.getFloat(2);
        }
        return ans;
    }
}
