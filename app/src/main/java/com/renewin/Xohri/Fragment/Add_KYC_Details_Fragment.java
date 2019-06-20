package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Urls;
import com.renewin.Xohri.Volly_class.Crop_Post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONObject;


public class Add_KYC_Details_Fragment extends Fragment {
    Fragment selectedFragment;
    TextView document,verify,document_number;
    EditText doc_number,doc_name_id;
    String status,message;
    SessionManager sessionManager;

    LinearLayout linearLayout,back_feed;

    public static Add_KYC_Details_Fragment newInstance() {
        Add_KYC_Details_Fragment fragment = new Add_KYC_Details_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.complete_kyc, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

       document=view.findViewById(R.id.select_document);

        doc_number=view.findViewById(R.id.license_no);
        verify=view.findViewById(R.id.verify_kyc);
        doc_name_id =view.findViewById(R.id.name_id);
        linearLayout =view.findViewById(R.id.profile_view);
        document_number =view.findViewById(R.id.document_numb);
        back_feed =view.findViewById(R.id.back_feed);
        sessionManager = new SessionManager(getActivity());


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (getArguments().getString("ADD_NKYC").equals("kyc details")) {
                        selectedFragment = Complete_KYC_Fragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();


                    }
                    else {

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack ("kyc_details", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }

                    return true;
                }
                return false;
            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getArguments().getString("ADD_NKYC").equals("kyc details")) {
                    selectedFragment = Complete_KYC_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();


                }/*else if (getArguments().getString("ADD_NBANK").equals("BANK_ADAPTR")) {

                    selectedFragment = Bank_Account_Details_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();


                }*/
                else {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("kyc_details", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }






               /* FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("kyc_details", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/
            }
        });



        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), document);


                popup.getMenu().add("Voter ID Card");
                popup.getMenu().add("PAN Card");
                popup.getMenu().add("Driving Licence");
                popup.getMenu().add("Aadhaar Card");
                popup.getMenu().add("Ration Card");
                popup.getMenu().add("NREGA Card");


                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        System.out.println("teeexxtt"+item.getTitle());
                        document.setText(item.getTitle());


                        if (item.getTitle().equals("Voter ID Card")){
                            document_number.setText("Voter ID Number");



                        }else if(item.getTitle().equals("PAN Card")){
                            document_number.setText("PAN Card Number");



                        }else if(item.getTitle().equals("Driving Licence")){
                            document_number.setText("Driving Licence Number");



                        }else if(item.getTitle().equals("Aadhaar Card")){
                            document_number.setText("Aadhaar Card Number");



                        }else if(item.getTitle().equals("Ration Card")) {
                            document_number.setText("Ration Card Number");


                        }else if(item.getTitle().equals("NREGA Card")) {
                            document_number.setText("NREGA Card Number");
                        }

                        return true;
                    }
                });

                popup.show(); //showing popup menu


            }
        });



        verify.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if(document.getText().toString().equals("Select Document")&&doc_number.getText().toString().equals("")&&doc_name_id.getText().toString().equals("")){

                                              Toast.makeText(getActivity(), "Enter All Details", Toast.LENGTH_SHORT).show();
                                          }

                                         else if (document.getText().toString().equals("Select Document")) {
                                              Toast.makeText(getActivity(), "Select Your Document", Toast.LENGTH_SHORT).show();

                                          } else if (doc_number.getText().toString().equals("")) {
                                              Toast.makeText(getActivity(), "Enter your Document Number", Toast.LENGTH_SHORT).show();

                                          } else if (doc_name_id.getText().toString().equals("")) {
                                              Toast.makeText(getActivity(), "Enter the Name ", Toast.LENGTH_SHORT).show();

                                          } else {

                                              //Add_Kyc();

                                              try{

                                                  JSONObject jsonObject = new JSONObject();
                                                  jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));
                                                  jsonObject.put("DocumentType",document.getText().toString());
                                                  jsonObject.put("DocumentNumber",doc_number.getText().toString());
                                                  jsonObject.put("NameAsPerID",doc_name_id.getText().toString());

                                                  Crop_Post.crop_posting(getActivity(), Urls.ADD_KYC_Details, jsonObject, new VoleyJsonObjectCallback() {
                                                      @Override
                                                      public void onSuccessResponse(JSONObject result) {
                                                          System.out.println("1111aaaakk1111aaaakk" + result);
                                                         // System.out.println("1111aaaakk" + getArguments().getString("KYC_ID"));


                                                          try{

                                                              status =result.getString("Status");
                                                              message = result.getString("Message");

                                                              if(status.equals("1")){

                                                                  Toast.makeText(getActivity(), "Your Details Updated Succesfully", Toast.LENGTH_SHORT).show();

                                                                  System.out.println("aaaaa updae kyc");
                                                                  selectedFragment = Complete_KYC_Fragment.newInstance();
                                                                  FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                                                                   transaction.replace(R.id.frame_layout, selectedFragment);
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
                                  });


        return view;
    }


}
