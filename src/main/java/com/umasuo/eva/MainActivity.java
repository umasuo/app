package com.umasuo.eva;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umasuo.eva.domain.device.service.DeviceService;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.infra.mqtt.MqttClient;
import com.umasuo.eva.ui.WaitingPage;
import com.umasuo.eva.ui.anim.ViewPagerScroller;
import com.umasuo.eva.ui.device.DeviceCenter;
import com.umasuo.eva.ui.device.NoneDevice;
import com.umasuo.eva.ui.personal.PersonalCenter;
import com.umasuo.eva.ui.scene.SceneCenter;
import com.umasuo.eva.ui.simulator.SimulatorCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面activity，需要保持干净、清晰.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    //四个主页在界面上的index，其他地方要跳转都适用这几个数值, 而不是直接使用数字.
    public static final int DEVICE_INDEX = 0;
    public static final int SCENE_INDEX = 1;
    public static final int SIMULATOR_INDEX = 2;
    public static final int PERSONAL_INDEX = 3;
    public static final int WAITING_INDEX = 4;
    public static final int DEVICE_NONE_INDEX = 5;

    private static MainActivity instance;

    private MainViewPager viewPager;
    private LinearLayout bottomMenu;

    // 底部菜单的组件
    private LinearLayout devicesLayout;
    private LinearLayout sceneLayout;
    private LinearLayout simulateLayout;
    private LinearLayout personalLayout;

    private ImageButton devicesImage;
    private ImageButton sceneImage;
    private ImageButton simulateImage;
    private ImageButton personalImage;

    private TextView devicesText;
    private TextView sceneText;
    private TextView simulateText;
    private TextView personalText;


    // 这个类里面只显示三个主入口，其他的入口交由不同的主入口去控制显示
    DeviceCenter devicesFragment; // 0 保持不变
    SceneCenter sceneFragment; // 1 保持不变
    SimulatorCenter simulatorCenter; // 1 保持不变
    PersonalCenter personalFragment; // 2 保持不变
    WaitingPage waitingPage; // 3 等待界面
    private NoneDevice noneDevice; // 没有设备的设备中心界面

    private List<FragmentRoot> pages = new ArrayList<>();

    FragmentPagerAdapter adapter;
    FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        instance = this;

        initView();
        initEvent();

        // TODO: 17/7/24 init mqtt
