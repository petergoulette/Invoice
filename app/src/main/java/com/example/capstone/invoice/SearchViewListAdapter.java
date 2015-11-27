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
public class SearchViewListAdapter extends BaseAdapter{

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<CustomerInvoice> customerInvoice;

    public SearchViewListAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        customerInvoice = new ArrayList();
    }


    @Override
    public int getCount() {
        return customerInvoice.size();
    }

    @Override
    public CustomerInvoice getItem(int position) {
        return customerInvoice.get(position);
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
            convertView = mInflater.inflate(R.layout.search_list_item, null);

            //create a "Holder" with subviews
            holder = new ViewHolder();
            holder.SIName = (TextView) convertView.findViewById(R.id.SIName);
            holder.SIDate = (TextView) convertView.findViewById(R.id.SIDate);
            holder.FName = (TextView) convertView.findViewById(R.id.FName);
            holder.LName = (TextView) convertView.findViewById(R.id.LName);

            // hang on to this holder for future recyclage
            convertView.setTag(holder);
        } else {
            //skip all the expensive inflation/findViewById
            //and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the curent item
        Log.d("View positon:", "" + position);
        CustomerInvoice Cinvoice = getItem(position);


        // Send these Strings to the TextViews for display
        holder.SIName.setText(Integer.toString(Cinvoice.getInvoiceID()));
        holder.SIDate.setText(Cinvoice.getInvoiceDate());
        holder.FName.setText(Cinvoice.getFirstName());
        holder.LName.setText(Cinvoice.getLastName());


        return convertView;
    }

    public void updateData(ArrayList<CustomerInvoice> aitem){
        customerInvoice = aitem;
        notifyDataSetChanged();
    }
    // this is used so you only ever have to do
// inflation and finding by ID once ever per View
    private static class ViewHolder {
        public TextView SIName;
        public TextView SIDate;
        public TextView FName;
        public TextView LName;

    }
}
