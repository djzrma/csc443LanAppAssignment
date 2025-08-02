package com.kindustry.lanappassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LanAdapter extends RecyclerView.Adapter {


    private Context context;
    private ArrayList<LAN> lanList;
    private static View.OnClickListener myOnItemClickListener;
    private boolean isDeleting;

    // Constructor
    public LanAdapter(Context context, ArrayList<LAN> lanList) {
        this.context = context;
        this.lanList = lanList;
    }

    // ViewHolder class
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lanName, lanDescription, lanAddress, lanCity, lanState, lanZipCode, lanLocationCode, lanLocationPhone, lanLocationManager, dateOfConfiguration;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            lanName = itemView.findViewById(R.id.nameTextField);
            lanDescription = itemView.findViewById(R.id.descriptionTextField);
            lanAddress = itemView.findViewById(R.id.addressTextField);
            lanCity = itemView.findViewById(R.id.cityTextField);
            lanState = itemView.findViewById(R.id.stateTextField);
            lanZipCode = itemView.findViewById(R.id.zipCodeTextField);
            lanLocationCode = itemView.findViewById(R.id.locationCodeTextField);
            lanLocationPhone = itemView.findViewById(R.id.locationPhoneTextField);
            lanLocationManager = itemView.findViewById(R.id.locationManagerTextField);
            dateOfConfiguration = itemView.findViewById(R.id.dateOfConfigurationTextField);
            itemView.setTag(this);
            itemView.setOnClickListener(myOnItemClickListener);
        }

        public TextView getLanNameTextView(){
            return lanName;
        }

        public TextView getLanDescriptionTextView(){
            return lanDescription;
        }

        public TextView getLanAddressTextView(){
            return lanAddress;
        }

        public TextView getLanCityTextView(){
            return lanCity;
        }

        public TextView getLanStateTextView(){
            return lanState;
        }

        public TextView getLanZipCode(){
            return lanZipCode;
        }

        public TextView getLanLocationCode(){
            return lanLocationCode;
        }

        public TextView getLanLocationPhone(){
            return lanLocationPhone;
        }

        public TextView getLanLocationManager(){
            return lanLocationManager;
        }

        public TextView getDateOfConfiguration(){
            return dateOfConfiguration;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lan_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder mvh = (MyViewHolder) holder;

        mvh.lanName.setText(lanList.get(position).getName());
        mvh.lanDescription.setText(lanList.get(position).getDescription());
        mvh.lanAddress.setText(lanList.get(position).getAddress());
        mvh.lanCity.setText(lanList.get(position).getCity());
        mvh.lanState.setText(lanList.get(position).getState());
        mvh.lanZipCode.setText(lanList.get(position).getZipCode());
        mvh.lanLocationCode.setText(lanList.get(position).getLocationCode());
        mvh.lanLocationPhone.setText(lanList.get(position).getLocationPhone());
        mvh.lanLocationManager.setText(lanList.get(position).getLocationManager());
        mvh.dateOfConfiguration.setText(lanList.get(position).getDateOfConfiguration());
    }

    @Override
    public int getItemCount() {
        return lanList.size();
    }
}
