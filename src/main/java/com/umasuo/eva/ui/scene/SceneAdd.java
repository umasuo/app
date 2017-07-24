package com.umasuo.eva.ui.scene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created on 2017/7/5.
 * 智能场景中的编辑场景和新建场景界面
 */

public class SceneAdd extends FragmentRoot implements View.OnClickListener, AdapterView.OnItemClickListener {
    private String TAG = "SceneAdd";

    private ImageView editor_back;
    private ImageView scene_editor_icon;
    private TextView scene_editor_title;
    private ImageView editor_add_condition;
    private TextView editor_add_condition_title;
    private ImageView editor_condition_icon;
    private TextView editor_current_condition;
    private ImageView editor_change_condition;
    private ImageView editor_add_task;
    private TextView editor_current_task;
    private ListView editor_list_task;

//    SceneModel sModel;

    private MainActivity mainActivity;
    Bundle bundle;
    private SceneCondition sceneAdd;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogControl.debug(TAG, "onCreate >>>");

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogControl.debug(TAG, "onCreateView >>> ");
        View view = inflater.inflate(R.layout.scene_editor, container, false);
        mainActivity = (MainActivity) getActivity();

        initView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogControl.debug(TAG, "onStart >>>");
    }

    private void initView(View view) {
        editor_back = (ImageView) view.findViewById(R.id.editor_back);
        editor_back.setOnClickListener(this);
        scene_editor_icon = (ImageView) view.findViewById(R.id.scene_editor_icon);
        scene_editor_title = (TextView) view.findViewById(R.id.scene_editor_title);
        scene_editor_icon.setBackgroundResource(R.drawable.scene_icon_default);
        scene_editor_title.setText("新建场景");


        editor_add_condition = (ImageView) view.findViewById(R.id.editor_add_condition);
        editor_add_condition.setOnClickListener(this);

        editor_condition_icon = (ImageView) view.findViewById(R.id.editor_condition_icon);
        editor_current_condition = (TextView) view.findViewById(R.id.editor_current_condition);
        editor_change_condition = (ImageView) view.findViewById(R.id.editor_change_condition);
        editor_change_condition.setOnClickListener(this);

        editor_add_task = (ImageView) view.findViewById(R.id.editor_add_task);
        editor_add_task.setOnClickListener(this);

        editor_current_task = (TextView) view.findViewById(R.id.editor_current_task);
        editor_list_task = (ListView) view.findViewById(R.id.editor_list_task);
        editor_list_task.setOnItemClickListener(this);

        onShow();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editor_back:
                mainActivity.getSupportFragmentManager().popBackStack();
                break;
            case R.id.editor_add_condition:
                if (sceneAdd == null) {
                    sceneAdd = new SceneCondition();
                    sceneAdd.setPreIndex(index);
                    sceneAdd.setIndex(mainActivity.getPagerSize());
                }
                mainActivity.showFragment(this, sceneAdd, true);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
