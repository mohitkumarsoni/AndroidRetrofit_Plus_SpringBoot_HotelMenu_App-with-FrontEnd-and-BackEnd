package com.example.hotelpracticetodelete;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hotelpracticetodelete.databinding.ActivityAddEditBinding;
import com.example.hotelpracticetodelete.retrofit.Hotel_Api;
import com.example.hotelpracticetodelete.retrofit.Hotel_Menu_Model;
import com.example.hotelpracticetodelete.retrofit.Retrofit_Service;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditActivity extends AppCompatActivity {
    ActivityAddEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_edit);

        String type = getIntent().getStringExtra("type");

        assert type != null;
        if (type.equals("insert")){
            //add new item in menu
            addingNewMenuItem();
        }
        if (type.equals("update")){
            //update item in menu
            updatingMenuItem();
        }

    }

    private void updatingMenuItem() {
        setTitle("Update details");

        //getting data via intent, to place at their respective fields
        long id = getIntent().getLongExtra("menuId",0);
        String dish = getIntent().getStringExtra("dish") ;
        long dishPrice = getIntent().getLongExtra("dishPrice",0);

        //converting dishPrice(long) to (String) before placing it on its EditText
        String convDishPrice = ""+dishPrice;

        // placing data at their respective fields
        binding.dishEt.setText(dish);
        binding.dishPriceEt.setText(convDishPrice);

        // Setting up Retrofit to modify data
        Retrofit_Service retrofitService = new Retrofit_Service();
        Hotel_Api api = retrofitService.getRetrofit().create(Hotel_Api.class);

        // on FAB pressed, data will be collected from their fields and will be saved to database
        binding.saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dish = binding.dishEt.getText().toString();
                String dishPrice = binding.dishPriceEt.getText().toString();
                long convDishPrice = Long.parseLong(dishPrice);

                Hotel_Menu_Model menu = new Hotel_Menu_Model(dish,convDishPrice);
                menu.setId(id);

                api.updateItemInMenu(menu)
                        .enqueue(new Callback<Hotel_Menu_Model>() {
                            @Override
                            public void onResponse(Call<Hotel_Menu_Model> call, Response<Hotel_Menu_Model> response) {
                                Toast.makeText(AddEditActivity.this, "updated successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Hotel_Menu_Model> call, Throwable t) {
                                Toast.makeText(AddEditActivity.this, "updating failed", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(AddEditActivity.class.getName()).log(Level.SEVERE, "update Error", t);
                            }
                        });
                finish();
            }
        });
    }

    private void addingNewMenuItem() {
        setTitle("Add new item");

        Retrofit_Service retrofitService = new Retrofit_Service();
        Hotel_Api api = retrofitService.getRetrofit().create(Hotel_Api.class);

        // on FAB pressed, data will be collected from their fields and will be saved to database
        binding.saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dish = binding.dishEt.getText().toString();
                String dishPrice = binding.dishPriceEt.getText().toString();
                long priceConv = Long.parseLong(dishPrice);

                Hotel_Menu_Model menu = new Hotel_Menu_Model(dish,priceConv);

                api.addItemInMenu(menu)
                        .enqueue(new Callback<Hotel_Menu_Model>() {
                            @Override
                            public void onResponse(Call<Hotel_Menu_Model> call, Response<Hotel_Menu_Model> response) {
                                Toast.makeText(AddEditActivity.this, "item added in menu", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Hotel_Menu_Model> call, Throwable t) {
                                Toast.makeText(AddEditActivity.this, "something went wrong...", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(AddEditActivity.class.getName()).log(Level.SEVERE,"menu error",t);
                            }
                        });

                finish();
            }
        });
    }


}