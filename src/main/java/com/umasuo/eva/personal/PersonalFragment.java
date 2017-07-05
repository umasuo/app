package com.umasuo.eva.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.R;


public class PersonalFragment extends Fragment {
    private ImageView main_title_icon;
    private TextView main_title;
    private ImageView main_add;
    private TextView main_title_right;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.devices_layout, container,false);
        main_title_icon = (ImageView) view.findViewById(R.id.main_title_icon);
        main_title_icon.setVisibility(View.GONE);

        main_title = (TextView)view.findViewById(R.id.main_title);
        main_title.setText(R.string.personal_center);

        main_add = (ImageView) view.findViewById(R.id.devices_add);
        main_add.setVisibility(View.GONE);

        main_title_right = (TextView) view.findViewById(R.id.main_title_right);
        main_title_right.setVisibility(View.VISIBLE);


        return view;
    }
}