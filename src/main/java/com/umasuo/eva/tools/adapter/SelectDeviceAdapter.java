package com.umasuo.eva.tools.adapter;

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
 * 选择设备类型时的adapter
 */
public class SelectDeviceAdapter extends BaseAdapter {
    private String TAG = "SelectDeviceAdapter";

    private LayoutInflater mInflater;
    private List<Map<String, Object>> mdata;
    private Context mcontext;

    public SelectDeviceAdapter(Context context, List<Map<String, Object>> data) {
        mInflater = LayoutInflater.from(context);
        mdata = data;
        mcontext = context;
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
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.device_type_item, null);
            holder.img_left = (ImageView) view.findViewById(R.id.device_type_icon);
            holder.text = (TextView) view.findViewById(R.id.device_type_name);
            holder.img_right = (ImageView) view.findViewById(R.id.device_type_arrow);
            holder.position = i;
//            holder.img_right.setOnClickListener(new MyOnClickListener(holder));
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.img_left.setBackgroundResource((Integer) mdata.get(i).get("img_left"));
        holder.text.setText((String) mdata.get(i).get("title"));
        return view;
    }

    public static class ViewHolder {
        public ImageView img_left;
        public TextView text;
        public ImageView img_right;
        public int position;
    }

}


