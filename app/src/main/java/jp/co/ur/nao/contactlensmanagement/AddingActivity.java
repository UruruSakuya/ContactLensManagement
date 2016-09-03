package jp.co.ur.nao.contactlensmanagement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
    }

    public void addContactLens(View view) {
        EditText contactLensNameView = (EditText)findViewById(R.id.contact_name);

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContactMasterContract.ContactEntry.COLUMN_ID, 1);
        values.put(ContactMasterContract.ContactEntry.COLUMN_LENS_NAME, contactLensNameView.getText().toString());

        long newRowId = db.insert(ContactMasterContract.ContactEntry.TABLE_NAME, null, values);


        System.out.println(values.get(ContactMasterContract.ContactEntry.COLUMN_ID));
        System.out.println(values.get(ContactMasterContract.ContactEntry.COLUMN_LENS_NAME));
        System.out.println(newRowId);

    }
}
