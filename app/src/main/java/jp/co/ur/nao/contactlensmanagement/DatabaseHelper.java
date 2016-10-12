package jp.co.ur.nao.contactlensmanagement;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by nao-ur on 2016/09/02.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "contact_lens_management.db";
    private static final int DB_VERSION = 1;

    private static final String SQL_CREATE_CONTACT_MASTER_ENTRIES = "CREATE TABLE "
            + ContactMasterContract.ContactEntry.TABLE_NAME + " ("
            + ContactMasterContract.ContactEntry._ID + " INTEGER PRIMARY KEY, "
            + ContactMasterContract.ContactEntry.COLUMN_LENS_NAME + " TEXT, "
            + ContactMasterContract.ContactEntry.COLUMN_LIMIT_ID + " TEXT, "
            + ContactMasterContract.ContactEntry.COLUMN_FREQUENCY + " REAL, "
            + ContactMasterContract.ContactEntry.COLUMN_LR + " TEXT, "
            + ContactMasterContract.ContactEntry.COLUMN_ADDING_DATE + " TEXT, "
            + ContactMasterContract.ContactEntry.COLUMN_STATUS + " TEXT, "
            + ContactMasterContract.ContactEntry.COLUMN_ID + " TEXT" + " )";

    private static final String SQL_CREATE_CONTACT_HISTORY_ENTRIES = "CREATE TABLE "
            + HistoryEntry.CONTACT_HISTORY + " ("
            + HistoryEntry._ID + " INTEGER PRIMARY KEY, "
            + HistoryEntry.LENS_ID + " INTEGER, "
            + HistoryEntry.BEGIN_DATE + " TEXT, "
            + HistoryEntry.PLAN_END_DATE + " TEXT, "
            + HistoryEntry.END_DATE + " TEXT )";

    private static final String SQL_CREATE_LIMIT_MASTER_ENTRIES = "CREATE TABLE "
            + LimitMasterEntry.LIMIT_MASTER + " ("
            + LimitMasterEntry._ID + " INTEGER PRIMARY KEY, "
            + LimitMasterEntry.LIMIT_DATE + " TEXT )";

    private static final String SQL_DROP_CONTACT_MASTER_ENTRIES = "DROP CONTACT_HISTORY IF EXISTS " + ContactMasterContract.ContactEntry.TABLE_NAME;
    private static final String SQL_DROP_CONTACT_HISTORY_ENTRIES = "DROP CONTACT_HISTORY IF EXISTS " + HistoryEntry.CONTACT_HISTORY;
    private static final String SQL_DROP_LIMIT_MASTER_ENTRIES = "DROP CONTACT_HISTORY IF EXISTS " + LimitMasterEntry.LIMIT_MASTER;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CONTACT_MASTER_ENTRIES);
        db.execSQL(SQL_CREATE_CONTACT_HISTORY_ENTRIES);
        db.execSQL(SQL_CREATE_LIMIT_MASTER_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_CONTACT_MASTER_ENTRIES);
        db.execSQL(SQL_DROP_CONTACT_HISTORY_ENTRIES);
        db.execSQL(SQL_DROP_LIMIT_MASTER_ENTRIES);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
