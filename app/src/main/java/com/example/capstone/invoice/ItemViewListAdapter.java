package com.example.capstone.invoice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pierro on 11/14/2015.
 */
public class ItemViewListAdapter extends BaseAdapter{

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<Item> itemAList;

    public ItemViewListAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        itemAList = new ArrayList();
    }


    @Override
    public int getCount() {
        return itemAList.size();
    }

    @Override
    public Item getItem(int position) {
        return itemAList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            //Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.item_list_view, null);

            //create a "Holder" with subviews
            holder = new ViewHolder();
            holder.titleNameView = (TextView) convertView.findViewById(R.id.text_item_name);
            holder.titleRateView = (TextView) convertView.findViewById(R.id.text_item_rate);

            // hang on to this holder for future recyclage
            convertView.setTag(holder);
        } else {
            //skip all the expensive inflation/findViewById
            //and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the curent item
        Log.d("View positon:", "" + position);
        Item item = getItem(position);

        String itemTitle = "";
        String rateTitle = "";

        if (item.getItemName()!= null){
            itemTitle = item.getItemName();
        }

        rateTitle = moneyFormat(item);
        // Send these Strings to the TextViews for display
        holder.titleNameView.setText(itemTitle);
        holder.titleRateView.setText(rateTitle);


        return convertView;
    }

    private String moneyFormat(Item item){
        Double temp = item.getItemRate()*.01;
        String s = String.format("$%.2f", temp );
        return s;
    }

    public void updateData(ArrayList<Item> aitem){
        itemAList = aitem;
        notifyDataSetChanged();
    }
    // this is used so you only ever have to do
// inflation and finding by ID once ever per View
    private static class ViewHolder {
        public TextView titleNameView;
        public TextView titleRateView;
    }
}
