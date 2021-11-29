package com.example.christian;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class testpage extends AppCompatActivity implements View.OnClickListener {
    Button scnbtn;
   // TextView mouse,keyboard,hmonitor,vmonitor,systemunit,motherboard,processor,memory,lan,videocard,disk,psu,casing;

    //extend
    String mouse,keyboard,hmonitor,vmonitor,systemunit,motherboard,processor,memory,lan,videocard,disk,psu,casing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testpage);

        scnbtn = findViewById(R.id.button);
        scnbtn.setOnClickListener(this);


        //extend



    }

    @Override
    public void onClick(View v) {
    scan();
    }

    private void scan() {

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(Capture.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());



                String barcode = result.getContents();

                if (barcode.contains("CC")){
                    TextView cc = findViewById(R.id.cc);
                    cc.setText(barcode);

                }

                if (barcode.contains("MS")){
                   TextView ms = findViewById(R.id.ms);
                   ms.setText(barcode);

                }


                if (barcode.contains("KB")){
                    TextView ms = findViewById(R.id.kb);
                    ms.setText(barcode);

                }

                if (barcode.contains("OM")){
                    TextView cc = findViewById(R.id.om);
                    cc.setText(barcode);

                }



                builder.setTitle("Scanning Result");



        //        builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
        //            @Override
        //            public void onClick(DialogInterface dialog, int which) {
        //                scan();
        //            }
        //        }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
        //            @Override
         //           public void onClick(DialogInterface dialog, int which) {
                      //  finish();
        //            }
        //       });
        //        AlertDialog dialog = builder.create();
       //         dialog.show();
            } else {
                Toast.makeText(this, "NO result", Toast.LENGTH_LONG).show();
            }
        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}