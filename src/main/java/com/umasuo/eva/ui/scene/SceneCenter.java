package com.umasuo.eva.ui.scene;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.adapter.SceneListAdapter;
import com.umasuo.eva.infra.log.LogControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liubin8095 on 2017/7/1.
 */
public class SceneCenter extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private static final String TAG = "SceneCenter";

    private ImageView main_title_icon;
    private ImageView sceneAddBtn;
    private ListView sceneList;
    public List<Map<String, Object>> mdata;

    private SceneEditor editorFragment;
    private int editorIndex;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.scene, container, false);

        sceneList = (ListView) view.findViewById(R.id.scene_list);
        mdata = getData();
        SceneListAdapter sAdapter = new SceneListAdapter(getContext(), mdata);
        sceneList.setAdapter(sAdapter);
        sceneList.setOnItemClickListener(this);

        sceneAddBtn = (ImageView) view.findViewById(R.id.scene_add_btn);
        sceneAddBtn.setOnClickListener(this);
        return view;
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
            case R.id.scene_add_btn:
                LogControl.debug(TAG, "add new  scene");
                break;
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
        map.put("sceneIcon", R.drawable.home);
        map.put("name", "到家");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("sceneIcon", R.drawable.leavehome);
        map.put("name", "离家");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("sceneIcon", R.drawable.getup);
        map.put("name", "起床");
        list.add(map);

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MainActivity activity = (MainActivity) getContext();

        LogControl.debug(TAG, "item: " + i + ", id: " + view.findViewById(R.id.scene_left_icon).getId());

        if (editorFragment == null) {
            editorFragment = new SceneEditor();
            editorIndex = activity.addFragment(editorFragment);
        }

        editorFragment.setCurSceneId(mdata.get(i).get("sceneIcon").toString());
        activity.showFragment(editorIndex);
    }
}