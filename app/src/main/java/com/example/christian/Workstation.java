package com.example.christian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.opencsv.CSVWriter;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Workstation extends AppCompatActivity {


    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    DBHelper mydb;
    static ArrayList array_list;
    static Boolean add;
    CoordinatorLayout coordinatorLayout;
    SearchView searchView;
    FloatingActionButton fabSearch;
    ArrayList<String> dataAcquired;
    static int iD;
    static String toFetch;
    static String action="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workstation);

        mydb = new DBHelper(this);
        array_list = mydb.getAllWorkstations();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        searchView = findViewById(R.id.searchView);
        fabSearch = findViewById(R.id.fabSearch);



        LinearLayout exportLayout = (LinearLayout) findViewById(R.id.exportLayout);
        exportLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("export");
            }
        });

        LinearLayout addLayout = (LinearLayout) findViewById(R.id.addLayout);
        addLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);
                add = true;
                Intent intent = new Intent(getApplicationContext(), WorkstationInfo.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
            }
        });

        if (action.matches("next")){
            Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
            addLayout.performClick();

        }


        obj = (ListView) findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int toSearch = arg2 + 1;
                Object o = obj.getAdapter().getItem(arg2);
                String nameToFetch = o.toString();


                add = false;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", toSearch);
                dataBundle.putString("name",nameToFetch);
                Intent intent = new Intent(getApplicationContext(), WorkstationInfo.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });


        searchView.setVisibility(View.GONE);
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setVisibility(View.VISIBLE);
                searchView.requestFocus();
                searchView.setIconified(false);
                searchView.setQueryHint("Search workstation");
                coordinatorLayout.setVisibility(View.GONE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                coordinatorLayout.setVisibility(View.VISIBLE);
                searchView.setVisibility(View.GONE);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });



        Intent intent = getIntent();
        View view = findViewById(android.R.id.content);
        Boolean showSnackbar;
        showSnackbar = intent.getBooleanExtra("showSnackbar",false);


        if (showSnackbar == true){
            if (WorkstationInfo.state.equals("Done")){
                Snackbar.make(view, WorkstationInfo.terminal+" has been successfully added", Snackbar.LENGTH_LONG).setAnchorView(R.id.fabSearch).show();
            }
            if (WorkstationInfo.state.equals("Failed")){
                Snackbar.make(view, "Failed", Snackbar.LENGTH_LONG).setAnchorView(R.id.fabSearch).show();
            }
            if (WorkstationInfo.state.equals("Updated")){
                Snackbar.make(view, "Updated", Snackbar.LENGTH_LONG).setAnchorView(R.id.fabSearch).show();
            }
            if (WorkstationInfo.state.equals("Deleted")){
                Snackbar.make(view, "Deleted successfully", Snackbar.LENGTH_LONG).setAnchorView(R.id.fabSearch).show();
            }

        }



    }



    public void showDialog(String str){

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        String str1 = null;
        String exportPath = (Environment.getExternalStorageDirectory().getAbsolutePath());


        if (str.matches("import")){
            builder.setTitle("Import");
            str1 = "import";
        }else if (str.matches("export")){
            builder.setTitle("Export as csv");
            str1 = "export";
        }

        LayoutInflater inflater = getLayoutInflater();
        View myLayout = inflater.inflate(R.layout.csv, null);
        TextView pathName= (TextView)myLayout.findViewById(R.id.pathName);
        EditText fileName= (EditText)myLayout.findViewById(R.id.fileName);
        pathName.setText(exportPath);
        fileName.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                pathName.setText(exportPath+"/"+s+".csv");

            }



            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });




        builder.setView(myLayout)


                .setPositiveButton(str1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {




                check();

                   // dialog.dismiss();




            }
        });


    }
    
    public void check(){
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        }
        else
        {

            // Code for Below 23 API Oriented Device
            // Do next code
        }

    }



    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Workstation.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Workstation.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(Workstation.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            Toast.makeText(Workstation.this, "Storage permission is denied. Please enable it manually ha app permission > storage. Damo nga salamat", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Workstation.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;
        }
    }
    

    public void exportCSV() throws IOException {
        ArrayList<Integer> id = new ArrayList<Integer>();
        id = mydb.idExport();

        String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyCsvFile.csv"); // Here csv file name is MyCsvFile.csv
        CSVWriter writer = null;
        writer = new CSVWriter(new FileWriter(csv,true));

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















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);
                add = true;
                Intent intent = new Intent(getApplicationContext(), WorkstationInfo.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            case R.id.item2:
                Intent intent1 = new Intent(getApplicationContext(), Ups.class);


                startActivity(intent1);
             //   exportDB();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }




/*    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
*/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Workstation.this,ScrollingActivity.class);
        startActivity(intent);
        finish();

    }


}




