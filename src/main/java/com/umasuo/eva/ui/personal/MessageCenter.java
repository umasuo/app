package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by umasuo on 17/7/7.
 * 个人消息中心
 */
public class MessageCenter extends FragmentRoot implements View.OnClickListener {
    private static final String TAG = "MessageCenter";

    private MainActivity mainActivity;

    private ImageView backImage;

    private ListView messageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_message, container, false);
        mainActivity = (MainActivity) getContext();

        backImage = (ImageView) view.findViewById(R.id.back);
        backImage.setOnClickListener(this);

        messageList = (ListView) view.findViewById(R.id.message_list);
        messageList.setAdapter(new MessageAdapter(mainActivity, getData()));

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                mainActivity.popBackStack();
                break;
            }
        }
    }

    public List<Map<String, String>> getData() {
        List<Map<String, String>> data = new ArrayList<>();

        Map<String, String> msg1 = new HashMap<>();
        msg1.put("title", "新版发布");
        msg1.put("content", "新版更新了重大功能！提供了室内VR效果体验...");

        data.add(msg1);

        return data;
    }
}