package com.renewin.FarmPe.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.renewin.FarmPe.Adapter.BankList_Adapter;
import com.renewin.FarmPe.Bean.Variety_Bean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Crop_Post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Add_New_Bank_Details_Fragment extends Fragment {


    LinearLayout linear_name, linear_mobile,linear_pincode,linear_city,linear_street,linear_house,linear_landmark;

    Fragment selectedFragment = null;
    public  static   TextView add_bnk_details;
    SessionManager sessionManager ;
    BankList_Adapter bankList_adapter;
    private ArrayList<Variety_Bean> uomList = new ArrayList();
    LinearLayout back_feed;
    Variety_Bean variety_bean;
    PopupMenu bank_popup;
    public static  EditText acc_no,ifsc_code,acc_name,confirm_acc,select_bank_list;
    String status,message;
    JSONArray banklist_array;
    public static Dialog bank_dialog;
    LinearLayout linearLayout;

    public static Dialog grade_dialog;


    public static Add_New_Bank_Details_Fragment newInstance() {
        Add_New_Bank_Details_Fragment fragment = new Add_New_Bank_Details_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_bank_account, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);




        select_bank_list = view.findViewById(R.id.bank_list);
       add_bnk_details = view.findViewById(R.id.submit);
        acc_no= view.findViewById(R.id.account_1);
        ifsc_code = view.findViewById(R.id.ifsc_cod);
        acc_name = view.findViewById(R.id.acc_name);
        back_feed = view.findViewById(R.id.back);
       // confirm_acc = view.findViewById(R.id.confirm_acc);
        linearLayout = view.findViewById(R.id.profile_view);


        sessionManager = new SessionManager(getActivity());

        if (!(getArguments().getString("Bank_name").equals(""))){
            select_bank_list.setText(getArguments().getString("Bank_name"));
        }if (!(getArguments().getString("Account_number").equals(""))){
            acc_no.setText(getArguments().getString("Account_number"));
        }if (!(getArguments().getString("Ifsc_Code").equals(""))){
            ifsc_code.setText(getArguments().getString("Ifsc_Code"));
        }if (!(getArguments().getString("Acc_name").equals(""))){
            acc_name.setText(getArguments().getString("Acc_name"));
        }


        bankList_adapter =new BankList_Adapter(uomList,getActivity());
        bank_popup = new PopupMenu(getActivity(), select_bank_list);

       // System.out.println("kjngjnbfj vn vjn"+getArguments().getString("Bank_name"));

       // select_bank_list.setText(getArguments().getString("Bank_name"));;



       /* select_bank_list.setText(getArguments().getString("Bank_name"));
        acc_name.setText(getArguments().getString("Acc_name"));
        acc_no.setText(getArguments().getString("Account_number"));
        ifsc_code.setText(getArguments().getString("Ifsc_Code"));
*/
        select_bank_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bank_dialog = new Dialog(getActivity());
                bank_dialog.setContentView(R.layout.select_variety_popup);
                ImageView image = (ImageView) bank_dialog.findViewById(R.id.close_popup);
                TextView popheading = (TextView) bank_dialog.findViewById(R.id.popup_heading);
                RecyclerView recyclerView = bank_dialog.findViewById(R.id.recycler_view1);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);

                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
               // System.out.println("12345t6ytfrdsdcfb" +uomList.size());

                bankList_adapter= new BankList_Adapter(uomList,getActivity());
                recyclerView.setAdapter(bankList_adapter);

                popheading.setText("Select Your Bank");

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bank_dialog.dismiss();
                    }
                });
                bank_list();

                bank_dialog.show();

            }
        });









