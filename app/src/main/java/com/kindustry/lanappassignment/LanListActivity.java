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

public class LanListActivity extends AppCompatActivity {

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

        addLanActivityButton();
        mapsActivityButton();
        settingsActivityButton();
        activitySwitchMessage();

        LanDataSource lanDS = new LanDataSource(this);

        ArrayList<String> lanList;
        try{
            lanDS.open();
            lanList = lanDS.getLanNames();
            lanDS.close();
            RecyclerView lans = findViewById(R.id.lanRecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            lans.setLayoutManager(layoutManager);
            LanAdapter lanAdapter = new LanAdapter(lanList);
            lans.setAdapter(lanAdapter);
        } catch (SQLException e){
            Toast.makeText(this, "Error retrieving lans", Toast.LENGTH_LONG).show();
        }
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
