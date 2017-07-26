package com.umasuo.eva.ui.device.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.domain.device.dto.ProductTypeModel;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

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
}