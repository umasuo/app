package com.umasuo.eva.infra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.log.LogControl;

import java.util.List;
import java.util.Map;

/**
 * Created by on 2017/7/7.
 * 个人中心界面适配器
 */

public class MessageAdapter extends BaseAdapter {

    private String TAG = "MessageAdapter";

    private LayoutInflater inflater;
    private List<Map<String, String>> data;
    private Context context;

    public MessageAdapter(Context context, List<Map<String, String>> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MessageAdapter.ViewHolder holder;
        if (view == null) {
            holder = new MessageAdapter.ViewHolder();
            view = inflater.inflate(R.layout.personal_message_item, null);
            holder.title = (TextView) view.findViewById(R.id.msg_title);
            holder.content = (TextView) view.findViewById(R.id.msg_content);
            holder.position = i;
            view.setTag(holder);
        } else {
            holder = (MessageAdapter.ViewHolder) view.getTag();
        }
        holder.title.setText(data.get(i).get("title"));
        holder.content.setText(data.get(i).get("content"));
        return view;
    }

    public static class ViewHolder {
        public TextView title;
        public TextView content;
        public int position;
    }

    public class MyOnClickListener implements View.OnClickListener {
        private MessageAdapter.ViewHolder mviewHolder;

        public MyOnClickListener(MessageAdapter.ViewHolder viewHolder) {
            mviewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            LogControl.debug(TAG, "position = " + mviewHolder.position);
//            ((MainActivity) context).showPage(3);
        }
    }
}
