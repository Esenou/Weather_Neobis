package app.superesenou.ru.weatherapplication.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import app.superesenou.ru.weatherapplication.R;
import app.superesenou.ru.weatherapplication.adapter.AdapterWeather;
import app.superesenou.ru.weatherapplication.api.ApiServerCinema;
import app.superesenou.ru.weatherapplication.api.RetroClientCinema;
import app.superesenou.ru.weatherapplication.model.Example;
import app.superesenou.ru.weatherapplication.model.Forecast;
import app.superesenou.ru.weatherapplication.model.Location;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {




    public String CITY="Bishkek";
    Location location;
    ImageView imageView;
    TextView city,country,time;
    TextView temp1,temp2,text_Main;
    private List<Forecast>forecasts;
    AdapterWeather adapter;
    RecyclerView recyclerView;
    List<Forecast> fore;
    TextView Temperature;
    EditText editText;
    Button button;
    String a,TEXT;
    private View     parentView;
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
        Temperature=(TextView) findViewById(R.id.Temperature);
        editText=(EditText)findViewById(R.id.editText);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclerView);
        button=(Button)findViewById(R.id.button);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        forecasts=new ArrayList<>();

          onClick();




    }

    private void onClick() {

            TEXT= String.valueOf(editText.getText());
            if(TEXT.isEmpty()){
                CITY="Bishkek";
            }
            else{
                CITY=TEXT ;
                Toast.makeText(MainActivity.this,CITY,Toast.LENGTH_LONG).show();
            }
            if (InternetConnection.checkConnection(getApplicationContext())) {
                final ProgressDialog dialog;
                /**
                 * Progress Dialog for User Interaction
                 */


                ApiServerCinema api = RetroClientCinema.getApiService();

                // Calling JSON
                Call<Example> call = api.getDate("/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + CITY + "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");


                call.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        // City , country , datatime
                        if(response.body().getQuery()==null){ Toast.makeText(MainActivity.this,CITY+"не найдено",Toast.LENGTH_LONG).show();}
                        else {
                            location = response.body().getQuery().getResults().getChannel().getLocation();
                            time.setText(response.body().getQuery().getResults().getChannel().getLastBuildDate());
                            city.setText(location.getCity());
                            country.setText(location.getCountry());


                            // Forecast
                            forecasts = response.body().getQuery().getResults().getChannel().getItem().getForecast();
                            adapter = new AdapterWeather(forecasts);
                            recyclerView.setAdapter(adapter);
                            fore = response.body().getQuery().getResults().getChannel().getItem().getForecast();
                            Forecast Day = forecasts.get(0);
                            a = Day.getHigh();
                            temp1.setText(a+"\u00b0");


                            temp2.setText(Day.getLow()+"\u00b0");
                            String name = Day.getText();
                            text_Main.setText(name);
                            Temperature.setText(Day.getCode()+"\u00b0");
                            int weatherIconImageResource = getResources().getIdentifier("icon_" + response.body().getQuery().getResults().getChannel().getItem().getCondition().getCode(), "drawable", getPackageName());
                            imageView.setImageResource(weatherIconImageResource);
                        }

                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }

                });

            }
            else {

                Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
            }


    }


    public void onClick(View view) {
        if(view.getId()==R.id.button){onClick();}
    }
}
