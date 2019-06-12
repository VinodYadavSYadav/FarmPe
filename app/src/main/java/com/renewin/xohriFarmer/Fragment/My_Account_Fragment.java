package com.renewin.xohriFarmer.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.renewin.xohriFarmer.R;
import com.renewin.xohriFarmer.SessionManager;
import com.renewin.xohriFarmer.Urls;
import com.renewin.xohriFarmer.Volly_class.Crop_Post;
import com.renewin.xohriFarmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class My_Account_Fragment extends Fragment {

    Fragment selectedFragment;

    ScrollView scroll_view;


  LinearLayout back_feed,wallet,logout,verify_kyc,lang_change,refer_earn,select_address,bank_account,
            transactions,store_transport,policies,help_support,about_us,change_password;


    TextView username_1,lang_setting,user_mobil;
   SessionManager sessionManager;
    CircleImageView profile_image;
    boolean doubleBackToExitPressedOnce = false;
    private static int RESULT_LOAD_IMG = 1;

    JSONObject jsonObject,jsonObject1;
    String bmmvendorstoreid;
    JSONArray bank_aray,trans_array,kyc_array;







    ////////////

    public static My_Account_Fragment newInstance() {
        My_Account_Fragment fragment = new My_Account_Fragment();
        return fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_account_layout, container, false);



    //    logout=view.findViewById(R.id.logout);
        username_1 =view.findViewById(R.id.name1);
        profile_image =view.findViewById(R.id.propic);
       // verify_kyc =view.findViewById(R.id.verify_kyc);
      //  lang_change =view.findViewById(R.id.lang_change);
       //lang_setting=view.findViewById(R.id.lang_setting);
        refer_earn=view.findViewById(R.id.list_earn);
        user_mobil=view.findViewById(R.id.phone_numb);
       bank_account=view.findViewById(R.id.bank_acc);
       // store_transport=view.findViewById(R.id.store_transport);
      //  transactions=view.findViewById(R.id.transatn_1);
//        policies=view.findViewById(R.id.policies);
//        help_support=view.findViewById(R.id.help);
        select_address =view.findViewById(R.id.your_address1);
        //about_us =view.findViewById(R.id.about);
       // change_password =view.findViewById(R.id.chng_password);
        scroll_view =view.findViewById(R.id.scroll_view);





//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//                    // getFragmentManager().popBackStack("farmers", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    Intent intent = new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
//                    intent.putExtra("nav_switch","HOME");
//                    startActivity(intent);
//
//
//                    return true;
//                }
//                return false;
//            }
//        });


      //  tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tomato).setText("Vegitables"));

//
//        FarmersNearMeBean item1 = new FarmersNearMeBean("Ravi Hattikal", "20 Acres");
//
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);

//        AgriBean item4 = new AgriBean("Mill");
//        newOrderBeansList.add(item4);
//
//        AgriBean item5 = new AgriBean("Warehouse");
//        newOrderBeansList.add(item5);
//

        sessionManager=new SessionManager(getActivity());

       // username_1.setText(sessionManager.getRegId("name"));
      //  user_mobil.setText(sessionManager.getRegId("phone"));
        //lang_setting.setText(sessionManager.getRegId("language"));






        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

            }
        });



//        username_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                intent.putExtra("CAT_NAV","EDIT_ACC");
//                startActivity(intent);
//            }
//        });

//        change_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                intent.putExtra("CAT_NAV","CHANGE_PASS");
//                startActivity(intent);
//            }
//        });


//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//
//                final TextView yes1,no1;
//                System.out.println("aaaaaaaaaaaa");
//                final Dialog dialog = new Dialog(getContext());
//                dialog.setContentView(R.layout.logout_layout);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.setCancelable(Utils.setCancleable);
//
//
//                no1 =  dialog.findViewById(R.id.no_1);
//                no1.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//
//                yes1 =  dialog.findViewById(R.id.yes_1);
//                yes1.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        sessionManager.logoutUser();
//                        getActivity().finish();
//
//                        dialog.dismiss();
//                    }
//                });
//
//                ImageView image = dialog.findViewById(R.id.close_popup);
//                image.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//
//
//                dialog.show();
//
//            }
//        });


        bank_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectedFragment = Bank_Account_Details_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.commit();


            }
        });


        wallet=view.findViewById(R.id.wallets);
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = WalletFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("menu");
                transaction.commit();
            }
        });



//        bank_account.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try{
//
//                    JSONObject jsonObject = new JSONObject();
//
//                    jsonObject.put("UserId",sessionManager.getRegId("userId"));
//
//                    Crop_Post.crop_posting(getActivity(), Urls.Get_Bank_Details, jsonObject, new VoleyJsonObjectCallback() {
//                        @Override
//                        public void onSuccessResponse(JSONObject result) {
//                            System.out.println("111111bbbbbbbbbbbbbbbbbbbb" + result);
//                            try{
//                             bank_aray= result.getJSONArray("UserBankDetails");
//                            if(bank_aray.length() == 0){
//
//                                Intent intent=new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                                intent.putExtra("CAT_NAV","ADD_NEW");
//                                startActivity(intent);
//
//
//                            }else {
//
//                                Intent intent = new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                                intent.putExtra("CAT_NAV", "BANK_ACC");
//                                startActivity(intent);
//                            }
//
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//
//                        }
//                    });
//
//
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//        });
//



