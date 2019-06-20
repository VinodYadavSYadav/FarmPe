package com.renewin.FarmPe.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Crop_Post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

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
                transaction.replace(R.id.first_full_frame, selectedFragment);
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
