package com.example.capstone.invoice;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "productDB.db";

    // db table constants for Item
    private static final String TABLE_ITEM = "itemTABLE";
    private static final String ITEM_ID = "_itemID";
    private static final String ITEM_NAME = "_itemName";
    private static final String ITEM_RATE = "_itemRate";

    // db table constants for Invoice
    private static final String TABLE_INVOICE = "invoiceTABLE";
    private static final String INVOICE_ID = "_itemID";
    private static final String INVOICE_DATE = "Date";
    private static final String INVOICE_NOTES = "Notes";
    private static final String INVOICE_CUSTOMER_ID = "CustID";

    // db table constants for Customer
    private static final String TABLE_CUSTOMER = "CustomerTABLE";
    private static final String CUSTOMER_ID = "CustID";
    private static final String CUSTOMER_FIRST_NAME = "CustFName";
    private static final String CUSTOMER_LAST_NAME = "CustLName";
    private static final String CUSTOMER_PHONE1 = "CustPhone";
    private static final String CUSTOMER_STREET = "CustStreet";
    private static final String CUSTOMER_ZIP = "CustZip";
    private static final String CUSTOMER_STATE = "CustState";
    private static final String CUSTOMER_CITY = "CustCity";
    private static final String CUSTOMER_NOTES = "CustNotes";



    private static final String TABLE_PRODUCTS = "products";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_QUANTITY = "quantity";

    // database constructor: when you call use current context
    // example Database db = new Database(this, null, null, 1);
    public Database(Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME
                + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")";

        String CREATE_INVOICE_TABLE = "CREATE TABLE " +
                TABLE_INVOICE + "("
                + INVOICE_ID + " INTEGER PRIMARY KEY," + INVOICE_CUSTOMER_ID
                + " INTEGER," + INVOICE_DATE + " TEXT," + INVOICE_NOTES + " TEXT" + ")";

        String CREATE_ITEMS_TABLE = "CREATE TABLE " +
                TABLE_ITEM + "("
                + ITEM_ID + " INTEGER PRIMARY KEY," + ITEM_NAME
                + " TEXT," + ITEM_RATE + " INTEGER" + ")";

        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " +
                TABLE_CUSTOMER + "("
                + CUSTOMER_ID + " INTEGER PRIMARY KEY," + CUSTOMER_FIRST_NAME
                + " TEXT," + CUSTOMER_LAST_NAME + " TEXT," + CUSTOMER_STREET + " TEXT," + CUSTOMER_CITY + " TEXT," + CUSTOMER_ZIP + " TEXT,"
                + CUSTOMER_STATE + " TEXT," + CUSTOMER_PHONE1 + " TEXT,"+ CUSTOMER_NOTES + " TEXT" + ")";

        db.execSQL(CREATE_INVOICE_TABLE);
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);


    }

    // look this one up
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addItem(Item item) {

        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, item.getItemName());
        values.put(ITEM_RATE, item.getItemRate());
        Log.d("adding: ", "inserting...");
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ITEM, null, values);
        db.close();
    }

    public void addInvoice(Invoice invoice) {

        ContentValues values = new ContentValues();
        values.put(INVOICE_CUSTOMER_ID, invoice.getCustomerID());
        values.put(INVOICE_DATE, invoice.getInvoiceDate());
        values.put(INVOICE_NOTES, invoice.getInvoiceNotes());
        Log.d("adding: ", "inserting...");
        SQLiteDatabase db = this.getWritableDatabase();
        boolean test;
        db.insert(TABLE_INVOICE, null, values);
        db.close();
    }

    public void addCustomer(Customer customer) {

        ContentValues values = new ContentValues();
        values.put(CUSTOMER_FIRST_NAME, customer.getCustomerFirstName());
        values.put(CUSTOMER_LAST_NAME, customer.getCustomerLastName());
        values.put(CUSTOMER_PHONE1, customer.getCustomerPhone());
        values.put(CUSTOMER_STREET, customer.getCustomerStreet());
        values.put(CUSTOMER_CITY, customer.getCustomerCity());
        values.put(CUSTOMER_ZIP, customer.getCustomerZip());
        values.put(CUSTOMER_STATE, customer.getCustomerState());
        values.put(CUSTOMER_NOTES, customer.getCustomerNotes());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_CUSTOMER, null, values);
        db.close();
    }

    // use this method to find invoice by customer id
    public Invoice findInvoice(int custid) {
        String query = "Select * FROM " + TABLE_INVOICE + " WHERE " + INVOICE_CUSTOMER_ID + " =  \"" + custid + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Invoice invoice = new Invoice();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            invoice.setInvoiceID(Integer.parseInt(cursor.getString(0)));
            invoice.setCustomerID(Integer.parseInt(cursor.getString(1)));
            invoice.setInvoiceDate(cursor.getString(2));
            invoice.setInvoiceNotes(cursor.getString(3));
            cursor.close();
        } else {
            invoice = null;
        }
        db.close();
        return invoice;
    }

    // use this method to find invoice by invoice id
    public Invoice getInvoice(int invoiceid) {
        String query = "Select * FROM " + TABLE_INVOICE + " WHERE " + INVOICE_ID + " =  \"" + invoiceid + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Invoice invoice = new Invoice();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            invoice.setInvoiceID(Integer.parseInt(cursor.getString(0)));
            invoice.setCustomerID(Integer.parseInt(cursor.getString(1)));
            invoice.setInvoiceDate(cursor.getString(2));
            invoice.setInvoiceNotes(cursor.getString(3));
            cursor.close();
        } else {
            invoice = null;
        }
        db.close();
        return invoice;
    }

    public Invoice getLastInvoice() {
        String query = "Select * FROM " + TABLE_INVOICE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Invoice invoice = new Invoice();

        if (cursor.moveToFirst()) {
            cursor.moveToLast();
            invoice.setInvoiceID(Integer.parseInt(cursor.getString(0)));
            invoice.setCustomerID(Integer.parseInt(cursor.getString(1)));
            invoice.setInvoiceDate(cursor.getString(2));
            invoice.setInvoiceNotes(cursor.getString(3));
            cursor.close();
        } else {
            invoice = null;
        }
        db.close();
        return invoice;
    }

    // use this method to find invoice by invoice id
    public ArrayList<Invoice> getInvoiceList() {
        String query = "Select * FROM " + TABLE_INVOICE;
        ArrayList<Invoice> invoiceList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Invoice invoice = new Invoice();
                invoice.setInvoiceID(Integer.parseInt(cursor.getString(0)));
                invoice.setCustomerID(Integer.parseInt(cursor.getString(1)));
                invoice.setInvoiceDate(cursor.getString(2));
                invoice.setInvoiceNotes(cursor.getString(3));
                invoiceList.add(invoice);
                cursor.moveToNext();
            }
            cursor.close();
        } else {
            //itemList = null; //creates a null pointer error
        }
        db.close();
        return invoiceList;
    }

    public Customer getLastCustomer(){
        String query = "Select * FROM " + TABLE_CUSTOMER;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Customer cust = new Customer();

        if (cursor.moveToFirst()) {
            cursor.moveToLast();
            cust.setCustomerID(Integer.parseInt(cursor.getString(0)));
            cust.setCustomerFirstName(cursor.getString(1));
            cust.setCustomerLastName(cursor.getString(2));
            cust.setCustomerPhone(cursor.getString(3));
            cust.setCustomerStreet(cursor.getString(4));
            cust.setCustomerCity(cursor.getString(5));
            cust.setCustomerZip(cursor.getString(6));
            cust.setCustomerState(cursor.getString(7));
            cust.setCustomerNotes(cursor.getString(8));
            cursor.close();
        } else {
            cust = null;
        }
        db.close();

        return cust;
    }

    public Customer findCustomer(String customerName){
        String query = "Select * FROM " + TABLE_CUSTOMER + " WHERE " + CUSTOMER_LAST_NAME + " =  \"" + customerName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Customer cust = new Customer();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            cust.setCustomerID(Integer.parseInt(cursor.getString(0)));
            cust.setCustomerFirstName(cursor.getString(1));
            cust.setCustomerLastName(cursor.getString(2));
            cust.setCustomerPhone(cursor.getString(3));
            cust.setCustomerStreet(cursor.getString(4));
            cust.setCustomerCity(cursor.getString(5));
            cust.setCustomerZip(cursor.getString(6));
            cust.setCustomerState(cursor.getString(7));
            cust.setCustomerNotes(cursor.getString(8));
            cursor.close();
        } else {
            cust = null;
        }
        db.close();

        return cust;
    }

    public Item findItem(String itemname) {
        String query = "Select * FROM " + TABLE_ITEM + " WHERE " + ITEM_NAME + " =  \"" + itemname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Item item = new Item();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            item.setItemId(Integer.parseInt(cursor.getString(0)));
            item.setItemName(cursor.getString(1));
            item.setItemRate(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            item = null;
        }
        db.close();
        return item;
    }

    // Returns an ArrayList of items, if no items then returns null;
    public ArrayList<Item> getItemList() {
        ArrayList<Item> itemList = new ArrayList<>();
        String query = "Select * FROM " + TABLE_ITEM;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Item item = new Item();
                item.setItemId(Integer.parseInt(cursor.getString(0)));
                item.setItemName(cursor.getString(1));
                item.setItemRate(Integer.parseInt(cursor.getString(2)));
                itemList.add(item);
                cursor.moveToNext();
            }
            cursor.close();
        } else {
            //itemList = null; //creates a null pointer error
        }
        db.close();
        return itemList;
    }

    public boolean deleteItem(String itemname) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_ITEM + " WHERE " + ITEM_NAME + " =  \"" + itemname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Item item = new Item();

        if (cursor.moveToFirst()) {
            item.setItemId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_ITEM, ITEM_ID + " = ?",
                    new String[] { String.valueOf(item.getItemId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

}
