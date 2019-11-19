package com.ceng319.greenhousesystemproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
