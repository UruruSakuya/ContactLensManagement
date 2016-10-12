package jp.co.ur.nao.contactlensmanagement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by nao-ur on 2016/09/18.
 */
public class AddingLimitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_limit);
    }

    public  void addLimit(View view) {
        String limitDate = ((EditText) findViewById(R.id.limit_days)).getText().toString();

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LimitMasterEntry.LIMIT_DATE.name(), limitDate);
        long newRowId = db.insert(LimitMasterEntry.LIMIT_MASTER.name(), null, values);

        finish();
    }
}
