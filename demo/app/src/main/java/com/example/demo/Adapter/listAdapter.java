package com.example.demo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.demo.Bean.listBean;
import com.example.demo.R;

import java.util.ArrayList;
import java.util.List;

public class listAdapter extends BaseAdapter {
    private List<listBean> aData;
    private Context mContext;

    public listAdapter(List<listBean> aData, Context mContext){
        this.aData = aData;
        this.mContext = mContext;
    }
    @Override
    public int getCount(){
        if (aData!=null){
            return aData.size();
        }
        return 0;
    }
    @Override
    public  Object getItem(int position){
        return null;
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_worklayout,parent,false);
            holder = new ViewHolder();



            holder.date = (TextView)convertView.findViewById(R.id.date);
            holder.transportation = (TextView)convertView.findViewById(R.id.transportation);
            holder.Groceries = (TextView)convertView.findViewById(R.id.Groceries);
            holder.Restaurants = (TextView)convertView.findViewById(R.id.Restaurants);
            holder.Take_Out_Food = (TextView)convertView.findViewById(R.id.Take_Out_Food);

            holder.Take_Out_Coffee = (TextView)convertView.findViewById(R.id.Take_Out_Coffee);
            holder.Household = (TextView)convertView.findViewById(R.id.Household);
            holder.Clothes = (TextView)convertView.findViewById(R.id.Clothes);
            holder.Entertainment = (TextView)convertView.findViewById(R.id.Entertainment);
            holder.Sports = (TextView)convertView.findViewById(R.id.Sports);
            holder.Other = (TextView)convertView.findViewById(R.id.Other);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

//        holder.nameTextView.setText("活动标题:"+aData.get(position).getHuodongName());

        holder.date.setText("date:"+aData.get(position).getDate());
        holder.transportation.setText("transportation:"+aData.get(position).getTransportation());
        holder.Groceries.setText("Groceries:"+aData.get(position).getGroceries());
        holder.Restaurants.setText("Restaurants:"+aData.get(position).getRestaurants());
        holder.Take_Out_Food .setText("Take_Out_Food:"+aData.get(position).getTake_Out_Food());

        holder.Take_Out_Coffee.setText("Take_Out_Coffee:"+aData.get(position).getTake_Out_Coffee());
        holder.Household.setText("Household:"+aData.get(position).getHousehold());
        holder.Clothes.setText("Clothes:"+aData.get(position).getClothes());
        holder.Entertainment.setText("Entertainment:"+aData.get(position).getCEntertainment());
        holder.Sports.setText("Sports:"+aData.get(position).getSports());
        holder.Other.setText("Other:"+aData.get(position).getOther());

        return convertView;
    }

    static class ViewHolder{

        TextView date;
        TextView transportation;
        TextView Groceries;
        TextView Restaurants;
        TextView Take_Out_Food;
        TextView Take_Out_Coffee;
        TextView Household;
        TextView Clothes;
        TextView Entertainment;
        TextView Sports;
        TextView Other;
    }

}