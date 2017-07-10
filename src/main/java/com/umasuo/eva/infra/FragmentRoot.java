package com.umasuo.eva.infra;

import android.support.v4.app.Fragment;

/**
 * Created by umasuo on 17/7/10.
 */

public abstract class FragmentRoot extends Fragment {

    /**
     * 前一个界面的index
     */
    public int preIndex;

    /**
     * 自己当前的index.
     */
    public int index;

    public void onShow() {
    }

    public int getPreIndex() {
        return preIndex;
    }

    public void setPreIndex(int preIndex) {
        this.preIndex = preIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
