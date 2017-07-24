package com.umasuo.eva.ui.scene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.domain.scene.dto.SceneModel;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.adapter.SceneConditionAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 选择条件界面
 */

public class SceneCondition extends FragmentRoot implements AdapterView.OnItemClickListener, View.OnClickListener {
    private String TAG = "SceneCondition";

    private View view;
    private List<Map<String, Object>> data;
    private ListView condition_list;
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.scene_condition, container, false);
        view.findViewById(R.id.condition_back).setOnClickListener(this);
        condition_list = view.findViewById(R.id.scene_condition_list);
        activity = (MainActivity) getActivity();

        data = getData();
        SceneConditionAdapter conditionAdapter = new SceneConditionAdapter(getContext(), data);
        condition_list.setAdapter(conditionAdapter);
        condition_list.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.condition_back:
                activity.popBackStack();
                break;
        }

    }

    /**
     * 此数据会根据设备可提供的数据而改变
     *
     * @return
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();
        map.put("condition", "温度");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("condition", "湿度");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("condition", "天气");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("condition", "PM2.5");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("condition", "空气质量");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("condition", "日落日出");
        list.add(map);


        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
