package com.APSharing.controller;

import com.APSharing.entity.Apod;
import com.APSharing.entity.FcstItems;
import com.APSharing.service.ForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@RequiredArgsConstructor
@Controller
public class OpenApiController {

    private final ForecastService service;
    @Value("${AstroEventInfoKey}")
    private String astroEventInfoKey;
    @Value("${AstroEventInfoUrl}")
    private String astroEventInfoUrl;

    @Value("${NasaKey}")
    private String nasaKey;

    @Value("${NasaApodUrl}")
    private String nasaApodUrl;

    @GetMapping("/forecast")
    public String callForecastApi(Model model){
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = astroEventInfoUrl +
                "serviceKey=" + astroEventInfoKey +
                "&solYear=2023&solMonth=05&_type=json";
        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            stream = getNetworkConnection(urlConnection);
            result = readStreamToString(stream);

            if (stream != null) stream.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        FcstItems response = service.parseJson(result,FcstItems.class);
        model.addAttribute("items",response.getFcstItems());
        return "main";
    }

    @GetMapping("/nasaApod")
    public String callNasaApodApi(Model model){

        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = nasaApodUrl +
                "api_key=" + nasaKey;

        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            stream = getNetworkConnection(urlConnection);
            result = readStreamToString(stream);

            if (stream != null) stream.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        Apod response=this.service.parseJson(result, Apod.class);
        model.addAttribute("Apod",response);


        return "main2";

    }

    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);

        if(urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
        }

        return urlConnection.getInputStream();
    }

    private String readStreamToString(InputStream stream) throws IOException{
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        String readLine;
        while((readLine = br.readLine()) != null) {
            result.append(readLine + "\n\r");
        }

        br.close();

        return result.toString();
    }
}
