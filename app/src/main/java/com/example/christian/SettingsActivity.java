package com.example.christian;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    Switch defaultSwitch,autoAddSwitch;
    DBHelper mydb;
    Spinner moCS,ppCS,mmCS,dmCS,lcCS,hdCS,pwCS,ccCS,omCS,kbCS,msCS,upCS,gqCS;
    static String keySpec;
    static String mInput = "";
    String name1,name2,name3,name4,name5,name6,name7,name8,name9,name10,name11,name12,name13;
    int lastVal = 0;
    String contxt;
    TextView textValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        textValue = findViewById(R.id.textValue);


        mydb = new DBHelper(this);
        defaultSwitch = findViewById(R.id.switch2);
        autoAddSwitch = findViewById(R.id.autoAddSwitch);
        mydb.getDataToStore();

        SharedPreferences prefs = getSharedPreferences("com.example.christian", MODE_PRIVATE);

        boolean defaultIsOnState = prefs.getBoolean("defaultIsOn", false);
        defaultSwitch.setChecked(prefs.getBoolean("defaultIsOn",false));
        boolean autoAddIsOnState = prefs.getBoolean("autoAddIsOn", false);
        autoAddSwitch.setChecked(prefs.getBoolean("autoAddIsOn",false));
        LinearLayout custom = findViewById(R.id.custom);


        if (defaultIsOnState == true){

            custom.setVisibility(View.GONE);
            TextView isCheckedT = findViewById(R.id.isCheckedT);
            isCheckedT.setVisibility(View.VISIBLE);
        }else{
            custom.setVisibility(View.VISIBLE);
            TextView isCheckedT = findViewById(R.id.isCheckedT);
            isCheckedT.setVisibility(View.GONE);
        }

        if (autoAddIsOnState == true){
            LinearLayout layout = findViewById(R.id.autoAddLayout);
            layout.setVisibility(View.VISIBLE);
        }else{
            LinearLayout layout = findViewById(R.id.autoAddLayout);
            layout.setVisibility(View.GONE);
        }

        SharedPreferences prefs1 = getSharedPreferences("com.example.christian", MODE_PRIVATE);
        name1 = prefs1.getString("motherboard", "None");
        name2 = prefs1.getString("processor", "None");
        name3 = prefs1.getString("memory", "None");
        name4 = prefs1.getString("lancard", "None");
        name5 = prefs1.getString("videocard", "None");
        name6 = prefs1.getString("harddisk", "None");
        name7 = prefs1.getString("powersupply", "None");
        name8 = prefs1.getString("casing", "None");
        name9 = prefs1.getString("monitor", "None");
        name10 = prefs1.getString("keyboard", "None");
        name11 = prefs1.getString("mouse", "None");
        name12 = prefs1.getString("ups", "None");
        name13 = prefs1.getString("battery", "None");
        Toast.makeText(this, name3, Toast.LENGTH_SHORT).show();





        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



       defaultSwitch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


           }
       });













        textValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue();
            }
        });


        autoAddSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    LinearLayout layout = findViewById(R.id.autoAddLayout);
                    layout.setVisibility(View.VISIBLE);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.christian", MODE_PRIVATE).edit();
                    editor.putBoolean("autoAddIsOn", true);
                    editor.commit();

                }else{
                    LinearLayout layout = findViewById(R.id.autoAddLayout);
                    layout.setVisibility(View.GONE);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.christian", MODE_PRIVATE).edit();
                    editor.putBoolean("autoAddIsOn", false);
                    editor.commit();
                }

            }
        });

        defaultSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){

                    custom.setVisibility(View.GONE);
                    TextView isCheckedT = findViewById(R.id.isCheckedT);
                    isCheckedT.setVisibility(View.VISIBLE);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.christian", MODE_PRIVATE).edit();
                    editor.putBoolean("defaultIsOn", true);
                    editor.commit();
                } else {

                    custom.setVisibility(View.VISIBLE);
                    TextView isCheckedT = findViewById(R.id.isCheckedT);
                    isCheckedT.setVisibility(View.GONE);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.christian", MODE_PRIVATE).edit();
                    editor.putBoolean("defaultIsOn", false);
                    editor.commit();
                }
            }
        });


            loopIntoSpecs();
    }
    public void test() {
      //  mydb.getDataToStore();
      //  ArrayList array_list1 = mydb.getData_Item();
        //   ArrayList array_list1 = mydb.getSpecs();
      //  ArrayAdapter arrayAdapter1=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list1);



    }







