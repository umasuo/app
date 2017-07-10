package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.adapter.PersonalAdapter;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.ui.sign.SigninStarter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心界面
 */

public class PersonalCenter extends FragmentRoot implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String TAG = "personal";

    private ListView personalList;
    private LinearLayout piSummary;
    public List<Map<String, Object>> mdata;
    PersonalSettings personalSettings;
    PersonalInfo personalInfo;
    Feedback feedback;
    FAQ faq;
    About about;
    MessageCenter messageCenter;

    MainActivity activity;

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
        activity = (MainActivity) getContext();

        //init data
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        LogControl.debug(TAG, "onHiddenChanged hidden = " + hidden);
        super.onHiddenChanged(hidden);
        if (hidden) {
            activity.hideBottom();
        } else {
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

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.leavehome);
        map.put("name", "设置");
        list.add(map);

        return list;
    }

    /**
     * 点击用户信息是，显示用户信息界面.
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        LogControl.debug(TAG, "click");

//        if (smsSignin == null) {
//            smsSignin = new SigninWithSms();
//            smsSigninIndex = ((MainActivity) getContext()).addFragment(smsSignin);
//        }
//        ((MainActivity) getContext()).showFragment(smsSigninIndex);

//        if (personalInfo == null) {
//            personalInfo = new PersonalInfo();
//            piIndex = ((MainActivity) getContext()).addFragment(personalInfo);
//        }
//        ((MainActivity) getContext()).showFragment(piIndex);

        SigninStarter signinStarter = new SigninStarter();
        int index = ((MainActivity) getContext()).addFragment(signinStarter);
        ((MainActivity) getContext()).showPage(index);
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
                }

                messageCenter.setIndex(activity.addFragment(messageCenter));
                activity.showPage(messageCenter.getIndex());
                break;
            }
            case 1: {
                if (faq == null) {
                    faq = new FAQ();
                    faq.setPreIndex(index);
                }
                faq.setIndex(activity.addFragment(faq));
                activity.showPage(faq.getIndex());
                break;
            }
            case 2: {
                if (feedback == null) {
                    feedback = new Feedback();
                    feedback.setPreIndex(index);
                }
                feedback.setIndex(activity.addFragment(feedback));
                activity.showPage(feedback.getIndex());
                break;
            }
            case 3: {
                if (about == null) {
                    about = new About();
                    about.setPreIndex(index);
                }
                about.setIndex(activity.addFragment(about));
                activity.showPage(about.getIndex());
                break;
            }
            case 4: {
                if (personalSettings == null) {
                    personalSettings = new PersonalSettings();
                    personalSettings.setPreIndex(index);
                }
                personalSettings.setIndex(activity.addFragment(personalSettings));
                activity.showPage(personalSettings.getIndex());
                break;
            }
            default:
                break;
        }
    }
}