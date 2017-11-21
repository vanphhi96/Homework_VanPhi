package com.example.vanph.homeworkrecycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vanph on 21/11/2017.
 */

public class RecycleAdapter  extends RecyclerView.Adapter<RecycleAdapter.WeatherHoolder>{
    List<WeatherModel> weatherModelList;
    public RecycleAdapter( List<WeatherModel> weatherModelList)
    {
        this.weatherModelList = weatherModelList;
    }
    @Override
    public WeatherHoolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_layout_thoitiet,parent,false);
        return new WeatherHoolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherHoolder holder, int position) {
        holder.setData(weatherModelList.get(position));
    }

    @Override
    public int getItemCount() {
       return weatherModelList.size();
    }

    public class WeatherHoolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv_nhietdo;
        TextView tv_doam;
        TextView tv_mota;
        TextView tv_ngay;
        public WeatherHoolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_tinhtrang);
            tv_nhietdo = itemView.findViewById(R.id.tv_nhietdo);
            tv_doam = itemView.findViewById(R.id.tv_doam);
            tv_mota = itemView.findViewById(R.id.tv_mota);
            tv_ngay = itemView.findViewById(R.id.tv_ngay);
        }
        public void setData(WeatherModel weatherModel)
        {
            float doC = Math.round(weatherModel.temp-273);
            float doam = Math.round(weatherModel.doam);
            imageView.setImageResource(weatherModel.imageID);
            tv_nhietdo.setText("Nhiệt độ: "+Float.toString(doC));
            tv_doam.setText("Độ ẩm: "+Float.toString(doam)+"%");
            tv_mota.setText("Mô tả: "+weatherModel.mota);
            tv_ngay.setText("Ngày: "+Integer.toString(weatherModel.ngay));
        }
    }
}
