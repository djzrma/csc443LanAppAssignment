package com.kindustry.lanappassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddLAN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initToggleButton();
        setForEditing(true);
        lanListActivityButton();
        mapsActivityButton();
        settingsActivityButton();
        activitySwitchMessage();
    }

    //method call for toggle button
    private void initToggleButton() {
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setForEditing(toggleButton.isChecked());
                setForEditing(true);
            }
        });
    }

    //method call for toggle button behavior
    private void setForEditing(boolean enabled){
        EditText nameText = findViewById(R.id.nameTextField);
        nameText.setEnabled(enabled);
        EditText descriptionText = findViewById(R.id.descriptionTextField);
        descriptionText.setEnabled(enabled);
        EditText addressText = findViewById(R.id.addressTextField);
        addressText.setEnabled(enabled);
        EditText cityText = findViewById(R.id.cityTextField);
        cityText.setEnabled(enabled);
        EditText stateText = findViewById(R.id.stateTextField);
        stateText.setEnabled(enabled);
        EditText zipCodeText = findViewById(R.id.zipCodeTextField);
        zipCodeText.setEnabled(enabled);
        EditText locationCodeText = findViewById(R.id.locationCodeTextField);
        locationCodeText.setEnabled(enabled);
        EditText locationPhoneText = findViewById(R.id.locationPhoneTextField);
        locationPhoneText.setEnabled(enabled);
        EditText locationManagerText = findViewById(R.id.locationManagerTextField);
        locationManagerText.setEnabled(enabled);
        EditText dateOfConfigurationText = findViewById(R.id.dateOfConfigurationTextField);
        dateOfConfigurationText.setEnabled(enabled);
        if (enabled) {
            nameText.requestFocus();
        } else {
            ScrollView scrollView = findViewById(R.id.lanInfoScroll);
            scrollView.fullScroll(ScrollView.FOCUS_UP);
        }
    }

    //switches to Lan List activity
    private void lanListActivityButton() {
        ImageButton lanListButton = findViewById(R.id.lanListImageButton);
        lanListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLAN.this, LanListActivity.class);
                String passedMessage = "You are now on the LAN List screen";
                intent.putExtra("lanListMessage", passedMessage);
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
                Intent intent = new Intent(AddLAN.this, MapsActivity.class);
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
                Intent intent = new Intent(AddLAN.this, SettingsActivity.class);
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
        String tempString = intent.getStringExtra("addLanMessage");
        message.setText(tempString);
    }
}