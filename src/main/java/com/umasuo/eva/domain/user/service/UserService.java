package com.umasuo.eva.domain.user.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.domain.user.dto.mapper.UserMapper;
import com.umasuo.eva.infra.database.DatabaseHelper;
import com.umasuo.eva.infra.database.FeedReaderContract;
import com.umasuo.eva.infra.database.FeedReaderDbHelper;
import com.umasuo.eva.infra.database.UserEntity;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.infra.server.user.UserServerApi;

/**
 * Created by umasuo on 17/7/8.
 * 用户相关的逻辑服务
 */
public class UserService {

    private static final String TAG = "UserService";

    /**
     * 服务器端的API.
     */
    private UserServerApi userServerApi;

    /**
     * 当前登录的用户的信息.
     */
    private static UserModel user;

    /**
     * 用户登录后的token.
     */
    private String token;

    private DatabaseHelper dbHelper;

    private SQLiteDatabase db;

    /**
     * 初始化的时候读取数据库
     */
    public UserService(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getReadableDatabase();
        db.execSQL(UserEntity.DELETE_TABLE_SQL);
        db.execSQL(UserEntity.CREATE_TABLE_SQL);
    }

    /**
     * 保存用户信息到数据库.
     */
    public void saveUser(UserModel user) {

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserEntity.TOKEN, "SDJXCVBNMTYUI");
        values.put(UserEntity.USER_ID, "user1");
        values.put(UserEntity.NAME, "name");
        values.put(UserEntity.SIGNATURE, "signature");
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserEntity.TABLE_NAME, null, values);
    }

    /**
     * 获取用户信息.
     *
     * @return
     */
    public UserModel getUser() {

        Cursor cursor = db.query(UserEntity.TABLE_NAME, UserEntity.projection, null, null, null, null, null);
        cursor.moveToFirst();
        user = UserMapper.toModel(cursor);

        LogControl.debug(TAG, user.toString());

        return user;
    }

    /**
     * 删除用户信息表.
     */
    public void deleteTable() {
        db.execSQL(UserEntity.DELETE_TABLE_SQL);
    }

}
