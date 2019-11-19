package com.ceng319.greenhousesystemproject;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class TemperatureDataActivity extends AppCompatActivity {

    BarChart chart1 ;
    ArrayList<BarEntry> BARENTRY1 ;
    ArrayList<String> BarEntryLabels1 ;
    BarDataSet Bardataset1 ;
    BarData BARDATA1 ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_data);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chart1 = (BarChart) findViewById(R.id.chart1);
        BARENTRY1 = new ArrayList<>();
        BarEntryLabels1 = new ArrayList<String>();

        AddValuesToBARENTRY1();
        AddValuesToBarEntryLabels1();

        Bardataset1 = new BarDataSet(BARENTRY1, "Temperature Data in °C");
        BARDATA1 = new BarData(BarEntryLabels1, Bardataset1);
        Bardataset1.setColors(ColorTemplate.COLORFUL_COLORS);
        chart1.setData(BARDATA1);
        chart1.animateY(3000);

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





    public void AddValuesToBarEntryLabels1(){

        BarEntryLabels1.add("Monday");
        BarEntryLabels1.add("Tuesday");
        BarEntryLabels1.add("Wednesday");
        BarEntryLabels1.add("Thursday");
        BarEntryLabels1.add("Friday");
        BarEntryLabels1.add("Saturday");
        BarEntryLabels1.add("Sunday");

    }
    public void AddValuesToBARENTRY1(){

        BARENTRY1.add(new BarEntry(23f, 0));
        BARENTRY1.add(new BarEntry(18f, 1));
        BARENTRY1.add(new BarEntry(17f, 2));
        BARENTRY1.add(new BarEntry(21f, 3));
        BARENTRY1.add(new BarEntry(25, 4));
        BARENTRY1.add(new BarEntry(15, 5));
        BARENTRY1.add(new BarEntry(20, 6));
    }

}
