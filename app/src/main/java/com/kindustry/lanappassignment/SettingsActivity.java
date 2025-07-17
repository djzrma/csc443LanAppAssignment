package com.kindustry.lanappassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lanActivityButton();
        mapsActivityButton();
        settingsActivityButton();
        activitySwitchMessage();
    }

    //method to switch to Lan Activity
    private void lanActivityButton(){
        ImageButton lanButton = findViewById(R.id.lanImageButton);
        lanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                String passedMessage = "You are now on Lan Screen";
                intent.putExtra("lanMessage", passedMessage);
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
                Intent intent = new Intent(SettingsActivity.this, MapsActivity.class);
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
                Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                String passedMessage = "You are now on Settings Screen";
                intent.putExtra("settingsMessage", passedMessage);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //method to display activity switch confirmation message
    private void activitySwitchMessage() {
        Intent intent = getIntent();
        TextView message = findViewById(R.id.activitySwitchConfirmation);
        String tempString = intent.getStringExtra("settingsMessage");
        message.setText(tempString);
    }

}