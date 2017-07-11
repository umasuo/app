package com.umasuo.eva.domain.scene.dto;

import java.io.Serializable;

/**
 * Created on 2017/7/10.
 */
public class SceneModel implements Serializable {

    private static final long serialVersionUID = -3294439470132569152L;

    private String mSceneName;
    private int mSceneIconId;

    public SceneModel() {
    }

    public SceneModel(String sceneName, int sceneIconId) {
        mSceneName = sceneName;
        mSceneIconId = sceneIconId;
    }

    public String getmSceneName() {
        return mSceneName;
    }

    public void setmSceneName(String mSceneName) {
        this.mSceneName = mSceneName;
    }

    public int getmSceneIconId() {
        return mSceneIconId;
    }

    public void setmSceneIconId(int mSceneIconId) {
        this.mSceneIconId = mSceneIconId;
    }

    @Override
    public String toString() {
        return "SceneModel{" +
                "mSceneName='" + mSceneName + '\'' +
                ", mSceneIconId=" + mSceneIconId +
                '}';
    }
}
