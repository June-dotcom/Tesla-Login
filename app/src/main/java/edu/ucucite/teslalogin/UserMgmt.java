package edu.ucucite.teslalogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import edu.ucucite.teslalogin.UserContract.UserEntry;

public class UserMgmt {

    public static boolean is_username_registered(String username, Context context){
        if (fetch_info(username, context).moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

    public static void insert_user(UserObj user, Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_USERNAME, user.getUsername());
        values.put(UserEntry.COLUMN_PASSWORD, user.getPassword());
        values.put(UserEntry.COLUMN_FULLNAME, user.getUsername());
        values.put(UserEntry.COLUMN_ADDRESS, user.getAddress());
        values.put(UserEntry.COLUMN_GENDER, user.getGender());
        values.put(UserEntry.COLUMN_STATUS, user.getStatus());
        long newRowId = db.insert(UserEntry.TABLE_NAME, null, values);
    }


    public static Cursor fetch_info(String username, Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = UserEntry.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(
                UserEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null// The sort order
        );
        return cursor;
    }


}
