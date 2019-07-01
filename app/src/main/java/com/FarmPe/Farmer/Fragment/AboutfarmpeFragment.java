package com.FarmPe.Farmer.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.FarmPe.Farmer.Bean.AgriBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class AboutfarmpeFragment extends Fragment {
    Fragment selectedFragment;
    TextView first_text, second_text;
    LinearLayout back, more, whatsapp, insta, facebook, back_feed, twitter;
    public static String status;
    Intent intent;
    private ArrayAdapter<AgriBean> arrayAdapter;
    private ListView listView;
    String packageName;
    SessionManager sessionManager;

    public static String refer_code;
    JSONObject lngObject;
    TextView editText,privacypolicytxt,privacypolicytxt1,second_textttxt,privacypolicytxt2,privacypolicytxt3,second_t,second_tx;
    private Context context;

    public static AboutfarmpeFragment newInstance() {
        AboutfarmpeFragment fragment = new AboutfarmpeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.privacy_policy, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        privacypolicytxt=view.findViewById(R.id.toolbar_title);
        privacypolicytxt.setText("About FarmPe");
        privacypolicytxt1=view.findViewById(R.id.txt);
        privacypolicytxt1.setText("The main functionalities of the app are :");
        first_text=view.findViewById(R.id.first_text);
        second_text=view.findViewById(R.id.second_text);
        second_textttxt=view.findViewById(R.id.second_textt);
        privacypolicytxt2=view.findViewById(R.id.txtt);
        privacypolicytxt3=view.findViewById(R.id.tx);
        second_t=view.findViewById(R.id.second_te);
        second_tx=view.findViewById(R.id.second_tex);
        first_text.setVisibility(View.GONE);
        privacypolicytxt2.setVisibility(View.GONE);
        privacypolicytxt3.setVisibility(View.GONE);
        second_t.setVisibility(View.GONE);
        second_tx.setVisibility(View.GONE);
        sessionManager = new SessionManager(getActivity());
        second_textttxt.setText("FarmPe is an omnibus mobile app which provides valuable " +
                "and relevant information quickly to the farming community to make their way easier to generate" +
                "their connectivity and business with plenty of agriculture stakeholders." +
                "\nWe stand as pioneers when it comes to connect farms and farmers and committed to provide best services to farmers." +
                "\nFarmPe for a better tomorrow, looking forward to make farmers use their phones for business purposes," +
                "beyond just talking or texting, there is a lot that a mobile app is capable of doing.");
        second_text.setText("1. Get Tractors and tools information: \nBrand-wise,"+
                "Model-wise and HP-wise details are available, user can request for quotation on a finger-tip." +
                "\n2. Get Farms information such as Dairy, Poultry, Goat." +
                "\n3. Get Farmers information: \nConnect with farmers." +
                "\n4. Search and get information of nearby farms and farmers and connect with them for the quick transactions." +
                "\n5. Request for price quotation, Agriculture finance and Insurance.");

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
        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));
            privacypolicytxt.setText(lngObject.getString("AboutFarmPe"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}

