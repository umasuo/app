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
import com.umasuo.eva.infra.log.LogControl;

import java.util.List;
import java.util.Map;

/**
 * Created on 2017/7/5.
 * 智能场景列表适配器，用于显示列表的内容.
 */
public class SceneListAdapter extends BaseAdapter {
    private String TAG = "SceneListAdapter";

    private LayoutInflater mInflater;
    private List<Map<String, Object>> mdata;

    public SceneListAdapter(Context context, List<Map<String, Object>> data) {
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
        SceneListAdapter.ViewHolder holder;
        if (view == null) {
            holder = new SceneListAdapter.ViewHolder();
            view = mInflater.inflate(R.layout.scene_item, null);
            holder.sceneIcon = (ImageView) view.findViewById(R.id.scene_left_icon);
            holder.name = (TextView) view.findViewById(R.id.scene_name);
            holder.actionBtn = (TextView) view.findViewById(R.id.scene_action_btn);
            holder.position = i;
            holder.actionBtn.setOnClickListener(new SceneListAdapter.SceneActionOnClickListener(holder));

            view.setTag(holder);

        } else {
            holder = (SceneListAdapter.ViewHolder) view.getTag();
        }
//        holder.sceneIcon.setBackgroundResource((Integer) mdata.get(i).get("sceneIcon"));
//        holder.name.setText((String) mdata.get(i).get("name"));

        SceneModel sModel = (SceneModel)(mdata.get(i).get("model"));
        if(sModel != null) {
            holder.sceneIcon.setBackgroundResource(sModel.getmSceneIconId());
            holder.name.setText(sModel.getmSceneName());
        }
        return view;
    }

    public static class ViewHolder {
        public ImageView sceneIcon;
        public TextView name;
        public TextView actionBtn;
        public int position;
    }


    /**
     * 用于执行某个场景.
     */
    public class SceneActionOnClickListener implements View.OnClickListener {
        private SceneListAdapter.ViewHolder mviewHolder;

        public SceneActionOnClickListener(SceneListAdapter.ViewHolder viewHolder) {
            mviewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            LogControl.debug(TAG, "action for scene: " + mviewHolder.position);
        }
    }
}