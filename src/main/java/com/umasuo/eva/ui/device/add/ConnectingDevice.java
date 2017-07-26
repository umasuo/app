package com.umasuo.eva.ui.device.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.domain.device.dto.DeviceInitModel;
import com.umasuo.eva.domain.device.dto.ProductTypeModel;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 添加设备第四步：给设备发送网络包.
 */
public class ConnectingDevice extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "ConnectingDevice";

    private MainActivity mainActivity;

    private ImageView backImg;
    private TextView cancelText;
    private ProgressBar progressBar;

    private ProductTypeModel productType;
    private String ssid;
    private String password;

    private boolean isReceived = false;
    private boolean isFinished = false;
    //配网进度
    private int percent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.device_add_connecting, container, false);
        mainActivity = (MainActivity) getContext();

        Bundle bundle = getArguments();
        productType = (ProductTypeModel) bundle.get("productType");
        ssid = (String) bundle.get("ssid");
        password = (String) bundle.get("password");
        LogControl.debug(TAG, "onCreateView name = " + productType.name);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        progressBar.setProgress(40);

        backImg = (ImageView) view.findViewById(R.id.back);
        backImg.setOnClickListener(this);

        cancelText = (TextView) view.findViewById(R.id.cancel);
        cancelText.setOnClickListener(this);

        startConnecting();
        return view;
    }

    @Override
    public void onClick(View view) {
        // TODO: 17/7/18 跳转到开始连接发包的界面
        switch (view.getId()) {
            case R.id.back: {
                mainActivity.popBackStack();
                break;
            }
            case R.id.cancel: {
                mainActivity.popAll();
            }
        }
    }

    /**
     * 向固定IP地址发包
     */
    public void startConnecting() {
        sendDate();
        receiveData();
    }

    private void sendDate() {
        // TODO: 17/7/26 根据配网类型，进行发包
        new Thread() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                try {

                    InetAddress serverAddress = InetAddress.getByName("255.255.255.255");// 广播
                    socket = new DatagramSocket();

                    while (!isReceived) {
                        DeviceInitModel deviceInit = new DeviceInitModel(UserService.getInstance(getContext()).getUser().getUserId());
                        byte[] buf = deviceInit.toString().getBytes();
                        // TODO: 17/7/26 端口
                        DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddress, 8888);
                        socket.send(packet);
                        Thread.currentThread().sleep(1000);//每秒发一次
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (socket != null) {
                        socket.close();
                    }
                }

            }
        }.start();
    }

    /**
     * 用于接受局域网内的数据，
     * 1、如果是AP模式，那么设备在接收到数据之后，返回一条信息给APP，App自动切换网络到设备需要连接的网络
     * 2、如果是SmartConfig的模式，则不用切换网络。
     */
    private void receiveData() {
        new Thread() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                try {
                    byte[] recbuf = new byte[255];
                    DatagramPacket recpacket = new DatagramPacket(recbuf, recbuf.length);
                    socket = new DatagramSocket(9999);

                    while (!isReceived) {
                        socket.receive(recpacket);
                        if (recpacket.getLength() > 0) {
                            isReceived = true;//停止发UDP包
                            //切换网络
                            mainActivity.runOnUiThread(
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(mainActivity, "找到设备，请切换网络", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                            );
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (socket != null) {
                        socket.close();
                    }
                }
            }

        }.start();
    }
}