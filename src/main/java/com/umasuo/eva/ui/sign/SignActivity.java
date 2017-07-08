package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.log.LogControl;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录、或者注册用的activity.
 */
public class SignActivity extends FragmentActivity {

    private String TAG = "SignActivity";

    private SignPager signPager;
    private FragmentPagerAdapter signAdapter;

    private List<Fragment> pages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        LogControl.debug(TAG, "SignActivity onCreate >>");

        signAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return pages.get(position);
            }

            @Override
            public int getCount() {
                return pages.size();
            }
        };

        signPager = (SignPager) findViewById(R.id.sign_pager);
        signPager.setAdapter(signAdapter);

        SigninStarter signinStarter = new SigninStarter();
        pages.add(signinStarter);
        signPager.setCurrentItem(0);
    }

    /**
     * 切换片段.
     *
     * @param i
     */
    public void replaceFragment(int i) {
//        addDeviceViewPager.setCurrentItem(i);
    }

    @Override
    public void onBackPressed() {
        LogControl.debug(TAG, "pressed back button");
        // TODO: 17/7/7 根据现在的状态显示不同的片段
    }

}
