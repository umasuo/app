package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created by umasuo on 17/7/7.
 * 软件的关于界面.
 */
public class About extends FragmentRoot implements View.OnClickListener {

    private String TAG = "About";
    private WebView webView;
    private TextView closeBtn;

    private MainActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogControl.debug(TAG,"onCreateView >>>");
        View view = inflater.inflate(R.layout.about, container, false);

        webView = (WebView) view.findViewById(R.id.web_about_content_viewer);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.umasuo.com");

        closeBtn = (TextView) view.findViewById(R.id.close_page);
        closeBtn.setOnClickListener(this);

        activity = (MainActivity) getContext();


        return view;
    }

    @Override
    public void onShow() {
        activity.hideBottom();
    }

    @Override
    public void onClick(View view) {
        activity.showPage(preIndex);
    }
}
