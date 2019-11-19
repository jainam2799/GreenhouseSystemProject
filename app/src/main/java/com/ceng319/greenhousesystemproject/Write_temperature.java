package com.ceng319.greenhousesystemproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ceng319.greenhousesystemproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Write_temperature extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button save;
    private EditText name;
    private EditText temperature;
    private EditText humidity;
    private EditText message;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    DataStructure mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_temperature);
        this.setTitle("Write to Database");

        findAllViews();
        getDatabase();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData(name.getText(), temperature.getText(), humidity.getText(), message.getText());
            }
        });
    }

    private void getDatabase(){
        // TODO: Find the reference form the database.
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String path = "userdata/" + mAuth.getUid();  // Write to the user account.
        myRef = database.getReference(path);

    }

    private DataStructure createData(Editable name, Editable temperature, Editable humidity, Editable message){
        // TODO: Get the timestamp
        Long time = System.currentTimeMillis()/1000;
        String timestamp = time.toString();
        return new DataStructure(String.valueOf(name),
                String.valueOf(temperature),
                String.valueOf(humidity),
                String.valueOf(message),
                timestamp);
    }


    private void writeData(Editable name, Editable temperature, Editable humidity, Editable message) {

        DataStructure mData = createData(name, temperature, humidity, message);
        // Select one of the following methods to update the data.
        // 1. To set the value of data
        // myRef.setValue(mData);
        // 2. To create a new node on database.
        //  myRef.push().setValue(mData);
        // TODO: Write the data to the database.
        // 3. To create a new node on database and detect if the writing is successful.
        myRef.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Value was set. ", Toast.LENGTH_LONG).show();
                gotoRead();  // after write the data, read it from another screen.
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Writing failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Find all the views for this activity.
    private void findAllViews(){
        save = findViewById(R.id.save);
        name = findViewById(R.id.name);
        temperature = findViewById(R.id.temperature);
        humidity=findViewById(R.id.soilmoisturepercentage);
        message = findViewById(R.id.log);
    }
    private void gotoRead() {
        // TODO : Start the read option After login
        Intent intent1 = new Intent(getApplicationContext(), Read_temperature.class);
        startActivity(intent1);
        finish();
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
