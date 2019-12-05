package com.ceng319.greenhousesystemproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainReadingsPageActivity extends AppCompatActivity{

    BarChart chart1 ;
    BarChart chart2 ;
    ArrayList<BarEntry> BARENTRY1 ;
    ArrayList<String> BarEntryLabels1 ;
    BarDataSet Bardataset1 ;
    BarData BARDATA1 ;
    ArrayList<BarEntry> BARENTRY2 ;
    ArrayList<String> BarEntryLabels2 ;
    BarDataSet Bardataset2 ;
    BarData BARDATA2 ;
    Button btn1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_readings_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //  btn1 = (Button) findViewById(R.id.tmpbtn);
        chart1 = (BarChart) findViewById(R.id.chart1);
        chart2 = (BarChart) findViewById(R.id.chart2);

        BARENTRY1 = new ArrayList<>();

        BarEntryLabels1 = new ArrayList<String>();

        BARENTRY2 = new ArrayList<>();

        BarEntryLabels2 = new ArrayList<String>();

        AddValuesToBARENTRY1();

        AddValuesToBarEntryLabels1();

        AddValuesToBARENTRY2();

        AddValuesToBarEntryLabels2();

        Bardataset1 = new BarDataSet(BARENTRY1, "Temperature Data in °C");
//Change was made here after implementing firbase_bar
        BARDATA1 = new BarData( Bardataset1);

        Bardataset1.setColors(ColorTemplate.COLORFUL_COLORS);

        Bardataset2 = new BarDataSet(BARENTRY2, "Soil Moisture Data");

        BARDATA2 = new BarData( Bardataset2);

        Bardataset2.setColors(ColorTemplate.COLORFUL_COLORS);

        chart1.setData(BARDATA1);
        chart2.setData(BARDATA2);

        chart1.animateY(3000);
        chart2.animateY(3000);

     /*   btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_register);
                Intent tmp_data = new Intent(HomeActivity.this, Temperature_data.class);
                startActivity(tmp_data);
            }
        });*/

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
            //finish();
            return true;
        }
        else if (id == R.id.action_TemperatureData)
        {
            // TODO: Start the read option.
            Intent temperatureData = new Intent(getApplicationContext(), TemperatureDataActivity.class);
            startActivity(temperatureData);
            //finish();
            return true;
        }
        else if (id == R.id.action_SoilMoistureData){
            // TODO: Start the write option.
            Intent soilMoistureData = new Intent(getApplicationContext(), SoilMoistureDataActivity.class);
            startActivity(soilMoistureData);
            //finish();
            return true;
        }
        else if (id == R.id.action_FirebaseBar){
            // TODO: Start the write option.
            Intent FireBaseBar = new Intent(getApplicationContext(), Firebase_Bar.class);
            startActivity(FireBaseBar);
            //finish();
            return true;
        }
        else if (id == R.id.action_WaterSuppyControl){
            // TODO: Start the write option.
            Intent waterSupplyControl = new Intent(getApplicationContext(), WaterSupplyControlActivity.class);
            startActivity(waterSupplyControl);
            //finish();
            return true;
        }
        else if (id == R.id.action_Settings){
            // TODO: Start the write option.
            Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(settings);
            //finish();
            return true;
        }
        else if (id == R.id.action_Help){
            // TODO: Start the write option.
            Intent help = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(help);
            //finish();
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
            //finish();
            return true;
        }
        else if (id == R.id.action_WriteData){
            // TODO: Start the write option.
            Intent writeData = new Intent(getApplicationContext(), Write_temperature.class);
            startActivity(writeData);
            //finish();
            return true;
        }
        else if (id == R.id.action_DisplayReadings){
            // TODO: Start the write option.
            Intent displayReadings = new Intent(getApplicationContext(), DisplayTempAndMoistureReadings.class);
            startActivity(displayReadings);
            //finish();
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