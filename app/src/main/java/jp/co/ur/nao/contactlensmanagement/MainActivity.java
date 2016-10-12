package jp.co.ur.nao.contactlensmanagement;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        TextView textView = (TextView) findViewById(R.id.value_right_remain);

        String[] cols = {HistoryEntry.END_DATE.name()};
        Cursor cursor = db.rawQuery("SELECT * FROM " + HistoryEntry.CONTACT_HISTORY.name() + " H INNER JOIN " + ContactMasterContract.ContactEntry.TABLE_NAME + " C ON H.LENS_ID = C.LENS_ID WHERE C.LR = ?", new String[]{"R"});
        while (cursor.moveToNext()) {
            textView.setText(cursor.getString(0));
        }



        AdView adview = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);

    }

    public boolean beginRightEye() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(HistoryEntry.BEGIN_DATE.name(),  DateFormat.format("YYYY/MM/DD", Calendar.getInstance()).toString());

        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_adding_contact_master:
                startActivity(new Intent(this, AddingContactActivity.class));
                return true;

            case R.id.action_adding_limit_master:
                startActivity(new Intent(this, AddingLimitActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
