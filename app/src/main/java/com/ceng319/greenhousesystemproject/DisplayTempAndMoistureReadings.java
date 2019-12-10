package com.ceng319.greenhousesystemproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.lang.*;

public class DisplayTempAndMoistureReadings extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    DataStructure mData;

    private TextView name;
    private TextView temperature;
    private TextView humidity;
    private TextView message;
    private TextView timestamp;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_temperature);
        this.setTitle("Read from Database");
        getDatabase();
        findAllViews();
       // reterieveData();
    }

    private void findAllViews(){
        name = findViewById(R.id.readname);
        temperature = findViewById(R.id.readtemperature);
        message = findViewById(R.id.readmessage);
        timestamp = findViewById(R.id.readtimestamp);
        humidity=findViewById(R.id.readsoilmoisture);
    }

    private void getDatabase(){
        // TODO: Find the reference form the database.
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String path = "userdata/" + mAuth.getUid();  // read from the user account.
        myRef = database.getReference(path);
    }

    private void reterieveData(){
        // TODO: Get the data on a single node.
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructure ds = dataSnapshot.getValue(DataStructure.class);
                //name.setText(name.getText()+"Name: "+ds.getName());

                temperature.setText("Temperature: "+ds.getTemperature());
                humidity.setText("Soil Moisture in %: " + ds.getHumidity());
                //message.setText("Message: " + ds.getMessage());

                // Convert from timestamp to Date and time
                timestamp.setText("Observed at: "+convertTimestamp(ds.getTimestamp()));
            }

            private String convertTimestamp(String timestamp){

                long yourSeconds = Long.valueOf(timestamp);
                Date mDate = new Date(yourSeconds * 1000);
                DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
                return df.format(mDate);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructure ds = dataSnapshot.getValue(DataStructure.class);
                name.setText("Name:      "+ ds.getName());
                temperature.setText("Temperature:      "+ds.getTemperature());
                humidity.setText("Humidity:      " + ds.getHumidity());
                message.setText("Message:      " + ds.getMessage());

                // Convert from timestamps to Date and time
                timestamp.setText(convertTimestamp(ds.getTimestamp()));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // TODO: Get the whole data array on a reference.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataStructure> arraylist= new ArrayList<DataStructure>();

                // TODO: Now data is retrieved, needs to process data.
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    // iterate all the items in the dataSnapshot
                    for (DataSnapshot a : dataSnapshot.getChildren()) {
                        DataStructure dataStructure = new DataStructure();
                        dataStructure.setName(a.getValue(DataStructure.class).getName());
                        dataStructure.setTemperature(a.getValue(DataStructure.class).getTemperature());
                        dataStructure.setHumidity(a.getValue(DataStructure.class).getHumidity());
                        dataStructure.setMessage(a.getValue(DataStructure.class).getMessage());
                        dataStructure.setTimestamp(a.getValue(DataStructure.class).getTimestamp());

                        arraylist.add(dataStructure);  // now all the data is in arraylist.
                        Log.d("MapleLeaf", "dataStructure " + dataStructure.getTimestamp());
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(), "No Data available", Toast.LENGTH_LONG).show();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting data failed, log a message
                Log.d("MapleLeaf", "Data Loading Canceled/Failed.", databaseError.toException());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainreadingspage, menu);
        //globalmenu = menu;
        // setMenuItem(R.id.action_write, false);  // enable the write function.
        //setMenuItem(R.id.action_read, false);  // enable the write function.
        return true;
    }
    /*
    private void setMenuItem(int id, boolean enable){
        globalmenu.findItem(id).setEnabled(enable);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_MainReadingsPage)
        {
            // TODO: Start the read option.
            Intent ReadingsData = new Intent(getApplicationContext(), MainReadingsPageActivity.class);
            startActivity(ReadingsData);
            finish();
            return true;
        }
        else if (id == R.id.action_TemperatureData)
        {
            // TODO: Start the read option.
            Intent temperatureData = new Intent(getApplicationContext(), TemperatureDataActivity.class);
            startActivity(temperatureData);
            finish();
            return true;
        }
        else if (id == R.id.action_SoilMoistureData){
            // TODO: Start the write option.
            Intent soilMoistureData = new Intent(getApplicationContext(), SoilMoistureDataActivity.class);
            startActivity(soilMoistureData);
            finish();
            return true;
        }
        else if (id == R.id.action_WaterSuppyControl){
            // TODO: Start the write option.
            Intent waterSupplyControl = new Intent(getApplicationContext(), WaterSupplyControlActivity.class);
            startActivity(waterSupplyControl);
            finish();
            return true;
        }
        else if (id == R.id.action_Settings){
            // TODO: Start the write option.
            Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(settings);
            finish();
            return true;
        }
        else if (id == R.id.action_Help){
            // TODO: Start the write option.
            Intent help = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(help);
            finish();
            return true;
        }

        else if (id == R.id.action_SignOut){
            // TODO: Start the write option.
            mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            Intent signOut = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(signOut);
            finish();
            return true;
        }
        else if (id == R.id.action_ReadData){
            // TODO: Start the write option.
            Intent readData = new Intent(getApplicationContext(), Read_temperature.class);
            startActivity(readData);
            finish();
            return true;
        }
        else if (id == R.id.action_WriteData){
            // TODO: Start the write option.
            Intent writeData = new Intent(getApplicationContext(), Write_temperature.class);
            startActivity(writeData);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
