package com.umasuo.eva.infra.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umasuo on 17/7/7.
 * 主界面的adapter，用于在main activity里面显示或替换fragment.
 */
public class MainPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();

    public MainPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public int add(Fragment fragment) {
        int index = mFragments.size();
        mFragments.add(fragment);
        return index;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int index) {
        return mFragments.get(index);
    }
}
