package com.umasuo.eva.infra.server.device;

import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.infra.server.ServiceCaller;
import com.umasuo.eva.domain.device.dto.DeviceModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by umasuo on 17/7/8.
 */

public class DeviceCloudApi extends ServiceCaller {

    /**
     * 与云端API对应的接口
     */
    private Service service;

    public DeviceCloudApi() {
        service = retrofit.create(Service.class);
    }

    /**
     * 获取该用户的所有设备.这个设备的信息可能稍微大一点
     * <b>这个接口只能够是使用平台官方app的时候才能够调用，其他的不能够调用</b>
     *
     * @param userId 用户的ID
     */
    public void getAllDevice(String userId) {

    }

    /**
     * 解绑一个设备.
     *
     * @param userId   用户ID
     * @param deviceId 设备ID
     */
    public void unbind(String userId, String deviceId) {

    }

    /**
     * 获取设备的配网token.
     *
     * @param userId 用户ID
     */
    public void getDeviceToken(String userId) {
        Call<ResponseBody> caller = service.getDeviceToken(userId);

        //异步发起请求，所有的网络请求都需要异步发起请求
//        caller.enqueue(new Device);
    }

    /**
     * 用于构建http 请求的base.
     */
    private interface Service {

        @GET("/v1/devices")
        Call<DeviceModel> getAllDevice(@Header("userId") String userId);

        @DELETE("/v1/devices/{id}")
        Call<Void> unbind(@Header("userId") String userId, @Path("id") String id);

        @POST("/v1/devices/tokens")
        Call<ResponseBody> getDeviceToken(@Header("userId") String userId);
    }
}
