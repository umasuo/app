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
import android.widget.TextView;

import com.umasuo.eva.evaapp.log.LogControl;

import java.util.ArrayList;
import java.util.List;


public class SettingsActivity extends FragmentActivity implements View.OnClickListener{

    private String TAG = "SettingsActivity1";
    private ImageView settings_back;
    private TextView settings_title;
    private ImageView settings_devices;
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
        settings_back = (ImageView) findViewById(R.id.settings_back);
        settings_title = (TextView) findViewById(R.id.settings_title);
        settings_devices = (ImageView) findViewById(R.id.settings_devices);

        settings_back.setOnClickListener(this);
        settings_title.setOnClickListener(this);
        settings_devices.setOnClickListener(this);

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

        changeCurrentItem(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.settings_back:
                System.out.println("liubin 333 onClick");
                this.finish();
                break;
            case R.id.settings_devices:
                changeCurrentItem(1);
                break;
        }

    }

    public void changeCurrentItem(int i){
        if (msettingsViewPager != null){
            msettingsViewPager.setCurrentItem(i);
        }
    }

    @Override
    public void finish() {
        System.out.println("liubin 333 choose finish");
        super.finish();
        this.overridePendingTransition(0,R.anim.choose_close);
    }
}
