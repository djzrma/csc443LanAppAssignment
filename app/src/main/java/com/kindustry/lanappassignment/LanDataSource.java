package com.kindustry.lanappassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LanDataSource {
    private SQLiteDatabase database;
    private LanDBHelper dbHelper;
    public LanDataSource(Context context){
        dbHelper = new LanDBHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        if (database != null && database.isOpen()) {
            database.close(); // this explicitly closes the database connection
        }
    }

    //inserts lan into DB
    public boolean insertLan(LAN lan) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("lanName", lan.getName());
            initialValues.put("description", lan.getDescription());
            initialValues.put("address",lan.getAddress());
            initialValues.put("city", lan.getCity());
            initialValues.put("state", lan.getState());
            initialValues.put("zip_code", lan.getZipCode());
            initialValues.put("locationCode", lan.getLocationCode());
            initialValues.put("locationPhone", lan.getLocationPhone());
            initialValues.put("locationManager", lan.getLocationManager());
            initialValues.put("dateOfConfiguration", lan.getDateOfConfiguration());
            didSucceed = database.insert("lans", null, initialValues) > 0;
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
        return didSucceed;
    }

    //updates a lan
    public boolean updateLan(LAN lan) {
        boolean didSucceed = false;
        try {
            Long rowId = (long)  lan.getLanID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("lanName", lan.getName());
            updateValues.put("description", lan.getDescription());
            updateValues.put("address",lan.getAddress());
            updateValues.put("city", lan.getCity());
            updateValues.put("state", lan.getCity());
            updateValues.put("state", lan.getState());
            updateValues.put("zip_code", lan.getZipCode());
            updateValues.put("locationCode", lan.getLocationCode());
            updateValues.put("locationPhone", lan.getLocationPhone());
            updateValues.put("locationManager", lan.getLocationManager());
            updateValues.put("dateOfConfiguration", lan.getDateOfConfiguration());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return didSucceed;
    }

    //method gets the id to support creating a new one
    public int getLastLanId(){
        int lastId;
        try {
            String query = "Select max(lanID) from lans";
            Cursor cursor = database.rawQuery(query, null); //cursor is an object that used to hold and move through the results

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

    public ArrayList<LAN> getLanInfo(){
        ArrayList<LAN> lanList = new ArrayList<LAN>();

        try {
            String query = "SELECT * FROM lans";
            Cursor cursor = database.rawQuery(query, null);
            LAN newLan;
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                newLan = new LAN();
                newLan.setLanID(cursor.getInt(0));
                newLan.setName(cursor.getString(1));
                newLan.setDescription(cursor.getString(2));
                newLan.setAddress(cursor.getString(3));
                newLan.setCity(cursor.getString(4));
                newLan.setState(cursor.getString(5));
                newLan.setZipCode(cursor.getString(6));
                newLan.setLocationCode(cursor.getString(7));
                newLan.setLocationPhone(cursor.getString(8));
                newLan.setLocationManager(cursor.getString(9));
                newLan.setDateOfConfiguration(cursor.getString(10));
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e){
            lanList = new ArrayList<LAN>();
        }

        return lanList;
    }
    //method call to populate a list of LANs in mainActivity
    public ArrayList<String> getLanNames() {

        ArrayList<String> lanNames = new ArrayList<>();
        try {
            String query = "SELECT lanName FROM lans";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) { //loop through all the records in the cursor
                lanNames.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();

        } catch (Exception e) {
            lanNames = new ArrayList<String>();
        }
        return lanNames;
    }

    // method call to pull a specific lan from the DB
    public LAN getLan(int lanID){
        LAN lan = new LAN();
        String query = "SELECT * FROM lans WHERE lanID =" + lanID;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            lan.setLanID(cursor.getInt(0));
            lan.setName(cursor.getString(1));
            lan.setDescription(cursor.getString(2));
            lan.setAddress(cursor.getString(3));
            lan.setCity(cursor.getString(4));
            lan.setState(cursor.getString(5));
            lan.setZipCode(cursor.getString(6));
            lan.setLocationCode(cursor.getString(7));
            lan.setLocationPhone(cursor.getString(8));
            lan.setLocationManager(cursor.getString(9));
            lan.setDateOfConfiguration(cursor.getString(10));
            cursor.close();
        }
        return lan;
    }

    //method to remove a lan from the DB
    public boolean deleteLan(int lanID){
        boolean didDelete = false;
        try {
            didDelete = database.delete("lan", "lanID" + lanID, null) > 0;
        }
        catch (Exception e){
        }
        return didDelete;
    }
}

