package com.example.christian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ups extends AppCompatActivity {
DBHelper mydb;
static int iD;
static String toFetch;
ArrayList<String> dataAcquired;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ups);
        Button buttonAdd = findViewById(R.id.button2);
        mydb = new DBHelper(this);

        Button button4 = findViewById(R.id.button3);


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText te1 = findViewById(R.id.te1);
                TextView te2 = findViewById(R.id.te2);
                String test5 = te1.getText().toString();
                if (test5.length() > 2) {
                    String first2String = test5.substring(0, 2);
                    String finalString = "20" + first2String;
                   // Toast.makeText(Ups.this, test5.toString().length(), Toast.LENGTH_SHORT).show();
                    te2.setText(finalString);
                }else{
                   // Toast.makeText(Ups.this, test5.length(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        try {
            exportCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Button button5 = findViewById(R.id.button4);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int t = 0; t <= 1; t++) {

                    Toast.makeText(Ups.this, t, Toast.LENGTH_SHORT).show();

                }
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyCsvFile.csv"); // Here csv file name is MyCsvFile.csv


                //by Hiting button csv will create inside phone storage.


                        CSVWriter writer = null;
                        try {
                            writer = new CSVWriter(new FileWriter(csv));

                            ArrayList<String> fromDB = new ArrayList<String>();
                            fromDB.add("adw");
                            fromDB.add("asdw");
                            fromDB.add("adw");
                            fromDB.add("asdw");
                            fromDB.add("adw");
                            fromDB.add("asdw");
                            fromDB.add("adw");
                            fromDB.add("asdw");
                            fromDB.add("adw");
                            fromDB.add("asdw");
                            fromDB.add("adw");
                            fromDB.add("asdw");
                            fromDB.add("adw");
                            fromDB.add("asdw");
                            List<String[]> data = new ArrayList<String[]>();

                            data.add(new String[]{fromDB.get(1), fromDB.get(0)});



                          //  data.add(new String[]{"ITEM", "DESCRIPTION","BARCODE", "DATE ACQUIRED","CATEGORY", "STATUS"});
                      /*      List<String[]> data = new ArrayList<String[]>();
                            data.add(new String[]{"Country", "Capital"});
                            data.add(new String[]{"India", "New Delhi"});
                            data.add(new String[]{"United States", "Washington D.C"});
                            data.add(new String[]{"Germany", "Berlin"});
*/
                            writer.writeAll(data); // data is adding to csv

                            writer.close();
                            callRead();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

            private void callRead() {
            }
        });




    }
    public void exportCSV() throws IOException {
        ArrayList<Integer> id = new ArrayList<Integer>();
        id = mydb.idExport();

        String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyCsvFile.csv"); // Here csv file name is MyCsvFile.csv
        CSVWriter writer = null;
        writer = new CSVWriter(new FileWriter(csv,true));
        Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < id.size(); i++) {
            iD = id.get(i);
            String[] val = {"name","motherboard", "processor", "memory1", "memory2", "lancard", "videocard", "harddisk1", "harddisk2", "powersupply", "casing", "monitor1", "monitor2", "keyboard", "mouse", "ups", "battery", "smotherboard", "sprocessor", "smemory1", "smemory2", "slancard", "svideocard", "sharddisk1", "sharddisk2", "spowersupply", "scasing", "smonitor1", "smonitor2", "skeyboard", "smouse", "sups", "sbattery"};
            ArrayList<String> fromDB = new ArrayList<String>();
            dataAcquired = new ArrayList<String>();
            ArrayList<String> status = new ArrayList<String>();
            status.clear();
            dataAcquired.clear();
            fromDB.clear();

            for (int x = 0; x < val.length; x++) {
                toFetch = val[x];
                String fetchedData = mydb.test2();
                fromDB.add(fetchedData);


            }

           for (int z = 1; z <= 16; z++) {
                String barcode;
                barcode = fromDB.get(z);
               Toast.makeText(this, barcode, Toast.LENGTH_SHORT).show();
                if (!barcode.matches("None") || !barcode.matches("")){

                    if (barcode.length() > 2) {
                        String first2String = barcode.substring(0, 2);

                        if (android.text.TextUtils.isDigitsOnly(first2String)){
                            String finalString = "20" + first2String;
                            dataAcquired.add(finalString);
                        }else{
                            dataAcquired.add("None");
                        }

                    }
                }else{

                    dataAcquired.add("None");
                }

            }




            for (int t = 17; t <= 32; t++) {
                String getDescription;
                getDescription = fromDB.get(t);

                if (!getDescription.toString().matches("None") || !getDescription.matches("")){
                    status.add("OK");
                }
                if (getDescription.matches("None") || getDescription.matches("")){
                    status.add("None");
                }
                 else{
                    status.add("None");
                }

            }




                List<String[]> dataOutput = new ArrayList<String[]>();
                dataOutput.add(new String[]{"","Christian Villablanca", "PERIOD COVERED: APRIL – JUNE 2019"});
                dataOutput.add(new String[]{"","ITEM", "DESCRIPTION","BARCODE", "DATE ACQUIRED","CATEGORY", "STATUS"});
                dataOutput.add(new String[]{fromDB.get(0),"MOTHERBOARD", fromDB.get(17),fromDB.get(1),dataAcquired.get(0),"",status.get(0)});
                dataOutput.add(new String[]{fromDB.get(0),"PROCESSOR", fromDB.get(18),fromDB.get(2),dataAcquired.get(1),"",status.get(1)});
                dataOutput.add(new String[]{fromDB.get(0),"MEMORY1", fromDB.get(19),fromDB.get(3),dataAcquired.get(2),"",status.get(2)});
                dataOutput.add(new String[]{fromDB.get(0),"MEMORY2", fromDB.get(20),fromDB.get(4), dataAcquired.get(3),"",status.get(3)});
                dataOutput.add(new String[]{fromDB.get(0),"LAN CARD", fromDB.get(21),fromDB.get(5),dataAcquired.get(4),"",status.get(4)});
                dataOutput.add(new String[]{fromDB.get(0),"VIDEO CARD", fromDB.get(22),fromDB.get(6),dataAcquired.get(5),"",status.get(5)});
                dataOutput.add(new String[]{fromDB.get(0),"HARD DISK1", fromDB.get(23),fromDB.get(7),dataAcquired.get(6),"",status.get(6)});
                dataOutput.add(new String[]{fromDB.get(0),"HARD DISK2", fromDB.get(24),fromDB.get(8),dataAcquired.get(7),"",status.get(7)});
                dataOutput.add(new String[]{fromDB.get(0),"POWER SUPPLY", fromDB.get(25),fromDB.get(9),dataAcquired.get(8),"",status.get(8)});
                dataOutput.add(new String[]{fromDB.get(0),"CASING", fromDB.get(26),fromDB.get(10),dataAcquired.get(9),"",status.get(9)});
                dataOutput.add(new String[]{fromDB.get(0),"MONITOR1", fromDB.get(27),fromDB.get(11),dataAcquired.get(10),"",status.get(10)});
                dataOutput.add(new String[]{fromDB.get(0),"MONITOR2", fromDB.get(28),fromDB.get(12),dataAcquired.get(11),"",status.get(11)});
                dataOutput.add(new String[]{fromDB.get(0),"KEYBOARD", fromDB.get(29),fromDB.get(13),dataAcquired.get(12),"",status.get(12)});
                dataOutput.add(new String[]{fromDB.get(0),"MOUSE", fromDB.get(30),fromDB.get(14),dataAcquired.get(13),"",status.get(13)});
                dataOutput.add(new String[]{fromDB.get(0),"UPS", fromDB.get(31),fromDB.get(15),dataAcquired.get(14),"",status.get(14)});
                dataOutput.add(new String[]{fromDB.get(0),"UPS BATTERY", fromDB.get(32),fromDB.get(16),dataAcquired.get(15),"",status.get(15)});
                dataOutput.add(new String[]{""});
                dataOutput.add(new String[]{""});


                writer.writeAll(dataOutput); // data is adding to csv



        }
        writer.close();
    }

        }








