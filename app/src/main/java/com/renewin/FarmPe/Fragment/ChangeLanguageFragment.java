package com.renewin.FarmPe.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.renewin.FarmPe.Activity.SelectLanguageActivity;
import com.renewin.FarmPe.Adapter.SelectLanguageAdapter;
import com.renewin.FarmPe.Bean.AgriBean;
import com.renewin.FarmPe.Bean.SelectLanguageBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Login_post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChangeLanguageFragment extends Fragment {
    private List<SelectLanguageBean> newOrderBeansList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SelectLanguageAdapter mAdapter;
    SessionManager sessionManager;
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout back_feed;

    TextView editText;
    private Context context;

    public static ChangeLanguageFragment newInstance() {
        ChangeLanguageFragment fragment = new ChangeLanguageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_language_layout, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        recyclerView =view.findViewById(R.id.recycler_view1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SelectLanguageAdapter(getActivity(), newOrderBeansList);
        recyclerView.setAdapter(mAdapter);
        Langauges();

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);


            }
        });

        return view;
    }

    private void Langauges() {
        try {
            newOrderBeansList.clear();
            JSONObject userRequestjsonObject = new JSONObject();
            JSONObject postjsonObject = new JSONObject();
            userRequestjsonObject.put("TalukId", 5495);
            postjsonObject.putOpt("Hobliobj", userRequestjsonObject);

            Login_post.login_posting(getActivity(), Urls.Languages,postjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssss"+result);
                    JSONObject jsonObject;
                    try {
                        JSONArray jsonArray=result.getJSONArray("LanguagesList");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String language=jsonObject1.getString("Language");
                            int langCode=jsonObject1.getInt("Id");
                            String langimg=jsonObject1.getString("ImageIcon");
                            SelectLanguageBean stateBean=new SelectLanguageBean(language,langCode,langimg);
                            newOrderBeansList.add(stateBean);
                            recyclerView.setAdapter(mAdapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

