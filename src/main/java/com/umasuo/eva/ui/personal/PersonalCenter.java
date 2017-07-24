package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.FragmentRoot;
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
    private TextView userSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.personal, container, false);

        headIcon = (ImageView) view.findViewById(R.id.head_icon);
        userName = (TextView) view.findViewById(R.id.name);
        userSettings = (TextView) view.findViewById(R.id.personal_settings);
        userSettings.setOnClickListener(this);

        activity = (MainActivity) getContext();
        userService = UserService.getInstance(activity);
        return view;
    }

    @Override
    public void onShow() {
        if (activity != null) {
            LogControl.debug(TAG, "onShow >>>");
            activity.showBottom();
            initUserData();
        }
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.personal_icon_msg);
        map.put("name", "消息中心");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.personal_icon_faq);
        map.put("name", "常见问题");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.personal_icon_feedback);
        map.put("name", "意见反馈");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.personal_icon_about);
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
    }

    /**
     * 点击用户信息是，显示用户信息界面.
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        LogControl.debug(TAG, "onClick");
        switch (view.getId()) {
            case R.id.personal_settings: {
                if (personalSettings == null) {
                    personalSettings = new PersonalSettings();
                    personalSettings.setPreIndex(index);
                    personalSettings.setIndex(activity.getPagerSize());
                }
                activity.showFragment(this, personalSettings, true);
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
                    messageCenter.setIndex(activity.getPagerSize());
//                    messageCenter.setIndex(activity.addFragment(messageCenter));
                }
//                activity.showPage(messageCenter.getIndex());
                activity.showFragment(this, messageCenter, true);
                break;
            }
            case 1: {
                if (faq == null) {
                    faq = new FAQ();
                    faq.setPreIndex(index);
                    faq.setIndex(activity.getPagerSize());
//                    faq.setIndex(activity.addFragment(faq));
                }
//                activity.showPage(faq.getIndex());
                activity.showFragment(this, faq, true);
                break;
            }
            case 2: {
                if (feedback == null) {
                    feedback = new Feedback();
                    feedback.setPreIndex(index);
                    feedback.setIndex(activity.getPagerSize());
//                    feedback.setIndex(activity.addFragment(feedback));
                }
                activity.showFragment(this, feedback, true);
//                activity.showPage(feedback.getIndex());
                break;
            }
            case 3: {
                if (about == null) {
                    LogControl.debug(TAG, "about");
                    about = new About();
                    about.setPreIndex(index);
                    about.setIndex(activity.getPagerSize());
//                    about.setIndex(activity.addFragment(about));
                }
                activity.showFragment(this, about, true);
//                activity.showPage(about.getIndex());
                break;
            }
            default:
                break;
        }
    }
}