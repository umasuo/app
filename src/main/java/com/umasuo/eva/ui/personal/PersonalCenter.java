package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.adapter.PersonalAdapter;
import com.umasuo.eva.infra.log.LogControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心界面
 */

public class PersonalCenter extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String TAG = "personal";

    private ListView personal_listview;
    private LinearLayout piSummary;
    public List<Map<String, Object>> mdata;
    PersonalSettings personalSettings;
    private int psIndex = 2;
    PersonalInfo personalInfo;
    private int piIndex = 2;
    PersonalSignin signin;
    private int signinIndex = 2;
    PersonalSigninWithSms smsSignin;
    private int smsSigninIndex = 2;
    Feedback feedback;
    private int fbIndex = 2;
    FAQ faq;
    private int faqIndex = 2;
    About about;
    private int aboutIndex = 2;
    MessageCenter messageCenter;
    private int mcIndex = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.personal, container, false);
        personal_listview = view.findViewById(R.id.personal_listview);
        PersonalAdapter pAdapter = new PersonalAdapter(getContext(), getData());
        personal_listview.setAdapter(pAdapter);
        personal_listview.setOnItemClickListener(this);

        piSummary = view.findViewById(R.id.personal_info_summary);
        piSummary.setOnClickListener(this);

        return view;
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
//            smsSignin = new PersonalSigninWithSms();
//            smsSigninIndex = ((MainActivity) getContext()).addFragment(smsSignin);
//        }
//        ((MainActivity) getContext()).showFragment(smsSigninIndex);

        if (personalInfo == null) {
            personalInfo = new PersonalInfo();
            piIndex = ((MainActivity) getContext()).addFragment(personalInfo);
        }
        ((MainActivity) getContext()).showFragment(piIndex);
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

        MainActivity activity = (MainActivity) getContext();
        switch (i) {
            case 0: {
                if (messageCenter == null) {
                    messageCenter = new MessageCenter();
                    mcIndex = activity.addFragment(messageCenter);
                }
                activity.showFragment(mcIndex);
                break;
            }
            case 1: {
                if (faq == null) {
                    faq = new FAQ();
                    faqIndex = activity.addFragment(faq);
                }
                activity.showFragment(faqIndex);
                break;
            }
            case 2: {
                if (feedback == null) {
                    feedback = new Feedback();
                    fbIndex = activity.addFragment(feedback);
                }
                activity.showFragment(fbIndex);
                break;
            }
            case 3: {
                if (about == null) {
                    about = new About();
                    aboutIndex = activity.addFragment(about);
                }
                activity.showFragment(aboutIndex);
                break;
            }
            case 4: {
                if (personalSettings == null) {
                    personalSettings = new PersonalSettings();
                    psIndex = ((MainActivity) getContext()).addFragment(personalSettings);
                }
                activity.showFragment(psIndex);
                break;
            }
            default:
                activity.showFragment(2);//个人中心主界面
                break;
        }
    }
}