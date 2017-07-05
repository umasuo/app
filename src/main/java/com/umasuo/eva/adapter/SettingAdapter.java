package com.umasuo.eva.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.R;
import com.umasuo.eva.SettingsActivity;
import com.umasuo.eva.log.LogControl;

import java.util.List;
import java.util.Map;


public class SettingAdapter extends BaseAdapter {
    private String TAG = "SettingAdapter";

    private LayoutInflater mInflater;
    private List<Map<String,Object>> mdata;
    private Context mcontext;

    public SettingAdapter(Context context,List<Map<String,Object>> data){
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
        if(view == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.settingadapteritem,null);
            holder.img_left = (ImageView) view.findViewById(R.id.imgLeft);
            holder.text = (TextView) view.findViewById(R.id.text_center);
            holder.img_right = (ImageView) view.findViewById(R.id.imgRight);
            holder.postion = i;
            holder.img_right.setOnClickListener(new MyOnClickListener(holder));
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.img_left.setBackgroundResource((Integer) mdata.get(i).get("img_left"));
        holder.text.setText((String)mdata.get(i).get("title"));

        return view;
    }

    public static class ViewHolder{
        public ImageView img_left;
        public TextView text;
        public ImageView img_right;
        public int postion;
    }


    public class MyOnClickListener implements View.OnClickListener{
        private ViewHolder mviewHolder;

        public MyOnClickListener(SettingAdapter.ViewHolder viewHolder){
            mviewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            LogControl.Print_D(TAG,"position = "+mviewHolder.postion);
            ((SettingsActivity)mcontext).changeCurrentItem(1);
        }
    }
}


