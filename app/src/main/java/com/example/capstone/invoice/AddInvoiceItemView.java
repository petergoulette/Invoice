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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice_item_view);
        Database dbHandler = new Database(this, null, null, 1);

        //get Bundle from intent and extract the invoiceId
        Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();
        if (extrasBundle != null){
            Log.d("bundle", "");
            //we have a bundle
            invoiceId = extrasBundle.getInt("SendInvoiceID");
            Log.d("bundle", "" + invoiceId);
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

    }

    public void AddInvoiceItem(View view){
        Database dbHandler = new Database(this, null, null, 1);

        if (IName.getText().toString().equals("") || IRate.getText().toString().equals("")){
            Log.d("InvoiceItemView", "error Should not add");
        }else{
            Log.d("InvoiceItemView", "Should add to database");
            InvoiceItem iitem;
            int rate, QF, QB, QL, QR;
            try {
                rate =(int) (Double.parseDouble(IRate.getText().toString()) * 100);
                QF = (int) (Integer.parseInt(QFront.getText().toString()));
                QB = (int) (Integer.parseInt(QBack.getText().toString()));
                QL = (int) (Integer.parseInt(QLeft.getText().toString()));
                QR = (int) (Integer.parseInt(QRight.getText().toString()));

                iitem = new InvoiceItem(invoiceId, IName.getText().toString(), rate, QF, QB, QL, QR);

                    dbHandler.addInvoiceItem(iitem);

                //ResetTextField();


                    //itemViewAdapter.updateData(dbHandler.getItemList());
//                    Log.d("InvoiceItemView", "ok item updated");
//                Log.d("InvoiceItemView", "before aInvoiceItem");
//                AInvoiceItem = dbHandler.getInvoiceItemList(invoiceId);
//                Log.d("InvoiceItemView", "after aInvoiceItem");
//                InvoiceItem Itest = AInvoiceItem.get(0);
//                Log.d("InvoiceItemView", "got list" + Itest.getInvoiceItemName());

            }catch (Exception e) {
                Log.d("InvoiceItemView", e.toString());
            }

        }
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
        IRate.setText(Integer.toString(item.getItemRate()));
        Log.d("In here", "here" + position);
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
