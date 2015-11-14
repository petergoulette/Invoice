package com.example.capstone.invoice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Items_view extends AppCompatActivity {

    EditText IName;
    EditText IRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_view);

        IName = (EditText) findViewById(R.id.itemName);
        IRate = (EditText) findViewById(R.id.itemRate);
    }

    public void newItem (View view) {

       Database dbHandler = new Database(this, null, null, 1);
        //IName.setText("ok");
        //IRate.setText("10");
        int quantity =
                Integer.parseInt(IRate.getText().toString());
        Item item =
                new Item(IName.getText().toString(), quantity);

        IRate.setText(Integer.toString(item.getItemRate()));

        dbHandler.addItem(item);
        IName.setText(item.getItemName() + " added");
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
}
