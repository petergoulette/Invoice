package com.example.capstone.invoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AddInvoiceItemView extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    boolean update = false;
    EditText IName;
    EditText IRate;
    EditText QFront;
    EditText QLeft;
    EditText QRight;
    EditText QBack;
    ListView itemListView;
    ItemViewListAdapter itemViewAdapter;
    private int invoiceId;
    private ArrayList<InvoiceItem> AInvoiceItem;
    private Database dbHandler;
    private int invoiceIID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice_item_view);
        dbHandler = new Database(this, null, null, 1);

        IName = (EditText) findViewById(R.id._itemName);
        IRate = (EditText) findViewById(R.id.itemRate);
        QFront = (EditText) findViewById(R.id.FQuantity);
        QBack = (EditText) findViewById(R.id.BQuantity);
        QLeft = (EditText) findViewById(R.id.LQuantity);
        QRight = (EditText) findViewById(R.id.RQuantity);
        QFront.setText("0");
        QBack.setText("0");
        QLeft.setText("0");
        QRight.setText("0");

        //get Bundle from intent and extract the invoiceId
        Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();
        if (extrasBundle != null){
            Log.d("bundle", "");
            //we have a bundle

            if(extrasBundle.getInt("SendInvoiceID")!=0){
                invoiceId = extrasBundle.getInt("SendInvoiceID");
                Log.d("bundle", "" + invoiceId);}

            if(extrasBundle.getInt("SendInvoiceItem")!=0){

                InvoiceItem iitem = new InvoiceItem();
                invoiceIID = extrasBundle.getInt("SendInvoiceItem");
                iitem = dbHandler.getInvoiceItem(invoiceIID);
                IName.setText(iitem.getInvoiceItemName());
                IRate.setText(moneyFormat(iitem));
                QFront.setText(Integer.toString(iitem.getInvoiceItemFQuantity()));
                QBack.setText(Integer.toString(iitem.getInvoiceItemBQuantity()));
                QLeft.setText(Integer.toString(iitem.getInvoiceItemLQuantity()));
                QRight.setText(Integer.toString(iitem.getInvoiceItemRQuantity()));
                update = true;
                }

            //IRate.setText(Integer.toString(invoiceId));
        }

        itemListView = (ListView) findViewById(R.id.main_item_listview);
        itemListView.setOnItemClickListener(this);
        Log.d("InvoiceItemView", "itemListView: created");

        // create a itemViewListAdapter for the ListView
        itemViewAdapter = new ItemViewListAdapter(this, getLayoutInflater());
        Log.d("InvoiceItemView", "itemViewAdapter: created");

        // Set ListView to use the ArrayAdapter
        itemListView.setAdapter(itemViewAdapter);
        Log.d("InvoiceItemView", "itemsetAdapter: created");
        itemViewAdapter.updateData(dbHandler.getItemList());

    }

    public void AddInvoiceItem(View view){
        Database dbHandler = new Database(this, null, null, 1);

        if (IName.getText().toString().equals("") || IRate.getText().toString().equals("") || update == true){
            Log.d("InvoiceItemView", "error Should not add");
        }else{
            Log.d("InvoiceItemView", "Should add to database");
            InvoiceItem iitem;
            int rate, QF, QB, QL, QR;
            try {
                String s= IRate.getText().toString();
                rate =(int) (Double.parseDouble(s.substring(1)) * 100);
                QF = (int) (Integer.parseInt(QFront.getText().toString()));
                QB = (int) (Integer.parseInt(QBack.getText().toString()));
                QL = (int) (Integer.parseInt(QLeft.getText().toString()));
                QR = (int) (Integer.parseInt(QRight.getText().toString()));

                iitem = new InvoiceItem(invoiceId, IName.getText().toString(), rate, QF, QB, QL, QR);

                    dbHandler.addInvoiceItem(iitem);

            }catch (Exception e) {
                Log.d("InvoiceItemView", e.toString());
            }

        }
        finish();
    }

    public void updateInvoiceItem(View view){
        if (update){
            InvoiceItem iitem;
            int rate, QF, QB, QL, QR;
            try {
                String s= IRate.getText().toString();
                rate =(int) (Double.parseDouble(s.substring(1)) * 100);
                QF = (Integer.parseInt(QFront.getText().toString()));
                QB = (Integer.parseInt(QBack.getText().toString()));
                QL = (Integer.parseInt(QLeft.getText().toString()));
                QR = (Integer.parseInt(QRight.getText().toString()));

                iitem = new InvoiceItem(invoiceId, IName.getText().toString(), rate, QF, QB, QL, QR);
                iitem.setInvoiceItemId(invoiceIID);
                dbHandler.updateInvoiceItem(iitem);

            }catch (Exception e) {
                Log.d("InvoiceItemView", e.toString());
            }
        }
        finish();
    }

    public void deleteInvoiceItem(View view){
        dbHandler.deleteInvoiceItem(invoiceIID);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_invoice_item_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Item item = itemViewAdapter.getItem(position);
        IName.setText(item.getItemName());
        IRate.setText(moneyFormat(item));
        Log.d("In here", "here" + position);
    }

    private String moneyFormat(Item item){
        Double temp = item.getItemRate()*.01;
        String s = String.format("$%.2f", temp );
        return s;
    }

    private String moneyFormat(InvoiceItem item){
        Double temp = item.getInvoiceItemRate()*.01;
        String s = String.format("$%.2f", temp );
        return s;
    }

    private void ResetTextField(){
        IRate.setText("");
        IName.setText("");
        QFront.setText("0");
        QBack.setText("0");
        QLeft.setText("0");
        QRight.setText("0");
    }


}
