package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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


public class Add_Transportation_Details_Fragment extends Fragment {
    Fragment selectedFragment;
    TextView document,verify,document_number;
    EditText veh_number,owner_name,select_trnsptn;
    SessionManager sessionManager;
   String status,message;

    LinearLayout linearLayout,back_arrow;

    public static Add_Transportation_Details_Fragment newInstance() {
        Add_Transportation_Details_Fragment fragment = new Add_Transportation_Details_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transportation_type_layout, container, false);
     //   Home_Page_WithBottomMenu_Activity.bottomNavigation.setVisibility(View.GONE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        document=view.findViewById(R.id.select_document);

        veh_number=view.findViewById(R.id.veh_no);
        verify=view.findViewById(R.id.verify_kyc);
        owner_name =view.findViewById(R.id.name_id);
        linearLayout =view.findViewById(R.id.profile_view);
        document_number =view.findViewById(R.id.document_numb);
        select_trnsptn =view.findViewById(R.id.select_document);
        back_arrow =view.findViewById(R.id.back_feed);


        sessionManager = new SessionManager(getActivity());

        if (!(getArguments().getString("TransportationType").equals(""))){
            document.setText(getArguments().getString("TransportationType"));
        }if (!(getArguments().getString("VehicleNumber").equals(""))){
            veh_number.setText(getArguments().getString("VehicleNumber"));
        }if (!(getArguments().getString("OwnerName").equals(""))){
            owner_name.setText(getArguments().getString("OwnerName"));
        }


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (getArguments().getString("ADD_NTRANSPOTATION").equals("Transpotation details")) {

                     /*   Intent intent = new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
                        intent.putExtra("CAT_NAV", "TRANSPORTATION");
                        startActivity(intent);
*/

                        selectedFragment = Transportation_Fragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();

                    }else if (getArguments().getString("ADD_NTRANSPOTATION").equals("TRANSPOTATION_ADAPTR")) {

                     /*   Intent intent = new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
                        intent.putExtra("CAT_NAV", "TRANSPORTATION");
                        startActivity(intent);*/

                        selectedFragment = Transportation_Fragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();

                    }

                    else {
                        selectedFragment = My_Account_Fragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();

                   /*     Intent intent = new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
                        intent.putExtra("nav_switch", "ADD_NEW_TRANSPORTATION");
                        startActivity(intent);*/
                    }
                    return true;
                }
                return false;
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments().getString("ADD_NTRANSPOTATION").equals("Transpotation details")) {

                     /*   Intent intent = new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
                        intent.putExtra("CAT_NAV", "TRANSPORTATION");
                        startActivity(intent);
*/

                    selectedFragment = Transportation_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();

                }else if (getArguments().getString("ADD_NTRANSPOTATION").equals("TRANSPOTATION_ADAPTR")) {

                     /*   Intent intent = new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
                        intent.putExtra("CAT_NAV", "TRANSPORTATION");
                        startActivity(intent);*/

                    selectedFragment = Transportation_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();

                }

                else {
                    selectedFragment = My_Account_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();

                   /*     Intent intent = new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
                        intent.putExtra("nav_switch", "ADD_NEW_TRANSPORTATION");
                        startActivity(intent);*/
                }


            }
        });







        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_trnsptn.getText().toString().equals("") && veh_number.getText().toString().equals("") && owner_name.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter All Details", Toast.LENGTH_SHORT).show();
                } else if (select_trnsptn.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter your Transportation", Toast.LENGTH_SHORT).show();
                } else if (veh_number.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter Vehicle Number", Toast.LENGTH_SHORT).show();
                } else if (owner_name.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter Your Name", Toast.LENGTH_SHORT).show();
                } else {

                    try {

                        JSONObject jsonObject = new JSONObject();

                        jsonObject.put("Id", getArguments().getString("Id"));
                        jsonObject.put("CreatedBy", sessionManager.getRegId("userId"));
                        jsonObject.put("TransportationType", select_trnsptn.getText().toString());
                        jsonObject.put("VehicleNumber", veh_number.getText().toString());
                        jsonObject.put("OwnerName", owner_name.getText().toString());

                        Crop_Post.crop_posting(getActivity(), Urls.AddUpdateTransportationDetails, jsonObject, new VoleyJsonObjectCallback() {
                            @Override
                            public void onSuccessResponse(JSONObject result) {
                                System.out.println("AddTtttttttttttttttttttttttttttt" + result);
                                try {
                                    status = result.getString("Status");
                                    message = result.getString("Message");

                                    if (status.equals("1")) {
                                        if (getArguments().getString("Id").equals("")) {
                                            Toast.makeText(getActivity(), "New Transportation details Added successfully", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(getActivity(), "Transportation details Updated successfully", Toast.LENGTH_SHORT).show();

                                        }

                                        selectedFragment = Transportation_Fragment.newInstance();
                                        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                                        transaction.replace(R.id.frame_layout, selectedFragment);
                                        transaction.commit();

                                    }

                                    /*if(status.equals("1")){

                                        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();

                                        selectedFragment = Transportation_Fragment.newInstance();
                                        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_menu, selectedFragment);
                                        transaction.commit();
                                    }*/


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            }

        });


        return view;
    }
}
