package com.example.christian;

import android.content.Context;

import java.util.ArrayList;

public class Resources {
    static String item,spec;
    DBHelper mydb;
    ArrayList<String> arraymo = new ArrayList<String>();
    ArrayList<String> arraypp = new ArrayList<String>();
    ArrayList<String> arraymm = new ArrayList<String>();
    ArrayList<String> arraylc = new ArrayList<String>();
    ArrayList<String> arraydm = new ArrayList<String>();
    ArrayList<String> arrayhd = new ArrayList<String>();
    ArrayList<String> arraypw = new ArrayList<String>();
    ArrayList<String> arraycc = new ArrayList<String>();
    ArrayList<String> arrayom = new ArrayList<String>();
    ArrayList<String> arraykb = new ArrayList<String>();
    ArrayList<String> arrayms = new ArrayList<String>();
    ArrayList<String> arrayup = new ArrayList<String>();
    ArrayList<String> arraygq = new ArrayList<String>();

    public void res(){
        //motherboard
        arraymo.add("ECS H16H2-M17");
        arraymo.add("ECS H61H2-MV");
        arraymo.add("ECS H110M4-C2H");
        //processor
        arraypp.add("INTEL i3");
        //memory
        arraymm.add("KINGSTON  4GB DDR3");
        //lancard
     //   arraylc.add("");
        //videocard
        arraydm.add("NVIDIA 8400GS");
        arraydm.add("NVIDIA GF 210");
        //harddisk
        arrayhd.add("SEAGATE 500GB");
        arrayhd.add("WESTERN DIGITAL 320GB");
        //powersupply
        arraypw.add("OVATION 600W");
        arraypw.add("FORTRESS 700W");
        //casing
        arraycc.add("BLACK CASING");
        //monitor
        arrayom.add("AOC E970SW");
        arrayom.add("BENQ");
        arrayom.add("LG");

        //keyboard
        arraykb.add("A4TECH KRS-85");
        arraykb.add("GENIUS");
        arraykb.add("LENOVO");
        //mouse
        arrayms.add("A4TECH OP-720");
        arrayms.add("OPTICAL MOUSE");
        arrayms.add("LENOVO");
        //ups
        arrayup.add("KAISER 650VA");
        arrayup.add("APOLLO BLAST");
        //battery



    }

}

