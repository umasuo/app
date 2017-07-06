package com.umasuo.eva;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umasuo.eva.device.DevicesFragment;
import com.umasuo.eva.personal.PersonalFragment;
import com.umasuo.eva.personal.PersonalSettingsFragment;
import com.umasuo.eva.scene.EditSceneFragment;
import com.umasuo.eva.scene.SceneFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private String TAG = "MainActivity";

    private MainViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    private LinearLayout devicesLayout;
    private LinearLayout sceneLayout;
    private LinearLayout personalLayout;

    private ImageButton devicesImage;
    private ImageButton sceneImage;
    private ImageButton personalImage;

    private TextView devicesText;
    private TextView sceneText;
    private TextView personalText;


    Fragment devicesFragment;
    Fragment sceneFragment;
    Fragment personalFragment;
    Fragment editSceneFragment;
    Fragment personalSettingsFragment;

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

        devicesFragment = new DevicesFragment();
        sceneFragment = new SceneFragment();
        personalFragment = new PersonalFragment();
        editSceneFragment = new EditSceneFragment();
        personalSettingsFragment = new PersonalSettingsFragment();

        mFragments.add(devicesFragment);//0
        mFragments.add(sceneFragment);//1
        mFragments.add(personalFragment);//2
        mFragments.add(editSceneFragment);//3
        mFragments.add(personalSettingsFragment);//4


        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

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

    @Override
    public void onBackPressed() {

        int currentPage = viewPager.getCurrentItem();
        if(currentPage == 4){//4 当前界面是个人中心的下一层
            setSelect(2);
            return;
        }else if(currentPage == 3){//4 当前界面是智能场景下一层
            setSelect(1);
            return;
        }
        super.onBackPressed();

    }

    private void initEvent() {
        // 设置事件
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

    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.devicesLayout:
            case R.id.devicesImgbtn:
            case R.id.devicesTextBtn:
                setSelect(0);
                break;
            case R.id.sceneLayout:
            case R.id.sceneImgBtn:
            case R.id.sceneTextBtn:
                setSelect(1);
                break;
            case R.id.personalLayout:
            case R.id.personalImgBtn:
            case R.id.personalTextBtn:
                setSelect(2);
                break;
            default:
                break;
        }
    }

    public void setSelect(int i) {
        System.out.println("liubin 111 MainActivity setSelect i =" + i);
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
        viewPager.setCurrentItem(i);
    }

    //将所有的图片都变暗
    private void resetImg() {
        devicesImage.setBackgroundResource(R.drawable.first);
        sceneImage.setBackgroundResource(R.drawable.second);
        personalImage.setBackgroundResource(R.drawable.personal_center);
    }
}
