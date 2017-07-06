package com.umasuo.eva.device.add;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.umasuo.eva.R;
import com.umasuo.eva.tools.log.LogControl;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加设备的activity.
 */
public class AddDeviceActivity extends FragmentActivity {

    private String TAG = "AddDeviceActivity";

    private AddDeviceViewPager addDeviceViewPager;
    private FragmentPagerAdapter addDeviceAdapter;
    private List<Fragment> addDeviceFragments = new ArrayList<Fragment>();
    private PowerUpDevice powerUpDevice;

    private SelectDeviceType selectDeviceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_add);
        LogControl.debug(TAG, "AddDeviceActivity onCreate >>");
        initView();
        initEvent();

    }

    private void initView() {
        addDeviceViewPager = (AddDeviceViewPager) findViewById(R.id.addDeviceViewPager);

        selectDeviceType = new SelectDeviceType();
        powerUpDevice = new PowerUpDevice();

        addDeviceFragments.add(selectDeviceType);
        addDeviceFragments.add(powerUpDevice);

        addDeviceAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return addDeviceFragments.get(position);
            }

            @Override
            public int getCount() {
                return addDeviceFragments.size();
            }
        };
        addDeviceViewPager.setAdapter(addDeviceAdapter);
    }

    /**
     * 替换当前的片段
     *
     * @param i
     */
    public void replaceFragment(int i, String value) {
        //用户选择了某个设备，这里进入下一步
        // Create fragment and give it an argument specifying the article it should show

        Bundle args = new Bundle();
        args.putString("name", value);
        powerUpDevice.setArguments(args);

        LogControl.debug(TAG, value);
        addDeviceViewPager.setCurrentItem(i);
    }

    /**
     * 初始化事件监听.
     */
    private void initEvent() {

        addDeviceViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                LogControl.debug(TAG, "onPageScrolled position =" + position + " positionOffset =" + positionOffset + " positionOffsetPixels=" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
//                LogControl.debug(TAG, "onPageSelected position =" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                LogControl.debug(TAG, "onPageScrollStateChanged int state =" + state);
            }

        });

        addDeviceViewPager.setCurrentItem(0);
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0, R.anim.choose_close);
    }
}
