package ru.kovalskiy.data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
//import ru.kovalskiy.domain.repository.models.Street;
//import retrofit2.converter.gson.GsonConverterFactory;


//import java.util.List;
//
//public class Test {
//    public static void main(String[] args) {
//        APi api = RetrofitClient.getClient();
//
//        System.out.println(api.getStreet(1));
//    }
//}
//
//interface APi {
//    @GET("/api/streets/{id}")
//    Call<Street> getStreet(@Path("id") Integer id);
//}
//class RetrofitClient {
//
//    private static final String BASE_URL = "http://your-base-url.com"; // Замените на свой базовый URL
//
//    private static Retrofit retrofit = null;
//
//    public static APi getClient() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit.create(APi.class);
//    }
//}