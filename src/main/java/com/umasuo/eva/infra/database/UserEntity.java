package com.umasuo.eva.infra.database;

/**
 * Created by umasuo on 17/7/9.
 */
public final class UserEntity {

    public static final String TABLE_NAME = "user";

    public static final String USER_ID = "user_id";
    public static final String TOKEN = "token";
    public static final String DEVELOPER_ID = "developer_id";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String EXTERNAL_ID = "external_id";
    public static final String NAME = "name";
    public static final String ICON = "icon";
    public static final String AGE = "age";
    public static final String SIGNATURE = "signature";

    // table creator sql
    public static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "_id" + " INTEGER PRIMARY KEY," +
                    USER_ID + " TEXT," +
                    TOKEN + " TEXT," +
                    DEVELOPER_ID + " TEXT," +
                    EMAIL + " TEXT," +
                    PHONE + " TEXT," +
                    EXTERNAL_ID + " TEXT," +
                    NAME + " TEXT," +
                    ICON + " TEXT," +
                    AGE + " TEXT," +
                    SIGNATURE + " TEXT )";
    public static final String DELETE_TABLE_SQL =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String[] projection = {
            USER_ID,
            TOKEN,
            DEVELOPER_ID,
            EMAIL,
            PHONE,
            EXTERNAL_ID,
            NAME,
            ICON,
            AGE,
            SIGNATURE
    };

}
