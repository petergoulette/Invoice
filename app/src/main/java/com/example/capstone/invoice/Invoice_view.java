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
import android.widget.EditText;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_view);
        dbHandler = new Database(this, null, null, 1);
        invoice = new Invoice();
        customer = new Customer();
        dbHandler.addInvoice(invoice); // create new invoice and input in database to assign invoice ID
        ArrayList<Invoice> ai = new ArrayList<>();
        ai = dbHandler.getInvoiceList();
        invoice = dbHandler.getLastInvoice(); // collect invoice and assign invoice ID

        Button _AddInvoiceItem = (Button) findViewById(R.id.ConstaddInvoiceItem);

        Log.d("Test Value of ID", invoice.getInvoiceID() + "");


        editTextHolder();

        GetDate();
        // Access the ListView
        invoiceItemListView = (ListView) findViewById(R.id.FInvoice_Item_listview);

        // test for list view scrolling fix
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
            Log.d("Test Value of ID", customer.getCustomerID()+"");
            Log.d("Test Value of ID", customer.getCustomerFirstName()+"");
            if (customer.getCustomerID() == 0) {
                setCustomerObject();
            }
        }
        catch (Exception e){
        }

        dbHandler.addCustomer(customer);
        customer = dbHandler.getLastCustomer();
        Log.d("Value of last ID", customer.getCustomerID() + "");
        customer = dbHandler.findCustomer("Peter");
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

    @Override
    public void onClick(View v) {

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
    }

    public void refresh(View view){
        //dbHandler = new Database(this, null, null, 1);
        invoiceItemViewAdapter.updateData(dbHandler.getInvoiceItemList(invoice.getInvoiceID()));
    }


}
