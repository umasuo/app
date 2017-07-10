package com.umasuo.eva.ui.device.add;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.log.LogControl;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加设备的activity.
 * todo 这个其实也可以换成fragment
 */
public class AddDeviceActivity extends FragmentActivity {

//    private String TAG = "AddDeviceActivity";
//
//    private String selectedDeviceName = "";
//
//    private AddDeviceViewPager addDeviceViewPager;
//    private FragmentPagerAdapter addDeviceAdapter;
//    private List<Fragment> addDeviceFragments = new ArrayList<Fragment>();
//
//
//    private SelectDeviceType selectDeviceType;
////    private PowerUpDevice powerUpDevice;
////    private InputWifiPassword inputWifiPassword;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.device_add);
//        LogControl.debug(TAG, "AddDeviceActivity onCreate >>");
//        initView();
//        initEvent();
//
//    }
//
//    private void initView() {
//        addDeviceViewPager = (AddDeviceViewPager) findViewById(R.id.addDeviceViewPager);
//
//        selectDeviceType = new SelectDeviceType();
////        powerUpDevice = new PowerUpDevice();
////        inputWifiPassword = new InputWifiPassword();
//
//        addDeviceFragments.add(selectDeviceType);
////        addDeviceFragments.add(powerUpDevice);
////        addDeviceFragments.add(inputWifiPassword);
//
//        addDeviceAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return addDeviceFragments.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return addDeviceFragments.size();
//            }
//        };
//        addDeviceViewPager.setAdapter(addDeviceAdapter);
//    }
//
//    /**
//     * 切换片段.
//     *
//     * @param i
//     */
//    public void addFragment(int i) {
//        addDeviceViewPager.setCurrentItem(i);
//    }
//
//    /**
//     * 切换片段
//     * @param firstFragment
//     * @param secondfragment
//     */
//    public void showFragment(Fragment firstFragment,Fragment secondfragment){
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.setCustomAnimations(R.anim.choose_open,R.anim.choose_close);
//        if (!secondfragment.isAdded()){
//            transaction.add(R.id.add_device_main,secondfragment).hide(firstFragment).show(secondfragment).addToBackStack(null).commit();
//        }else{
//            transaction.hide(firstFragment).show(secondfragment).addToBackStack(null).commitAllowingStateLoss();
//        }
//    }
//
//    /**
//     * 初始化事件监听.
//     */
//    private void initEvent() {
//
//        addDeviceViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////                LogControl.debug(TAG, "onPageScrolled position =" + position + " positionOffset =" + positionOffset + " positionOffsetPixels=" + positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
////                LogControl.debug(TAG, "onPageSelected position =" + position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
////                LogControl.debug(TAG, "onPageScrollStateChanged int state =" + state);
//            }
//
//        });
//
//        addDeviceViewPager.setCurrentItem(0);
//    }
//
//    @Override
//    public void onBackPressed() {
//        LogControl.debug(TAG, "pressed back button");
//        // TODO: 17/7/7 根据现在的状态显示不同的片段
////        switch (getCurrentFragment()){
////            case 2:
////                showPage(1);
////                break;
////            case 1:
////                showPage(0);
////                break;
////            case 0:
////                this.finish();
////                break;
////        }
//        super.onBackPressed();
//    }
//
//
//    private int getCurrentFragment(){
//        return addDeviceViewPager.getCurrentItem();
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        this.overridePendingTransition(0, R.anim.choose_close);
//    }
//
//    public String getSelectedDeviceName() {
//        return selectedDeviceName;
//    }
//
//    public void setSelectedDeviceName(String name) {
//        this.selectedDeviceName = name;
//    }
}
