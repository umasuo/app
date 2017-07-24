package com.umasuo.eva.ui.scene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.domain.scene.dto.SceneModel;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.adapter.SceneListAdapter;
import com.umasuo.eva.infra.log.LogControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 智能场景
 */
public class SceneCenter extends FragmentRoot implements AdapterView.OnItemClickListener, View.OnClickListener {

    private static final String TAG = "SceneCenter";

    private ImageView addBtn;
    private ListView sceneList;
    public List<Map<String, Object>> data;

    private SceneEditor editorFragment;
    private SceneAdd sceneAdd;
    private MainActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogControl.debug(TAG, "onCreate >>>");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogControl.debug(TAG, "onCreateView >>>");
        View view = inflater.inflate(R.layout.scene, container, false);
        activity = (MainActivity) getContext();

        sceneList = (ListView) view.findViewById(R.id.scene_list);
        data = getData();
        SceneListAdapter sAdapter = new SceneListAdapter(getContext(), data);
        sceneList.setAdapter(sAdapter);
        sceneList.setOnItemClickListener(this);

        addBtn = (ImageView) view.findViewById(R.id.add_btn);
        addBtn.setOnClickListener(this);


        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        LogControl.debug(TAG, "onHiddenChanged hidden = " + hidden);
        super.onHiddenChanged(hidden);
        if (hidden) {
            activity.hideBottom();
        } else {
            activity.showBottom();
        }
    }

    /**
     * 用于响应新建场景的按钮事件.
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        LogControl.debug(TAG, "click: " + view.getId());
        switch (view.getId()) {
            case R.id.add_btn: {
                if (sceneAdd == null) {
                    sceneAdd = new SceneAdd();
                    sceneAdd.setIndex(activity.getPagerSize());
                    sceneAdd.setPreIndex(index);
                }
                activity.showFragment(this, sceneAdd, true);
                break;
            }
        }
    }

    /**
     * 所有场景的数据，一部分从数据库读取，一部分写死，一部分从服务器读取.
     *
     * @return
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();
        map.put("model", new SceneModel("到家", R.drawable.scene_icon_home));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new SceneModel("离家", R.drawable.scene_icon_leave));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new SceneModel("起床", R.drawable.scene_icon_getup));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new SceneModel("休息", R.drawable.scene_icon_sleep));
        list.add(map);

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        LogControl.debug(TAG, "item: " + i + ", id: " + view.findViewById(R.id.scene_icon).getId());
        if (editorFragment == null) {
            editorFragment = new SceneEditor();
            editorFragment.setPreIndex(index);
            editorFragment.setIndex(activity.getPagerSize());
        }
        Bundle bundle = new Bundle();
        HashMap<String, Object> item = (HashMap<String, Object>) data.get(i);
        bundle.putSerializable("model", (SceneModel) item.get("model"));
        editorFragment.setArguments(bundle);

        activity.showFragment(this, editorFragment, true);

    }
}