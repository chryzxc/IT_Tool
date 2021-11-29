package com.example.christian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import static com.example.christian.Workstation.add;


public class WorkstationInfo extends AppCompatActivity implements View.OnClickListener {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb;
    static String keySpecs,state,terminal,textwnameD,textwnumberD,mInput,contxt,stringNext;
    View view;
    LinearLayout nextLayout;
    int lastVal = 0;

    String nextWorkstationName;

    LinearLayout layoutNext;
    Spinner smoS, sppS, smm1S, smm2S, slcS, sdmS, shd1S, shd2S, spwS, sccS, som1S, som2S, skbS, smsS, supS, sgqS;


    //barcode
    TextView wname, moT, ppT, mm1T, mm2T, lcT, dmT, hd1T, hd2T, pwT, ccT, om1T, om2T, kbT, msT, upT, gqT;
    //specs
    String smoT, sppT, smm1T, smm2T, slcT, sdmT, shd1T, shd2T, spwT, sccT, som1T, som2T, skbT, smsT, supT, sgqT;
    int id_To_Update = 0;
    String workstationToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workstation_info);

        wname = (TextView) findViewById(R.id.wname);

        moT = (TextView) findViewById(R.id.moB);
        ppT = (TextView) findViewById(R.id.ppB);
        mm1T = (TextView) findViewById(R.id.mm1B);
        mm2T = (TextView) findViewById(R.id.mm2B);
        lcT = (TextView) findViewById(R.id.lcB);
        dmT = (TextView) findViewById(R.id.dmB);
        hd1T = (TextView) findViewById(R.id.hd1B);
        hd2T = (TextView) findViewById(R.id.hd2B);
        pwT = (TextView) findViewById(R.id.pwB);
        ccT = (TextView) findViewById(R.id.ccB);
        om1T = (TextView) findViewById(R.id.om1B);
        om2T = (TextView) findViewById(R.id.om2B);
        kbT = (TextView) findViewById(R.id.kbB);
        msT = (TextView) findViewById(R.id.msB);
        upT = (TextView) findViewById(R.id.upB);
        gqT = (TextView) findViewById(R.id.gqB);

        smoS = (Spinner)findViewById(R.id.moS);
        sppS = (Spinner)findViewById(R.id.ppS);
        smm1S = (Spinner)findViewById(R.id.mm1S);
        smm2S = (Spinner)findViewById(R.id.mm2S);
        slcS = (Spinner)findViewById(R.id.lcS);
        sdmS = (Spinner)findViewById(R.id.dmS);
        shd1S = (Spinner)findViewById(R.id.hd1S);
        shd2S = (Spinner)findViewById(R.id.hd2S);
        spwS = (Spinner)findViewById(R.id.pwS);
        sccS = (Spinner)findViewById(R.id.ccS);
        som1S = (Spinner)findViewById(R.id.om1S);
        som2S = (Spinner)findViewById(R.id.om2S);
        skbS = (Spinner)findViewById(R.id.kbS);
        smsS = (Spinner)findViewById(R.id.msS);
        supS = (Spinner)findViewById(R.id.upS);
        sgqS = (Spinner)findViewById(R.id.gqS);

       nextLayout = findViewById(R.id.nextLayout);
        nextLayout.setVisibility(View.GONE);
        view = findViewById(android.R.id.content);

        nextLayout = findViewById(R.id.nextLayout);
        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            String nameToFetch = extras.getString("name");


            if (Value > 0) {
                workstationToUpdate = nameToFetch;

                Cursor rs = mydb.getData(nameToFetch);

                id_To_Update = Value;

                rs.moveToFirst();

                String name = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_NAME));
                String moS = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_MOTHERBOARD));
                String ppS = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_PROCESSOR));
                String mm1S = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_MEMORY1));
                String mm2S = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_MEMORY2));
                String lcS = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_LAN_CARD));
                String dmS = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_VIDEO_CARD));
                String hd1S = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_HARD_DISK1));
                String hd2S = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_HARD_DISK2));
                String pwS = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_POWERSUPPLY));
                String ccS = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_CASING));
                String om1S = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_MONITOR1));
                String om2S = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_MONITOR2));
                String kbS = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_KEYBOARD));
                String msS = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_MOUSE));
                String upS = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_UPS));
                String gqS = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_BATTERY));


                smoT = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sMOTHERBOARD));
                sppT = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sPROCESSOR));
                smm1T = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sMEMORY1));
                smm2T = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sMEMORY2));
                slcT = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sLAN_CARD));
                sdmT = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sVIDEO_CARD));
                shd1T = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sHARD_DISK1));
                shd2T = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sHARD_DISK2));
                spwT = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sPOWERSUPPLY));
                sccT = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sCASING));
                som1T = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sMONITOR1));
                som2T = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sMONITOR2));
                skbT = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sKEYBOARD));
                smsT = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sMOUSE));
                supT = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sUPS));
                sgqT = rs.getString(rs.getColumnIndex(DBHelper.WORKSTATION_sBATTERY));

                if (!rs.isClosed()) {
                    rs.close();
                }


                FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabSave);
            //    if (fabSave.getVisibility() == View.VISIBLE) {
           //         fabSave.setVisibility(View.INVISIBLE);
          //      }

                wname.setText((CharSequence) name);
                wname.setFocusable(false);
                wname.setClickable(false);

                moT.setText((CharSequence) moS);
                moT.setFocusable(false);
                moT.setClickable(false);

                ppT.setText((CharSequence) ppS);
                ppT.setFocusable(false);
                ppT.setClickable(false);

                mm1T.setText((CharSequence) mm1S);
                mm1T.setFocusable(false);
                mm1T.setClickable(false);

                mm2T.setText((CharSequence) mm2S);
                mm2T.setFocusable(false);
                mm2T.setClickable(false);

                lcT.setText((CharSequence) lcS);
                lcT.setFocusable(false);
                lcT.setClickable(false);

                dmT.setText((CharSequence) dmS);
                dmT.setFocusable(false);
                dmT.setClickable(false);

                hd1T.setText((CharSequence) hd1S);
                hd1T.setFocusable(false);
                hd1T.setClickable(false);

                hd2T.setText((CharSequence) hd2S);
                hd2T.setFocusable(false);
                hd2T.setClickable(false);

                pwT.setText((CharSequence) pwS);
                pwT.setFocusable(false);
                pwT.setClickable(false);

                ccT.setText((CharSequence) ccS);
                ccT.setFocusable(false);
                ccT.setClickable(false);

                om1T.setText((CharSequence) om1S);
                om1T.setFocusable(false);
                om1T.setClickable(false);

                om2T.setText((CharSequence) om2S);
                om2T.setFocusable(false);
                om2T.setClickable(false);

                kbT.setText((CharSequence) kbS);
                kbT.setFocusable(false);
                kbT.setClickable(false);

                msT.setText((CharSequence) msS);
                msT.setFocusable(false);
                msT.setClickable(false);

                upT.setText((CharSequence) upS);
                upT.setFocusable(false);
                upT.setClickable(false);

                gqT.setText((CharSequence) gqS);
                gqT.setFocusable(false);
                gqT.setClickable(false);



            }else{

                if (Workstation.action.matches("next")){

                    wname.setText(stringNext);
                   // add =true;
                    Workstation.action = "";
                }else{showAddDialog("add");}




            }

        }


        wname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog("edit");
            }
        });

        nextLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next();


            }
        });



        SharedPreferences prefs1 = getSharedPreferences("com.example.christian", MODE_PRIVATE);

        //spinner
     //   item = "name";
        keySpecs ="motherboard_specs";
        ArrayList<String> array1 = new ArrayList<String>();
        array1.add("None");
        array1.addAll(mydb.getSpecsWI());
        array1.add("Add new");
        Integer val1 = array1.size() -1;
        ArrayAdapter<String> moadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,array1);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        smoS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val1 == position){
                    keySpecs ="motherboard_specs";
                    contxt = "motherboard";
                    addNew();
                    smoS.setSelection(moadapter.getPosition(smoT));

                }else{
                    smoS.setPrompt("Selected: "+smoS.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {
                smoS.setPrompt("Selected: "+smoS.getSelectedItem().toString());
            }
        });
        smoS.setAdapter(moadapter);
        smoS.setSelection(moadapter.getPosition(smoT));
        if (Workstation.add == true){
            smoS.setSelection(moadapter.getPosition(prefs1.getString("motherboard", "None")));
        }

     //   smoS.setPrompt("Selected: "+smoS.getSelectedItem().toString());


        keySpecs ="processor_specs";
        ArrayList<String> array2 = new ArrayList<String>();
        array2.add("None");
        array2.addAll(mydb.getSpecsWI());
        array2.add("Add new");
        Integer val2 = array1.size() -1;
        ArrayAdapter<String> ppadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array1);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sppS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val2 == position){
                    keySpecs ="processor_specs";
                    contxt = "processor";
                    addNew();
                    sppS.setSelection(ppadapter.getPosition(sppT));
                }else{
                    sppS.setPrompt("Selected: "+sppS.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        sppS.setAdapter(ppadapter);
        sppS.setSelection(ppadapter.getPosition(sppT));
     //   sppS.setPrompt("Selected: "+sppS.getSelectedItem().toString());


        keySpecs ="memory_specs";
        ArrayList<String> array3 = new ArrayList<String>();
        array3.add("None");
        array3.addAll(mydb.getSpecsWI());
        array3.add("Add new");
        Integer val3 = array1.size() -1;
        ArrayAdapter<String> mm1adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array3);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smm1S.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val3 == position){
                    keySpecs ="memory_specs";
                    contxt = "memory";
                    addNew();
                    smm1S.setSelection(mm1adapter.getPosition(smm1T));
                }else{
                    smm1S.setPrompt("Selected: "+smm1S.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        smm1S.setAdapter(mm1adapter);
        smm1S.setSelection(mm1adapter.getPosition(smm1T));
   //     smm1S.setPrompt("Selected: "+smm1S.getSelectedItem().toString());

        keySpecs ="memory_specs";
        ArrayList<String> array4 = new ArrayList<String>();
        array4.add("None");
        array4.addAll(mydb.getSpecsWI());
        array4.add("Add new");
        Integer val4 = array1.size() -1;
        ArrayAdapter<String> mm2adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array4);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smm2S.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val4 == position){
                    keySpecs ="memory_specs";
                    contxt = "memory";
                    addNew();
                    smm2S.setSelection(mm2adapter.getPosition(smm2T));
                }else{
                    smm2S.setPrompt("Selected: "+smm2S.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        smm2S.setAdapter(mm2adapter);
        smm2S.setSelection(mm2adapter.getPosition(smm2T));
     //   smm2S.setPrompt("Selected: "+smm2S.getSelectedItem().toString());

        keySpecs ="lancard_specs";
        ArrayList<String> array5 = new ArrayList<String>();
        array5.add("None");
        array5.addAll(mydb.getSpecsWI());
        array5.add("Add new");
        Integer val5 = array1.size() -1;
        ArrayAdapter<String> lcadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array5);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slcS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val5 == position){
                    keySpecs ="lancard_specs";
                    contxt = "lancard";
                    addNew();
                    slcS.setSelection(lcadapter.getPosition(slcT));
                }else{
                    slcS.setPrompt("Selected: "+slcS.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        slcS.setAdapter(lcadapter);
        slcS.setSelection(lcadapter.getPosition(slcT));
     //   slcS.setPrompt("Selected: "+slcS.getSelectedItem().toString());

        keySpecs ="videocard_specs";
        ArrayList<String> array6 = new ArrayList<String>();
        array6.add("None");
        array6.addAll(mydb.getSpecsWI());
        array6.add("Add new");
        Integer val6 = array1.size() -1;
        ArrayAdapter<String> dmadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array6);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sdmS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val6 == position){
                    keySpecs ="videocard_specs";
                    contxt = "videocard";
                    addNew();
                    sdmS.setSelection(dmadapter.getPosition(sdmT));
                }else{
                    sdmS.setPrompt("Selected: "+sdmS.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        sdmS.setAdapter(dmadapter);
        sdmS.setSelection(dmadapter.getPosition(sdmT));
    //    sdmS.setPrompt("Selected: "+sdmS.getSelectedItem().toString());

        keySpecs ="harddisk_specs";
        ArrayList<String> array7 = new ArrayList<String>();
        array7.add("None");
        array7.addAll(mydb.getSpecsWI());
        array7.add("Add new");
        Integer val7 = array1.size() -1;
        ArrayAdapter<String> hd1adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array7);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shd1S.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val6 == position){
                    keySpecs ="harddisk_specs";
                    contxt = "harddisk";
                    addNew();
                    shd1S.setSelection(hd1adapter.getPosition(shd1T));
                }else{
                    shd1S.setPrompt("Selected: "+shd1S.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        shd1S.setAdapter(hd1adapter);
        shd1S.setSelection(hd1adapter.getPosition(shd1T));
    //    shd1S.setPrompt("Selected: "+shd1S.getSelectedItem().toString());

        keySpecs ="harddisk_specs";
        ArrayList<String> array8 = new ArrayList<String>();
        array8.add("None");
        array8.addAll(mydb.getSpecsWI());
        array8.add("Add new");
        Integer val8 = array1.size() -1;
        ArrayAdapter<String> hd2adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array8);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shd2S.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val8 == position){
                    keySpecs ="harddisk_specs";
                    contxt = "harddisk";
                    addNew();
                    shd2S.setSelection(hd2adapter.getPosition(shd2T));
                }else{
                    shd2S.setPrompt("Selected: "+shd2S.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        shd2S.setAdapter(hd2adapter);
        shd2S.setSelection(hd2adapter.getPosition(shd2T));
    //    shd2S.setPrompt("Selected: "+shd2S.getSelectedItem().toString());

        keySpecs ="powersupply_specs";
        ArrayList<String> array9 = new ArrayList<String>();
        array9.add("None");
        array9.addAll(mydb.getSpecsWI());
        array9.add("Add new");
        Integer val9 = array1.size() -1;
        ArrayAdapter<String> pwadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array9);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spwS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val9 == position){
                    keySpecs ="powersupply_specs";
                    contxt = "powersupply";
                    addNew();
                    spwS.setSelection(pwadapter.getPosition(spwT));
                }else{
                    spwS.setPrompt("Selected: "+spwS.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        spwS.setAdapter(pwadapter);
        spwS.setSelection(pwadapter.getPosition(spwT));
     //   spwS.setPrompt("Selected: "+spwS.getSelectedItem().toString());

        keySpecs ="casing_specs";
        ArrayList<String> array10 = new ArrayList<String>();
        array10.add("None");
        array10.addAll(mydb.getSpecsWI());
        array10.add("Add new");
        Integer val10 = array1.size() -1;
        ArrayAdapter<String> ccadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array10);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sccS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val10 == position){
                    keySpecs ="casing_specs";
                    contxt = "casing";
                    addNew();
                    sccS.setSelection(ccadapter.getPosition(sccT));
                }else{
                    sccS.setPrompt("Selected: "+sccS.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        sccS.setAdapter(ccadapter);
        sccS.setSelection(ccadapter.getPosition(sccT));
   //     sccS.setPrompt("Selected: "+sccS.getSelectedItem().toString());

        keySpecs ="monitor_specs";
        ArrayList<String> array11 = new ArrayList<String>();
        array11.add("None");
        array11.addAll(mydb.getSpecsWI());
        array11.add("Add new");
        Integer val11 = array1.size() -1;
        ArrayAdapter<String> om1adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array11);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        som1S.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val11 == position){
                    keySpecs ="monitor_specs";
                    contxt = "monitor";
                    addNew();
                    som1S.setSelection(om1adapter.getPosition(som1T));
                }else{
                    som1S.setPrompt("Selected: "+som1S.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        som1S.setAdapter(om1adapter);
        som1S.setSelection(om1adapter.getPosition(som1T));
    //    som1S.setPrompt("Selected: "+som1S.getSelectedItem().toString());

        keySpecs ="monitor_specs";
        ArrayList<String> array12 = new ArrayList<String>();
        array12.add("None");
        array12.addAll(mydb.getSpecsWI());
        array12.add("Add new");
        Integer val12 = array1.size() -1;
        ArrayAdapter<String> om2adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array12);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        som2S.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val12 == position){
                    keySpecs ="monitor_specs";
                    contxt = "monitor";
                    addNew();
                    som2S.setSelection(om2adapter.getPosition(som2T));
                }else{
                    som2S.setPrompt("Selected: "+som2S.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        som2S.setAdapter(om2adapter);
        som2S.setSelection(om2adapter.getPosition(som2T));
    //    som2S.setPrompt("Selected: "+som2S.getSelectedItem().toString());

        keySpecs ="keyboard_specs";
        ArrayList<String> array13 = new ArrayList<String>();
        array13.add("None");
        array13.addAll(mydb.getSpecsWI());
        array13.add("Add new");
        Integer val13 = array1.size() -1;
        ArrayAdapter<String> kbadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array13);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skbS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val13 == position){
                    keySpecs ="keyboard_specs";
                    contxt = "keyboard";
                    addNew();
                    skbS.setSelection(kbadapter.getPosition(skbT));
                }else{
                    skbS.setPrompt("Selected: "+skbS.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        skbS.setAdapter(kbadapter);
        skbS.setSelection(kbadapter.getPosition(skbT));
   //     skbS.setPrompt("Selected: "+skbS.getSelectedItem().toString());

        keySpecs ="mouse_specs";
        ArrayList<String> array14 = new ArrayList<String>();
        array14.add("None");
        array14.addAll(mydb.getSpecsWI());
        array14.add("Add new");
        Integer val14 = array1.size() -1;
        ArrayAdapter<String> msadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array14);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smsS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val14 == position){
                    keySpecs ="mouse_specs";
                    contxt = "mouse";
                    addNew();
                    smsS.setSelection(msadapter.getPosition(smsT));
                }else{
                    smsS.setPrompt("Selected: "+smsS.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        smsS.setAdapter(msadapter);
        smsS.setSelection(msadapter.getPosition(smsT));
   //     smsS.setPrompt("Selected: "+smsS.getSelectedItem().toString());

        keySpecs ="ups_specs";
        ArrayList<String> array15 = new ArrayList<String>();
        array15.add("None");
        array15.addAll(mydb.getSpecsWI());
        array15.add("Add new");
        Integer val15 = array1.size() -1;
        ArrayAdapter<String> upadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array15);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val15 == position){
                    keySpecs ="ups_specs";
                    contxt = "ups";
                    addNew();
                    supS.setSelection(upadapter.getPosition(supT));
                }else{
                    supS.setPrompt("Selected: "+supS.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        supS.setAdapter(upadapter);
        supS.setSelection(upadapter.getPosition(supT));
    //    supS.setPrompt("Selected: "+supS.getSelectedItem().toString());

        keySpecs ="battery_specs";
        ArrayList<String> array16 = new ArrayList<String>();
        array16.add("None");
        array16.addAll(mydb.getSpecsWI());
        array16.add("Add new");
        Integer val16= array1.size() -1;
        ArrayAdapter<String> gqadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array16);
        moadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sgqS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (val16 == position){
                    keySpecs ="battery_specs";
                    contxt = "battery";
                    addNew();
                    sgqS.setSelection(gqadapter.getPosition(sgqT));
                }else{
                    sgqS.setPrompt("Selected: "+sgqS.getSelectedItem().toString());
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        sgqS.setAdapter(gqadapter);
        sgqS.setSelection(gqadapter.getPosition(sgqT));
     //   sgqS.setPrompt("Selected: "+sgqS.getSelectedItem().toString());







  //test

























        FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabSave);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dmB = findViewById(R.id.dmB);

                String output1 = wname.getText().toString();


                if (output1.matches("") ) {
                    if (output1.matches("")){
                        Snackbar.make(view, "Workstation name cannot be empty", Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                        wname.requestFocus();
                    }



                }


                else {
                    if (Workstation.add == true) {
                        FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabSave);

                        if (Workstation.array_list.contains(wname.getText().toString())) {
                            Snackbar.make(view, wname.getText().toString() + " is already in the list", Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                        } else {
                       if (Workstation.action.matches("")){
                           Workstation.action = "next";

                       }

                            run(view);

                        }
                    }
                    if(Workstation.add == false) {

                        run(view);

                    }

                    }


            }
        });


        FloatingActionButton fabScan = (FloatingActionButton) findViewById(R.id.fabScan);
        fabScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // test();
                scan();
            }
        });



        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fabDelete);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WorkstationInfo.this);
                builder.setMessage("Are you sure you want to delete " + wname.getText().toString() +"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteWorkstation(workstationToUpdate);



                                state = "Deleted";
                                Intent intent = new Intent(getApplicationContext(),Workstation.class);
                                intent.putExtra("showSnackbar", true);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Delete workstation");
                d.show();
            }
        });




    }



    public void next(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your changes has not been saved yet")
                .setPositiveButton("Save and proceed", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabSave);

                        fabSave.performClick();


                        Snackbar.make(view, "Saved successfully", Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();



                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });

        AlertDialog d = builder.create();
        d.setTitle("Save?");
        d.show();

        //   super.onBackPressed();  // optional depending on your needs
    }






    public void showAddDialog(String str){

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        String str1 = null;

            if (str.matches("add")){
                builder.setTitle("Add workstation");
                str1 = "Add";
            }else if (str.matches("edit")){
                builder.setTitle("Edit workstation name");
                str1 = "Edit";
            }

        LayoutInflater inflater = getLayoutInflater();
        View myLayout = inflater.inflate(R.layout.dialogadd, null);
        
        
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
                finish();
            }
        });

        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                EditText wnameD = (EditText)myLayout.findViewById(R.id.wnameD);
                EditText wnumberD = (EditText)myLayout.findViewById(R.id.wnumberD);
                String output1 = wnameD.getText().toString();
                String output2 = wnumberD.getText().toString();
                String output3 = output1+output2;
                FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabSave);

                if (output1.matches("") || output2.matches("")) {
                    if (output1.matches("")){
                        dialog.setTitle("Workstation name is empty");
                        wnameD.requestFocus();


                    }else if (output2.matches("")){
                        dialog.setTitle("Workstation number is empty");
                        wnumberD.requestFocus();


                    }


                }else if (Workstation.array_list.contains(output3)) {
                    dialog.setTitle(output3 + " is already in the list");

                } else {
                wname.setText(output1+"-"+output2);
                Snackbar.make(view, output1+"-"+output2 +" has been set", Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                nextLayout.setVisibility(View.VISIBLE);
                TextView textViewInfo = (TextView) findViewById(R.id.textViewInfo);

                Integer nextValue = Integer.parseInt(output2)+1;
                String str = String.format("%04d", nextValue);
                textViewInfo.setText("Add "+output1+"-"+str);
                stringNext= output1+"-"+str;

                dialog.dismiss();

            }


            }
        });


        }


















