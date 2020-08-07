package tech.hoangphi.demo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tech.hoangphi.demo.Model.Weather;
import tech.hoangphi.demo.R;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{
    Context context;
    ArrayList<Weather> arrWeathers;

    public WeatherAdapter(Context context, ArrayList<Weather> arrWeathers) {
        this.context = context;
        this.arrWeathers = arrWeathers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.line_weather, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Weather weather = arrWeathers.get(position);
        holder.tv_dia_diem.setText(weather.getDiadiem());
        int nhiet_do = weather.getNhietdo();
        holder.tv_nhiet_do.setText(String.valueOf(nhiet_do));
        if(nhiet_do > 30){

            holder.imv_logo.setImageResource(R.drawable.sun);
        } else if(nhiet_do < 20){
            holder.imv_logo.setImageResource(R.drawable.rain);
        } else if(nhiet_do >= 20 || nhiet_do <= 30){
            holder.imv_logo.setImageResource(R.drawable.cloud);
        }
        holder.tv_kieu_thoi_tiet.setText(weather.getKieuThoiTiet());
    }

    @Override
    public int getItemCount() {
        return arrWeathers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_logo;
        TextView tv_dia_diem, tv_nhiet_do, tv_kieu_thoi_tiet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_logo = itemView.findViewById(R.id.imv_logo);
            tv_dia_diem = itemView.findViewById(R.id.tv_dia_diem);
            tv_nhiet_do = itemView.findViewById(R.id.tv_nhiet_do);
            tv_kieu_thoi_tiet = itemView.findViewById(R.id.tv_kieu_thoi_tiet);
        }
    }
}
