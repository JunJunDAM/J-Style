package com.example.alu2015059.jstyle.Repository;

/**
 * Created by alu2015059 on 24/04/2018.
 */

public class LoginDB {

    public static class USERS {
        public static final String TABLE_NAME = "USERS";
        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
    }

    public static final String USERS_CREATE_TABLE =
            "CREATE TABLE " + LoginDB.USERS.TABLE_NAME + " (" +
                    USERS.USERNAME + " TEXT, " +
                    USERS.PASSWORD + " TEXT);";

    public static final String USERS_DROPTABLE = "DROP TABLE IF EXISTS " + LoginDB.USERS.TABLE_NAME;
}
