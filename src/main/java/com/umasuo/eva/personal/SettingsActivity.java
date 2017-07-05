package com.umasuo.eva.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.R;
import com.umasuo.eva.tools.log.LogControl;

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
        LogControl.debug(TAG,"SettingsActivity onCreate >>");
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
        mSettings_three = new SettingsFragmentThree();

        mSettingsFragments.add(mSettings_one);
        mSettingsFragments.add(mSettings_two);
        mSettingsFragments.add(mSettings_three);

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
                LogControl.debug(TAG,"onPageScrolled position ="+position + " positionOffset ="+positionOffset+" positionOffsetPixels="+positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                LogControl.debug(TAG,"onPageSelected position ="+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogControl.debug(TAG,"onPageScrollStateChanged int state ="+state);
            }

        });

        changeCurrentItem(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.settings_back:
                changeCurrentItem(getCurrentItem() -1);

                break;
            case R.id.settings_devices:
                changeCurrentItem(2);
                break;
        }

    }

    public void changeCurrentItem(int i){
        if (msettingsViewPager != null){
            msettingsViewPager.setCurrentItem(i);
        }
    }

    public int getCurrentItem(){
        if(msettingsViewPager != null){
            return msettingsViewPager.getCurrentItem();
        }
        return 0;
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0,R.anim.choose_close);
    }
}
