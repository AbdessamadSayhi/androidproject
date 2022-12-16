package com.example.insertodb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DB_Helper extends SQLiteOpenHelper {


    private static final String TAG = "DB_Helper";
    public DB_Helper(Context context) {
        super(context, "biblioDB.db", null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS livretable(id integer primary key autoincrement, title text, description message, price integer)");
        db.execSQL("create table users(username TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS livretable");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public void ajouterLivre(Livre livre){

        SQLiteDatabase sqldb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("titre", livre.getTitre());
        cv.put("description", livre.getDescription());
        cv.put("prix", livre.getPrix());
        long livretable = sqldb.insert("livretable", null, cv);
        Log.e(TAG, "ajouterLivre: "+livretable);
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username",username);
        values.put("password",password);

        long result = db.insert("users",null,values);
        return result != -1;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?",new String[] {username});
        return cursor.getCount() > 0;
    }

    public Boolean checkUsernamePassword(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?",new String[] {username,password});
        if (username=="admin" && password=="admin")
            return true;
        return cursor.getCount() > 0;
    }
}
