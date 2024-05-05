package ru.kovalskiy.data.api;

import android.util.Log;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.kovalskiy.data.api.retrofit.RetrofitClient;
import ru.kovalskiy.data.api.retrofit.StreetApi;
import ru.kovalskiy.data.models.StreetData;
import ru.kovalskiy.data.models.StreetResponse;
import ru.kovalskiy.data.storage.sqlite.StreetLocalDataSource;

@AllArgsConstructor
public class StreetApiDataSource implements StreetRemoteDataSource {
    StreetApi streetApi;
    StreetLocalDataSource sqLiteStreetService;

    public StreetApiDataSource(StreetLocalDataSource sqLiteStreetService) {
        streetApi = RetrofitClient.getClient();
        this.sqLiteStreetService = sqLiteStreetService;
    }

    @Override
    public void fetchStreetById(int id, StreetFetchCallback callback) {
        streetApi.getStreetById(id).enqueue(new Callback<StreetResponse>() {
            @Override
            public void onResponse(@NotNull Call<StreetResponse> call, @NotNull Response<StreetResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    sqLiteStreetService.saveStreet(new StreetData(response.body().getData().getId(),
                            response.body().getData().getName(),
                            response.body().getData().getTotalSpace(),
                            response.body().getData().getAvailableSpace(),
                            response.body().getData().getOccupiedSpace()));
                    Log.d("apidatasource", response.body() + " response code: " +response.code());
                } else Log.d("SQLiteStreetServiceImpl", "response is not successful");

            }

            @Override
            public void onFailure(@NotNull Call<StreetResponse> call, @NotNull Throwable throwable) {
                Log.d("StreetApiService", throwable.getMessage());
            }
        });
    }
}
