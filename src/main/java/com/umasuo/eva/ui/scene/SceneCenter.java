package com.umasuo.eva.ui.scene;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
 * Created by liubin8095 on 2017/7/1.
 */
public class SceneCenter extends FragmentRoot implements AdapterView.OnItemClickListener, View.OnClickListener {

    private static final String TAG = "SceneCenter";

    private ImageView sceneAddBtn;
    private ListView sceneList;
    public List<Map<String, Object>> mdata;

    private SceneEditor editorFragment;
    MainActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogControl.debug(TAG,"onCreate >>>");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogControl.debug(TAG,"onCreateView >>>");
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.scene, container, false);

        sceneList = (ListView) view.findViewById(R.id.scene_list);
        mdata = getData();
        SceneListAdapter sAdapter = new SceneListAdapter(getContext(), mdata);
        sceneList.setAdapter(sAdapter);
        sceneList.setOnItemClickListener(this);

        sceneAddBtn = (ImageView) view.findViewById(R.id.scene_add_btn);
        sceneAddBtn.setOnClickListener(this);

        activity = (MainActivity) getContext();
        return view;
    }

    @Override
    public void onResume() {
        LogControl.debug(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        LogControl.debug(TAG, "onAttach");
        super.onAttach(context);
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
//        map.put("sceneIcon", R.drawable.home);
//        map.put("name", "到家");
        map.put("model", new SceneModel("到家", R.drawable.home));
        list.add(map);

        map = new HashMap<String, Object>();
//        map.put("sceneIcon", R.drawable.leavehome);
//        map.put("name", "离家");
        map.put("model", new SceneModel("离家", R.drawable.leavehome));
        list.add(map);

        map = new HashMap<String, Object>();
//        map.put("sceneIcon", R.drawable.getup);
//        map.put("name", "起床");
        map.put("model", new SceneModel("起床", R.drawable.getup));
        list.add(map);

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        LogControl.debug(TAG, "item: " + i + ", id: " + view.findViewById(R.id.scene_left_icon).getId());
                editorFragment = new SceneEditor();
                Bundle bundle = new Bundle();

                HashMap<String, Object> item = (HashMap<String, Object>) mdata.get(i);
                bundle.putSerializable("model", (SceneModel) item.get("model"));
                editorFragment.setArguments(bundle);
                editorFragment.setPreIndex(index);
//                editorFragment.setIndex(activity.addFragment(editorFragment));
                editorFragment.setIndex(activity.getPagerSize());
            activity.showFragment(this,editorFragment,true);
//        activity.showPage(editorFragment.getIndex());

    }
}