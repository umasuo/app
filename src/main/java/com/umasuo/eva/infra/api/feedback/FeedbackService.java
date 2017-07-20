package com.umasuo.eva.infra.api.feedback;

import com.umasuo.eva.infra.api.ServiceCaller;
import com.umasuo.eva.domain.feedback.dto.FeedbackModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by umasuo on 17/7/8.
 * 用户反馈的所有请求.
 */
public class FeedbackService extends ServiceCaller {

    /**
     * 与云端API对应的接口
     */
    private Service service;

    public FeedbackService() {
        service = retrofit.create(Service.class);
    }

    /**
     * 获取该用户的所有反馈
     * <b>这个接口只能够是使用平台官方app的时候才能够调用，其他的不能够调用</b>
     *
     * @param userId 用户的ID
     */
    public void getAllFeedback(String userId) {

    }

    /**
     * 添加一条反馈.
     *
     * @param userId   用户ID
     * @param deviceId 设备ID
     */
    public void addNewFeedback(String userId, String deviceId) {

    }

    /**
     * 用于构建http 请求的base.
     */
    private interface Service {

        @GET("/v1/feedbacks")
        Call<FeedbackModel> getAllFeedback(@Header("userId") String userId);

        @POST("/v1/feedbacks")
        Call<Void> addNewFeedback(@Header("userId") String userId);
    }
}
