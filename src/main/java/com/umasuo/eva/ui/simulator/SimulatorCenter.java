package com.umasuo.eva.ui.simulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.adapter.PersonalAdapter;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.ui.personal.About;
import com.umasuo.eva.ui.personal.FAQ;
import com.umasuo.eva.ui.personal.Feedback;
import com.umasuo.eva.ui.personal.MessageCenter;
import com.umasuo.eva.ui.personal.PersonalInfo;
import com.umasuo.eva.ui.personal.PersonalSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心界面
 */

public class SimulatorCenter extends FragmentRoot {

    private static final String TAG = "PersonalCenter";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.simulator, container, false);
        //init data
        return view;
    }

    @Override
    public void onShow() {
    }
}