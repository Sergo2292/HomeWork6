package org.HW6;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WeatherApp {
    private String apiKey = "krQzv0BU9JSblk5uEDWVObHlMf7En2NG";
    private String cityID = "295212";

    public WeatherApp() {

    }

    public String getCityID() {
        return cityID;
    }

    public String get5DayForecast(String cityID) throws IOException {
        OkHttpClient forecastHttpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        HttpUrl forecastUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("www.accuweather.com")
                .addPathSegment("/forecasts/v1/daily/5day/" + cityID)
                .query("apikey=" + apiKey)
                .build();

        Request forecastRequest = new Request.Builder()
                .url(forecastUrl)
                .get()
                .build();

        Response forecastResponse = forecastHttpClient.newCall(forecastRequest).execute();


        String forecastResponseJSON = forecastResponse.body().string();

        return forecastResponseJSON;
    }
}
