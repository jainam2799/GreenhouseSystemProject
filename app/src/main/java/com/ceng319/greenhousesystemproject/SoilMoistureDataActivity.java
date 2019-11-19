package com.ceng319.greenhousesystemproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SoilMoistureDataActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    BarChart chart2 ;
    ArrayList<BarEntry> BARENTRY2 ;
    ArrayList<String> BarEntryLabels2 ;
    BarDataSet Bardataset2 ;
    BarData BARDATA2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_moisture_data);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chart2 = (BarChart) findViewById(R.id.chart2);


        BARENTRY2 = new ArrayList<>();

        BarEntryLabels2 = new ArrayList<String>();



        AddValuesToBARENTRY2();

        AddValuesToBarEntryLabels2();



        Bardataset2 = new BarDataSet(BARENTRY2, "Soil Moisture Data");

        BARDATA2 = new BarData(BarEntryLabels2, Bardataset2);

        Bardataset2.setColors(ColorTemplate.COLORFUL_COLORS);

        chart2.setData(BARDATA2);

        chart2.animateY(3000);

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



    public void AddValuesToBARENTRY2(){

        BARENTRY2.add(new BarEntry(35f, 0));
        BARENTRY2.add(new BarEntry(45f, 1));
        BARENTRY2.add(new BarEntry(68f, 2));
        BARENTRY2.add(new BarEntry(59f, 3));
        BARENTRY2.add(new BarEntry(70f, 4));
        BARENTRY2.add(new BarEntry(45f, 5));
        BARENTRY2.add(new BarEntry(35f, 6));

    }
    public void AddValuesToBarEntryLabels2(){

        BarEntryLabels2.add("Monday");
        BarEntryLabels2.add("Tuesday");
        BarEntryLabels2.add("Wednesday");
        BarEntryLabels2.add("Thursday");
        BarEntryLabels2.add("Friday");
        BarEntryLabels2.add("Saturday");
        BarEntryLabels2.add("Sunday");

    }
}