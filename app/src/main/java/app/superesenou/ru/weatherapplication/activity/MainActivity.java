package app.superesenou.ru.weatherapplication.activity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import app.superesenou.ru.weatherapplication.R;
import app.superesenou.ru.weatherapplication.adapter.AdapterWeather;
import app.superesenou.ru.weatherapplication.api.ApiServerCinema;
import app.superesenou.ru.weatherapplication.api.RetroClientCinema;
import app.superesenou.ru.weatherapplication.model.Example;
import app.superesenou.ru.weatherapplication.model.Forecast;
import app.superesenou.ru.weatherapplication.model.Image;
import app.superesenou.ru.weatherapplication.model.Location;
import app.superesenou.ru.weatherapplication.model.Query;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Location location;
    ImageView imageView;
    TextView city,country,time;
    TextView temp1,temp2,text_Main;
    private List<Forecast>forecasts;
    AdapterWeather adapter;
    RecyclerView recyclerView;
    List<Forecast> fore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city=(TextView)findViewById(R.id.city);
        country=(TextView)findViewById(R.id.country);
        time=(TextView)findViewById(R.id.time);
        temp1=(TextView)findViewById(R.id.Temp1);
        temp2=(TextView)findViewById(R.id.Temp2);
        text_Main=(TextView)findViewById(R.id.text_main);
        imageView=(ImageView)findViewById(R.id.imageView);


        recyclerView=(RecyclerView)findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        forecasts=new ArrayList<>();

        ApiServerCinema api= RetroClientCinema.getApiService();

        // Calling JSON
        Call<Example> call= api.getDate();


       call.enqueue(new Callback<Example>() {
           @Override
           public void onResponse(Call<Example> call, Response<Example> response) {
               // City , country , datatime
            location=response.body().getQuery().getResults().getChannel().getLocation();
            time.setText(response.body().getQuery().getResults().getChannel().getLastBuildDate());
            city.setText(location.getCity());
            country.setText(location.getCountry());



             // Forecast
               forecasts=response.body().getQuery().getResults().getChannel().getItem().getForecast();
               adapter=new AdapterWeather(forecasts);
               recyclerView.setAdapter(adapter);
               fore=response.body().getQuery().getResults().getChannel().getItem().getForecast();
               Forecast Day=forecasts.get(0);
               temp1.setText(Day.getHigh());
               temp2.setText(Day.getLow());
               String name=Day.getText();
               text_Main.setText(name);

               int weatherIconImageResource = getResources().getIdentifier("icon_" + response.body().getQuery().getResults().getChannel().getItem().getCondition().getCode(), "drawable", getPackageName());
               imageView.setImageResource(weatherIconImageResource);


           }

           @Override
           public void onFailure(Call<Example> call, Throwable t) {

           }
       });





    }
}
