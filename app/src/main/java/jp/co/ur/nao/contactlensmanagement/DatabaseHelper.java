package jp.co.ur.nao.contactlensmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nao-ur on 2016/09/02.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "contact_lens_management.db";
    private static final int DB_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + ContactMasterContract.ContactEntry.TABLE_NAME + " ("
            + ContactMasterContract.ContactEntry._ID + " INTEGER PRIMARY KEY, "
            + ContactMasterContract.ContactEntry.COLUMN_ID + " TEXT, "
            + ContactMasterContract.ContactEntry.COLUMN_LENS_NAME + " TEXT" + " )";

    private static final String SQL_DROP_ENTRIES = "DROP TABLE IF EXISTS " + ContactMasterContract.ContactEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_ENTRIES);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
