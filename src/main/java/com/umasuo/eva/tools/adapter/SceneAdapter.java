package com.umasuo.eva.tools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.tools.log.LogControl;

import java.util.List;
import java.util.Map;

/**
 * Created on 2017/7/5.  智能场景界面适配器
 */

public class SceneAdapter extends BaseAdapter {
    private String TAG = "SceneAdapter";

    private LayoutInflater mInflater;
    private List<Map<String, Object>> mdata;
    private Context mcontext;

    public SceneAdapter(Context context, List<Map<String, Object>> data) {
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
        SceneAdapter.ViewHolder holder;
        if (view == null) {
            holder = new SceneAdapter.ViewHolder();
            view = mInflater.inflate(R.layout.scene_adapter_item, null);
            holder.img_left = (ImageView) view.findViewById(R.id.imgLeft);
            holder.text = (TextView) view.findViewById(R.id.text_center);
            holder.img_right = (ImageView) view.findViewById(R.id.imgRight);
            holder.position = i;
            holder.img_right.setOnClickListener(new SceneAdapter.MyOnClickListener(holder));
            view.setTag(holder);
        } else {
            holder = (SceneAdapter.ViewHolder) view.getTag();
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


    public class MyOnClickListener implements View.OnClickListener {
        private SceneAdapter.ViewHolder mviewHolder;

        public MyOnClickListener(SceneAdapter.ViewHolder viewHolder) {
            mviewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            LogControl.debug(TAG, "position = " + mviewHolder.position);
            ((MainActivity) mcontext).setSelect(3);
        }
    }
}