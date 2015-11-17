package com.example.capstone.invoice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class Items_view extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText IName;
    EditText IRate;
    ListView itemListView;
    ItemViewListAdapter itemViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_view);
        Database dbHandler = new Database(this, null, null, 1);

        IName = (EditText) findViewById(R.id.itemName);
        IRate = (EditText) findViewById(R.id.itemRate);

        // Access the ListView
        itemListView = (ListView) findViewById(R.id.main_listview);
        Log.d("itemListView:", "created");
        // create a itemViewListAdapter for the ListView
        itemViewAdapter = new ItemViewListAdapter(this, getLayoutInflater());
        Log.d("itemViewAdapter:", "created");
        // Set ListView to use the ArrayAdapter
        itemListView.setAdapter(itemViewAdapter);
        Log.d("itemsetAdapter:", "created");
        itemViewAdapter.updateData(dbHandler.getItemList());
    }

    public void newItem (View view) {

            Database dbHandler = new Database(this, null, null, 1);
        Item item;
        int rate;
        try {
                rate =
                        (int) (Double.parseDouble(IRate.getText().toString()) * 100);
                item =
                        new Item(IName.getText().toString(), rate);
            if (item.getItemName().equals("")){Log.d("Empty Value", "no string");}
            else {
            dbHandler.addItem(item);
            //ArrayList<Item> test = new ArrayList<Item>();
            //test = dbHandler.getItemList();
            IRate.setText("");
            IName.setText("");
            itemViewAdapter.updateData(dbHandler.getItemList());
            Log.d("item updated", "ok");}
            }catch (Exception e) {
            Log.d("Empty Value", e.toString());
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items_view, menu);
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
}
