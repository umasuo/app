package com.umasuo.eva.domain.feedback.service;

import android.content.Context;

/**
 * Created by umasuo on 17/7/22.
 */

public class FeedbackService {

    private static final String TAG = "FeedbackService";

    /**
     * 开发者注册后，发行设备时指定具体的developerID.
     */
    private static final String developerId = "developer1";

    private static FeedbackService instance;

    private Context context;

    public static FeedbackService getInstance() {
        return instance;
    }

}
