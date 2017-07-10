package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created by umasuo on 17/7/7.
 * 常见问题。用一个web view显示即可.
 */
public class FAQ extends FragmentRoot {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faq, container, false);
        return view;
    }
}