public void loopIntoSpecs(){
    SharedPreferences.Editor editor = getSharedPreferences("com.example.christian", MODE_PRIVATE).edit();




    String[] itemType = {"motherboard_specs", "processor_specs", "memory_specs", "lancard_specs", "videocard_specs", "harddisk_specs", "powersupply_specs", "casing_specs", "monitor_specs", "keyboard_specs", "mouse_specs", "ups_specs", "battery_specs"};

    for (int x = 0; x < itemType.length; x++) {
        keySpec = itemType[x];
        switch (keySpec) {
            case "motherboard_specs":
                moCS = (Spinner)findViewById(R.id.moCS);
                ArrayList<String> array1 = new ArrayList<String>();
                array1.clear();
                array1.add("None");
                array1.addAll(mydb.getSpecsFromTableSpecs());
                array1.add("Add new");
                Integer val1 = array1.size() -1;


                ArrayAdapter<String> moadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array1);
                moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                moCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {

                        if (val1 == position){
                            keySpec = "motherboard_specs";
                            contxt = "motherboard";
                            addNew();
                            moCS.setSelection(lastVal);
                        }else{
                            lastVal = position;
                            editor.putString("motherboard", moCS.getSelectedItem().toString());
                            editor.apply();
                        }

                        


                    }
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });


                moCS.setAdapter(moadapter);
                moCS.setSelection(moadapter.getPosition(name1));

               break;

            case "processor_specs":
                ppCS = (Spinner)findViewById(R.id.ppCS);
                ArrayList<String> array2 = new ArrayList<String>();
                array2.clear();
                array2.add("None");
                array2.addAll(mydb.getSpecsFromTableSpecs());
                array2.add("Add new");
                Integer val2 = array2.size() -1;


                ArrayAdapter<String> ppadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array2);
                ppadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                ppCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {


                            if (val2 == position){
                                contxt ="processor";
                                keySpec = "processor_specs";
                                addNew();

                                ppCS.setSelection(lastVal);
                            }else{
                                lastVal = position;
                                editor.putString("processor", ppCS.getSelectedItem().toString());
                                editor.apply();
                            }
                        }



                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                ppCS.setAdapter(ppadapter);
                ppCS.setSelection(ppadapter.getPosition(name2));
                break;

            case "memory_specs":

                mmCS = (Spinner)findViewById(R.id.mmCS);
                ArrayList<String> array3 = new ArrayList<String>();
                array3.clear();
                array3.add("None");
                array3.addAll(mydb.getSpecsFromTableSpecs());
                array3.add("Add new");
                Integer val3 = array3.size() -1;


                ArrayAdapter<String> mmadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array3);
                mmadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                mmCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {



                        if (val3 == position){
                            contxt = "memory";
                            keySpec = "memory_specs";
                            addNew();
                            mmCS.setSelection(lastVal);

                        }else{
                            lastVal = position;
                            editor.putString("memory", mmCS.getSelectedItem().toString());
                            editor.apply();
                        }




                    }
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                mmCS.setAdapter(mmadapter);
                mmCS.setSelection(mmadapter.getPosition(name3));

                break;

            case "lancard_specs":
                lcCS = (Spinner)findViewById(R.id.lcCS);
                ArrayList<String> array4 = new ArrayList<String>();
                array4.clear();
                array4.add("None");
                array4.addAll(mydb.getSpecsFromTableSpecs());
                array4.add("Add new");

                Integer val4 = array4.size() -1;


                ArrayAdapter<String> lcadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array4);
                lcadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                lcCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {


                        if (val4 == position){
                            contxt ="lancard";
                            keySpec = "lancard_specs";
                            addNew();

                            lcCS.setSelection(lastVal);
                        }else{
                            lastVal = position;
                            editor.putString("lancard", lcCS.getSelectedItem().toString());
                            editor.apply();
                        }
                    }



                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                lcCS.setAdapter(lcadapter);
                lcCS.setSelection(lcadapter.getPosition(name4));
                break;

            case "videocard_specs":
                dmCS = (Spinner)findViewById(R.id.dmCS);
                ArrayList<String> array5 = new ArrayList<String>();
                array5.clear();
                array5.add("None");
                array5.addAll(mydb.getSpecsFromTableSpecs());
                array5.add("Add new");



                Integer val5 = array5.size() -1;


                ArrayAdapter<String> dmadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array5);
                dmadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                dmCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {



                        if (val5 == position){
                            contxt = "videocard";
                            keySpec = "videocard_specs";
                            addNew();

                            dmCS.setSelection(lastVal);
                        }else{
                            lastVal = position;
                            editor.putString("videocard", dmCS.getSelectedItem().toString());
                            editor.apply();
                        }




                    }
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                dmCS.setAdapter(dmadapter);
                dmCS.setSelection(dmadapter.getPosition(name5));

                break;


            case "harddisk_specs":
                hdCS = (Spinner)findViewById(R.id.hdCS);
                ArrayList<String> array6 = new ArrayList<String>();
                array6.clear();
                array6.add("None");
                array6.addAll(mydb.getSpecsFromTableSpecs());
                array6.add("Add new");



                Integer val6 = array6.size() -1;


                ArrayAdapter<String> hdadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array6);
                hdadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                hdCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {



                        if (val6 == position){
                            contxt = "harddisk";
                            keySpec = "harddisk_specs";
                            addNew();

                            hdCS.setSelection(lastVal);
                        }else{
                            lastVal = position;
                            editor.putString("harddisk", hdCS.getSelectedItem().toString());
                            editor.apply();
                        }




                    }
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                hdCS.setAdapter(hdadapter);
                hdCS.setSelection(hdadapter.getPosition(name6));

                break;


            case "powersupply_specs":
                pwCS = (Spinner)findViewById(R.id.pwCS);
                ArrayList<String> array7 = new ArrayList<String>();
                array7.clear();
                array7.add("None");
                array7.addAll(mydb.getSpecsFromTableSpecs());
                array7.add("Add new");
                Integer val7 = array7.size() -1;


                ArrayAdapter<String> pwadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array7);
                pwadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                pwCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {

                        if (val7 == position){
                            keySpec = "powersupply_specs";
                            contxt = "powersupply";
                            addNew();
                            pwCS.setSelection(lastVal);
                        }else{
                            lastVal = position;
                            editor.putString("powersupply", pwCS.getSelectedItem().toString());
                            editor.apply();
                        }




                    }
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });


                pwCS.setAdapter(pwadapter);
                pwCS.setSelection(pwadapter.getPosition(name7));

                break;

            case "casing_specs":
                ccCS = (Spinner)findViewById(R.id.ccCS);
                ArrayList<String> array8 = new ArrayList<String>();
                array8.clear();
                array8.add("None");
                array8.addAll(mydb.getSpecsFromTableSpecs());
                array8.add("Add new");
                Integer val8 = array8.size() -1;


                ArrayAdapter<String> ccadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array8);
                ccadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                ccCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {


                        if (val8 == position){
                            contxt ="casing";
                            keySpec = "casing_specs";
                            addNew();

                            ccCS.setSelection(lastVal);
                        }else{
                            lastVal = position;
                            editor.putString("casing", ccCS.getSelectedItem().toString());
                            editor.apply();
                        }
                    }



                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                ccCS.setAdapter(ccadapter);
                ccCS.setSelection(ccadapter.getPosition(name8));
                break;

            case "monitor_specs":

                omCS = (Spinner)findViewById(R.id.omCS);
                ArrayList<String> array9 = new ArrayList<String>();
                array9.clear();
                array9.add("None");
                array9.addAll(mydb.getSpecsFromTableSpecs());
                array9.add("Add new");
                Integer val9 = array9.size() -1;


                ArrayAdapter<String> omadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array9);
                omadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                omCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {



                        if (val9 == position){
                            contxt = "monitor";
                            keySpec = "monitor_specs";
                            addNew();
                            omCS.setSelection(lastVal);

                        }else{
                            lastVal = position;
                            editor.putString("monitor", omCS.getSelectedItem().toString());
                            editor.apply();
                        }




                    }
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                omCS.setAdapter(omadapter);
                omCS.setSelection(omadapter.getPosition(name9));


                break;

            case "keyboard_specs":
                kbCS = (Spinner)findViewById(R.id.kbCS);
                ArrayList<String> array10 = new ArrayList<String>();
                array10.clear();
                array10.add("None");
                array10.addAll(mydb.getSpecsFromTableSpecs());
                array10.add("Add new");

                Integer val10 = array10.size() -1;


                ArrayAdapter<String> kbadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array10);
                kbadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                kbCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {


                        if (val10 == position){
                            contxt ="keyboard";
                            keySpec = "keyboard_specs";
                            addNew();

                            kbCS.setSelection(lastVal);
                        }else{
                            lastVal = position;
                            editor.putString("keyboard", kbCS.getSelectedItem().toString());
                            editor.apply();
                        }
                    }



                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                kbCS.setAdapter(kbadapter);
                kbCS.setSelection(kbadapter.getPosition(name10));
                break;

            case "mouse_specs":
                msCS = (Spinner)findViewById(R.id.msCS);
                ArrayList<String> array11 = new ArrayList<String>();
                array11.clear();
                array11.add("None");
                array11.addAll(mydb.getSpecsFromTableSpecs());
                array11.add("Add new");



                Integer val11 = array11.size() -1;


                ArrayAdapter<String> msadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array11);
                msadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                msCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {



                        if (val11 == position){
                            contxt = "mouse";
                            keySpec = "mouse_specs";
                            addNew();

                            msCS.setSelection(lastVal);
                        }else{
                            lastVal = position;
                            editor.putString("mouse", msCS.getSelectedItem().toString());
                            editor.apply();
                        }




                    }
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                msCS.setAdapter(msadapter);
                msCS.setSelection(msadapter.getPosition(name11));

                break;

            case "ups_specs":
                upCS = (Spinner)findViewById(R.id.upCS);
                ArrayList<String> array12 = new ArrayList<String>();
                array12.clear();
                array12.add("None");
                array12.addAll(mydb.getSpecsFromTableSpecs());
                array12.add("Add new");



                Integer val12 = array12.size() -1;


                ArrayAdapter<String> upadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array12);
                upadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                upCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {



                        if (val12 == position){
                            contxt = "ups";
                            keySpec = "ups_specs";
                            addNew();

                            upCS.setSelection(lastVal);
                        }else{
                            lastVal = position;
                            editor.putString("ups", upCS.getSelectedItem().toString());
                            editor.apply();
                        }




                    }
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                upCS.setAdapter(upadapter);
                upCS.setSelection(upadapter.getPosition(name12));

                break;

            case "battery_specs":
                gqCS = (Spinner)findViewById(R.id.gqCS);
                ArrayList<String> array13 = new ArrayList<String>();
                array13.clear();
                array13.add("None");
                array13.addAll(mydb.getSpecsFromTableSpecs());
                array13.add("Add new");



                Integer val13 = array13.size() -1;


                ArrayAdapter<String> gqadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array13);
                gqadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                gqCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {



                        if (val13 == position){
                            contxt = "battery";
                            keySpec = "battery_specs";
                            addNew();

                            gqCS.setSelection(lastVal);
                        }else{
                            lastVal = position;
                            editor.putString("battery", gqCS.getSelectedItem().toString());
                            editor.apply();
                        }




                    }
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

                gqCS.setAdapter(gqadapter);
                gqCS.setSelection(gqadapter.getPosition(name13));

                break;



        }
    }
    }
    public void setCustom(){

    }

    public void setValue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
      //  builder.setTitle("Add specs in "+contxt);


        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
                //  finish();
                //  startActivity(getIntent());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }




 public void addNew(){
     AlertDialog.Builder builder = new AlertDialog.Builder(this);
     builder.setTitle("Add specs in "+contxt);


     final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
     input.setInputType(InputType.TYPE_CLASS_TEXT);
     builder.setView(input);

// Set up the buttons
     builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             mInput = input.getText().toString();
             mydb.AddSpecs(keySpec,mInput);
             recreate();
           //  finish();
           //  startActivity(getIntent());
         }
     });
     builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             dialog.cancel();
         }
     });

     builder.show();
 }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
