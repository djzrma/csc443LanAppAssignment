package com.kindustry.lanappassignment;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//this is the "main" method
public class LanListActivity extends AppCompatActivity {

    ArrayList<LAN> lanList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lan_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LanDataSource lanDS = new LanDataSource(this);
        populateDB(lanDS);


        try{
            lanDS.open();
            lanList = lanDS.getLanInfo();
            lanDS.close();
            RecyclerView lans = findViewById(R.id.lanRecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            lans.setLayoutManager(layoutManager);
            LanAdapter lanAdapter = new LanAdapter(this, lanList);
            lans.setAdapter(lanAdapter);
        } catch (SQLException e){
            Toast.makeText(this, "Error retrieving lans", Toast.LENGTH_LONG).show();
        }

        addLanActivityButton();
        mapsActivityButton();
        settingsActivityButton();
        activitySwitchMessage();
    }

    private void populateDB(LanDataSource lanDS){
        LAN lan1 = new LAN();
        lan1.setName("lan1");
        lan1.setDescription("first lan");
        lan1.setAddress("123 lan blvd.");
        lan1.setCity("San Diego");
        lan1.setState("California");
        lan1.setZipCode("92543");
        lan1.setLocationCode("456");
        lan1.setLocationPhone("909-552-1256");
        lan1.setLocationManager("Donavan");
        lan1.setDateOfConfiguration("07/22/2025");

        lanDS.insertLan(lan1);
    }
    //switches to Add Lan Activity
    private void addLanActivityButton() {
        ImageButton addLanButton = findViewById(R.id.addLanImageButton);
        addLanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanListActivity.this, AddLAN.class);
                String passedMessage = "You are now on the Add LAN screen";
                intent.putExtra("addLanMessage", passedMessage);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //method to switch to Maps Activity
    private void mapsActivityButton(){
        ImageButton mapsButton = findViewById(R.id.mapsImageButton);
        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanListActivity.this, MapsActivity.class);
                String passedMessage = "You are now on Maps Screen";
                intent.putExtra("mapsMessage", passedMessage);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //method to switch to Settings Activity
    private void settingsActivityButton(){
        ImageButton settingsButton = findViewById(R.id.settingsImageButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanListActivity.this, SettingsActivity.class);
                String passedMessage = "You are now on Settings Screen";
                intent.putExtra("settingsMessage", passedMessage);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //method to display activity switch confirmation message
    private void activitySwitchMessage(){
        Intent intent = getIntent();
        TextView message = findViewById(R.id.activitySwitchConfirmation);
        String tempString = intent.getStringExtra("lanListMessage");
        message.setText(tempString);
    }
}
