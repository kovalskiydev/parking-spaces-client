package ru.kovalskiy.data.api.retrofit;

import retrofit2.Call;
import retrofit2.http.*;
import ru.kovalskiy.data.models.StreetResponse;

import java.util.List;

public interface StreetApi {
    @POST("/api/streets/save")
    Call<Void> saveStreet(@Body StreetResponse street);

    @GET("/api/streets/list")
    Call<List<StreetResponse>> getStreets();

    @PUT("/api/streets/update/{id}")
    Call<Void> updateStreet(@Path("id") int id, @Body StreetResponse street);

    @DELETE("/api/streets/delete/{id}")
    Call<Void> deleteStreet(@Path("id") int id);

    @GET("/api/streets/{id}")
    Call<StreetResponse> getStreetById(@Path("id") int id);
}
