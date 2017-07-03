package com.umasuo.eva.evaapp;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.umasuo.eva.evaapp.adapter.SettingAdapter;
import com.umasuo.eva.evaapp.log.LogControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SettingsFragmentOne extends Fragment {
    private String TAG = "SettingsFragmentOne";

    private ListView msetting_list;
    public List<Map<String ,Object>> mdata;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.settings_one_layout, container,false);
        msetting_list = view.findViewById(R.id.settings_list);
        mdata = getData();
        SettingAdapter sadapter = new SettingAdapter(getContext(),mdata);
        msetting_list.setAdapter(sadapter);



        return view;
    }


    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();
        map.put("img_left", R.drawable.xyj);
        map.put("title", "洗衣机");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img_left", R.drawable.kt);
        map.put("title", "空调");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img_left", R.drawable.wbl);
        map.put("title", "微波炉");
        list.add(map);

        return list;
    }


}