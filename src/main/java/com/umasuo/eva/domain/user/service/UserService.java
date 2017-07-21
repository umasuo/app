package com.umasuo.eva.domain.user.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.domain.user.dto.mapper.UserMapper;
import com.umasuo.eva.infra.api.user.UserCloudApi;
import com.umasuo.eva.infra.database.DatabaseHelper;
import com.umasuo.eva.infra.database.UserEntity;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created by umasuo on 17/7/8.
 * 用户相关的逻辑服务
 */
public class UserService {

    private static final String TAG = "UserService";

    /**
     * 开发者注册后，发行设备时指定具体的developerID.
     */
    private static final String developerId = "developer1";

    private static UserService instance;

    /**
     * 服务器端的API.
     */
    private UserCloudApi userServerApi;

    /**
     * 当前登录的用户的信息.
     */
    private static UserModel user;

    /**
     * 用户登录后的token.
     */
    private String token;

    //todo  这个是否需要共享一个呢？
    private DatabaseHelper dbHelper;

    //todo 这个是否需要共享一个呢？
    private SQLiteDatabase db;

    private Context context;

    public static UserService getInstance(Context context) {
        if (instance == null) {
            instance = new UserService(context);
        } else if (!context.equals(instance.getContext())) {
            //切换了context，需要重新加载, // TODO: 17/7/10 待优化
            instance = new UserService(context);
        }
        return instance;
    }

    /**
     * 初始化的时候读取数据库
     */
    public UserService(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getReadableDatabase();
        // 如果数据表不存在，则创建表
        db.execSQL(UserEntity.CREATE_TABLE_SQL);
        userServerApi = new UserCloudApi();
        this.context = context;
    }

    /**
     * 保存用户信息到数据库.
     */
    public void saveUser(UserModel user) {

        // Create a new map of values, where column names are the keys
        ContentValues values = UserMapper.toEntity(user);
        String whereClause = UserEntity.USER_ID + " = ?";
        String[] whereArgs = {user.getUserId()};
        //todo 待优化
        db.execSQL(UserEntity.DELETE_TABLE_SQL);
        db.execSQL(UserEntity.CREATE_TABLE_SQL);
        // Insert the new row, returning the primary key value of the new row
        db.update(UserEntity.TABLE_NAME, values, whereClause, whereArgs);
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
        token = "bearer " + user.getToken();

        LogControl.debug(TAG, user.toString());
        return user;
    }

    /**
     * 获取sms code.
     */
    public void getSmsCode(String phone) {
        userServerApi.getSmsCode(phone);
    }

    /**
     * 注册并自动登录.
     *
     * @param phone
     * @param password
     * @param smsCode
     */
    public void register(String phone, String password, String smsCode) {
        userServerApi.register(phone, developerId, password, smsCode, new RegisterCallback(context, this));
    }

    /**
     * login with password and phone.
     *
     * @param phone
     * @param password
     */
    public void login(String phone, String password) {
        userServerApi.login(phone, developerId, password, new SignInCallback(context, this));
    }

    public void resetPassword(String phone, String password, String smsCode) {

    }

    /**
     * 通过短信验证码的方式登录.
     *
     * @param phone
     * @param developerId
     * @param code
     */
    public void signinWithSmsCode(String phone, String developerId, String code) {
        userServerApi.signIn(phone, code, developerId, new SignInCallback(context, this));
    }

    /**
     * 更新用户信息.
     */
    public void updateUser(String email, String phone, int age, String country, String name, String signature) {
        UserModel newModel = new UserModel();

        newModel.setUserId(user.getUserId());
        newModel.setSignature(signature);
        newModel.setName(name);
        newModel.setPhone(phone);
        newModel.setAge(age);
        newModel.setEmail(email);
        newModel.setIcon(user.getIcon());
        newModel.setCountry(country);

        userServerApi.updateUserInfo(newModel, token, developerId, new UserModelCallback(context, this));

    }

    /**
     * 退出登录.
     */
    public void signOut() {
        userServerApi.signOut(user.getUserId(), token, developerId, new SignOutCallback(context, this));
    }

    /**
     * 删除用户信息表.
     */
    public void deleteTable() {
        db.execSQL(UserEntity.DELETE_TABLE_SQL);
    }

    public Context getContext() {
        return context;
    }

    public String getToken() {
        return token;
    }
}
