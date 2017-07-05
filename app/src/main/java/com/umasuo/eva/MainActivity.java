package com.umasuo.eva;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private String TAG = "MainActivity";

    private MainViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    private LinearLayout devicesLayout;
    private LinearLayout sceneLayout;
    private LinearLayout personalLayout;

    private ImageButton devicesImage;
    private ImageButton sceneImage;
    private ImageButton persoanlImage;



    Fragment devicesFragment;
    Fragment sceneFragement;
    Fragment personalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        initView();
        initEvent();
    }

    private void initView() {
        viewPager = (MainViewPager)findViewById(R.id.container);

        devicesLayout = (LinearLayout)findViewById(R.id.devices_layout);
        sceneLayout = (LinearLayout)findViewById(R.id.scene_layout);
        personalLayout = (LinearLayout)findViewById(R.id.personal_layout);

        devicesImage = (ImageButton)findViewById(R.id.devices_imagbtn);
        sceneImage = (ImageButton)findViewById(R.id.scene_imgbtn);
        persoanlImage = (ImageButton)findViewById(R.id.personal_imgbtn);

        devicesFragment = new DevicesFragment();
        sceneFragement = new SceneFragment();
        personalFragment = new PersonalFragment();

        mFragments.add(devicesFragment);
        mFragments.add(sceneFragement);
        mFragments.add(personalFragment);


        mAdapter = new FragmentPagerAdapter( getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        viewPager.setAdapter(mAdapter);
    }
    private void initEvent() {
        // 设置事件
        devicesImage.setOnClickListener(this);
        sceneImage.setOnClickListener(this);
        persoanlImage.setOnClickListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                //当前选中的Fragment 下标
                int currentItem = viewPager.getCurrentItem();
                //把图片全设置为暗的
                resetImg();
                switch (currentItem) {
                    case 0:
                        devicesImage.setBackgroundResource(R.drawable.first);
                        break;
                    case 1:
                        sceneImage.setBackgroundResource(R.drawable.second);
                        break;
                    case 2:
                        persoanlImage.setBackgroundResource(R.drawable.three);
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

    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.devices_imagbtn:
                setSelect(0);
                break;
            case R.id.scene_imgbtn:
                setSelect(1);
                break;
            case R.id.personal_imgbtn:
                setSelect(2);
                break;
            default:
                break;
        }
    }
    private void setSelect(int i){
        System.out.println("liubin 111 MainActivity setSelect i ="+i);
        //改变内容区域，把图片设置为亮的
        switch (i) {
            case 0:
                devicesImage.setBackgroundResource(R.drawable.first);
                break;
            case 1:
                sceneImage.setBackgroundResource(R.drawable.second);
                break;
            case 2:
                persoanlImage.setBackgroundResource(R.drawable.three);
                break;

            default:
                break;
        }
        //切换Fragment
        viewPager.setCurrentItem(i);
    }
    //将所有的图片都变暗
    private void resetImg(){
        devicesImage.setBackgroundResource(R.drawable.first);
        sceneImage.setBackgroundResource(R.drawable.second);
        persoanlImage.setBackgroundResource(R.drawable.three);
    }
}
