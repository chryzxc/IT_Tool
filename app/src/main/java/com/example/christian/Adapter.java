package com.example.christian;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internet_speed_testing.ProgressionModel;

import java.util.List;

import static java.security.AccessController.getContext;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    List<String> titles;
    List<Integer> images;
    Context context;
    LayoutInflater inflater;

    public Adapter(Context ctx, List<String> titles, List<Integer> images){
        this.titles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(ctx);


    }








    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.gridIcon.setImageResource(images.get(position));



    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public void setDataList(int count, ProgressionModel progressModel) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.textView2);
            gridIcon =itemView.findViewById(R.id.imageView2);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 Integer pos = getAdapterPosition();

                    switch(pos) {
                        case 0:
                            Intent intent1 = new Intent(v.getContext(), Workstation.class);
                            v.getContext().startActivity(intent1);
                            break;
                        case 1:
                            Intent intent2 = new Intent(v.getContext(), UpsCheck.class);
                            v.getContext().startActivity(intent2);
                            break;
                        case 2:

                            break;
                        case 3:
                            Intent intent3 = new Intent(v.getContext(), Speedtest.class);
                            v.getContext().startActivity(intent3);
                            break;
                        default:
                  //          setContentView(R.layout.default);
                            break;
                    }




                }
            });
        }
    }
}
