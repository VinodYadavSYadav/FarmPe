package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.renewin.Xohri.Adapter.Bank_Account_Details_Adapter;
import com.renewin.Xohri.Bean.Bank_Account_Bean;
import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Urls;
import com.renewin.Xohri.Volly_class.Crop_Post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Bank_Account_Details_Fragment extends Fragment {

    Fragment selectedFragment;
    private RecyclerView recyclerView;
    SessionManager sessionManager;

    ArrayList<Bank_Account_Bean> bank_account_ArrayList = new ArrayList<>();
    Bank_Account_Bean bank_account_bean;
    public static TextView bank_account;
    LinearLayout back_feed;
    Bank_Account_Details_Adapter mAdapter;
    JSONArray bank_array;




    public static Bank_Account_Details_Fragment newInstance() {
        Bank_Account_Details_Fragment fragment = new Bank_Account_Details_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bank_acc_details_recyc_layout, container, false);

        recyclerView = view.findViewById(R.id.recycler_2);
        bank_account = view.findViewById(R.id.add_bnk_acc);
        back_feed = view.findViewById(R.id.back_feed);
        sessionManager = new SessionManager(getActivity());


//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(new View.OnKeyListener() {
//
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//                    Intent intent=new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                  // intent.putExtra("nav_switch","BANK_ACC");
//                    startActivity(intent);
//
//
//                    return true;
//                }
//                return false;
//            }
//        });


//        back_feed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               /* Intent intent = new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                intent.putExtra("nav_switch", "BANK_ACC");
//                startActivity(intent);*/
//               /* selectedFragment = My_Account_Fragment.newInstance();
//                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.commit();*/
//               /* FragmentManager fm = getActivity().getSupportFragmentManager();
//                fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/
//                Intent intent=new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//              //  intent.putExtra("nav_switch","BANK_ACC");
//                startActivity(intent);
//            }
//        });
//


        bank_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("Bank_name","");
                bundle.putString("Account_number","");
                bundle.putString("Ifsc_Code","");
                bundle.putString("Acc_name","");
                bundle.putString("BankId","");
                bundle.putString("ADD_NBANK","bank details");
                selectedFragment = Add_New_Bank_Details_Fragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                selectedFragment.setArguments(bundle);
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("new_account");
                transaction.commit();

            }
        });




//        dataModels= new ArrayList<>();
//        for (int i=0;i<=4;i++){
//            dataModels.add(new Bank_Account_Bean("Apple Pie", "Android 1.0"));
//        }


        mAdapter = new Bank_Account_Details_Adapter(bank_account_ArrayList,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



        try{

            JSONObject jsonObject = new JSONObject();

             jsonObject.put("UserId",sessionManager.getRegId("userId"));

             Crop_Post.crop_posting(getActivity(), Urls.Get_Bank_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("111111bbbbbbbbbbbbb" + result);
                    try{
                        bank_account_ArrayList.clear();


                        bank_array = result.getJSONArray("UserBankDetails");
                        for(int i = 0;i<bank_array.length();i++){
                            JSONObject jsonObject1 = bank_array.getJSONObject(i);

                            bank_account_bean = new Bank_Account_Bean(jsonObject1.getString("BankName"),jsonObject1.getString("AccountHolderName"),jsonObject1.getString("AccountNumber"),jsonObject1.getString("IFSCCode"),jsonObject1.getString("Id"),false);
                            bank_account_ArrayList.add(bank_account_bean);

                        }

                          mAdapter.notifyDataSetChanged();


                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }



        return view;
    }
}
