package com.umasuo.eva.domain.user.dto.mapper;

import android.database.Cursor;

import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.infra.database.UserEntity;

/**
 * Created by umasuo on 17/7/10.
 */

public final class UserMapper {

    /**
     * 将数据库里面的数据转换成model数据
     *
     * @return UserModel
     */
    public static UserModel toModel(Cursor cursor) {
        UserModel user = new UserModel();
        if (cursor.getCount() > 0) {
            user.setUserId(cursor.getString(cursor.getColumnIndexOrThrow(UserEntity.USER_ID)));
            user.setToken(cursor.getString(cursor.getColumnIndexOrThrow(UserEntity.TOKEN)));
            user.setDeveloperId(cursor.getString(cursor.getColumnIndexOrThrow(UserEntity.DEVELOPER_ID)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(UserEntity.EMAIL)));
            user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(UserEntity.PHONE)));
            user.setExternalId(cursor.getString(cursor.getColumnIndexOrThrow(UserEntity.EXTERNAL_ID)));
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow(UserEntity.NAME)));
            user.setIcon(cursor.getString(cursor.getColumnIndexOrThrow(UserEntity.ICON)));
            user.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(UserEntity.AGE)));
            user.setSignature(cursor.getString(cursor.getColumnIndexOrThrow(UserEntity.SIGNATURE)));
        }
        return user;
    }
}
