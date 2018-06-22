package app.superesenou.ru.weatherapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import app.superesenou.ru.weatherapplication.R;
import app.superesenou.ru.weatherapplication.model.Example;
import app.superesenou.ru.weatherapplication.model.Forecast;
import app.superesenou.ru.weatherapplication.model.Image;

public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.ViewHolder> {
    List<Forecast> forecasts;
    String weatherIconImageResource;
    public AdapterWeather(List<Forecast> forecasts) {
        this.forecasts=forecasts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forecast Day=forecasts.get(position);
        holder.day.setText(Day.getDay());
        holder.text.setText(Day.getText());
        String a=Day.getHigh();
        holder.high.setText(Day.getHigh()+"\u00b0");
        holder.low.setText(Day.getLow()+"\u00b0");
        holder.data.setText(Day.getDate());
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView day,text,high,low,data;


        public ViewHolder(View itemView) {

            super(itemView);
            day=(TextView)itemView.findViewById(R.id.Day);
            text=(TextView)itemView.findViewById(R.id.text);
            low=(TextView)itemView.findViewById(R.id.low);
            high=(TextView)itemView.findViewById(R.id.high);
            data=(TextView)itemView.findViewById(R.id.data);

        }

        private String getPackageName() {
            return getPackageName();
        }
    }
}
