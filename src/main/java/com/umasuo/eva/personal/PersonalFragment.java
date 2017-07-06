package com.umasuo.eva.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.tools.adapter.PersonalAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心界面
 */

public class PersonalFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView personal_listview;
    public List<Map<String ,Object>> mdata;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.personal_layout, container, false);
        personal_listview = view.findViewById(R.id.personal_listview);
        PersonalAdapter pAdapter = new PersonalAdapter(getContext(),getData());
        personal_listview.setAdapter(pAdapter);
        personal_listview.setOnItemClickListener(this);


        return view;
    }

    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.home);
        map.put("name", "消息中心");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.leavehome);
        map.put("name", "扫一扫");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.getup);
        map.put("name", "常见问题");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.getup);
        map.put("name", "意见反馈");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.getup);
        map.put("name", "关于");
        list.add(map);

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ((MainActivity)getContext()).setSelect(4);

    }
}