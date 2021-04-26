package com.example.ahmeddiab_180470;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather extends AppCompatActivity {
    String weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q=riyadh&appid=622310da579c96263f16a1971ae938ba&units=metric";
    ImageView weatherBackground;
    public static int imgSource;
    // textview to show temperature and description
    TextView temperature, description;
    Button submit;
    Button Back;

    // JSON object that contains weather information
    JSONObject jsonObj;
    EditText City;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


            //link graphical items to variables
        temperature = (TextView) findViewById(R.id.temperature);
        description = (TextView) findViewById(R.id.description);
        submit=(Button)findViewById(R.id.button2);
        Back=(Button)findViewById(R.id.button3);
        description.setVisibility(View.INVISIBLE);
        temperature.setVisibility(View.INVISIBLE);
        City=(EditText)findViewById(R.id.city);
        weatherBackground = (ImageView) findViewById(R.id.weatherbackground);
        Back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Weather.this,Start.class));
                }
            });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setVisibility(View.VISIBLE);
                temperature.setVisibility(View.VISIBLE);

                String city=City.getText().toString();
                String url="http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=622310da579c96263f16a1971ae938ba&units=metric";

                weather(url);


            }
        });
        }
        public void weatherPic(JSONArray jArray){
            for (int i=0; i < jArray.length(); i++)
            {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    Log.d("Ahmed","jArray(i): "+ oneObject.toString());
                    String weatherCondition =oneObject.getString("main");
                    Log.d("Ahmed","weather condition: "+weatherCondition);

                    weatherBackground.setImageResource(R.drawable.cloudy);

                    if(weatherCondition.equals("Clouds")){

                        weatherBackground.setImageResource(R.drawable.cloudy);
                        weatherBackground.setTag(R.drawable.cloudy);
                        imgSource=(Integer)weatherBackground.getTag();
                    }
                    else if(weatherCondition.equals("Clear")){
                        weatherBackground.setImageResource(R.drawable.clear);
                        weatherBackground.setTag(R.drawable.clear);
                        imgSource=(Integer)weatherBackground.getTag();
                    }
                    else if(weatherCondition.equals("Rain")){
                        weatherBackground.setImageResource(R.drawable.rainy);
                        weatherBackground.setTag(R.drawable.rainy);
                        imgSource=(Integer)weatherBackground.getTag();
                    }
                    else if(weatherCondition.equals("Snow")){
                        weatherBackground.setImageResource(R.drawable.snowy);
                        weatherBackground.setTag(R.drawable.snowy);
                        imgSource=(Integer)weatherBackground.getTag();
                    }


                } catch (JSONException e) {
                    Log.d("Ahmed","Error JSONArray: "+ jArray.toString());
                }
            }

        }
    public int getImgResource(){

        return imgSource;
    }


        public void weather(String url) {
            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url,
                    null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    //get the results.....
                    Log.d("Ahmed",response.toString());
                    try {
                        JSONObject jsonMain = response.getJSONObject("main");
                        double temp =jsonMain.getDouble("temp");
                        temperature.setText(String.valueOf(temp)+" °C");
                        Log.d("Ahmed","subObject:"+jsonMain.toString());
                        Log.d("Ahmed","temp: "+String.valueOf(temp));
                        double humidity=jsonMain.getDouble("humidity");//getting the humidity in the main json object
                        Log.d("Ahmed","Humidity is: "+humidity);
                        description.setText("Humidity: "+String.valueOf(humidity));
                        JSONArray weatherArray = response.getJSONArray("weather");
                        weatherPic(weatherArray);

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Log errors here
                    Log.d("Ahmed","Error in url");
                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObj);
    }
}