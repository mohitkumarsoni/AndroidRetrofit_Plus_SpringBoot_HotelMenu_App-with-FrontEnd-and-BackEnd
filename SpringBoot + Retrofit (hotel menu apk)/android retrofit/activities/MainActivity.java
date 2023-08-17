package com.example.hotelpracticetodelete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hotelpracticetodelete.databinding.ActivityMainBinding;
import com.example.hotelpracticetodelete.retrofit.Hotel_Api;
import com.example.hotelpracticetodelete.retrofit.Hotel_Menu_Model;
import com.example.hotelpracticetodelete.retrofit.Retrofit_Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;
    HotelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //method to load "hotel menu" item in recycler view
        loadMenuListInRv();

        //button to perform action when clicked (loads "Add Edit Activity")
        binding.addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                intent.putExtra("type","insert");
                startActivity(intent);
            }
        });

        //Action performed on swiping RecyclerView item (Left || Right)
        actionOnSwipe();
    }

    @Override
    protected void onPostResume() {
        loadMenuListInRv();
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void actionOnSwipe() {

        // "ItemTouchHelper" used to perform action
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction==ItemTouchHelper.LEFT){

                    //delete menu item on left swipe
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("WARNING !!");
                    builder.setMessage("do you want to delete item from menu ? ");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Retrofit_Service retrofitService = new Retrofit_Service();
                            Hotel_Api api = retrofitService.getRetrofit().create(Hotel_Api.class);

                            long id = adapter.getPosition(viewHolder.getAdapterPosition()).getId();
                            String longToStringIdForApiInput = ""+id;

                            api.deleteItemFromMenu(longToStringIdForApiInput)
                                    .enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            Toast.makeText(MainActivity.this, "deleted successfully", Toast.LENGTH_SHORT).show();
                                            onResume();
                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {
                                            Toast.makeText(MainActivity.this, "cant delete", Toast.LENGTH_SHORT).show();
                                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"error in deletion",t);
                                            onPostResume();
                                        }
                                    });
                        }
                    });
                    builder.setNegativeButton("No Sorry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "ja Maaf kia....", Toast.LENGTH_SHORT).show();
                            onPostResume();
                        }
                    });
                    builder.create().show();


                } else if (direction== ItemTouchHelper.RIGHT) {

                    //update menu item on right swipe
                    Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                    intent.putExtra("type","update");

                    // Transferring particular data to AddEditActivity which needs to be updated
                    intent.putExtra("dish", adapter.getPosition(viewHolder.getAdapterPosition()).getDish());
                    intent.putExtra("dishPrice", adapter.getPosition(viewHolder.getAdapterPosition()).getDish_price());
                    intent.putExtra("menuId", adapter.getPosition(viewHolder.getAdapterPosition()).getId());
                    startActivity(intent);
                }
            }
        }).attachToRecyclerView(binding.rv);
    }

    private void loadMenuListInRv() {
        Retrofit_Service retrofitService = new Retrofit_Service();
        Hotel_Api api = retrofitService.getRetrofit().create(Hotel_Api.class);

        api.getAllMenu()
                .enqueue(new Callback<List<Hotel_Menu_Model>>() {
                    @Override
                    public void onResponse(Call<List<Hotel_Menu_Model>> call, Response<List<Hotel_Menu_Model>> response) {
                        loadWithAdapter(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Hotel_Menu_Model>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"error loading menu",t);
                    }
                });

    }

    private void loadWithAdapter(List<Hotel_Menu_Model> menu) {
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setHasFixedSize(true);
        adapter = new HotelAdapter(menu);
        binding.rv.setAdapter(adapter);
    }


}