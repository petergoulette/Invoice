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
public class InvoiceItemViewListAdapter extends BaseAdapter{

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<InvoiceItem> invoiceItemAList;

    public InvoiceItemViewListAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        invoiceItemAList = new ArrayList();
    }


    @Override
    public int getCount() {
        return invoiceItemAList.size();
    }

    @Override
    public InvoiceItem getItem(int position) {
        return invoiceItemAList.get(position);
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
            convertView = mInflater.inflate(R.layout.invoice_item_list_view, null);

            //create a "Holder" with subviews
            holder = new ViewHolder();
            holder.titleNameView = (TextView) convertView.findViewById(R.id.text_item_name);
            holder.titleRateView = (TextView) convertView.findViewById(R.id.text_item_rate);
            holder.titleRightQuantity = (TextView) convertView.findViewById(R.id.text_InvoiceRQuantity);
            holder.titleLeftQuantity = (TextView) convertView.findViewById(R.id.text_InvoiceLQuantity);
            holder.titleFrontQuantity = (TextView) convertView.findViewById(R.id.text_InvoiceFQuantity);
            holder.titleBackQuantity = (TextView) convertView.findViewById(R.id.text_InvoiceBQuantity);

            // hang on to this holder for future recyclage
            convertView.setTag(holder);
        } else {
            //skip all the expensive inflation/findViewById
            //and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the curent item
        Log.d("View positon:", "" + position);
        InvoiceItem Iitem = getItem(position);

        String itemTitle = "";
        String rateTitle = "";
        String qFront ="";
        String qBack ="";
        String qLeft ="";
        String qRight ="";

        if (Iitem.getInvoiceItemName()!= null){
            itemTitle = Iitem.getInvoiceItemName();
        }

        rateTitle = moneyFormat(Iitem);
        qFront = Integer.toString(Iitem.getInvoiceItemFQuantity());
        qBack = Integer.toString(Iitem.getInvoiceItemBQuantity());
        qLeft = Integer.toString(Iitem.getInvoiceItemLQuantity());
        qRight = Integer.toString(Iitem.getInvoiceItemRQuantity());
        // Send these Strings to the TextViews for display
        holder.titleNameView.setText(itemTitle);
        holder.titleRateView.setText(rateTitle);
        holder.titleFrontQuantity.setText(qFront);
        holder.titleBackQuantity.setText(qBack);
        holder.titleLeftQuantity.setText(qLeft);
        holder.titleRightQuantity.setText(qRight);


        return convertView;
    }

    private String moneyFormat(InvoiceItem item){
        Double temp = item.getInvoiceItemRate()*.01;
        String s = String.format("$%.2f", temp );
        return s;
    }

    public void updateData(ArrayList<InvoiceItem> aitem){
        invoiceItemAList = aitem;
        notifyDataSetChanged();
    }
    // this is used so you only ever have to do
// inflation and finding by ID once ever per View
    private static class ViewHolder {
        public TextView titleNameView;
        public TextView titleRateView;
        public TextView titleRightQuantity;
        public TextView titleLeftQuantity;
        public TextView titleFrontQuantity;
        public TextView titleBackQuantity;
    }
}
