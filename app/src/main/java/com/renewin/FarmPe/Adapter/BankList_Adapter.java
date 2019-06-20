package com.renewin.FarmPe.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.renewin.FarmPe.Bean.StateBean;
import com.renewin.FarmPe.Bean.Variety_Bean;
import com.renewin.FarmPe.Fragment.Add_New_Bank_Details_Fragment;
import com.renewin.FarmPe.R;

import java.util.ArrayList;
import java.util.List;

public class BankList_Adapter extends RecyclerView.Adapter<BankList_Adapter.MyStateHolder> {
    List<StateBean>stateBeans;
    Activity activity;
    private ArrayList<Variety_Bean> uomList=new ArrayList();



    public BankList_Adapter(ArrayList<Variety_Bean> stateBeans,Activity activity) {
        this.uomList = stateBeans;
        this.activity=activity;
    }


    @NonNull
    @Override
    public MyStateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stateview=LayoutInflater.from(parent.getContext()).inflate(R.layout.state_name,parent,false);
        return new MyStateHolder(stateview);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyStateHolder holder, final int position) {

        holder.statename.setText(uomList.get(position).getName());

        holder.state_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_New_Bank_Details_Fragment.select_bank_list.setText(uomList.get(position).getName());
                Add_New_Bank_Details_Fragment.bank_dialog.dismiss();

            }
                //Add_New_Bank_Details_Fragment.bank_dialog.dismiss();

               /* if (uomList.get(position).isSelected()){
                    uomList.get(position).setSelected(false);
                    holder.statename.setTextColor(Color.parseColor("#e8bc1a"));

                }else {
                    uomList.get(position).setSelected(true);

                    holder.statename.setTextColor(Color.parseColor("#000000"));

                }*/
                //notifyDataSetChanged();




           // }
        });


    }

    @Override
    public int getItemCount() {
        return uomList.size();
    }

    public class MyStateHolder extends RecyclerView.ViewHolder{

        TextView statename;
        LinearLayout state_name_layout;

        public MyStateHolder(View itemView) {
            super(itemView);
            statename=itemView.findViewById(R.id.state_item);
            state_name_layout=itemView.findViewById(R.id.state_name_layout);

        }
    }
}
