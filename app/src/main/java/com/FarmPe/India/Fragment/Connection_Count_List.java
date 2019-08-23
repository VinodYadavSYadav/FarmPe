package com.FarmPe.India.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.India.Adapter.InvitationLeadAdapter;
import com.FarmPe.India.Bean.ConnectionBean;
import com.FarmPe.India.R;

import java.util.ArrayList;
import java.util.List;

public class Connection_Count_List extends Fragment {

    TextView invite_text,conn_count;
    LinearLayout back_feed,main_layout;
    Fragment selectedFragment;


    public static Connection_Count_List newInstance() {
        Connection_Count_List fragment = new Connection_Count_List();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.connection_count_list, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        main_layout=view.findViewById(R.id.main_layout);
        invite_text=view.findViewById(R.id.invite_text);
        conn_count=view.findViewById(R.id.conn_count);

        invite_text.setText("Invite your contacts to connect on FarmPe. We will import your address book to suggest connections and help you manage your contacts.");

        conn_count.setText(HomeMenuFragment.conn_count.getText().toString());
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

             /*   selectedFragment = HomeMenuFragment.newInstance();
                HomeMenuFragment.drawer.openDrawer(Gravity.START);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();*/



            }
        });



        return view;
    }


}