//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(new View.OnKeyListener() {
//
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//                    if (getArguments().getString("ADD_NBANK").equals("bank details")) {
//
//                        Intent intent = new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                        intent.putExtra("CAT_NAV", "BANK_ACC");
//                        startActivity(intent);
//
//                    }
//                    else if (getArguments().getString("ADD_NBANK").equals("BANK_ADAPTR")) {
//
//                        Intent intent = new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                        intent.putExtra("CAT_NAV", "BANK_ACC");
//                        startActivity(intent);
//
//
//                    }
//
//                else {
//
//                  Intent intent = new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
//                        intent.putExtra("nav_switch", "ADD_NEW");
//                        startActivity(intent);
//
//                }
//                    return true;
//                }
//                return false;
//            }
//        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


       /* select_bank_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = Bank_List_Fragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("bank_list");
                transaction.commit();

            }


        });
*/

        add_bnk_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(acc_no.getText().toString().equals("")&&(select_bank_list.getText().toString().equals("")||select_bank_list.getText().toString().equals(""))&&ifsc_code.getText().toString().equals("")&&acc_name.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }

              //  String confirm_acc_no = confirm_acc.getText().toString();

                else if(acc_no.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Enter  Your Account Number", Toast.LENGTH_SHORT).show();
                   /* Toast toast =Toast.makeText(getActivity(), "Enter Your Account Number", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.AXIS_PULL_BEFORE|Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
*/



                } else if(select_bank_list.getText().toString().equals("")||select_bank_list.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Select Your  Bank", Toast.LENGTH_SHORT).show();
                    /*Snackbar snackbar = Snackbar
                            .make(linearLayout, "Select Bank Name", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();*/

                   // Toast.makeText(getActivity(), "Select Bank Name", Toast.LENGTH_SHORT).show();

                }/* else if(confirm_acc.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Confirm Your Account Number", Toast.LENGTH_SHORT).show();


                }else if(!(acc_no.getText().toString().equals(confirm_acc_no))){
                    Toast.makeText(getActivity(), "Your Account Number Is Not Matching", Toast.LENGTH_SHORT).show();

                }*/else if(ifsc_code.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Enter IFSC Code", Toast.LENGTH_SHORT).show();


                }else if(acc_name.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Enter Account Holder Name", Toast.LENGTH_SHORT).show();

                }else{

                    add_bank();
                }




            }
        });


        return view;

    }

    private void bank_list() {
        try{

            JSONObject jsonObject = new JSONObject();

            Crop_Post.crop_posting(getActivity(), Urls.Bank_Name_List, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("bbbbbbbbbb" + result);
                    try{


                        banklist_array = result.getJSONArray("BankLists");
                        for(int i = 0;i<banklist_array.length();i++){

                            JSONObject jsonObject1 = banklist_array.getJSONObject(i);
                            variety_bean = new Variety_Bean(jsonObject1.getString("BankName"),jsonObject1.getString("Id"),false);
                            uomList.add(variety_bean);


                        }

                        bankList_adapter.notifyDataSetChanged();


                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void add_bank(){



        try{

            JSONObject jsonObject = new JSONObject();


            jsonObject.put("Id", getArguments().getString("BankId"));
            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));
            jsonObject.put("AccountHolderName",acc_name.getText().toString());
            jsonObject.put("BankName",select_bank_list.getText().toString());
            jsonObject.put("AccountNumber",acc_no.getText().toString());
            jsonObject.put("IFSCCode",ifsc_code.getText().toString());
            System.out.println("1111122xx" + jsonObject);


            Crop_Post.crop_posting(getActivity(), Urls.Add_Bank_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("1111122xx" + result);
                    System.out.println("1111122xxBankId" + getArguments().getString("BankId"));

                    try{

                        status = result.getString("Status");
                        message = result.getString("Message");

                        if(status.equals("1")){
                            if(getArguments().getString("BankId").equals("")){
                                Toast.makeText(getActivity(),"New bank details Added successfully", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Toast.makeText(getActivity(),"Bank details Updated successfully", Toast.LENGTH_SHORT).show();

                            }

                            selectedFragment = Bank_Account_Details_Fragment.newInstance();
                            FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout_home, selectedFragment);
                            transaction.commit();

                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });



        }catch (Exception e){
            e.printStackTrace();

        }


    }





}

