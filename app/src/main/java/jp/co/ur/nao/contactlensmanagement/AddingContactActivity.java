package jp.co.ur.nao.contactlensmanagement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddingContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_contact_master);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        List spinnerList = new ArrayList<String>();
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String[] cols = {LimitMasterEntry.LIMIT_DATE.name()};
        try {
            Cursor cursor = db.query(LimitMasterEntry.LIMIT_MASTER.name(), cols, null, null, null, null, null);

            while (cursor.moveToNext()) {
                spinnerList.add(cursor.getString(0));
            }


        } finally {
            db.close();

        }
        Adapter adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerList);

        spinner.setAdapter((SpinnerAdapter) adapter);


    }

    public void addContactLens(View view) {
        EditText contactLensNameView = (EditText) findViewById(R.id.contact_name);

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContactMasterContract.ContactEntry.COLUMN_ID, 1);
        values.put(ContactMasterContract.ContactEntry.COLUMN_LENS_NAME, contactLensNameView.getText().toString());

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        values.put(ContactMasterContract.ContactEntry.COLUMN_LIMIT_ID, spinner.getSelectedItem().toString());

        EditText contactFrequencyView = (EditText) findViewById(R.id.contact_frequency);
        values.put(ContactMasterContract.ContactEntry.COLUMN_FREQUENCY, contactFrequencyView.getText().toString());

        long newRowId = db.insert(ContactMasterContract.ContactEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            displayToast(R.string.info_success_adding_master);

        } else {
            displayToast(R.string.info_failure_adding_master);

        }

        finish();
    }

    private void displayToast(int messageCode) {
        Toast toast = Toast.makeText(this, messageCode, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.show();
    }
}
