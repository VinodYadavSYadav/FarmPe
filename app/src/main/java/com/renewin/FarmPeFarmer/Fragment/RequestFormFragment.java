package com.renewin.FarmPeFarmer.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.renewin.FarmPeFarmer.Adapter.AddFirstAdapter;
import com.renewin.FarmPeFarmer.Adapter.AddHpAdapter;
import com.renewin.FarmPeFarmer.Adapter.AddModelAdapter;
import com.renewin.FarmPeFarmer.Bean.FarmsImageBean;
import com.renewin.FarmPeFarmer.R;
import com.renewin.FarmPeFarmer.SessionManager;
import com.renewin.FarmPeFarmer.Urls;
import com.renewin.FarmPeFarmer.Volly_class.Login_post;
import com.renewin.FarmPeFarmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestFormFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static AddHpAdapter farmadapter;
    TextView toolbar_title,request,address_text;
    Fragment selectedFragment;
    RadioGroup radioGroup,radioGroup_finance;
    RadioButton radioButton,finance_yes,finance_no,radioButton1;
    LinearLayout back_feed,address_layout;
    CheckBox check_box;
    SessionManager sessionManager;
    View view;
    String addId;
    String time_period;
    boolean finance;
    String finance_status;
    public static int selectedId,selectedId_time_recent;
    int finance_selected,time_selected;
    public static RequestFormFragment newInstance() {
        RequestFormFragment fragment = new RequestFormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.request_form, container, false);
        // recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        check_box=view.findViewById(R.id.check_box);
        address_layout=view.findViewById(R.id.address_layout);
        radioGroup=view.findViewById(R.id.radio_group_time);
        radioGroup_finance=view.findViewById(R.id.radioGroup_finance);
        //finance_yes=view.findViewById(R.id.finance_yes);
        //finance_no=view.findViewById(R.id.finance_no);
        request=view.findViewById(R.id.request);
        address_text=view.findViewById(R.id.address_text);
        toolbar_title.setText("Request for Quotation");

        sessionManager=new SessionManager(getActivity());

        Bundle bundle=getArguments();
        if (bundle==null){

        }else{
            finance_selected=bundle.getInt("selected_id2");
            time_selected=bundle.getInt("selected_id_time1");
            System.out.println("tiiiiimmmee"+time_selected);
            addId=bundle.getString("add_id");
            String city=bundle.getString("city");
            address_text.setText(city);
            radioGroup.check(bundle.getInt("selected_id_time1"));
            radioGroup_finance.check(finance_selected);


        }

        // String addId=bundle.getString("add_id");

        check_box.setText("I agree that by clicking 'Request for Tractor Price' button, I am explicitly soliciting a call from FarmPe Farmer App users on my 'Mobile' in order to assist me with my tractor purchase.");

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("model", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("model", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });

        address_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hrrrjjkj");
                Bundle bundle1=new Bundle();
                bundle1.putString("navigation_from","request");
                selectedFragment = Add_New_Address_Fragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("request");
                selectedFragment.setArguments(bundle1);
                transaction.commit();
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestForm();
              /*  selectedFragment = HomeMenuFragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();*/

            }
        });

        radioGroup_finance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
                selectedId = radioGroup_finance.getCheckedRadioButtonId();
                radioButton = (RadioButton)view.findViewById(selectedId);
                System.out.println("checkinggg"+radioButton.getText().toString());
                finance_status=radioButton.getTag().toString();



            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
                selectedId_time_recent = radioGroup.getCheckedRadioButtonId();
                System.out.println("radio_group_1"+selectedId_time_recent);
                radioButton1 = (RadioButton)view.findViewById(selectedId_time_recent);
                //System.out.println("valueee"+radioButton.getText());
                time_period=String.valueOf(radioButton1.getText());
                System.out.println("valueee"+time_period);
            }
        });

       /* final String value =
                ((RadioButton)view.findViewById(radioGroup.getCheckedRadioButtonId()))
                        .getText().toString();*/


       /* radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               // Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
                System.out.println("valueee"+value);
            }
        });*/

        return view;
    }
    private void RequestForm() {




        System.out.println("purchase"+time_period);
        System.out.println("finance"+time_period);

        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
            userRequestjsonObject.put("TractorId", AddModelAdapter.tractor_id);
            userRequestjsonObject.put("PurchaseTimeline", time_period);
            userRequestjsonObject.put("LookingForFinance", finance_status);
            userRequestjsonObject.put("AddressId", addId);
            userRequestjsonObject.put("ModelId", AddModelAdapter.tractor_id);
            userRequestjsonObject.put("IsAgreed", "True");
            userRequestjsonObject.put("LookingForDetailsId", AddFirstAdapter.looinkgId);


          /*  JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objCropDetails", userRequestjsonObject);
*/
            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.AddRequestForQuotation,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);

                    try {
                        String status=result.getString("Status");
                        String message=result.getString("Message");

                        selectedFragment = HomeMenuFragment.newInstance();
                        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();

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
