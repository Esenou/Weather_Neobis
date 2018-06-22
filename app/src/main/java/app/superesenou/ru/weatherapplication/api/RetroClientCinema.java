package app.superesenou.ru.weatherapplication.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClientCinema {

   // base url cinema
    private static final String BASE_URL="https://query.yahooapis.com";

    //get Retrofit instance

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServerCinema getApiService(){
        return getRetrofitInstance().create(ApiServerCinema.class);
    }
}
