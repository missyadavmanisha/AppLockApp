package com.codingblocks.applockfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class snsAdapter extends BaseAdapter {


    ArrayList<sns_list>sns_lists;
    Context ctx;

    public snsAdapter(ArrayList<sns_list> sns_lists, Context ctx) {
        this.sns_lists = sns_lists;
        this.ctx = ctx;
    }

    @Override
    public sns_list getItem(int position) {
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

            holder.imageView = inflatedView.findViewById(R.id.image_sns);
            holder.Heading = inflatedView.findViewById(R.id.text_sns);
            inflatedView.setTag(holder);

        }
        else
        {
            inflatedView=convertView;
            holder=(SuperHeroHolder)inflatedView.getTag();
        }
       sns_list  snsList =getItem(position) ;


        holder.Heading.setText(snsList.getNamesns());
        holder.imageView.setImageDrawable(snsList.getImagesns());

        return inflatedView;
    }

    class SuperHeroHolder{
        ImageView imageView;
        TextView Heading;
    }


}