// Scan FUnction


    @Override
    public void onClick(View v) {
        scan();
    }

    private void scan() {

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(Capture.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Itutok hn maupay");
        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage(result.getContents());
                TextView moB = findViewById(R.id.moB);
                TextView ppB = findViewById(R.id.ppB);
                TextView mm1B = findViewById(R.id.mm1B);
                TextView mm2B = findViewById(R.id.mm2B);
                TextView lcB = findViewById(R.id.lcB);
                TextView dmB = findViewById(R.id.dmB);
                TextView hd1B = findViewById(R.id.hd1B);
                TextView hd2B = findViewById(R.id.hd2B);
                TextView pwB = findViewById(R.id.pwB);
                TextView ccB = findViewById(R.id.ccB);
                TextView om1B = findViewById(R.id.om1B);
                TextView om2B = findViewById(R.id.om2B);
                TextView kbB = findViewById(R.id.kbB);
                TextView msB = findViewById(R.id.msB);
                TextView upB = findViewById(R.id.upB);
                TextView gqB = findViewById(R.id.gqB);
                //specs
            /*      TextView smoB = findViewById(R.id.smoB);
                TextView sppB = findViewById(R.id.sppB);
                TextView smm1B = findViewById(R.id.smm1B);
                TextView smm2B = findViewById(R.id.smm2B);
                TextView slcB = findViewById(R.id.slcB);
                TextView sdmB = findViewById(R.id.sdmB);
                TextView shd1B = findViewById(R.id.shd1B);
                TextView shd2B = findViewById(R.id.shd2B);
                TextView spwB = findViewById(R.id.spwB);
                TextView sccB = findViewById(R.id.sccB);
                TextView som1B = findViewById(R.id.som1B);
                TextView som2B = findViewById(R.id.som2B);
                TextView skbB = findViewById(R.id.skbB);
                TextView smsB = findViewById(R.id.smsB);
                TextView supB = findViewById(R.id.supB);
                TextView sgqB = findViewById(R.id.sgqB);
*/


                String barcode = result.getContents();

                if (barcode.contains("MO")) {
                    moB.setText(barcode);
                    Snackbar.make(view, "Motherboard : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                }
                if (barcode.contains("PP")) {
                    ppB.setText(barcode);
                    Snackbar.make(view, "Processor : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                }
                if (barcode.contains("MM")) {

                    if (!mm1B.getText().toString().equals("") && mm1B != null) {

                        dialog.setTitle("Already have existing barcode in memory");
                        dialog.setPositiveButton("Add as 2nd barcode", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mm2B.setText(barcode);
                                Snackbar.make(view, "Second Memory : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                            }
                        }).setNegativeButton("Replace existing primary barcode", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mm1B.setText(barcode);
                                Snackbar.make(view, "First Memory : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                            }
                        });
                        AlertDialog dialog1 = dialog.create();
                        dialog1.show();

                } else {
                    mm1B.setText(barcode);
                        Snackbar.make(view, "First Memory : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                    }
                }
                if (barcode.contains("LC")) {
                    lcB.setText(barcode);
                    Snackbar.make(view, "Lan card : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                }
                if (barcode.contains("DM")) {
                    dmB.setText(barcode);
                    Snackbar.make(view, "Video card : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                }
                if (barcode.contains("HD")) {

                    if (!hd1B.getText().toString().equals("") && hd1B != null) {

                        dialog.setTitle("Already have existing barcode in hard disk");
                        dialog.setPositiveButton("Add as 2nd barcode", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hd2B.setText(barcode);
                                Snackbar.make(view, "Second Hard disk : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                            }
                        }).setNegativeButton("Replace existing primary barcode", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hd1B.setText(barcode);
                                Snackbar.make(view, "First Hard disk : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                            }
                        });
                        AlertDialog dialog1 = dialog.create();
                        dialog1.show();

                    } else {
                        hd1B.setText(barcode);
                        Snackbar.make(view, "First Hard disk : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                    }
                }
                if (barcode.contains("PW")) {
                    pwB.setText(barcode);
                    Snackbar.make(view, "Power supply : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                }
                if (barcode.contains("CC")) {
                    ccB.setText(barcode);
                    Snackbar.make(view, "Casing : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                }
                if (barcode.contains("OM")) {

                    if (!om1B.getText().toString().equals("") && om1B != null ) {

                        dialog.setTitle("Already have existing barcode in monitor");
                        dialog.setPositiveButton("Add as 2nd barcode", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                om2B.setText(barcode);
                                Snackbar.make(view, "Second Monitor : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                            }
                        }).setNegativeButton("Replace existing primary barcode", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                om1B.setText(barcode);
                                Snackbar.make(view, "First Monitor : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                            }
                        });
                        AlertDialog dialog1 = dialog.create();
                        dialog1.show();

                    } else {
                        om1B.setText(barcode);
                        Snackbar.make(view, "First Monitor : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                    }
                }
                if (barcode.contains("KB")) {
                    kbB.setText(barcode);
                    Snackbar.make(view, "Keyboard : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                }
                if (barcode.contains("MS")) {
                    msB.setText(barcode);
                    Snackbar.make(view, "Mouse : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                }
                if (barcode.contains("UP")) {
                    upB.setText(barcode);
                    Snackbar.make(view, "Ups : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                }
                if (barcode.contains("GQ")) {
                    gqB.setText(barcode);
                    Snackbar.make(view, "Ups battery : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();
                }

            } else {
                   // Toast.makeText(this, "No result", Toast.LENGTH_LONG).show();
                    Snackbar.make(view, "No result", Snackbar.LENGTH_LONG)
                            .setAction("Scan again", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    scan();
                                }
                            }).setActionTextColor(getResources().getColor(R.color.secondary)).setAnchorView(R.id.fabScan).show();

                }



        } else {

                super.onActivityResult(requestCode, resultCode, data);
            }
        }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.workstationappbarmenu, menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.workstation_info, menu);
            } else{
                getMenuInflater().inflate(R.menu.test, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.Edit_Workstation:
          //      Button button1 = (Button)findViewById(R.id.button1);
          //      button1.setVisibility(View.VISIBLE);

                FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabSave);
            //    fabSave.setVisibility(View.VISIBLE);
                add = false;

                wname.setEnabled(true);
                wname.setFocusableInTouchMode(true);
                wname.setClickable(true);

                moT.setEnabled(true);
                moT.setFocusableInTouchMode(true);
                moT.setClickable(true);

                ppT.setEnabled(true);
                ppT.setFocusableInTouchMode(true);
                ppT.setClickable(true);

                mm1T.setEnabled(true);
                mm1T.setFocusableInTouchMode(true);
                mm1T.setClickable(true);

                mm2T.setEnabled(true);
                mm2T.setFocusableInTouchMode(true);
                mm2T.setClickable(true);

                lcT.setEnabled(true);
                lcT.setFocusableInTouchMode(true);
                lcT.setClickable(true);

                dmT.setEnabled(true);
                dmT.setFocusableInTouchMode(true);
                dmT.setClickable(true);

                hd1T.setEnabled(true);
                hd1T.setFocusableInTouchMode(true);
                hd1T.setClickable(true);

                hd2T.setEnabled(true);
                hd2T.setFocusableInTouchMode(true);
                hd2T.setClickable(true);

                pwT.setEnabled(true);
                pwT.setFocusableInTouchMode(true);
                pwT.setClickable(true);

                ccT.setEnabled(true);
                ccT.setFocusableInTouchMode(true);
                ccT.setClickable(true);

                om1T.setEnabled(true);
                om1T.setFocusableInTouchMode(true);
                om1T.setClickable(true);

                om2T.setEnabled(true);
                om2T.setFocusableInTouchMode(true);
                om2T.setClickable(true);

                kbT.setEnabled(true);
                kbT.setFocusableInTouchMode(true);
                kbT.setClickable(true);

                msT.setEnabled(true);
                msT.setFocusableInTouchMode(true);
                msT.setClickable(true);

                upT.setEnabled(true);
                upT.setFocusableInTouchMode(true);
                upT.setClickable(true);

                gqT.setEnabled(true);
                gqT.setFocusableInTouchMode(true);
                gqT.setClickable(true);

                return true;
            case R.id.Delete_Workstation:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Delete")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteWorkstation(workstationToUpdate);

                                Snackbar.make(view, "Deleted successfully", Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();

                           //     Toast.makeText(getApplicationContext(), "Deleted Successfully",
                            //            Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Workstation.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void addNew(){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Add specs in "+contxt);


        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);


        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mInput = input.getText().toString();
                mydb.AddSpecsWorkstationInfo(keySpecs,mInput);
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



    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){

                if (moT.getText().toString().matches("")) {
                    moT.setText("None");
                }
                if (ppT.getText().toString().matches("")) {
                    ppT.setText("None");
                }
                if (mm1T.getText().toString().matches("")) {
                    mm1T.setText("None");
                }
                if (mm2T.getText().toString().matches("")) {
                    mm2T.setText("None");
                }
                if (lcT.getText().toString().matches("")) {
                    lcT.setText("None");
                }
                if (dmT.getText().toString().matches("")) {
                    dmT.setText("None");
                }
                if (hd1T.getText().toString().matches("")) {
                    hd1T.setText("None");
                }
                if (hd2T.getText().toString().matches("")) {
                    hd2T.setText("None");
                }
                if (pwT.getText().toString().matches("")) {
                    pwT.setText("None");
                }
                if (ccT.getText().toString().matches("")) {
                    ccT.setText("None");
                }
                if (om1T.getText().toString().matches("")) {
                    om1T.setText("None");
                }
                if (om2T.getText().toString().matches("")) {
                    om2T.setText("None");
                }
                if (kbT.getText().toString().matches("")) {
                    kbT.setText("None");
                }
                if (msT.getText().toString().matches("")) {
                    msT.setText("None");
                }
                if (upT.getText().toString().matches("")){
                    upT.setText("None");
                }
                if (gqT.getText().toString().matches("")) {
                    gqT.setText("None");
                }
                if(mydb.updateWorkstation(id_To_Update,wname.getText().toString(),
                        moT.getText().toString(),
                        ppT.getText().toString(),
                        mm1T.getText().toString(),
                        mm2T.getText().toString(),
                        lcT.getText().toString(),
                        dmT.getText().toString(),
                        hd1T.getText().toString(),
                        hd2T.getText().toString(),
                        pwT.getText().toString(),
                        ccT.getText().toString(),
                        om1T.getText().toString(),
                        om2T.getText().toString(),
                        kbT.getText().toString(),
                        msT.getText().toString(),
                        upT.getText().toString(),
                        gqT.getText().toString(),
                        smoS.getSelectedItem().toString(),
                        sppS.getSelectedItem().toString(),
                        smm1S.getSelectedItem().toString(),
                        smm2S.getSelectedItem().toString(),
                        slcS.getSelectedItem().toString(),
                        sdmS.getSelectedItem().toString(),
                        shd1S.getSelectedItem().toString(),
                        shd2S.getSelectedItem().toString(),
                        spwS.getSelectedItem().toString(),
                        sccS.getSelectedItem().toString(),
                        som1S.getSelectedItem().toString(),
                        som2S.getSelectedItem().toString(),
                        skbS.getSelectedItem().toString(),
                        smsS.getSelectedItem().toString(),
                        supS.getSelectedItem().toString(),
                        sgqS.getSelectedItem().toString())){
                    state = "Updated";

                    Intent intent = new Intent(getApplicationContext(),Workstation.class);
                    startActivity(intent);
                } else{
                    state = "Failed";

                }
            } else{

                if (moT.getText().toString().matches("")) {
                    moT.setText("None");
                }
                if (ppT.getText().toString().matches("")) {
                    ppT.setText("None");
                }
                if (mm1T.getText().toString().matches("")) {
                    mm1T.setText("None");
                }
                if (mm2T.getText().toString().matches("")) {
                    mm2T.setText("None");
                }
                if (lcT.getText().toString().matches("")) {
                    lcT.setText("None");
                }
                if (dmT.getText().toString().matches("")) {
                    dmT.setText("None");
                }
                if (hd1T.getText().toString().matches("")) {
                    hd1T.setText("None");
                }
                if (hd2T.getText().toString().matches("")) {
                    hd2T.setText("None");
                }
                if (pwT.getText().toString().matches("")) {
                    pwT.setText("None");
                }
                if (ccT.getText().toString().matches("")) {
                    ccT.setText("None");
                }
                if (om1T.getText().toString().matches("")) {
                    om1T.setText("None");
                }
                if (om2T.getText().toString().matches("")) {
                    om2T.setText("None");
                }
                if (kbT.getText().toString().matches("")) {
                    kbT.setText("None");
                }
                if (msT.getText().toString().matches("")) {
                    msT.setText("None");
                }
                if (upT.getText().toString().matches("")) {
                    upT.setText("None");
                }
                if (gqT.getText().toString().matches("")) {
                    gqT.setText("None");
                }
                if(mydb.insertWorkstation(wname.getText().toString(),

                        moT.getText().toString(),
                        ppT.getText().toString(),
                        mm1T.getText().toString(),
                        mm2T.getText().toString(),
                        lcT.getText().toString(),
                        dmT.getText().toString(),
                        hd1T.getText().toString(),
                        hd2T.getText().toString(),
                        pwT.getText().toString(),
                        ccT.getText().toString(),
                        om1T.getText().toString(),
                        om2T.getText().toString(),
                        kbT.getText().toString(),
                        msT.getText().toString(),
                        upT.getText().toString(),
                        gqT.getText().toString(),

                        smoS.getSelectedItem().toString(),
                        sppS.getSelectedItem().toString(),
                        smm1S.getSelectedItem().toString(),
                        smm2S.getSelectedItem().toString(),
                        slcS.getSelectedItem().toString(),
                        sdmS.getSelectedItem().toString(),
                        shd1S.getSelectedItem().toString(),
                        shd2S.getSelectedItem().toString(),
                        spwS.getSelectedItem().toString(),
                        sccS.getSelectedItem().toString(),
                        som1S.getSelectedItem().toString(),
                        som2S.getSelectedItem().toString(),
                        skbS.getSelectedItem().toString(),
                        smsS.getSelectedItem().toString(),
                        supS.getSelectedItem().toString(),
                        sgqS.getSelectedItem().toString())){
                         state = "Done";
                         terminal = wname.getText().toString();




                } else{
                    state = "Failed";

                }
                Intent intent = new Intent(getApplicationContext(),Workstation.class);
                intent.putExtra("showSnackbar", true);

                startActivity(intent);
            }
        }
    }



    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your changes has not been saved yet")
                .setPositiveButton("Save and exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabSave);
                        fabSave.performClick();
                        Snackbar.make(view, "Saved successfully", Snackbar.LENGTH_LONG).setAnchorView(R.id.fabScan).show();


                        Intent intent = new Intent(getApplicationContext(),Workstation.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Abort", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      finish();
                    }
                });

        AlertDialog d = builder.create();
        d.setTitle("Save and exit?");
        d.show();

     //   super.onBackPressed();  // optional depending on your needs
    }
}

















