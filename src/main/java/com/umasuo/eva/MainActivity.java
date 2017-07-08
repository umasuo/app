package com.umasuo.eva;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umasuo.eva.ui.device.DeviceCenter;
import com.umasuo.eva.ui.personal.PersonalCenter;
import com.umasuo.eva.ui.scene.SceneCenter;
import com.umasuo.eva.infra.adapter.MainPageAdapter;
import com.umasuo.eva.infra.log.LogControl;

/**
 * 主界面activity，需要保持干净、清晰.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private String TAG = "MainActivity";

    private MainViewPager viewPager;

    // 底部菜单的组件
    private LinearLayout devicesLayout;
    private LinearLayout sceneLayout;
    private LinearLayout personalLayout;

    private ImageButton devicesImage;
    private ImageButton sceneImage;
    private ImageButton personalImage;

    private TextView devicesText;
    private TextView sceneText;
    private TextView personalText;


    // 这个类里面只显示三个主入口，其他的入口交由不同的主入口去控制显示
    Fragment devicesFragment; // 0 保持不变
    Fragment sceneFragment; // 1 保持不变
    Fragment personalFragment; // 2 保持不变

    MainPageAdapter adapter;

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

        devicesLayout = (LinearLayout) findViewById(R.id.devicesLayout);
        sceneLayout = (LinearLayout) findViewById(R.id.sceneLayout);
        personalLayout = (LinearLayout) findViewById(R.id.personalLayout);

        devicesImage = (ImageButton) findViewById(R.id.devicesImgbtn);
        sceneImage = (ImageButton) findViewById(R.id.sceneImgBtn);
        personalImage = (ImageButton) findViewById(R.id.personalImgBtn);

        devicesText = (TextView) findViewById(R.id.devicesTextBtn);
        sceneText = (TextView) findViewById(R.id.sceneTextBtn);
        personalText = (TextView) findViewById(R.id.personalTextBtn);

        devicesFragment = new DeviceCenter();
        sceneFragment = new SceneCenter();
        personalFragment = new PersonalCenter();

        adapter = new MainPageAdapter(getSupportFragmentManager());

        adapter.add(devicesFragment);
        adapter.add(sceneFragment);
        adapter.add(personalFragment);

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

        int currentPage = viewPager.getCurrentItem();
        if (currentPage == 4) {//4 当前界面是个人中心的下一层
            showFragment(2);
            return;
        } else if (currentPage == 3) {//4 当前界面是智能场景下一层
            showFragment(1);
            return;
        }
        super.onBackPressed();

    }

    private void initEvent() {

        // 设置底部主菜单事件
        devicesLayout.setOnClickListener(this);
        devicesImage.setOnClickListener(this);
        devicesText.setOnClickListener(this);

        sceneImage.setOnClickListener(this);
        sceneLayout.setOnClickListener(this);
        sceneText.setOnClickListener(this);

        personalImage.setOnClickListener(this);
        personalLayout.setOnClickListener(this);
        personalText.setOnClickListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                //当前选中的Fragment 下标
                int currentItem = viewPager.getCurrentItem();
                //只有点击底部菜单时才会变更菜单的图片
                switch (currentItem) {
                    case 0:
                        devicesImage.setBackgroundResource(R.drawable.first);
                        break;
                    case 1:
                        sceneImage.setBackgroundResource(R.drawable.second);
                        break;
                    case 2:
                        personalImage.setBackgroundResource(R.drawable.three);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
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
            case R.id.devicesImgbtn:
            case R.id.devicesTextBtn:
                showFragment(0);
                break;
            case R.id.sceneLayout:
            case R.id.sceneImgBtn:
            case R.id.sceneTextBtn:
                showFragment(1);
                break;
            case R.id.personalLayout:
            case R.id.personalImgBtn:
            case R.id.personalTextBtn:
                showFragment(2);
                break;
            default:
                break;
        }
    }

    public void showFragment(int i) {
        LogControl.debug(TAG, "MainActivity showFragment i: " + i);
        //改变内容区域，把图片设置为亮的
        switch (i) {
            case 0:
                devicesImage.setBackgroundResource(R.drawable.first);
                break;
            case 1:
                sceneImage.setBackgroundResource(R.drawable.second);
                break;
            case 2:
                personalImage.setBackgroundResource(R.drawable.three);
                break;
            default:
                break;
        }
        //切换Fragment
        viewPager.setCurrentItem(i, true);
    }

    /**
     * 新添加并直接显示添加的片段.
     *
     * @param fragment
     */
    public int addFragment(Fragment fragment) {
        int index = adapter.add(fragment);
        adapter.notifyDataSetChanged();
        return index;
    }

    /**
     * 将所有菜单图片还原.
     */
    private void resetImg() {
        devicesImage.setBackgroundResource(R.drawable.first);
        sceneImage.setBackgroundResource(R.drawable.second);
        personalImage.setBackgroundResource(R.drawable.personal_center);
    }
}
