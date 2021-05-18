package com.example.mad.animation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MAD on 1/29/2018.
 */

public class UserRegister extends SQLiteOpenHelper
{
    Context ctx;
    public static final int version=1;

    private static final String DATABASE_NAME = "new_user.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "user_details";
    public static final String COLUMN_FULLNAME =  "name";
    public static final String COLUMN_EMAIL =  "email";
    public static final String COLUMN_PASSWORD =  "password";
    public static final String COLUMN_MOBILE =  "mobile";



    public UserRegister(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String qry="create table "+TABLE_NAME+"("+COLUMN_FULLNAME+" TEXT,"+COLUMN_MOBILE+" VARCHAR,"+COLUMN_EMAIL+" TEXT,"+COLUMN_PASSWORD+" TEXT)";

        sqLiteDatabase.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
    public Boolean emailpassword(String email,String password)

    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c= db.rawQuery("select * from user_details where email=? and password=?",new String[]{email,password});
        if(c.getCount()>0)
        {
            return true;

        }

        else
        {
            return false;
        }

    }
}
