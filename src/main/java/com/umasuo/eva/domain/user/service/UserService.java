package com.umasuo.eva.domain.user.service;

import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.infra.server.user.UserServerApi;

/**
 * Created by umasuo on 17/7/8.
 * 用户相关的逻辑服务
 */
public class UserService {

    /**
     * 服务器端的API.
     */
    private UserServerApi userServerApi;

    /**
     * 当前登录的用户的信息
     */
    private UserModel user;

    /**
     * 用户登录后的token.
     */
    private String token;

    /**
     * 初始化的时候读取数据库
     */
    public UserService() {
        // TODO: 17/7/8 读取数据库
        // 如果用户是登录状态，那么发起一个网络请求更新用户信息.
    }

    /**
     * 获取用户信息.
     *
     * @return
     */
    public UserModel getUser() {
        //todo 如果用户没有登录
        return user;
    }
}
