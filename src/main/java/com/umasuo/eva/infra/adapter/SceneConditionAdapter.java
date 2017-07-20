package com.umasuo.eva.infra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.R;
import com.umasuo.eva.domain.scene.dto.SceneModel;

import java.util.List;
import java.util.Map;

/**
 * 编辑场景中的条件添加界面List
 */

public class SceneConditionAdapter extends BaseAdapter {
    private String TAG = "SceneListAdapter";

    private LayoutInflater mInflater;
    private List<Map<String, Object>> mdata;

    public SceneConditionAdapter(Context context, List<Map<String, Object>> data) {
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SceneConditionAdapter.ViewHolder holder;
        if (view == null) {
            holder = new SceneConditionAdapter.ViewHolder();
            view = mInflater.inflate(R.layout.condition_add_item, null);
            holder.condition_name = (TextView) view.findViewById(R.id.condition_name);
            holder.condition_state = (TextView) view.findViewById(R.id.condition_state);
            holder.condition_right_icon = view.findViewById(R.id.condition_right_icon);
            holder.position = i;
            view.setTag(holder);

        } else {
            holder = (SceneConditionAdapter.ViewHolder) view.getTag();
        }

        holder.condition_name.setText(mdata.get(i).get("condition").toString());
        return view;
    }

    public static class ViewHolder {
        public TextView condition_name;
        public TextView condition_state;
        public ImageView condition_right_icon;
        public int position;
    }
}
