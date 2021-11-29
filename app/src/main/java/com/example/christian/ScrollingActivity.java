package com.example.christian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {
    RecyclerView dataList;
    List<String> titles;
    List<Integer> images;
    static String first,second;
    Adapter adapter;
    Resources res;
    DBHelper mydb;
    ArrayList<String> mo = new ArrayList<String>();
    ArrayList<String> mp = new ArrayList<String>();
    ArrayList<String> mm = new ArrayList<String>();
    ArrayList<String> lc = new ArrayList<String>();
    ArrayList<String> dm = new ArrayList<String>();
    ArrayList<String> hd = new ArrayList<String>();
    ArrayList<String> pw = new ArrayList<String>();
    ArrayList<String> cc = new ArrayList<String>();
    ArrayList<String> om = new ArrayList<String>();
    ArrayList<String> kb = new ArrayList<String>();
    ArrayList<String> ms = new ArrayList<String>();
    ArrayList<String> up = new ArrayList<String>();
    ArrayList<String> gq = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        SharedPreferences.Editor editor = getSharedPreferences("com.example.christian", MODE_PRIVATE).edit();
        editor.putString("name", "Elena");
        editor.putInt("idName", 12);
        editor.apply();

        mydb = new DBHelper(this);
        res = new Resources();
        res.res();
        if(res.arraylc.size() == 0){
        //    Toast.makeText(this, "emp", Toast.LENGTH_SHORT).show();
        }else{
          //  Toast.makeText(this, "temp", Toast.LENGTH_SHORT).show();
        }
       // Toast.makeText(this, res.arraylc.toString(), Toast.LENGTH_SHORT).show();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabScan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //extension

        dataList = findViewById(R.id.dataList);

        titles = new ArrayList<>();
        images = new ArrayList<>();


        titles.add("Workstation Reports");
        titles.add("Monthly ups test");
        titles.add("Salary calculator");
        titles.add("Internet utilities");

        images.add(R.drawable.ic_baseline_featured_play_list_24);
        images.add(R.drawable.ic_baseline_fact_check_24);
        images.add(R.drawable.ic_baseline_calculate_24);
        images.add(R.drawable.ic_baseline_network_check_24);



        adapter = new Adapter(this,titles,images);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);


        SharedPreferences prefs = getSharedPreferences("com.example.christian", MODE_PRIVATE);
        boolean resIsUpdated = prefs.getBoolean("updatedRes", false);
        if (resIsUpdated == false){
            insertToDB();
        }else{

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertToDB(){
        if (res.arraymo.size() != 0) {
            for (int i = 0; i < res.arraymo.size(); i++) {
                first = "motherboard_specs";
                second = res.arraymo.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arraypp.size() != 0) {
            for (int i = 0; i < res.arraypp.size(); i++) {
                first = "processor_specs";
                second = res.arraypp.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arraymm.size() != 0) {
            for (int i = 0; i < res.arraymm.size(); i++) {
                first = "memory_specs";
                second = res.arraymm.get(i);
                mydb.addRes(first, second);
            }
        }
        if (res.arraylc.size() != 0) {
            for (int i = 0; i < res.arraylc.size(); i++) {
                first = "lancard_specs";
                second = res.arraylc.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arraydm.size() != 0) {
            for (int i = 0; i < res.arraydm.size(); i++) {
                first = "videocard_specs";
                second = res.arraydm.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arrayhd.size() != 0) {
            for (int i = 0; i < res.arrayhd.size(); i++) {
                first = "harddisk_specs";
                second = res.arrayhd.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arraypw.size() != 0) {
            for (int i = 0; i < res.arraypw.size(); i++) {
                first = "powersupply_specs";
                second = res.arraypw.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arraycc.size() != 0) {
            for (int i = 0; i < res.arraycc.size(); i++) {
                first = "casing_specs";
                second = res.arraycc.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arrayom.size() != 0) {
            for (int i = 0; i < res.arrayom.size(); i++) {
                first = "monitor_specs";
                second = res.arrayom.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arraykb.size() != 0) {
            for (int i = 0; i < res.arraykb.size(); i++) {
                first = "keyboard_specs";
                second = res.arraykb.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arrayms.size() != 0) {
            for (int i = 0; i < res.arrayms.size(); i++) {
                first = "mouse_specs";
                second = res.arrayms.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arrayup.size() != 0) {
            for (int i = 0; i < res.arrayup.size(); i++) {
                first = "ups_specs";
                second = res.arrayup.get(i);
                mydb.addRes(first, second);
            }
        }

        if (res.arraygq.size() != 0) {
            for (int i = 0; i < res.arraygq.size(); i++) {
                first = "battery_specs";
                second = res.arraygq.get(i);
                mydb.addRes(first, second);
            }
        }
        SharedPreferences.Editor editor = getSharedPreferences("com.example.christian", MODE_PRIVATE).edit();
        editor.putBoolean("updatedRes", true);
        editor.apply();
    }
}