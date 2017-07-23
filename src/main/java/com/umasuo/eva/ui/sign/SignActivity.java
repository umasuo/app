package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录、或者注册用的activity.
 */
public class SignActivity extends FragmentActivity {

    private static final String TAG = "SignActivity";

    private SignPager signPager;
    private FragmentPagerAdapter signAdapter;

    //登录初始界面、用来选择是登录还是注册。
    SignInStarter signinStarter;

    SignInWithPassword signinWithPassword;

    private List<FragmentRoot> pages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        LogControl.debug(TAG, "SignActivity onCreate >>");

        signinStarter = new SignInStarter();
        signinStarter.setPreIndex(0);

        pages.add(signinStarter);


//        signinWithPassword = new SignInWithPassword();
//        signinWithPassword.setPreIndex(0);
//        signinWithPassword.setIndex(this.addFragment(signinWithPassword));
//        pages.add(signinWithPassword);


        signAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return pages.get(position);
            }

            @Override
            public int getCount() {
                return pages.size();
            }
        };

        signPager = (SignPager) findViewById(R.id.sign_pager);
        signPager.setAdapter(signAdapter);

        //显示登录初始界面
        signPager.setCurrentItem(0);
    }

    /**
     * 切换片段.
     *
     * @param i
     */
    public void replaceFragment(int i) {
        //显示登录初始界面
        signPager.setCurrentItem(i);
    }

    /**
     * 添加一个新页,只有添加，没有删除.
     *
     * @param fragment Fragment
     * @return 当前fragment在列表中的index
     */
    public int addFragment(FragmentRoot fragment) {
        int index = this.pages.size();
        pages.add(fragment);
        signAdapter.notifyDataSetChanged();
        return index;
    }

    /**
     * 响应菜单的回退点击事件.
     */
    @Override
    public void onBackPressed() {
        LogControl.debug(TAG, "pressed back button");
        int curIndex = signPager.getCurrentItem();
        FragmentRoot curFrag = pages.get(curIndex);
        signPager.setCurrentItem(curFrag.getPreIndex());
        // TODO: 17/7/10 如果是最开始的界面了，那么就关闭程序
    }

}
