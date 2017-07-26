package com.umasuo.eva.ui.scene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * 场景条件设置界面
 */

public class SceneConditionSettings extends FragmentRoot implements View.OnClickListener{
    private static final String TAG = "SceneConditionSettings";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scene_condition_settings, container, false);
        Bundle bundle = getArguments();
        String current_condition = bundle.get("condition").toString();
        LogControl.debug(TAG,current_condition);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
