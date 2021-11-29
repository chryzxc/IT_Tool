package com.example.christian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class UpsAdapter extends ArrayAdapter<UpsDataModel> implements View.OnClickListener{

    private ArrayList<UpsDataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView upsWorkstation;
        TextView upsDescription;
        TextView upsBarcode;
        TextView batteryBarcode;
        TextView upsDate;
        TextView upsStatus;
        ImageView upsIcon;
    }

    public UpsAdapter(ArrayList<UpsDataModel> data, Context context) {
        super(context, R.layout.ups_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        UpsDataModel upsdataModel=(UpsDataModel)object;

        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +upsdataModel.getUpsDate(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        UpsDataModel upsdataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ups_item, parent, false);
            viewHolder.upsWorkstation = (TextView) convertView.findViewById(R.id.upsWorkstation);
            viewHolder.upsDescription = (TextView) convertView.findViewById(R.id.upsDescription);
            viewHolder.upsBarcode = (TextView) convertView.findViewById(R.id.upsBarcode);
            viewHolder.batteryBarcode = (TextView) convertView.findViewById(R.id.batteryBarcode);
            viewHolder.upsDate = (TextView) convertView.findViewById(R.id.upsDate);
            viewHolder.upsStatus = (TextView) convertView.findViewById(R.id.upsStatus);
            viewHolder.upsIcon = (ImageView) convertView.findViewById(R.id.upsIcon);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.upsWorkstation.setText(upsdataModel.getUpsWorkstation());
        viewHolder.upsDescription.setText(upsdataModel.getUpsDescription());
        viewHolder.upsBarcode.setText(upsdataModel.getUpsBarcode());
        viewHolder.batteryBarcode.setText(upsdataModel.getBatteryBarcode());
        viewHolder.upsDate.setText(upsdataModel.getUpsDate());
        viewHolder.upsStatus.setText(upsdataModel.getUpsStatus());
        if (upsdataModel.getUpsStatus().matches("Working")){
            viewHolder.upsIcon.setBackgroundResource(R.drawable.ic_working_24);
        }else if (upsdataModel.getUpsStatus().matches("Defective"))
        {
            viewHolder.upsIcon.setBackgroundResource(R.drawable.ic_defective_24);


        }

        viewHolder.upsIcon.setOnClickListener(this);
        viewHolder.upsIcon.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}

