package com.umasuo.eva.scene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.R;

/**
 * Created  on 2017/7/5.
 * 智能场景中的编辑场景和新建场景界面
 */

public class SceneEditor extends Fragment {

    /**
     * 当前在编辑的场景的ID.
     */
    private String curSceneId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scene_editor, container, false);

        return view;
    }


    /**
     * 进入scene editor 之前需要设置当前需要编辑的场景
     *
     * @param sceneId
     */
    public void setCurSceneId(String sceneId) {
        this.curSceneId = sceneId;
    }
}
