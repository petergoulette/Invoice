package com.example.capstone.invoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class Invoice_view extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Customer customer;
    private Invoice invoice;
    private EditText CFirstName;
    private EditText CLastName;
    private EditText CStreet;
    private EditText CCity;
    private EditText CState;
    private EditText CZip;
    private EditText CPhone;
    private EditText CustID;
    private EditText InvoiceDate;
    private EditText InvoiceID;
    private EditText CNotes;
    private Database dbHandler;
    private ListView invoiceItemListView;
    private InvoiceItemViewListAdapter invoiceItemViewAdapter;
    private CheckBox FCheck;
    private CheckBox BCheck;
    private CheckBox LCheck;
    private CheckBox RCheck;
    private TextView fTotal;
    private TextView bTotal;
    private TextView rTotal;
    private TextView lTotal;
    private TextView finalTotal;
    private ArrayList<InvoiceItem> IItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_view);
        dbHandler = new Database(this, null, null, 1);
        invoice = new Invoice();
        customer = new Customer();

        Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();
        if (extrasBundle != null){
            Log.d("bundle", "");
            //we have a bundle
            invoice.setInvoiceID(extrasBundle.getInt("SendInvoiceID"));
            Log.d("bundle", "" + invoice.getInvoiceID());
            invoice = dbHandler.getInvoice(invoice.getInvoiceID());
            //IRate.setText(Integer.toString(invoiceId));
        } else {

            dbHandler.addInvoice(invoice); // create new invoice and input in database to assign invoice ID
            invoice = dbHandler.getLastInvoice(); // collect invoice and assign invoice ID

        }



        Button _AddInvoiceItem = (Button) findViewById(R.id.ConstaddInvoiceItem);

        Log.d("Test Value of ID", invoice.getInvoiceID() + "");


        editTextHolder();

        GetDate();
        // Access the ListView
        invoiceItemListView = (ListView) findViewById(R.id.FInvoice_Item_listview);

        // shuts off scrolling within the main view during the list view
        invoiceItemListView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });


        // create a itemViewListAdapter for the ListView
        invoiceItemViewAdapter = new InvoiceItemViewListAdapter(this, getLayoutInflater());

        // Set ListView to use the ArrayAdapter
        invoiceItemListView.setAdapter(invoiceItemViewAdapter);

        // update the list view using the listview adapter
        invoiceItemViewAdapter.updateData(dbHandler.getInvoiceItemList(invoice.getInvoiceID()));
        invoiceItemListView.setOnItemClickListener(this);

        // method adds invoice items to the invoice
        // calls view that allows for items to be added and passes the invoiceID
        _AddInvoiceItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentBundle = new Intent(Invoice_view.this, AddInvoiceItemView.class);
                Bundle bundle = new Bundle();
                bundle.putInt("SendInvoiceID", invoice.getInvoiceID());
                intentBundle.putExtras(bundle);
                //startActivity(intentBundle);
                startActivityForResult(intentBundle, 1);
            }
        });


    }

    public void addInvoiceCustomer (View view) {

        try{

            if (customer.getCustomerID() == 0) {
                setCustomerObject();
                dbHandler.addCustomer(customer);
                customer = dbHandler.getLastCustomer();
                Log.d("tag cust", "initial customer got added");
            }else{
                setCustomerObject();
                boolean result = dbHandler.updateCustomer(customer);
                Log.d("tag cust", "result of updateCustomer = " + result);
            }
        }
        catch (Exception e){
        }

        Log.d("Value of last ID", customer.getCustomerID() + "");

        if(customer == null){
            Log.d("no customer", "");
        } else {

        Log.d("Test Value of ID", customer.getCustomerID()+"");
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_invoice, menu);
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

    public void AddInvoice(View v){

        addInvoiceCustomer(v);
        setInvoiceObject();
        dbHandler.updateInvoice(invoice);
        finish();
    }

    @Override
    public void onClick(View v) {
        int sumB, sumF, sumL, sumR, sumTotal;

        if(BCheck.isChecked()){
            sumB = getSideSum("B");
            bTotal.setText(Integer.toString(sumB));

        }else {
            bTotal.setText(Integer.toString(sumB=0));}

        if(FCheck.isChecked()){
            sumF = getSideSum("F");
            fTotal.setText(Integer.toString(sumF));

        }else {
            fTotal.setText(Integer.toString(sumF = 0));}

        if(LCheck.isChecked()){
            sumL = getSideSum("L");
            lTotal.setText(Integer.toString(sumL));

        }else {
            lTotal.setText(Integer.toString(sumL = 0));}

        if(RCheck.isChecked()){
            sumR = getSideSum("R");
            rTotal.setText(Integer.toString(sumR));

        }else {
            rTotal.setText(Integer.toString(sumR = 0));}
        sumTotal = sumB + sumF + sumL + sumR;

        finalTotal.setText(Integer.toString(sumTotal));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                invoiceItemViewAdapter.updateData(dbHandler.getInvoiceItemList(invoice.getInvoiceID()));
                // Do something with the contact here (bigger example below)
            }
        }
    }


    private void GetDate(){

        final Calendar cal = Calendar.getInstance();
        int dd = cal.get(Calendar.DAY_OF_MONTH);
        int mm = cal.get(Calendar.MONTH);
        int yy = cal.get(Calendar.YEAR);
// set current date into textview
        InvoiceDate.setText(new StringBuilder().append(mm + 1).append("").append("-").append(dd).append("-").append(yy));

    }

    private void setCustomerObject(){
        customer.setCustomerFirstName(CFirstName.getText().toString());
        customer.setCustomerLastName(CLastName.getText().toString());
        customer.setCustomerStreet(CStreet.getText().toString());
        customer.setCustomerZip(CZip.getText().toString());
        customer.setCustomerState(CState.getText().toString());
        customer.setCustomerPhone(CPhone.getText().toString());
        customer.setCustomerNotes(CNotes.getText().toString());
        customer.setCustomerCity(CCity.getText().toString());
    }

    private void setInvoiceObject(){
        invoice.setInvoiceDate(InvoiceDate.getText().toString());
        invoice.setCustomerID(customer.getCustomerID());
    }

    private void editTextHolder(){
        // attach text to invoice fields
        InvoiceDate = (EditText) findViewById(R.id.DateText);
        InvoiceID = (EditText) findViewById(R.id.InvoiceIDText);
        InvoiceID.setText(Integer.toString(invoice.getInvoiceID()));

        //attach edittext to customer fields
        CFirstName = (EditText) findViewById(R.id.FirstNameText);
        CLastName = (EditText) findViewById(R.id.LastNameText);
        CStreet = (EditText) findViewById(R.id.StreetText);
        CCity = (EditText) findViewById(R.id.CityText);
        CState = (EditText) findViewById(R.id.StateText);
        CZip = (EditText) findViewById(R.id.ZipText);
        CPhone = (EditText) findViewById(R.id.PhoneText);
        //CEmail = (EditText) findViewById(R.id.EmailText);
        CNotes = (EditText) findViewById(R.id.NotesText);
        //CustID = (EditText) findViewById(R.id.CustIDText);

        FCheck=(CheckBox)findViewById(R.id.CheckFront);
        BCheck=(CheckBox)findViewById(R.id.CheckBack);
        LCheck=(CheckBox)findViewById(R.id.CheckLeft);
        RCheck=(CheckBox)findViewById(R.id.CheckRight);
        fTotal=(TextView)findViewById(R.id.FTotalValue);
        bTotal=(TextView)findViewById(R.id.BTotalValue);
        rTotal=(TextView)findViewById(R.id.RTotalValue);
        lTotal=(TextView)findViewById(R.id.LTotalValue);
        finalTotal=(TextView)findViewById(R.id.FinalTotal);

        FCheck.setOnClickListener(this);
        BCheck.setOnClickListener(this);
        LCheck.setOnClickListener(this);
        RCheck.setOnClickListener(this);


    }

    private int getSideSum(String Side){
        int sum=0;
        IItemList = dbHandler.getInvoiceItemList(invoice.getInvoiceID());
        InvoiceItem temp;

        if(Side.equals("F")) {
            for (int i = 0; i < IItemList.size(); i++) {
                temp = IItemList.get(i);
                sum += temp.getInvoiceItemFQuantity() * temp.getInvoiceItemRate();
            }
        }
        if(Side.equals("B")) {
            for (int i = 0; i < IItemList.size(); i++) {
                temp = IItemList.get(i);
                sum += temp.getInvoiceItemBQuantity() * temp.getInvoiceItemRate();
            }
        }
        if(Side.equals("L")) {
            for (int i = 0; i < IItemList.size(); i++) {
                temp = IItemList.get(i);
                sum += temp.getInvoiceItemLQuantity() * temp.getInvoiceItemRate();
            }
        }
        if(Side.equals("R")) {
            for (int i = 0; i < IItemList.size(); i++) {
                temp = IItemList.get(i);
                sum += temp.getInvoiceItemRQuantity() * temp.getInvoiceItemRate();
            }
        }
        return sum;
    }

    public void refresh(View view){
        //dbHandler = new Database(this, null, null, 1);
        invoiceItemViewAdapter.updateData(dbHandler.getInvoiceItemList(invoice.getInvoiceID()));
    }


}
