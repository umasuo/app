package com.umasuo.eva.infra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.R;
import com.umasuo.eva.domain.feedback.dto.FeedbackModel;

import java.util.List;
import java.util.Map;

/**
 * Created on 2017/7/11.
 * 用户反馈。
 */
public class FeedbackListAdapter extends BaseAdapter {
    private String TAG = "FeedbackListAdapter";

    private LayoutInflater mInflater;
    private List<Map<String, Object>> mdata;

    public FeedbackListAdapter(Context context, List<Map<String, Object>> data) {
        mInflater = LayoutInflater.from(context);
        mdata = data;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * 根据
     *
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FeedbackListAdapter.ViewHolder holder;
        if (view == null) {
            holder = new FeedbackListAdapter.ViewHolder();
            view = mInflater.inflate(R.layout.feedback_item, null);
            holder.icon = (ImageView) view.findViewById(R.id.icon);
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.date = (TextView) view.findViewById(R.id.date);
            holder.status = (TextView) view.findViewById(R.id.status);
            holder.position = i;
            view.setTag(holder);

        } else {
            holder = (FeedbackListAdapter.ViewHolder) view.getTag();
        }

        FeedbackModel sModel = (FeedbackModel) (mdata.get(i).get("model"));
        if (sModel != null) {
            // todo 根据不同类型的反馈显示不同的icon
            holder.title.setText(sModel.getTitle());
        }
        return view;
    }

    public static class ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView date;
        public TextView status;
        public int position;
    }

}