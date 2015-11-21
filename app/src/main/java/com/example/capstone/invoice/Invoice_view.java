package com.example.capstone.invoice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

public class Invoice_view extends AppCompatActivity {
    Customer customer;
    Invoice invoice;
    EditText CFirstName;
    EditText CLastName;
    EditText CStreet;
    EditText CCity;
    EditText CState;
    EditText CZip;
    EditText CPhone;
    EditText CustID;
    EditText InvoiceDate;
    EditText InvoiceID;
    EditText CNotes;
    Database dbHandler;

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

        Log.d("Test Value of ID", invoice.getInvoiceID()+"");

        // attach text to invoice fields
        InvoiceDate = (EditText) findViewById(R.id.DateText);
        InvoiceID = (EditText) findViewById(R.id.InvoiceIDText);
        InvoiceID.setText(Integer.toString(invoice.getInvoiceID()));


        GetDate();

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

    public void addInvoiceCustomer (View view) {

        try{
            Log.d("Test Value of ID", customer.getCustomerID()+"");
            Log.d("Test Value of ID", customer.getCustomerFirstName()+"");
            if (customer.getCustomerID() == 0) {
                customer.setCustomerFirstName(CFirstName.getText().toString());
                customer.setCustomerLastName(CLastName.getText().toString());
                customer.setCustomerStreet(CStreet.getText().toString());
                customer.setCustomerZip(CZip.getText().toString());
                customer.setCustomerState(CState.getText().toString());
                customer.setCustomerPhone(CPhone.getText().toString());
                customer.setCustomerNotes(CNotes.getText().toString());
                customer.setCustomerCity(CCity.getText().toString());
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

    private void GetDate(){

        final Calendar cal = Calendar.getInstance();
        int dd = cal.get(Calendar.DAY_OF_MONTH);
        int mm = cal.get(Calendar.MONTH);
        int yy = cal.get(Calendar.YEAR);
// set current date into textview
        InvoiceDate.setText(new StringBuilder().append(mm + 1).append("").append("-").append(dd).append("-").append(yy));

    }
}
