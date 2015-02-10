package com.tamrefrank.www.cardviewapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Main extends Activity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ProductsData> products;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Set the Onclick Listener

        myOnClickListener = new MyOnclickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        products = new ArrayList<ProductsData>();
        //Loop around the array of products
        for (int i=0; i < Data.nameArray.length; i++){

           products.add(new ProductsData(
                   Data.nameArray[i],
                   Data.descriptionArray[i],
                   Data.priceArray[i],
                   Data.drawableArray[i],
                   Data.id_[i]

            )) ;

        }
          removedItems = new ArrayList<Integer>();
        adapter = new Adapter(products);
        recyclerView.setAdapter(adapter);

    }

    private static class MyOnclickListener implements View.OnClickListener{

        private final Context context;
        private MyOnclickListener(Context context){
            this.context = context;

        }


        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewProductName = (TextView) viewHolder.itemView.findViewById(R.id.name);
            String selectedName = (String) textViewProductName.getText();
            int selectedItemId = -1;

            for (int i = 0; i < Data.nameArray.length; i++) {
                if (selectedName.equals(Data.nameArray[i])) {
                    selectedItemId = Data.id_[i];
                }
            }
            removedItems.add(selectedItemId);
            products.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_add_item) {
//            check if any items to add
            if (removedItems.size() != 0) {
                addRemovedItemToList();
            } else {
                Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    private void addRemovedItemToList() {
        int addItemAtListPosition = 3;
        products.add(addItemAtListPosition, new ProductsData(
                Data.nameArray[removedItems.get(0)],
                Data.descriptionArray[removedItems.get(0)],
                Data.priceArray[removedItems.get(0)],
                Data.drawableArray[removedItems.get(0)],
                Data.id_[removedItems.get(0)]
        ));
        adapter.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }
}

