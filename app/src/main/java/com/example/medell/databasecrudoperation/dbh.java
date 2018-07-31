package com.example.medell.databasecrudoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by me dell on 15-11-2017.
 */

public class dbh extends SQLiteOpenHelper
{

    public static final String DbName="MyDb";
    public static final String TbName="Info";
    public static  final  String ColName="Name";
    public static  final  String ColNum="Number";
    public static  final  String ColPwd="Password";

    public dbh(Context context)
    {
        super(context, DbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String Sql="create table "+ TbName + " ( " + ColName+ " Text , "  + ColNum + " Text , " + ColPwd + " Text ) ";
        sqLiteDatabase.execSQL(Sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TbName);
        onCreate(sqLiteDatabase);
    }

    boolean insertDetails(details details)
    {
        // first of all getting a writeable database
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        // class to get values
        ContentValues contentValues=new ContentValues();
        // getting values to the table
        contentValues.put(ColName,details.getName());
        contentValues.put(ColNum,details.getNum());
        contentValues.put(ColPwd,details.getPwd());
        // inserting data to the table
        sqLiteDatabase.insert(TbName,null,contentValues);
        return true;
    }

    boolean updateDetails(details details,String name)
    {
        // first of all getting a writeable database
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        // class to get values
        ContentValues contentValues=new ContentValues();
        // getting values to the table
        contentValues.put(ColName,details.getName());
        contentValues.put(ColNum,details.getNum());
        contentValues.put(ColPwd,details.getPwd());
        // updating data to the table
        sqLiteDatabase.update(TbName,contentValues,ColName +" = ? ",new String[]{name});
        return true;
    }

    boolean deleteByName(String name)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            /*
            * sqLiteDatabase.delete(Info,Name = ? ,new String[]{name})
            * sqLiteDatabase.delete(TbName,ColName+" = ? ",new String[]{name})
            * 1st parameter : table name
            * 2nd parameter : where clause : clause to delete reference
            * 3rd parameter : arguments to be deleted
            */
        sqLiteDatabase.delete(TbName,ColName+" = ? ",new String[]{name});
        return  true;
    }

    /*
    * creating a data pointer like  fn to access the database
    */
    Cursor getData()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            /*
            * "select * from Info"
            * this query means to select all the data from the table
            */
        Cursor  cursor=sqLiteDatabase.rawQuery("select * from " +TbName,null);
        return  cursor;
    }

    Cursor getDatabyname(String name)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            /*
            * "select * from Info where Name = 'name_of_the_person'"
            * this query means to select all data from the table where column_name "Name" = 'name_of_the_person'
            * */
        Cursor  cursor=sqLiteDatabase.rawQuery("select * from "+TbName+" where "+ColName+" = '"+name+"'",null);
        return  cursor;
    }

}

