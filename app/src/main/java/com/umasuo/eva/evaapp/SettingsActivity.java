package com.umasuo.eva.evaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.umasuo.eva.evaapp.log.LogControl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liubin8095 on 2017/7/2.
 */

public class SettingsActivity extends FragmentActivity implements View.OnClickListener{

    private String TAG = "SettingsActivity1";
    private ImageView back;
    private SettingsViewPager msettingsViewPager;
    private FragmentPagerAdapter mSettingsAdapter;
    private List<Fragment> mSettingsFragments = new ArrayList<Fragment>();
    Fragment mSettings_one;
    Fragment mSettings_two;
    Fragment mSettings_three;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        LogControl.Print_D(TAG,"SettingsActivity onCreate >>");
        initView();
        initEvent();

    }

    private void initView(){
        msettingsViewPager = (SettingsViewPager) findViewById(R.id.settingsViewPager);
        mSettings_one = new SettingsFragmentOne();
        mSettings_two = new SettingsFragmentTwo();

        mSettingsFragments.add(mSettings_one);
        mSettingsFragments.add(mSettings_two);

        mSettingsAdapter = new FragmentPagerAdapter( getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mSettingsFragments.get(position);
            }

            @Override
            public int getCount() {
                return mSettingsFragments.size();
            }
        };
        msettingsViewPager.setAdapter(mSettingsAdapter);
    }

    private void initEvent(){

        msettingsViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogControl.Print_D(TAG,"onPageScrolled position ="+position + " positionOffset ="+positionOffset+" positionOffsetPixels="+positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                LogControl.Print_D(TAG,"onPageSelected position ="+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogControl.Print_D(TAG,"onPageScrollStateChanged int state ="+state);
            }


        });


        msettingsViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.choose_back:
                System.out.println("liubin 333 onClick");
                this.finish();
                break;
        }

    }
    private void startMainActivity(){
        Intent intent = new Intent();
        intent.setClassName(this,"com.umasuo.eva.evaapp.MainActivity");//打开一个activity
        this.startActivity(intent);
        this.overridePendingTransition(R.anim.choose_close,0);
    }

    @Override
    public void finish() {
        System.out.println("liubin 333 choose finish");
        super.finish();
        this.overridePendingTransition(0,R.anim.choose_close);
    }
}
