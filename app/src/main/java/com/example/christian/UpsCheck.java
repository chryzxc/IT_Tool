package com.example.christian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UpsCheck extends AppCompatActivity {

    ArrayList<UpsDataModel> upsDataModels;
    ListView listView;
    private static UpsAdapter adapter;

    LayoutInflater inflater;
    View myLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ups_check);
    //    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);
        Date currentTime = Calendar.getInstance().getTime();

        listView=(ListView)findViewById(R.id.list);



        inflater = getLayoutInflater();
        myLayout = inflater.inflate(R.layout.upsinfo, null);

        upsDataModels= new ArrayList<>();

        upsDataModels.add(new UpsDataModel("FPo01-0022", "Working", "Kaiser","18UP11110","18GQ11110",currentTime.toString()));
        upsDataModels.add(new UpsDataModel("FPo01-0023", "Defective", "Kaiser2","18UP11110","18GQ11110","October 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0022", "Working", "Kaiser","18UP11110","18GQ11110","September 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0023", "Defective", "Kaiser2","18UP11110","18GQ11110","October 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0022", "Working", "Kaiser","18UP11110","18GQ11110","September 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0023", "Defective", "Kaiser2","18UP11110","18GQ11110","October 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0022", "Working", "Kaiser","18UP11110","18GQ11110","September 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0023", "Defective", "Kaiser2","18UP11110","18GQ11110","October 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0022", "Working", "Kaiser","18UP11110","18GQ11110","September 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0023", "Defective", "Kaiser2","18UP11110","18GQ11110","October 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0022", "Working", "Kaiser","18UP11110","18GQ11110","September 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0023", "Defective", "Kaiser2","18UP11110","18GQ11110","October 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0022", "Working", "Kaiser","18UP11110","18GQ11110","September 23, 2008"));
        upsDataModels.add(new UpsDataModel("FPo01-0023", "Defective", "Kaiser2","18UP11110","18GQ11110","October 23, 2008"));













        adapter= new UpsAdapter(upsDataModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UpsDataModel upsDataModel= upsDataModels.get(position);

             //   Snackbar.make(view, upsDataModel.getUpsWorkstation()+"\n"+upsDataModel.getBatteryBarcode()+" API: "+ upsDataModel.getBatteryBarcode(), Snackbar.LENGTH_LONG)
          //              .setAction("No action", null).show();


               showUpsInfo(upsDataModel.getUpsWorkstation(),upsDataModel.getUpsDescription(),upsDataModel.getUpsBarcode(),upsDataModel.getBatteryBarcode(),upsDataModel.getUpsStatus());
            }
        });
    }




    public void showAddDialogUps(){

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);



        LayoutInflater inflater = getLayoutInflater();
        View myLayout = inflater.inflate(R.layout.dialogaddups, null);


        builder.setView(myLayout)


                .setPositiveButton("sss", new DialogInterface.OnClickListener() {
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

            //    showUpsInfo();
          /*      EditText wnameD = (EditText)myLayout.findViewById(R.id.wnameD);
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


       */     }
        });


    }






    public void showUpsInfo(String upsInfoWorkstation, String upsInfoDescription, String upsInfoBarcode,String upsInfoBatteryBarcode, String upsInfoStatus) {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);






        TextView upsInfoWorkstation1 = (TextView)myLayout.findViewById(R.id.upsInfoWorkstation);
        upsInfoWorkstation1.setText(upsInfoWorkstation);

        TextView upsInfoDescription1 = (TextView)myLayout.findViewById(R.id.upsInfoDescription);
        upsInfoDescription1.setText(upsInfoDescription);

        TextView upsInfoBarcode1 = (TextView)myLayout.findViewById(R.id.upsInfoBarcode);
        upsInfoBarcode1.setText(upsInfoBarcode);

        TextView upsInfoBatteryBarcode1 = (TextView)myLayout.findViewById(R.id.upsInfoBatteryBarcode);
        upsInfoBatteryBarcode1.setText(upsInfoBatteryBarcode);

        RadioButton upsInfoWorking = (RadioButton)myLayout.findViewById(R.id.upsInfoWorking);
        RadioButton upsInfoDefective = (RadioButton)myLayout.findViewById(R.id.upsInfoDefective);

        if (upsInfoStatus.matches("Working")){
            upsInfoWorking.setChecked(true);
        }else if(upsInfoStatus.matches("Defective")){
            upsInfoDefective.setChecked(true);
        }


        ImageView upsInfoScan = (ImageView)myLayout.findViewById(R.id.upsInfoScan);
        ImageView upsInfoDelete = (ImageView)myLayout.findViewById(R.id.upsInfoDelete);

        upsInfoScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
            }
        });





        builder.setView(myLayout)




                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((ViewGroup)myLayout.getParent()).removeView(myLayout);

            }
        });

        androidx.appcompat.app.AlertDialog dialog = builder.create();




        dialog.setCancelable(false);
        dialog.show();


        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


          /*      EditText wnameD = (EditText)myLayout.findViewById(R.id.wnameD);
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


       */
            }
        });


    }



    private void scan() {

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(Capture.class);
        integrator.setOrientationLocked(true);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Itutok hn maupay");
        integrator.initiateScan();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        View view = findViewById(android.R.id.content);
        if (result != null) {
            if (result.getContents() != null) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage(result.getContents());




                String barcode = result.getContents();



                EditText upsInfoBarcode = (EditText)myLayout.findViewById(R.id.upsInfoBarcode);
                EditText upsInfoBatteryBarcode = (EditText)myLayout.findViewById(R.id.upsInfoBatteryBarcode);



                if (barcode.contains("UP")) {
                    upsInfoBarcode.setText(barcode);
                    Snackbar.make(view, "Ups : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabUpsSearch).show();
                }
                if (barcode.contains("GQ")) {
                    upsInfoBatteryBarcode.setText(barcode);
                    Snackbar.make(view, "Ups battery : " +barcode, Snackbar.LENGTH_LONG).setAnchorView(R.id.fabUpsSearch).show();
                }

            } else {
                // Toast.makeText(this, "No result", Toast.LENGTH_LONG).show();
                Snackbar.make(view, "No result", Snackbar.LENGTH_LONG)
                        .setAction("Scan again", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                scan();
                            }
                        }).setActionTextColor(getResources().getColor(R.color.secondary)).setAnchorView(R.id.fabUpsSearch).show();

            }



        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}