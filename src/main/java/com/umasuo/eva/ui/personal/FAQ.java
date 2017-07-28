package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created by umasuo on 17/7/7.
 * 常见问题。用一个web view显示即可.
 */
public class FAQ extends FragmentRoot implements View.OnClickListener {

    private WebView webView;
    private TextView closeBtn;

    private MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faq, container, false);

        webView = (WebView) view.findViewById(R.id.web_faq_content_viewer);
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
        activity.popBackStack();
    }
}
