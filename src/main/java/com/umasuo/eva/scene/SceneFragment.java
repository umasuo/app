package com.umasuo.eva.scene;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.umasuo.eva.R;
import com.umasuo.eva.tools.adapter.SceneAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liubin8095 on 2017/7/1.
 */

public class SceneFragment extends Fragment {

    private ImageView main_title_icon;
    private TextView main_title;
    private ImageView main_add;
    private ListView scene_list;
    public List<Map<String ,Object>> mdata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.scene, container,false);
        main_title_icon = (ImageView) view.findViewById(R.id.main_title_icon);
        main_title_icon.setVisibility(View.GONE);

        main_title = (TextView)view.findViewById(R.id.main_title);
        main_title.setText(R.string.intelligentScene);

        scene_list = (ListView)view.findViewById(R.id.scene_list);
        SceneAdapter sAdapter = new SceneAdapter(getContext(),getData());
        scene_list.setAdapter(sAdapter);

        return view;
    }

    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();
        map.put("img_left", R.drawable.home);
        map.put("title", "����");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img_left", R.drawable.leavehome);
        map.put("title", "���");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img_left", R.drawable.getup);
        map.put("title", "��");
        list.add(map);

        return list;
    }

}