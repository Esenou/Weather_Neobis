package app.superesenou.ru.weatherapplication.api;



import app.superesenou.ru.weatherapplication.activity.MainActivity;
import app.superesenou.ru.weatherapplication.model.Example;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface ApiServerCinema  {




    @GET
    Call<Example> getDate(@Url String name );



}
