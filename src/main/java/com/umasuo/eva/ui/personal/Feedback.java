package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.domain.feedback.dto.FeedbackModel;
import com.umasuo.eva.domain.scene.dto.SceneModel;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.adapter.FeedbackListAdapter;
import com.umasuo.eva.infra.log.LogControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by umasuo on 17/7/7.
 * 意见反馈.
 */
public class Feedback extends FragmentRoot implements AdapterView.OnItemClickListener {

    private static final String TAG = "Feedback";

    private MainActivity mainActivity;

    private ListView contents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback, container, false);

        contents = (ListView) view.findViewById(R.id.feedback_content);
        contents.setAdapter(new FeedbackListAdapter(getContext(), getData()));
        contents.setOnItemClickListener(this);

        mainActivity = (MainActivity) getContext();

        return view;
    }

    /**
     * 每次这个显示的时候会调用.
     */
    @Override
    public void onShow() {
        //todo 拉取用户反馈信息
        mainActivity.hideBottom();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        LogControl.debug(TAG, "Click: " + i);
    }


    /**
     * 假数据
     *
     * @return
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();

        FeedbackModel feedbackModel = new FeedbackModel();
        feedbackModel.setTitle("这个反馈不行啊");

        map.put("model", feedbackModel);
        list.add(map);


        return list;
    }
}
