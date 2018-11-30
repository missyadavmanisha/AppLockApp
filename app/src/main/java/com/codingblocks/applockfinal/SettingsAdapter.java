package com.codingblocks.applockfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingsAdapter extends BaseAdapter {


    ArrayList<Settingslist> sns_lists;
    Context ctx;

    public SettingsAdapter(ArrayList<Settingslist> sns_lists, Context ctx) {
        this.sns_lists = sns_lists;
        this.ctx = ctx;
    }

    @Override
    public Settingslist getItem(int position) {
        return sns_lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return  sns_lists.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SuperHeroHolder holder;
        View inflatedView;
        LayoutInflater li = LayoutInflater.from(ctx);
        if(convertView==null) {
            inflatedView = li.inflate(R.layout.list_chamber, parent, false);
            holder = new SuperHeroHolder();

            holder.imageView = inflatedView.findViewById(R.id.image1);
            holder.Heading = inflatedView.findViewById(R.id.lock);
            holder.subheading=inflatedView.findViewById(R.id.lock1);
            inflatedView.setTag(holder);

        }
        else
        {
            inflatedView=convertView;
            holder=(SuperHeroHolder)inflatedView.getTag();
        }
        Settingslist  snsList =getItem(position) ;


        holder.Heading.setText(snsList.getName());
        holder.imageView.setImageDrawable(snsList.getDrawable());
        holder.subheading.setText(snsList.getSubheading());

        return inflatedView;
    }

    class SuperHeroHolder{
        ImageView imageView;
        TextView Heading,subheading;
    }


}



