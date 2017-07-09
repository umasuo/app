package com.umasuo.eva.ui.scene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.domain.user.dto.SceneModel;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created on 2017/7/5.
 * 智能场景中的编辑场景和新建场景界面
 */

public class SceneEditor extends Fragment implements View.OnClickListener{
    private String TAG="SceneEditor";

    /**
     * 当前在编辑的场景的ID.
     */
    private String curSceneId;
    private ImageView editor_back;
    View view;
    private MainActivity mActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogControl.debug(TAG,"onCreate");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogControl.debug(TAG,"onCreateView");

        view = inflater.inflate(R.layout.scene_editor, container, false);
        editor_back = (ImageView) view.findViewById(R.id.editor_back);
        editor_back.setOnClickListener(this);

        mActivity = (MainActivity) getActivity();

        Bundle bundle = getArguments();
        SceneModel sModel = (SceneModel) bundle.get("model");
        LogControl.debug(TAG,sModel.getmSceneName());


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogControl.debug(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.editor_back:
                mActivity.getSupportFragmentManager().popBackStack();
                break;
        }
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
