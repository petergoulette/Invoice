package com.example.capstone.invoice;

/**
 * Created by Pierro on 10/29/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";

    // db table constants for Item
    private static final String TABLE_ITEM = "item";
    private static final String ITEM_ID = "itemID";
    private static final String ITEM_NAME = "itemName";
    private static final String ITEM_RATE = "itemRate";

    // db table constants for Customer
    private static final String TABLE_CUSTOMER = "Customer";
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

        String CREATE_ITEMS_TABLE = "CREATE TABLE " +
                TABLE_ITEM + "("
                + ITEM_ID + " INTEGER PRIMARY KEY," + ITEM_NAME
                + " TEXT," + ITEM_RATE + " INTEGER" + ")";

        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " +
                TABLE_CUSTOMER + "("
                + CUSTOMER_ID + " INTEGER PRIMARY KEY," + CUSTOMER_FIRST_NAME
                + " TEXT," + CUSTOMER_LAST_NAME + " TEXT," + CUSTOMER_STREET + " TEXT," + CUSTOMER_CITY + " TEXT," + CUSTOMER_ZIP + " INTEGER"
                + CUSTOMER_STATE + " TEXT," + CUSTOMER_PHONE1 + " TEXT,"+ CUSTOMER_NOTES + " TEXT," + ")";

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
        values.put(ITEM_RATE, item.getItemQuantity());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ITEM, null, values);
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

    /*public Product findProduct(String productname) {
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " =  \"" + productname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Product product = new Product();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            product.setID(Integer.parseInt(cursor.getString(0)));
            product.setProductName(cursor.getString(1));
            product.setQuantity(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            product = null;
        }
        db.close();
        return product;
    }*/

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
