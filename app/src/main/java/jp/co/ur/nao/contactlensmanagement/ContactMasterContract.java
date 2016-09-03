package jp.co.ur.nao.contactlensmanagement;

import android.provider.BaseColumns;

/**
 * Created by nao-ur on 2016/09/02.
 */
public final class ContactMasterContract {
    public ContactMasterContract() {
    }

    public static abstract class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "CONTACT_LENS_MASTER";
        public static final String COLUMN_ID = "LENS_ID";
        public static final String COLUMN_LENS_NAME = "LENS_NAME";
    }
}
