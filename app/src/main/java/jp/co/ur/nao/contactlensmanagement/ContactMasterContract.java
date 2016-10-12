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
        public static final String COLUMN_LIMIT_ID = "LIMIT_ID";
        public static final String COLUMN_FREQUENCY = "FREQUENCY";
        public static final String COLUMN_LR = "LR";
        public static final String COLUMN_ADDING_DATE = "ADDING_DATE";
        public static final String COLUMN_STATUS = "STATUS";
    }
}
