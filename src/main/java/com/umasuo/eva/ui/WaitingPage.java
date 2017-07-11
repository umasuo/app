package com.umasuo.eva.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created by liubin8095 on 2017/7/1.
 * 等待页
 */
public class WaitingPage extends FragmentRoot {

    private static final String TAG = "WaitingPage";

    MainActivity activity;
    private ImageView waitingImg;
    Animation mRefreshAnim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        LogControl.debug("DeviceFragment", "on create.");
        View view = inflater.inflate(R.layout.waiting, container, false);
        waitingImg = (ImageView) view.findViewById(R.id.waiting_img);

        mRefreshAnim = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotate_refresh);

        activity = (MainActivity) getContext();
        onShow();
        return view;
    }

    @Override
    public void onShow() {
        activity.hideBottom();
        mRefreshAnim.reset();
        waitingImg.clearAnimation();
        waitingImg.startAnimation(mRefreshAnim);
    }
}