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
 * Created by on 2017/7/7.
 * 个人中心界面适配器
 */

public class PersonalAdapter extends BaseAdapter {

    private String TAG = "PersonalAdapter";

    private LayoutInflater inflater;
    private List<Map<String, Object>> data;
    private Context mcontext;

    public PersonalAdapter(Context context, List<Map<String, Object>> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.mcontext = context;
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
        PersonalAdapter.ViewHolder holder;
        if (view == null) {
            holder = new PersonalAdapter.ViewHolder();
            view = inflater.inflate(R.layout.personal_item, null);
            holder.img_left = (ImageView) view.findViewById(R.id.personal_left_icon);
            holder.name = (TextView) view.findViewById(R.id.personal_name);
            holder.img_right = (ImageView) view.findViewById(R.id.personal_right_arrow);
            holder.position = i;
            view.setTag(holder);
        } else {
            holder = (PersonalAdapter.ViewHolder) view.getTag();
        }
        holder.img_left.setBackgroundResource((Integer) data.get(i).get("icon"));
        holder.name.setText((String) data.get(i).get("name"));
        return view;
    }

    public static class ViewHolder {
        public ImageView img_left;
        public TextView name;
        public ImageView img_right;
        public int position;
    }

    public class MyOnClickListener implements View.OnClickListener {
        private PersonalAdapter.ViewHolder mviewHolder;

        public MyOnClickListener(PersonalAdapter.ViewHolder viewHolder) {
            mviewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            LogControl.debug(TAG, "position = " + mviewHolder.position);
            ((MainActivity) mcontext).setSelect(3);
        }
    }
}
