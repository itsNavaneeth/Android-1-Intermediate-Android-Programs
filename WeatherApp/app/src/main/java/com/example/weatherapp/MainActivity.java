package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText textcity;
    TextView texttemp, texthumidity, textmain, textdesc;
    String apikey = "406b154331868aa69ddc3dd64454c8c6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textcity  = findViewById(R.id.editTextCity);
        texttemp = findViewById(R.id.textTemp);
        texthumidity = findViewById(R.id.textHumidity);
        textmain = findViewById(R.id.textMain);
        textdesc = findViewById(R.id.textDesc);
    }

    public void getVolleyWeather(View v){
        String apikey1 = "406b154331868aa69ddc3dd64454c8c6";
        String city = textcity.getText().toString();
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apikey1;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject object = response.getJSONObject("main");
                    JSONArray arr1 = response.getJSONArray("weather");
                    JSONObject object2 = arr1.getJSONObject(0);

                    String temperature = object.getString("temp");
                    String humidity = object.getString("humidity");
                    String main = object2.getString("main");
                    String desc = object2.getString("description");

                    Double temp = Double.parseDouble(temperature) - 273.15;

                    texttemp.setText(temp.toString().substring(0, 5));
                    texthumidity.setText(humidity);
                    textmain.setText(main);
                    textdesc.setText(desc);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }

    public void getRetrofitWeather(View v){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherapi myapi = retrofit.create(weatherapi.class);
        Call<Example> examplecall = myapi.getWeather(textcity.getText().toString().trim(), apikey);
        examplecall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
                if(response.code() == 404){
                    Toast.makeText(MainActivity.this, "Please enter a valid city", Toast.LENGTH_SHORT).show();
                }
                else if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }
                Example mydata = response.body();
                Main main = mydata.getMain();
                Double temp = main.getTemp();
//                Integer humidity = main.getHumidity();
                Integer temperature = (int)(temp-273.15);
                texttemp.setText(String.valueOf(temperature));
//                texthumidity.setText(String.valueOf(humidity));
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}