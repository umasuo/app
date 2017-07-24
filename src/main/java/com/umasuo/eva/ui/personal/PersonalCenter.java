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

/**
 * 个人中心界面
 */

public class PersonalCenter extends FragmentRoot implements AdapterView.OnItemClickListener, View.OnClickListener {

    private static final String TAG = "PersonalCenter";

    private PersonalSettings personalSettings;
    private PersonalInfo personalInfo;
    private Feedback feedback;
    private FAQ faq;
    private About about;
    private MessageCenter messageCenter;

    private MainActivity activity;
    private UserService userService;

    // 用户信息
    private ImageView headIcon;
    private TextView userName;
    private TextView editInfo;

    //用户设置
    private LinearLayout msgLayout;
    private ImageView msgIcon;
    private TextView msgText;
    private ImageView msgRight;

    //常见问题
    private LinearLayout faqLayout;
    private ImageView faqIcon;
    private TextView faqText;
    private ImageView faqRight;

    //意见反馈
    private LinearLayout feedbackLayout;
    private ImageView feedbackIcon;
    private TextView feedbackText;
    private ImageView feedbackRight;

    //关于
    private LinearLayout aboutLayout;
    private ImageView aboutIcon;
    private TextView aboutText;
    private ImageView aboutRight;

    //关于
    private LinearLayout settingLayout;
    private ImageView settingIcon;
    private TextView settingText;
    private ImageView settingRight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.personal, container, false);
        activity = (MainActivity) getContext();

        editInfo = (TextView) view.findViewById(R.id.personal_info);
        editInfo.setOnClickListener(this);
        headIcon = (ImageView) view.findViewById(R.id.head_icon);
        headIcon.setOnClickListener(this);
        userName = (TextView) view.findViewById(R.id.name);
        userName.setOnClickListener(this);

        msgLayout = (LinearLayout) view.findViewById(R.id.msg_layout);
        msgLayout.setOnClickListener(this);
        msgIcon = (ImageView) view.findViewById(R.id.msg_icon);
        msgIcon.setOnClickListener(this);
        msgText = (TextView) view.findViewById(R.id.msg_text);
        msgText.setOnClickListener(this);
        msgRight = (ImageView) view.findViewById(R.id.msg_right);
        msgRight.setOnClickListener(this);

        faqLayout = (LinearLayout) view.findViewById(R.id.faq_layout);
        faqLayout.setOnClickListener(this);
        faqIcon = (ImageView) view.findViewById(R.id.faq_icon);
        faqIcon.setOnClickListener(this);
        faqText = (TextView) view.findViewById(R.id.faq_text);
        faqText.setOnClickListener(this);
        faqRight = (ImageView) view.findViewById(R.id.faq_right);
        faqRight.setOnClickListener(this);

        feedbackLayout = (LinearLayout) view.findViewById(R.id.feedback_layout);
        feedbackLayout.setOnClickListener(this);
        feedbackIcon = (ImageView) view.findViewById(R.id.feedback_icon);
        feedbackIcon.setOnClickListener(this);
        feedbackText = (TextView) view.findViewById(R.id.feedback_text);
        feedbackText.setOnClickListener(this);
        feedbackRight = (ImageView) view.findViewById(R.id.feedback_right);
        feedbackRight.setOnClickListener(this);

        aboutLayout = (LinearLayout) view.findViewById(R.id.about_layout);
        aboutLayout.setOnClickListener(this);
        aboutIcon = (ImageView) view.findViewById(R.id.about_icon);
        aboutIcon.setOnClickListener(this);
        aboutText = (TextView) view.findViewById(R.id.about_text);
        aboutText.setOnClickListener(this);
        aboutRight = (ImageView) view.findViewById(R.id.about_right);
        aboutRight.setOnClickListener(this);

        settingLayout = (LinearLayout) view.findViewById(R.id.setting_layout);
        settingLayout.setOnClickListener(this);
        settingIcon = (ImageView) view.findViewById(R.id.setting_icon);
        settingIcon.setOnClickListener(this);
        settingText = (TextView) view.findViewById(R.id.setting_text);
        settingText.setOnClickListener(this);
        settingRight = (ImageView) view.findViewById(R.id.setting_right);
        settingRight.setOnClickListener(this);


        userService = UserService.getInstance(activity);

        initUserData();
        return view;
    }

    @Override
    public void onShow() {
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
            case R.id.personal_info:
            case R.id.head_icon:
            case R.id.name: {
                if (personalInfo == null) {
                    personalInfo = new PersonalInfo();
                    personalInfo.setPreIndex(index);
                    personalInfo.setIndex(activity.getPagerSize());
                }
                activity.showFragment(this, personalInfo, true);
                break;
            }
            case R.id.msg_layout:
            case R.id.msg_icon:
            case R.id.msg_text:
            case R.id.msg_right: {
                if (messageCenter == null) {
                    messageCenter = new MessageCenter();
                    messageCenter.setPreIndex(index);
                    messageCenter.setIndex(activity.getPagerSize());
                }
                activity.showFragment(this, messageCenter, true);
                break;
            }
            case R.id.faq_layout:
            case R.id.faq_icon:
            case R.id.faq_text:
            case R.id.faq_right: {
                if (faq == null) {
                    faq = new FAQ();
                    faq.setPreIndex(index);
                    faq.setIndex(activity.getPagerSize());
                }
                activity.showFragment(this, faq, true);
                break;
            }
            case R.id.feedback_layout:
            case R.id.feedback_icon:
            case R.id.feedback_text:
            case R.id.feedback_right: {
                if (feedback == null) {
                    feedback = new Feedback();
                    feedback.setPreIndex(index);
                    feedback.setIndex(activity.getPagerSize());
                }
                activity.showFragment(this, feedback, true);
                break;
            }
            case R.id.about_layout:
            case R.id.about_icon:
            case R.id.about_text:
            case R.id.about_right: {
                if (about == null) {
                    LogControl.debug(TAG, "about");
                    about = new About();
                    about.setPreIndex(index);
                    about.setIndex(activity.getPagerSize());
                }
                activity.showFragment(this, about, true);
                break;
            }
            case R.id.setting_layout:
            case R.id.setting_icon:
            case R.id.setting_text:
            case R.id.setting_right: {
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

            }
            case 1: {

            }
            case 2: {

            }
            case 3: {

            }
            default:
                break;
        }
    }
}