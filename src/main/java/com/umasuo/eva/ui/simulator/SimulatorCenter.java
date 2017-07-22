package com.umasuo.eva.ui.simulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.infra.mqtt.MessageListener;
import com.umasuo.eva.infra.mqtt.MqttClient;
import com.umasuo.eva.infra.mqtt.MqttMessage;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.QoS;

/**
 * 个人中心界面
 */

public class SimulatorCenter extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "PersonalCenter";

    ImageView testImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.simulator, container, false);

        testImage = (ImageView) view.findViewById(R.id.test_img);

        testImage.setOnClickListener(this);
        //init data
        return view;
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onClick(View view) {
        LogControl.debug(TAG, "Test MQTT");

        try {
            //for test
//            MqttClient.getInstance("umasuo", "password").startListen();
//            BlockingConnection con = MqttClient.getInstance("umasuo", "password").getConnect();
//            MessageListener listener = new MessageListener(con);
//            listener.run();
//            MqttMessage msg = new MqttMessage();
//            msg.setDeviceId("0b07f075-c4d2-4c7b-9a95-53284e36157a");
//            msg.setT(System.currentTimeMillis() / 1000);
//            msg.setType(1);
//            MqttMessage.Payload payload = new MqttMessage.Payload();
//            payload.setId("f001");
//            payload.setData("100");
//            msg.setPayload(payload);
//            con.publish("user:user1", msg.toString().getBytes(), QoS.AT_LEAST_ONCE, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}