package com.APSharing.controller;

import com.APSharing.LocalDateParam;
import com.APSharing.vo.Apod;
import com.APSharing.vo.DivisionsItems;
import com.APSharing.vo.AstroEventItems;
import com.APSharing.service.ApiService;
import com.APSharing.vo.LunPhItems;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class OpenApiController {

    private final ApiService service;
    @Value("${OpenApiKey}")
    private String openApiKey;
    @Value("${AstroEventInfoUrl}")
    private String astroEventInfoUrl;

    @Value("${DivisionsInfo24Url}")
    private String divisionsInfo24;

    @Value("${LunPhInfoServiceUrl}")
    private String lunPhInfoServiceUrl;

    @Value("${NasaKey}")
    private String nasaKey;

    @Value("${ApodUrl}")
    private String nasaApodUrl;

    private LocalDateParam localDateParam=new LocalDateParam(LocalDate.now());

    @ModelAttribute
    public void callAstroEventInfo(Model model){


        String urlStr = astroEventInfoUrl +
                "solYear="+localDateParam.getSolYear()+
                "&solMonth="+localDateParam.getSolMonth()+
                "&serviceKey=" + openApiKey +
                "&_type=json";



        String result = getJson(urlStr);

        AstroEventItems response = service.parseJson(result, AstroEventItems.class);
        model.addAttribute("astroEventInfo",response.getAstroEventItems());
    }



    @ModelAttribute
    public void callApod(Model model){


        String urlStr = nasaApodUrl +
                "api_key=" + nasaKey;

        String result = getJson(urlStr);

        Apod response=this.service.parseJson(result, Apod.class);
        model.addAttribute("apod",response);

    }

    @ModelAttribute
    public void callLunPhInfoService(Model model){

        String urlStr = lunPhInfoServiceUrl +
                "solYear="+localDateParam.getSolYear()+"&solMonth="+
                localDateParam.getSolMonth()+"&solDay="+
                localDateParam.getSolDay()+
                "&serviceKey=" + openApiKey +
                "&_type=json";

        String result=getJson(urlStr);

        LunPhItems response=this.service.parseJson(result,LunPhItems.class);
        model.addAttribute("lunPhInfoService",response.getLunPhItem());

    }



    @ModelAttribute
    public void callAnniversaryInfo24(Model model){
        String urlStr = divisionsInfo24 +
                "solYear="+localDateParam.getSolYear()+
                "&solMonth="+localDateParam.getSolMonth()+
                "&serviceKey=" + openApiKey +
                "&_type=json";

        String result=getJson(urlStr);

        DivisionsItems response=this.service.parseJson(result, DivisionsItems.class);
        model.addAttribute("divisionsInfo24",response.getDivisionsItems());

    }



    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setConnectTimeout(5000);
        urlConnection.setReadTimeout(5000);
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

    private String getJson(String urlStr) {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;


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
        return result;
    }
}
