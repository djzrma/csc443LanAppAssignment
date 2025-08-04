package com.kindustry.lanappassignment;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
    private LAN currentLan;

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
        saveButton();
        lanListActivityButton();
        mapsActivityButton();
        settingsActivityButton();
        activitySwitchMessage();
    }

    //method depicting behavior for saveButton on AddLANActivity
    private void saveButton(){
        Button saveButton = findViewById(R.id.addLanSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wasSuccessful;
                hideKeyboard();
                LanDataSource lanDS = new LanDataSource(AddLAN.this);

                try {
                    lanDS.open();

                    if (currentLan.getLanID() == -1){
                        //if the database opens and is a new Rental record, save it
                        //if -1 we will insert data
                        wasSuccessful = lanDS.insertLan(currentLan);
                        //successfully opened, get the ID we will use
                        if (wasSuccessful){
                            int newId = lanDS.getLastLanId();
                            currentLan.setLanID(newId);
                        }
                    } else {
                        //if the database opens and there is an existing rental record, update it
                        wasSuccessful = lanDS.updateLan(currentLan);
                    }
                    lanDS.close(); //close the database
                    } catch (Exception e){
                    wasSuccessful = false;
                }
                if (wasSuccessful){
                    ToggleButton editToggle = findViewById(R.id.toggleButton);
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText lanName = findViewById(R.id.nameTextField);
        imm.hideSoftInputFromWindow(lanName.getWindowToken(),0);
        EditText lanDescription = findViewById(R.id.descriptionTextField);
        imm.hideSoftInputFromWindow(lanDescription.getWindowToken(), 0);
        EditText lanAddress = findViewById(R.id.addressTextField);
        imm.hideSoftInputFromWindow(lanAddress.getWindowToken(),0);
        EditText lanCity = findViewById(R.id.cityTextField);
        imm.hideSoftInputFromWindow(lanCity.getWindowToken(),0);
        EditText lanState = findViewById(R.id.stateTextField);
        imm.hideSoftInputFromWindow(lanState.getWindowToken(),0);
        EditText lanZipCode = findViewById(R.id.zipCodeTextField);
        imm.hideSoftInputFromWindow(lanZipCode.getWindowToken(),0);
        EditText lanLocationCode = findViewById(R.id.locationCodeTextField);
        imm.hideSoftInputFromWindow(lanLocationCode.getWindowToken(),0);
        EditText lanLocationPhone = findViewById(R.id.locationPhoneTextField);
        imm.hideSoftInputFromWindow(lanLocationPhone.getWindowToken(),0);
        EditText lanLocationManager = findViewById(R.id.locationManagerTextField);
        imm.hideSoftInputFromWindow(lanLocationManager.getWindowToken(),0);
        EditText dateOfConfiguration = findViewById(R.id.dateOfConfigurationTextField);
        imm.hideSoftInputFromWindow(dateOfConfiguration.getWindowToken(), 0);
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

    private void initTextChangedEvents(){
        /*A reference to the Rental object Customer Name EditText is assigned to the variable etCustomerName
         * This is declared as a final because it is used inside the event code*/
        final EditText etLanName = findViewById(R.id.nameTextField);
        /*A text changed listener is added by creating a TextWatcher object
         * The Textwatcher requires three methods as below*/
        etLanName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*The beforeTextChanged is executed when a user presses down on a key to enter
                 * the EditText, but before the value in the EditText is changed*/
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*The onTextChanged method is executed after every character change in
                 * the EditText*/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                /*The afterTextChanged method is called after a user completes editing the data
                 * and leaves the EditText*/
                currentLan.setName(etLanName.getText().toString());
            }
        });

        final EditText etLanDescription = findViewById(R.id.descriptionTextField);
        etLanDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLan.setDescription(etLanDescription.getText().toString());
            }
        });
        final EditText etAddress = findViewById(R.id.addressTextField);
        etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLan.setAddress(etAddress.getText().toString());
            }
        });
        final EditText etCity = findViewById(R.id.cityTextField);
        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLan.setCity(etCity.getText().toString());
            }
        });
        final EditText etState = findViewById(R.id.stateTextField);
        etState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLan.setState(etState.getText().toString());
            }
        });

        final EditText etZipCode = findViewById(R.id.zipCodeTextField);
        etZipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLan.setZipCode(etZipCode.getText().toString());
            }
        });
        final EditText etLocationCode = findViewById(R.id.locationCodeTextField);
        etLocationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLan.setLocationCode(etLocationCode.getText().toString());
            }
        });

        final EditText etLocationPhone = findViewById(R.id.locationPhoneTextField);
        etLocationPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLan.setLocationPhone(etLocationPhone.getText().toString());
            }
        });

        final EditText etLocationManager = findViewById(R.id.locationManagerTextField);
        etLocationManager.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLan.setDescription(etLocationManager.getText().toString());
            }
        });

        final EditText etDateOfConfiguration = findViewById(R.id.dateOfConfigurationTextField);
        etLocationManager.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLan.setDateOfConfiguration(etDateOfConfiguration.getText().toString());
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