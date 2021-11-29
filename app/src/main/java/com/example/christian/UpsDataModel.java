package com.example.christian;

public class UpsDataModel {

    String upsWorkstation;
    String upsBarcode;
    String batteryBarcode;
    String upsStatus;
    String upsDate;
    String upsDescription;

    public UpsDataModel(String upsWorkstation, String upsStatus,  String upsDescription,String upsBarcode, String batteryBarcode, String upsDate ) {
        this.upsWorkstation=upsWorkstation;
        this.upsBarcode=upsBarcode;
        this.batteryBarcode=batteryBarcode;
        this.upsStatus=upsStatus;
        this.upsDescription=upsDescription;
        this.upsDate=upsDate;

    }

    public String getUpsWorkstation() {
        return upsWorkstation;
    }

    public String getUpsBarcode() {
        return upsBarcode;
    }

    public String getBatteryBarcode() {
        return batteryBarcode;
    }

    public String getUpsDate() {
        return upsDate;
    }

    public String getUpsStatus() {
        return upsStatus;
    }

    public String getUpsDescription() {
        return upsDescription;
    }


}
