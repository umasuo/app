package com.umasuo.eva.infra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.R;

import java.util.List;
import java.util.Map;

/**
 * 我的设备界面的设备列表adapter.
 */
public class DeviceListAdapter extends BaseAdapter {
    private String TAG = "DeviceListAdapter";

    private LayoutInflater inflater;
    private List<Map<String, Object>> data;
    private Context context;

    public DeviceListAdapter(Context context, List<Map<String, Object>> data) {
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
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.device_item, null);
            holder.icon = (ImageView) view.findViewById(R.id.device_icon);
            holder.name = (TextView) view.findViewById(R.id.device_name);
            holder.position = i;
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.icon.setBackgroundResource((Integer) data.get(i).get("icon"));
        holder.name.setText((String) data.get(i).get("name"));
        return view;
    }

    public static class ViewHolder {
        public ImageView icon;
        public TextView name;
        public int position;
    }

}


