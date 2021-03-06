package com.felipe.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.felipe.app.models.pojos.Fruit;
import com.felipe.app.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by felipe on 7/11/17.
 */

public class FruitDetailActivity extends BaseActivity implements ActivityAction {

    private Fruit fruit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(Utils.MUXI_TAG, "Creating Fruit Detail Activity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        fruit = getFruit();
        onConfigure();
    }

    @Override
    public void onConfigure(){
        Log.i(Utils.MUXI_TAG, "Configure activity");

        configureToolbar();
        configWidgets(R.id.txt_info_name);
        configWidgets(R.id.txt_info_price);
        configWidgets(R.id.txt_info_price_real);
        configWidgets(R.id.img_fruit_image);
    }

    @Override
    public void configureToolbar(){
        Log.i(Utils.MUXI_TAG, "Configure toolbar");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void configWidgets(final int vid) {

        Log.i(Utils.MUXI_TAG, "Configure widgets");

        switch (vid){
            case R.id.txt_info_name:
                TextView txtName = (TextView) findViewById(vid);
                txtName.setText(fruit.getName());
                break;

            case R.id.txt_info_price:
                TextView txtPrice = (TextView) findViewById(vid);
                txtPrice.setText(String.format(Locale.UK, "U$ " + "%,.2f",  fruit.getPrice()));
                break;

            case R.id.txt_info_price_real:
                TextView txtPriceReal = (TextView) findViewById(vid);
                txtPriceReal.setText(String.format(Locale.UK, "R$ " + "%,.2f",  fruit.getPriceReal()));
                break;

            case R.id.img_fruit_image:
                ImageView nImg = (ImageView) findViewById(vid);
                Picasso.with(this).load(fruit.getImage()).into(nImg);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public Fruit getFruit(){
        Log.i(Utils.MUXI_TAG, "Retrieve fruit that was clicked on list.");

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        Fruit fruit = new Fruit();
        fruit.setName(extra.getString("name"));
        fruit.setImage(extra.getString("image"));
        fruit.setPrice(extra.getDouble("price"));
        fruit.setPriceReal(extra.getDouble("priceReal"));

        return fruit;
    }
}
