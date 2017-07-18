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

import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.ui.WaitingPage;
import com.umasuo.eva.ui.device.DeviceCenter;
import com.umasuo.eva.ui.personal.PersonalCenter;
import com.umasuo.eva.ui.scene.SceneCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面activity，需要保持干净、清晰.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private String TAG = "MainActivity";

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
    FragmentRoot devicesFragment; // 0 保持不变
    FragmentRoot sceneFragment; // 1 保持不变
    FragmentRoot personalFragment; // 2 保持不变
    FragmentRoot waitingPage; // 3 等待界面

    private List<FragmentRoot> pages = new ArrayList<>();

    FragmentPagerAdapter adapter;
    FragmentTransaction transaction;

    //用来双击退出
    private boolean isExit = false;
    Handler exitHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        initView();
        initEvent();
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
        devicesFragment.setIndex(0);
        sceneFragment = new SceneCenter();
        sceneFragment.setIndex(1);
        personalFragment = new PersonalCenter();
        personalFragment.setIndex(2);
        waitingPage = new WaitingPage();
        waitingPage.setIndex(3);
        pages.add(devicesFragment);
        pages.add(sceneFragment);
        pages.add(personalFragment);
        pages.add(waitingPage);

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
                // TODO: 17/7/18 显示体验中心／模拟中心
                break;
            case R.id.personalLayout:
            case R.id.personalImg:
            case R.id.personalText:
                showPage(2);
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
        if (i >= 0 && i < 3) {
            viewPager.setCurrentItem(i);
        }
        //显示登录初始界面
        pages.get(i).onShow();
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
        pages.add(fragment);
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
     * @param secondfragment
     * @param inStack
     */
    public void showFragment(Fragment firstFragment, Fragment secondfragment, boolean inStack) {
        transaction = getSupportFragmentManager().beginTransaction();

        if (inStack) {
            if (!secondfragment.isAdded()) {
                transaction.add(R.id.main, secondfragment).hide(firstFragment).show(secondfragment).addToBackStack(null).commit();
            } else {
                transaction.hide(firstFragment).show(secondfragment).addToBackStack(null).commit();
            }
        } else {//不用stack管理fragment
            transaction = getSupportFragmentManager().beginTransaction();
            if (!secondfragment.isAdded()) {
                transaction.add(R.id.main, secondfragment).hide(firstFragment).show(secondfragment).commit();
            } else {
                transaction.hide(firstFragment).show(secondfragment).commitAllowingStateLoss();
            }
        }
    }

    /**
     * 响应菜单的回退点击事件.
     */
    @Override
    public void onBackPressed() {
//        int curIndex = viewPager.getCurrentItem();
//        LogControl.debug(TAG,"onBackPressed curIndex ="+curIndex);


//        FragmentRoot curFrag = pages.get(curIndex);
//        showPage(curFrag.getPreIndex());


        //后面再优化，哪些fragment在popBackStack 或者改变跳转方式 不被销毁
        getSupportFragmentManager().popBackStack();

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
        devicesImage.setBackgroundResource(R.drawable.first);
        sceneImage.setBackgroundResource(R.drawable.second);
        personalImage.setBackgroundResource(R.drawable.personal_center);
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
}
