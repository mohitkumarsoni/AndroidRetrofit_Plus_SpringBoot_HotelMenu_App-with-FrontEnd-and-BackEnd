package com.example.hotelpracticetodelete.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Hotel_Api {

    @GET("hotel/menu")
    Call<List<Hotel_Menu_Model>> getAllMenu();

    @POST("hotel/add")
    Call<Hotel_Menu_Model> addItemInMenu(@Body Hotel_Menu_Model menu);

    @PUT("hotel/update")
    Call<Hotel_Menu_Model> updateItemInMenu(@Body Hotel_Menu_Model menu);

    @DELETE("hotel/delete/{menuId}")
    Call<Void> deleteItemFromMenu(@Path("menuId") String menuId);

}