//        scroll_view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//
//                Log.d(TAG, "onScrollChangeForY - scrollY: " + scrollY + " oldScrollY: " + oldScrollY);
//
//                int MOVE = -1, SCROLL_UP = 0, SCROLL_DOWN = 1;
//                //    float initialPositionY = headerView.getY();
//
//                MOVE = scrollY > oldScrollY ? SCROLL_UP : SCROLL_DOWN;
//
//                if (MOVE == SCROLL_UP) {
//
//                    int incrementY = scrollY - oldScrollY;
//                    System.out.println("11111ss" + "up");
//                    Home_Page_WithBottomMenu_Activity.bottomNavigation.hideBottomNavigation(true);
//
//                    //     headerView.setY(initialPositionY - incrementY);
//
//                } else {
//                    System.out.println("11111ss" + "down");
//
//                    int incrementY = oldScrollY - scrollY;
//                    Home_Page_WithBottomMenu_Activity.bottomNavigation.restoreBottomNavigation(true);
//
//                    //   headerView.setY(initialPositionY + incrementY);
//                }
//
//
//            }
//        });


//
//        scroll_view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//
//                int x = scrollY - oldScrollY;
//                if (x > 0) {
//                    System.out.println("11111uu" + "up");
//                    //scroll up
//                } else if (x < 0) {
//                    System.out.println("11111ddd" + "down");
//                    //scroll down
//                } else {
//                    System.out.println("11111ddd" + "else");
//
//                }
//
//            }
//        });


//        store_transport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try{
//
//                    JSONObject jsonObject1 = new JSONObject();
//
//                    jsonObject1.put("CreatedBy",sessionManager.getRegId("userId"));
//
//                    Crop_Post.crop_posting(getActivity(), Urls.GetTransportationDetails, jsonObject1, new VoleyJsonObjectCallback() {
//                        @Override
//                        public void onSuccessResponse(JSONObject result) {
//                            System.out.println("abcdanvvvvbbbmnn" + result);
//                            try{
//                                trans_array= result.getJSONArray("TransportationLists");
//                                if(trans_array.length() == 0){
//
//                                    Intent i=new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                                    i.putExtra("CAT_NAV","NEW_TRANSPORTATION");
//                                    startActivity(i);
//
//
//                                }else {
//
//                                    Intent i = new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                                    i.putExtra("CAT_NAV", "TRANSPORTATION");
//                                    startActivity(i);
//                                }
//
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//
//                        }
//                    });
//
//
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }

               /* selectedFragment = Transportation_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("transport");
                transaction.commit();*/
//            }
//        });



//        transactions.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedFragment = Transaction_Fragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.addToBackStack("transaction");
//                transaction.commit();
//
//            }
//        });


//        policies.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedFragment = Policy_Fragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.addToBackStack("policy");
//                transaction.commit();
//
//            }
//        });
//
//
//        help_support.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedFragment = Policy_Fragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.addToBackStack("policy");
//                transaction.commit();
//
//            }
//        });

//        about_us.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                intent.putExtra("CAT_NAV","ABOUT US");
//                startActivity(intent);
//
//            }
//        });

//        verify_kyc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try{
//
//                    JSONObject  jsonObject = new JSONObject();
//                    jsonObject.put("UserId",sessionManager.getRegId("userId"));
//
//
//                    Crop_Post.crop_posting(getActivity(), Urls.KYC_Details, jsonObject, new VoleyJsonObjectCallback() {
//                        @Override
//                        public void onSuccessResponse(JSONObject result) {
//                            System.out.println("11111kkkk" + result);
//                            try{
//                                kyc_array = result.getJSONArray("UserKYCDetails");
//
//                                if(kyc_array.length()==0){
//                                    Intent i=new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                                    i.putExtra("CAT_NAV","ADD_NEW_KYC");
//                                    startActivity(i);
//
//
//
//                                }else{
//
//                                    Intent i = new Intent(getActivity(), Home_Page_Without_BottomMenu_Activity.class);
//                                    i.putExtra("CAT_NAV", "KYC_DETAILS");
//                                    startActivity(i);
//
//
//                                }
//
////                                for(int i = 0;i<kyc_array.length();i++){
////                                    JSONObject jsonObject1 = kyc_array.getJSONObject(i);
////                                    kyc_bean = new Kyc_Bean(jsonObject1.getString("DocumentType"),jsonObject1.getString("DocumentNumber"),jsonObject1.getString("NameAsPerID"),jsonObject1.getString("Id"));
////                                    kyc_beanArrayList.add(kyc_bean);
////
////                                }
////
////                                mAdapter.notifyDataSetChanged();
//
//
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//
//                        }
//                    });
//
//
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//
////                selectedFragment = Complete_KYC_Fragment.newInstance();
////                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
////                transaction.replace(R.id.frame_menu, selectedFragment);
////                transaction.addToBackStack("kyc_list");
////                transaction.commit();
//            }
//        });



//       lang_change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedFragment = LanguageChangeFragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.addToBackStack("menu");
//                transaction.commit();
//            }
//        });
//

        refer_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = ReferAndEarncopy.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("menu");
                transaction.commit();
            }
        });


        select_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                selectedFragment = You_Address_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("you_c");
                transaction.commit();

            }
        });



        try {

            JSONObject jsonObject = new JSONObject();
            JSONObject post_json = new JSONObject();


            jsonObject.put("Id", sessionManager.getRegId("userId"));
            post_json.put("objUser", jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Edit_Profile_Details, post_json, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dddddddddddd" + result);
                    try {

                        JSONObject jsonObject1 = result.getJSONObject("user");
                        String user_name1 = jsonObject1.getString("FullName");
                        String phone_no1 = jsonObject1.getString("PhoneNo");


                        username_1.setText(user_name1.trim());
                        user_mobil.setText(phone_no1.trim());


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream =getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                profile_image.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }


}