//        initMqtt();
    }

    private void initView() {
        viewPager = (MainViewPager) findViewById(R.id.container);
        bottomMenu = (LinearLayout) findViewById(R.id.bottom_main);

        devicesLayout = (LinearLayout) findViewById(R.id.devicesLayout);
        sceneLayout = (LinearLayout) findViewById(R.id.sceneLayout);
        simulateLayout = (LinearLayout) findViewById(R.id.simulate);
        personalLayout = (LinearLayout) findViewById(R.id.personalLayout);

        devicesImage = (ImageButton) findViewById(R.id.devicesImg);
        sceneImage = (ImageButton) findViewById(R.id.sceneImg);
        simulateImage = (ImageButton) findViewById(R.id.simulateImg);
        personalImage = (ImageButton) findViewById(R.id.personalImg);

        devicesText = (TextView) findViewById(R.id.devicesText);
        sceneText = (TextView) findViewById(R.id.sceneText);
        simulateText = (TextView) findViewById(R.id.simulateText);
        personalText = (TextView) findViewById(R.id.personalText);

        devicesFragment = new DeviceCenter();
        devicesFragment.setIndex(DEVICE_INDEX);
        pages.add(devicesFragment);

        sceneFragment = new SceneCenter();
        sceneFragment.setIndex(SCENE_INDEX);
        pages.add(sceneFragment);

        simulatorCenter = new SimulatorCenter();
        simulatorCenter.setIndex(SIMULATOR_INDEX);
        pages.add(simulatorCenter);

        personalFragment = new PersonalCenter();
        personalFragment.setIndex(PERSONAL_INDEX);
        pages.add(personalFragment);

        waitingPage = new WaitingPage();
        waitingPage.setIndex(WAITING_INDEX);
        pages.add(waitingPage);

        noneDevice = new NoneDevice();
        noneDevice.setIndex(DEVICE_NONE_INDEX);
        pages.add(noneDevice);

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return pages.get(position);
            }

            @Override
            public int getCount() {
                return pages.size();
            }
        };

        //todo 设置一下切换的动画，不要太快，调整得合适点
        viewPager.setAdapter(adapter);
        //四个主界面之间跳转，没有滑动效果
        ViewPagerScroller scroller = new ViewPagerScroller(this);
        scroller.initViewPagerScroll(viewPager);


        this.showPage(DEVICE_INDEX);
    }

    private void initEvent() {

        // 设置底部主菜单事件
        devicesLayout.setOnClickListener(this);
        devicesImage.setOnClickListener(this);
        devicesText.setOnClickListener(this);

        sceneImage.setOnClickListener(this);
        sceneLayout.setOnClickListener(this);
        sceneText.setOnClickListener(this);

        simulateImage.setOnClickListener(this);
        simulateLayout.setOnClickListener(this);
        simulateText.setOnClickListener(this);

        personalImage.setOnClickListener(this);
        personalLayout.setOnClickListener(this);
        personalText.setOnClickListener(this);
    }

    /**
     * 连接MQTT. 如果网络失败，或者什么的，那么就显示错误信息，提示用户打开网络设置.
     */
    private void initMqtt() {
        UserService userService = UserService.getInstance(this);
//        MqttClient.getInstance(userService.getUser().getUserId(), userService.getToken()).startListen();
        MqttClient.getInstance("umasuo", "password").startListen();
    }

    /**
     * 底部button的点击事件用来更改主界面以及菜单图片
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.devicesLayout:
            case R.id.devicesImg:
            case R.id.devicesText:
                showPage(0);
                break;
            case R.id.sceneLayout:
            case R.id.sceneImg:
            case R.id.sceneText:
                showPage(1);
                break;
            case R.id.simulate:
            case R.id.simulateImg:
            case R.id.simulateText:
                showPage(2);
                break;
            case R.id.personalLayout:
            case R.id.personalImg:
            case R.id.personalText:
                showPage(3);
                break;
            default:
                break;
        }
    }

    /**
     * 切换片段.
     *
     * @param i
     */
    public void showPage(int i) {
        if (i >= 0 && i < 4) {
            switch (i) {
                case DEVICE_INDEX:
                    devicesImage.setBackgroundResource(R.drawable.device_press);
                    devicesText.setTextColor(getResources().getColor(R.color.mainColor));
                    showDeviceCenter();
                    pages.get(i).onShow();
                    break;
                case SCENE_INDEX:
                    sceneImage.setBackgroundResource(R.drawable.scene_press);
                    sceneText.setTextColor(getResources().getColor(R.color.mainColor));
                    viewPager.setCurrentItem(i);
                    pages.get(i).onShow();
                    break;
                case SIMULATOR_INDEX:
                    simulateImage.setBackgroundResource(R.drawable.simulator_press);
                    simulateText.setTextColor(getResources().getColor(R.color.mainColor));
                    viewPager.setCurrentItem(i);
                    pages.get(i).onShow();
                    break;
                case PERSONAL_INDEX:
                    personalImage.setBackgroundResource(R.drawable.personal_press);
                    personalText.setTextColor(getResources().getColor(R.color.mainColor));
                    viewPager.setCurrentItem(i);
                    pages.get(i).onShow();
                    break;
            }
        }

    }

    /**
     * 显示设备中心.
     */
    private void showDeviceCenter() {
        popAll();
        if (DeviceService.getInstance(this).getAllDevice().isEmpty()) {
            viewPager.setCurrentItem(DEVICE_NONE_INDEX);
        } else {
            viewPager.setCurrentItem(DEVICE_INDEX);
        }
    }

    /**
     * 添加一个新页,只有添加，没有删除.
     *
     * @param fragment Fragment
     * @return 当前fragment在列表中的index
     */
    public int addFragment(FragmentRoot fragment) {
        int index = this.pages.size();
        LogControl.debug(TAG, "add Fragment index = " + index);
        if (!fragment.isAdded()) {
            pages.add(fragment);
        }
        adapter.notifyDataSetChanged();
        return index;
    }

    /**
     * 获得pages 个数
     *
     * @return
     */
    public int getPagerSize() {
        return this.pages.size();
    }

    /**
     * Fragment之间跳转
     *
     * @param firstFragment
     * @param secondFragment
     * @param inStack
     */
    public void showFragment(Fragment firstFragment, Fragment secondFragment, boolean inStack) {
        transaction = getSupportFragmentManager().beginTransaction();
        //可通过此处，设置进入二级以下界面的动画效果，设置为0，则没有动画
        transaction.setCustomAnimations(0, 0, 0, 0);
        if (inStack) {
            if (!secondFragment.isAdded()) {
                transaction.add(R.id.main, secondFragment).hide(firstFragment).show(secondFragment).addToBackStack(null).commit();
            } else {
                transaction.hide(firstFragment).show(secondFragment).addToBackStack(null).commit();
            }
        } else {//不用stack管理fragment
            transaction = getSupportFragmentManager().beginTransaction();
            if (!secondFragment.isAdded()) {
                transaction.add(R.id.main, secondFragment).hide(firstFragment).show(secondFragment).commit();
            } else {
                transaction.hide(firstFragment).show(secondFragment).commitAllowingStateLoss();
            }
        }
    }

    /**
     * Fragment 退出Stack
     */
    public void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * pop 所有的非主界面.
     */
    public void popAll() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        while (count > 0) {
            getSupportFragmentManager().popBackStack();
            count--;
        }
    }

    /**
     * 响应菜单的回退点击事件.
     */
    @Override
    public void onBackPressed() {

        //后面再优化，哪些fragment在popBackStack 或者改变跳转方式 不被销毁
        popBackStack();

        int popNum = getSupportFragmentManager().getBackStackEntryCount();
        LogControl.debug(TAG, "popNum = " + popNum);
        if (popNum == 0) {
            exit();
        } else if (popNum == 1) {
            showBottom();
        }

    }

    /**
     * 将所有菜单图片还原.
     */
    private void resetImg() {
        devicesImage.setBackgroundResource(R.drawable.device);
        devicesText.setTextColor(getResources().getColor(R.color.black));

        sceneImage.setBackgroundResource(R.drawable.scene);
        sceneText.setTextColor(getResources().getColor(R.color.black));

        simulateImage.setBackgroundResource(R.drawable.simulator);
        simulateText.setTextColor(getResources().getColor(R.color.black));

        personalImage.setBackgroundResource(R.drawable.personal);
        personalText.setTextColor(getResources().getColor(R.color.black));
    }

    /**
     * SHOW BOTTOM
     */
    public void showBottom() {
        if (bottomMenu != null) {
            bottomMenu.setVisibility(View.VISIBLE);
        }
    }

    public void hideBottom() {
        if (bottomMenu != null) {
            bottomMenu.setVisibility(View.GONE);
        }
    }


    //用来双击退出
    private boolean isExit = false;
    Handler exitHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), R.string.isexit,
                    Toast.LENGTH_SHORT).show();
            exitHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    public static MainActivity getInstance() {
        return instance;
    }
}
