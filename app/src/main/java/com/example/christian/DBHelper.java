package com.example.christian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "chan.db";
    public static final String WORKSTATIONS = "tableWorkstations";
    public static final String WORKSTATION_COLUMN_ID = "id";
    public static final String WORKSTATION_NAME = "name";
    public static final String WORKSTATION_MOTHERBOARD = "motherboard";
    public static final String WORKSTATION_PROCESSOR = "processor";
    public static final String WORKSTATION_MEMORY1 = "memory1";
    public static final String WORKSTATION_MEMORY2 = "memory2";
    public static final String WORKSTATION_LAN_CARD = "lancard";
    public static final String WORKSTATION_VIDEO_CARD = "videocard";
    public static final String WORKSTATION_HARD_DISK1 = "harddisk1";
    public static final String WORKSTATION_HARD_DISK2 = "harddisk2";
    public static final String WORKSTATION_POWERSUPPLY = "powersupply";
    public static final String WORKSTATION_CASING = "casing";
    public static final String WORKSTATION_MONITOR1 = "monitor1";
    public static final String WORKSTATION_MONITOR2 = "monitor2";
    public static final String WORKSTATION_KEYBOARD = "keyboard";
    public static final String WORKSTATION_MOUSE = "mouse";
    public static final String WORKSTATION_UPS = "ups";
    public static final String WORKSTATION_BATTERY = "battery";

    //specs


    public static final String WORKSTATION_sMOTHERBOARD = "smotherboard";
    public static final String WORKSTATION_sPROCESSOR = "sprocessor";
    public static final String WORKSTATION_sMEMORY1 = "smemory1";
    public static final String WORKSTATION_sMEMORY2 = "smemory2";
    public static final String WORKSTATION_sLAN_CARD = "slancard";
    public static final String WORKSTATION_sVIDEO_CARD = "svideocard";
    public static final String WORKSTATION_sHARD_DISK1 = "sharddisk1";
    public static final String WORKSTATION_sHARD_DISK2 = "sharddisk2";
    public static final String WORKSTATION_sPOWERSUPPLY = "spowersupply";
    public static final String WORKSTATION_sCASING = "scasing";
    public static final String WORKSTATION_sMONITOR1 = "smonitor1";
    public static final String WORKSTATION_sMONITOR2 = "smonitor2";
    public static final String WORKSTATION_sKEYBOARD = "skeyboard";
    public static final String WORKSTATION_sMOUSE = "smouse";
    public static final String WORKSTATION_sUPS = "sups";
    public static final String WORKSTATION_sBATTERY = "sbattery";


    static String item, workstationItem,workstationItem2,keySpecs, dataKey, insertData;
    static ArrayList<String> firstList;
    static ArrayList<String> secondList;

    ArrayList<String> moA = new ArrayList<String>();
    ArrayList<String> ppA = new ArrayList<String>();
    ArrayList<String> mmA = new ArrayList<String>();
    ArrayList<String> lcA = new ArrayList<String>();
    ArrayList<String> dmA = new ArrayList<String>();
    ArrayList<String> hdA = new ArrayList<String>();
    ArrayList<String> pwA = new ArrayList<String>();
    ArrayList<String> ccA = new ArrayList<String>();
    ArrayList<String> omA = new ArrayList<String>();
    ArrayList<String> kbA = new ArrayList<String>();
    ArrayList<String> msA = new ArrayList<String>();
    ArrayList<String> upA = new ArrayList<String>();
    ArrayList<String> gqA = new ArrayList<String>();








    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table tableWorkstations " +
                        "(id integer primary key, name text, motherboard text, processor text, memory1 text, memory2 text, lancard text, videocard text, harddisk1 text, harddisk2 text, powersupply text, casing text, monitor1 text, monitor2 text, keyboard text, mouse text, ups text, battery text, smotherboard text, sprocessor text, smemory1 text, smemory2 text, slancard text, svideocard text, sharddisk1 text, sharddisk2 text, spowersupply text, scasing text, smonitor1 text, smonitor2 text, skeyboard text, smouse text, sups text, sbattery text)"
        );
        db.execSQL(
                "create table tableSpecs " +
                        "(item text, specs text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS tableWorkstations");
        onCreate(db);
    }


  //  public boolean insertSpecs (String specs, String moth)




    public boolean insertWorkstation(String name, String motherboard, String processor, String memory1, String memory2, String lancard, String videocard, String harddisk1, String harddisk2, String powersupply, String casing, String monitor1, String monitor2, String keyboard, String mouse, String ups, String battery, String smotherboard, String sprocessor, String smemory1, String smemory2, String slancard, String svideocard, String sharddisk1, String sharddisk2, String spowersupply, String scasing, String smonitor1, String smonitor2, String skeyboard, String smouse, String sups, String sbattery) {
        SQLiteDatabase db = this.getWritableDatabase();
        //barcode
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("motherboard", motherboard);
        contentValues.put("processor", processor);
        contentValues.put("memory1", memory1);
        contentValues.put("memory2", memory2);
        contentValues.put("lancard", lancard);
        contentValues.put("videocard", videocard);
        contentValues.put("harddisk1", harddisk1);
        contentValues.put("harddisk2", harddisk2);
        contentValues.put("powersupply", powersupply);
        contentValues.put("casing", casing);
        contentValues.put("monitor1", monitor1);
        contentValues.put("monitor2", monitor2);
        contentValues.put("keyboard", keyboard);
        contentValues.put("mouse", mouse);
        contentValues.put("ups", ups);
        contentValues.put("battery", battery);
        //specs
       contentValues.put("smotherboard", smotherboard);
       contentValues.put("sprocessor", sprocessor);
        contentValues.put("smemory1", smemory1);
        contentValues.put("smemory2", smemory2);
        contentValues.put("slancard", slancard);
        contentValues.put("svideocard", svideocard);
        contentValues.put("sharddisk1", harddisk1);
        contentValues.put("sharddisk2", harddisk2);
        contentValues.put("spowersupply", powersupply);
        contentValues.put("scasing", casing);
        contentValues.put("smonitor1", monitor1);
        contentValues.put("smonitor2", monitor2);
        contentValues.put("skeyboard", keyboard);
        contentValues.put("smouse", mouse);
        contentValues.put("sups", ups);
        contentValues.put("sbattery", battery);

        db.insert("tableWorkstations", null, contentValues);
        return true;
    }



    public boolean insertSpecs(String item, String specs){
    SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

       contentValues.put("item", dataKey);
       contentValues.put("specs", this.insertData);
       db.insert("tableSpecs", null, contentValues);
        return true;
    }

    public boolean AddSpecs(String item, String specs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("item", SettingsActivity.keySpec);
        contentValues.put("specs", SettingsActivity.mInput);
        db.insert("tableSpecs", null, contentValues);
        return true;
    }

    public boolean AddSpecsWorkstationInfo(String item, String specs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("item", WorkstationInfo.keySpecs);
        contentValues.put("specs", WorkstationInfo.mInput);
        db.insert("tableSpecs", null, contentValues);
        return true;
    }


  /* void addSpecs() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("item", dataKey);
        values.put("specs", this.insertData);


        // Inserting Row
        db.insert("specs", null, values);
    //    db.close(); // Closing database connection
    }
*/





    public Cursor getData1(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tableWorkstations where id="+id+"", null );
        return res;
    }
    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tableWorkstations where name="+"'"+name+"'", null );
        return res;
    }



    public void deleteTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from tableSpecs where item="+"'"+keySpecs+"'");
    }



    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, WORKSTATIONS);
        return numRows;
    }

    public boolean updateWorkstation(Integer id, String name, String motherboard, String processor, String memory1, String memory2, String lancard, String videocard, String harddisk1, String harddisk2, String powersupply, String casing, String monitor1, String monitor2, String keyboard, String mouse, String ups, String battery, String smotherboard, String sprocessor, String smemory1, String smemory2, String slancard, String svideocard, String sharddisk1, String sharddisk2, String spowersupply, String scasing, String smonitor1, String smonitor2, String skeyboard, String smouse, String sups, String sbattery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //barcode
        contentValues.put("name", name);
        contentValues.put("motherboard", motherboard);
        contentValues.put("processor", processor);
        contentValues.put("memory1", memory1);
        contentValues.put("memory2", memory2);
        contentValues.put("lancard", lancard);
        contentValues.put("videocard", videocard);
        contentValues.put("harddisk1", harddisk1);
        contentValues.put("harddisk2", harddisk2);
        contentValues.put("powersupply", powersupply);
        contentValues.put("casing", casing);
        contentValues.put("monitor1", monitor1);
        contentValues.put("monitor2", monitor2);
        contentValues.put("keyboard", keyboard);
        contentValues.put("mouse", mouse);
        contentValues.put("ups", ups);
        contentValues.put("battery", battery);
        //specs
      contentValues.put("smotherboard", smotherboard);
      contentValues.put("sprocessor", sprocessor);
        contentValues.put("smemory1", smemory1);
        contentValues.put("smemory2", smemory2);
        contentValues.put("slancard", slancard);
        contentValues.put("svideocard", svideocard);
        contentValues.put("sharddisk1", sharddisk1);
        contentValues.put("sharddisk2", sharddisk2);
        contentValues.put("spowersupply", spowersupply);
        contentValues.put("scasing", scasing);
        contentValues.put("smonitor1", smonitor1);
        contentValues.put("smonitor2", smonitor2);
        contentValues.put("skeyboard", skeyboard);
        contentValues.put("smouse", smouse);
        contentValues.put("sups", sups);
        contentValues.put("sbattery", sbattery);

        db.update("tableWorkstations", contentValues, "name = ? ", new String[] { (name) } );
        return true;
    }






    public Integer deleteWorkstation1 (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tableWorkstations",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteWorkstation (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tableWorkstations",
                "name = ? ",
                new String[] { (name) });
    }



    public ArrayList<String> getAllWorkstations() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tableWorkstations order by name ASC", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(WORKSTATION_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getData_Item() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tableWorkstations", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(workstationItem)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getData_Item2() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tableWorkstations", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(workstationItem2)));
            res.moveToNext();
        }
        return array_list;
    }


    public ArrayList<String> getSpecsFromTableSpecs() {

        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tableSpecs where item="+"'"+SettingsActivity.keySpec+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("specs")));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getSpecsFromTableSpecs1() {

        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tableSpecs where item="+"'memory_specs'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("specs")));
            res.moveToNext();
        }
        return array_list;
    }




    public ArrayList<String> getSpecs() {

        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tableSpecs where item="+"'"+keySpecs+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("specs")));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getSpecsWI() {

        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tableSpecs where item="+"'"+WorkstationInfo.keySpecs+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("specs")));
            res.moveToNext();
        }
        return array_list;
    }







    public void getDataToStore() {



        String[] specsFromWorkstation = {"smotherboard", "sprocessor", "smemory", "slancard", "svideocard", "sharddisk", "spowersupply", "scasing", "smonitor", "skeyboard", "smouse", "sups", "sbattery"};
        String[] specsFromSpecs = {"motherboard_specs", "processor_specs", "memory_specs", "lancard_specs", "videocard_specs", "harddisk_specs", "powersupply_specs", "casing_specs", "monitor_specs", "keyboard_specs", "mouse_specs", "ups_specs", "battery_specs"};

        firstList = new ArrayList<String>();
        secondList = new ArrayList<String>();



        for (int x = 0; x < specsFromWorkstation.length; x++) {
            Boolean isDouble = false;
            secondList.clear();
            workstationItem = specsFromWorkstation[x];
            keySpecs = specsFromSpecs[x];
            if (workstationItem.equals("smemory")){
                isDouble = true;
            }
            if (workstationItem.equals("sharddisk")){
                isDouble = true;
            }
            if (workstationItem.equals("smonitor")){
                isDouble = true;
            }

            if (isDouble == false ) {

                firstList = this.getData_Item();
                HashSet<String> hashSet = new HashSet<String>();
                hashSet.addAll(firstList);
                firstList.clear();
                firstList.addAll(hashSet);

                secondList = this.getSpecs();
                secondList.addAll(firstList);
                HashSet<String> hashSet1 = new HashSet<String>();
                hashSet1.addAll(secondList);
                secondList.clear();
                secondList.addAll(hashSet1);
                insert();
            }




           switch (workstationItem) {
                case "smemory":
                    firstList.clear();
                    secondList.clear();
                    keySpecs="memory_specs";
                    workstationItem2 = "smemory1";
                    ArrayList memory1 = this.getData_Item2();
                    HashSet<String> hashSetm1 = new HashSet<String>();
                    hashSetm1.addAll(memory1);
                    memory1.clear();
                    memory1.addAll(hashSetm1);

                    workstationItem2 = "smemory2";
                    ArrayList memory2 = this.getData_Item2();
                    memory2.addAll(memory1);
                    HashSet<String> hashSetm2 = new HashSet<String>();
                    hashSetm2.addAll(memory2);
                    memory2.clear();
                    memory2.addAll(hashSetm2);



                    firstList.addAll(memory2);
                    HashSet<String> hashSet3 = new HashSet<String>();
                    hashSet3.addAll(firstList);
                    firstList.clear();
                    firstList.addAll(hashSet3);

                    secondList = this.getSpecs();
                    secondList.addAll(firstList);
                    HashSet<String> hashSet4 = new HashSet<String>();
                    hashSet4.addAll(secondList);
                    secondList.clear();
                    secondList.addAll(hashSet4);

                    dataKey = "memory_specs";
                    item = workstationItem;
                    mmA.addAll(secondList);
                    deleteTable();
                    for (int i = 0; i < secondList.size(); i++) {
                        insertData = mmA.get(i);

                        this.insertSpecs(dataKey,insertData);
                    }


                    break;
                case "sharddisk":
                    firstList.clear();
                    secondList.clear();
                    keySpecs="harddisk_specs";
                    workstationItem2 = "sharddisk1";
                    ArrayList harddisk1 = this.getData_Item2();
                    HashSet<String> hashSeth1 = new HashSet<String>();
                    hashSeth1.addAll(harddisk1);
                    harddisk1.clear();
                    harddisk1.addAll(hashSeth1);

                    workstationItem2 = "sharddisk2";
                    ArrayList harddisk2 = this.getData_Item2();
                    harddisk2.addAll(harddisk1);
                    HashSet<String> hashSeth2 = new HashSet<String>();
                    hashSeth2.addAll(harddisk2);
                    harddisk2.clear();
                    harddisk2.addAll(hashSeth2);



                    firstList.addAll(harddisk2);
                    HashSet<String> hashSet6 = new HashSet<String>();
                    hashSet6.addAll(firstList);
                    firstList.clear();
                    firstList.addAll(hashSet6);

                    secondList = this.getSpecs();
                    secondList.addAll(firstList);
                    HashSet<String> hashSet5 = new HashSet<String>();
                    hashSet5.addAll(secondList);
                    secondList.clear();
                    secondList.addAll(hashSet5);

                    dataKey = "harddisk_specs";
                    item = workstationItem;
                    hdA.addAll(secondList);
                    deleteTable();
                    for (int i = 0; i < secondList.size(); i++) {
                        insertData = hdA.get(i);

                        this.insertSpecs(dataKey,insertData);
                    }
                    break;
                case "smonitor":
                    firstList.clear();
                    secondList.clear();
                    keySpecs="monitor_specs";
                    workstationItem2 = "smonitor1";
                    ArrayList monitor1 = this.getData_Item2();
                    HashSet<String> hashSeth6 = new HashSet<String>();
                    hashSeth6.addAll(monitor1);
                    monitor1.clear();
                    monitor1.addAll(hashSeth6);

                    workstationItem2 = "smonitor2";
                    ArrayList monitor2 = this.getData_Item2();
                    monitor2.addAll(monitor2);
                    HashSet<String> hashSeth7 = new HashSet<String>();
                    hashSeth7.addAll(monitor2);
                    monitor2.clear();
                    monitor2.addAll(hashSeth7);



                    firstList.addAll(monitor2);
                    HashSet<String> hashSet8 = new HashSet<String>();
                    hashSet8.addAll(firstList);
                    firstList.clear();
                    firstList.addAll(hashSet8);

                    secondList = this.getSpecs();
                    secondList.addAll(firstList);
                    HashSet<String> hashSet9 = new HashSet<String>();
                    hashSet9.addAll(secondList);
                    secondList.clear();
                    secondList.addAll(hashSet9);

                    dataKey = "monitor_specs";
                    item = workstationItem;
                    omA.addAll(secondList);
                    deleteTable();
                    for (int i = 0; i < secondList.size(); i++) {
                        insertData = omA.get(i);

                        this.insertSpecs(dataKey,insertData);
                    }
                    break;
                default:


            }


        }
    }







    public void insert(){


        switch (workstationItem) {
        case "smotherboard":
            dataKey = "motherboard_specs";
            item = "motherboard";
            moA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = moA.get(i);

                this.insertSpecs(dataKey,insertData);
            }


            break;

        case "sprocessor":
            dataKey = "processor_specs";
            item = workstationItem;
            ppA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = ppA.get(i);

                this.insertSpecs(dataKey,insertData);
            }
            break;

        case "smemory":

            break;

        case "slancard":
            dataKey = "lancard_specs";
            item = workstationItem;
            lcA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = lcA.get(i);

                this.insertSpecs(dataKey,insertData);
            }
            break;

        case "svideocard":
            dataKey = "videocard_specs";
            item = workstationItem;
            dmA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = dmA.get(i);

                this.insertSpecs(dataKey,insertData);
            }
            break;

        case "sharddisk":
            dataKey = "harddisk_specs";
            item = workstationItem;
            hdA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = hdA.get(i);

                this.insertSpecs(dataKey,insertData);
            }
            break;

        case "spowersupply":
            dataKey = "powersupply_specs";
            item = workstationItem;
            pwA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = pwA.get(i);

                this.insertSpecs(dataKey,insertData);
            }
            break;

        case "scasing":
            dataKey = "casing_specs";
            item = workstationItem;
            ccA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = ccA.get(i);

                this.insertSpecs(dataKey,insertData);
            }

            break;


        case "skeyboard":
            dataKey = "keyboard_specs";
            item = workstationItem;
            kbA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = kbA.get(i);

                this.insertSpecs(dataKey,insertData);
            }
            break;
        case "smouse":
            dataKey = "mouse_specs";
            item = workstationItem;
            msA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = msA.get(i);

                this.insertSpecs(dataKey,insertData);
            }
            break;

        case "sup":
            dataKey = "ups_specs";
            item = workstationItem;
            upA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = upA.get(i);

                this.insertSpecs(dataKey,insertData);
            }
            break;

        case "gq":
            dataKey = "battery_specs";
            item = workstationItem;
            gqA.addAll(secondList);
            deleteTable();
            for (int i = 0; i < secondList.size(); i++) {
                insertData = gqA.get(i);

                this.insertSpecs(dataKey,insertData);
            }
            break;

    }

}


   /*  public void toInsert () {
         for (int i = 0; i < .size;i++){
             String insertS = secondList.get(i).toString();
             //  Toast.makeText(WorkstationInfo.this, secondList.get(i).toString(), Toast.LENGTH_SHORT).show();
             mydb.insertSpecs(insertS.toString(),
                     insertS.toString(),
                     insertS.toString(),
                     insertS.toString(),
                     insertS.toString(),
                     insertS.toString(),
                     insertS.toString(),
                     insertS.toString(),
                     insertS.toString(),
                     insertS.toString(),
                     insertS.toString(),
                     insertS.toString(),
                     insertS.toString());

         }
     }
*/
        public void mergeMemory () {

            ArrayList<String> values = new ArrayList<String>();
            HashSet<String> hashSet = new HashSet<String>();
            hashSet.addAll(values);
            values.clear();
            values.addAll(hashSet);

        }
        public void mergeDisk () {


        }
        public void mergeMonitor () {


        }



    public boolean addRes(String item, String specs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("item", ScrollingActivity.first);
        contentValues.put("specs", ScrollingActivity.second);
        db.insert("tableSpecs", null, contentValues);
        return true;
    }




    public ArrayList<Integer> idExport() {
        ArrayList<Integer> array_list = new ArrayList<Integer>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tableWorkstations order by name ASC", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getInt(res.getColumnIndex("id")));
            res.moveToNext();
        }
        return array_list;
    }


    public String test2() {

        String rv = "not found";
        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause = "ID=?";
        String[] whereargs = new String[]{String.valueOf(Ups.iD)};
        Cursor csr = db.query("tableWorkstations",null,whereclause,whereargs,null,null,null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndex(Ups.toFetch));
        }
        return rv;
    }




}


























