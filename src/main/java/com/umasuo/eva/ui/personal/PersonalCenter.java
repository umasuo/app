package com.umasuo.eva.ui.personal;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心界面
 */

public class PersonalCenter extends FragmentRoot implements AdapterView.OnItemClickListener, View.OnClickListener {

    private static final String TAG = "PersonalCenter";

    private ListView personalList;
    private LinearLayout piSummary;

    private PersonalSettings personalSettings;
    private PersonalInfo personalInfo;
    private Feedback feedback;
    private FAQ faq;
    private About about;
    private MessageCenter messageCenter;

    private MainActivity activity;
    private UserService userService;

    // 登录的用户概要信息
    private ImageView headIcon;
    protected TextView userName;
    private TextView userSignature;
    private TextView userSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.personal, container, false);
        personalList = view.findViewById(R.id.personal_listview);
        PersonalAdapter pAdapter = new PersonalAdapter(getContext(), getData());
        personalList.setAdapter(pAdapter);
        personalList.setOnItemClickListener(this);

        piSummary = view.findViewById(R.id.personal_info_summary);
        piSummary.setOnClickListener(this);

        headIcon = (ImageView) view.findViewById(R.id.personal_head_portrait);
        userName = (TextView) view.findViewById(R.id.personal_info_summary_name);
        userSignature = (TextView) view.findViewById(R.id.personal_info_summary_signature);
        userSettings = (TextView) view.findViewById(R.id.personal_settings);
        userSettings.setOnClickListener(this);

        activity = (MainActivity) getContext();
        userService = UserService.getInstance(activity);
        //init data
        initUserData();
        return view;
    }

    @Override
    public void onShow() {
        if (activity != null) {
            activity.showBottom();
        }
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.home);
        map.put("name", "消息中心");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.getup);
        map.put("name", "常见问题");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.getup);
        map.put("name", "意见反馈");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.getup);
        map.put("name", "关于");
        list.add(map);

        return list;
    }

    /**
     * 初始化界面上显示的数据.
     */
    private void initUserData() {

        UserModel userModel = userService.getUser();
        // TODO: 17/7/10 设置头像
        if (userModel.getName() == null) {
            userName.setText(userModel.getPhone());//显示手机号
        } else {
            userName.setText(userModel.getName());//设置名字
        }

        if (userModel.getSignature() != null) {
            userSignature.setText(userModel.getSignature());
        }
    }

    /**
     * 点击用户信息是，显示用户信息界面.
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        LogControl.debug(TAG, "click");
        switch (view.getId()) {
            case R.id.personal_info_summary: {
                if (personalInfo == null) {
                    personalInfo = new PersonalInfo();
                    personalInfo.setPreIndex(index);
                    personalInfo.setIndex(activity.addFragment(personalInfo));
                }
                activity.showPage(personalInfo.getIndex());
                break;
            }
            case R.id.personal_settings: {
                if (personalSettings == null) {
                    personalSettings = new PersonalSettings();
                    personalSettings.setPreIndex(index);
                    personalSettings.setIndex(activity.addFragment(personalSettings));
                }
                activity.showPage(personalSettings.getIndex());
                break;
            }
        }
    }

    /**
     * 用户信息下面各个功能点的点击事件.
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i) {
            case 0: {
                if (messageCenter == null) {
                    messageCenter = new MessageCenter();
                    messageCenter.setPreIndex(index);
                    messageCenter.setIndex(activity.addFragment(messageCenter));
                }
                activity.showPage(messageCenter.getIndex());
                break;
            }
            case 1: {
                if (faq == null) {
                    faq = new FAQ();
                    faq.setPreIndex(index);
                    faq.setIndex(activity.addFragment(faq));
                }
                activity.showPage(faq.getIndex());
                break;
            }
            case 2: {
                if (feedback == null) {
                    feedback = new Feedback();
                    feedback.setPreIndex(index);
                    feedback.setIndex(activity.addFragment(feedback));
                }
                activity.showPage(feedback.getIndex());
                break;
            }
            case 3: {
                if (about == null) {
                    about = new About();
                    about.setPreIndex(index);
                    about.setIndex(activity.addFragment(about));
                }
                activity.showPage(about.getIndex());
                break;
            }
            default:
                break;
        }
    }
}