package app.superesenou.ru.weatherapplication.api;



import app.superesenou.ru.weatherapplication.model.Example;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServerCinema {

    String name="Bishkek";
    @GET("/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+name+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Call<Example> getDate();


}
