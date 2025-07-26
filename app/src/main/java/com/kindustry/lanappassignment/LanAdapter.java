package com.kindustry.lanappassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LanAdapter extends RecyclerView.Adapter<LanAdapter.MyViewHolder> {

    private List<LAN> lanList;

    // Constructor
    public LanAdapter(List<LAN> lanList) {
        this.lanList = lanList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lan_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LAN lanItem = lanList.get(position);

        holder.lanName.setText(lanItem.getName());
        holder.lanDescription.setText(lanItem.getDescription());
        holder.lanAddress.setText(lanItem.getAddress());
        holder.lanCity.setText(lanItem.getCity());
        holder.lanState.setText(lanItem.getState());
        holder.lanZipCode.setText(lanItem.getZipCode());
        holder.lanLocationCode.setText(lanItem.getLocationCode());
        holder.lanLocationPhone.setText(lanItem.getLocationPhone());
        holder.lanLocationManager.setText(lanItem.getLocationManager());
        holder.dateOfConfiguration.setText(lanItem.getDateOfConfiguration());
    }

    @Override
    public int getItemCount() {
        return lanList.size();
    }

    // ViewHolder class
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lanName, lanDescription, lanAddress, lanCity, lanState, lanZipCode, lanLocationCode, lanLocationPhone, lanLocationManager, dateOfConfiguration;

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
        }
    }
}
