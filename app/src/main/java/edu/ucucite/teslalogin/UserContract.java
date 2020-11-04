package edu.ucucite.teslalogin;

import android.provider.BaseColumns;

public class UserContract {
    private UserContract(){

    }

    public static final class UserEntry implements  BaseColumns{
        public static final String TABLE_NAME = "Users_tbl";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_FULLNAME = "full_name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_STATUS = "status";
    }

}
