package com.APSharing.service.api;


import com.APSharing.service.JsonService;
import com.APSharing.service.ParamFormatService;
import com.APSharing.service.api.ApiService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KasiApiService implements ApiService {

    @Value("${KasiKey}")
    private String openApiKey;
    @Value("${AstroEventInfoUrl}")
    private String astroEventInfoUrl;

    @Value("${DivisionsInfo24Url}")
    private String divisionsInfo24;

    @Value("${LunPhInfoServiceUrl}")
    private String lunPhInfoServiceUrl;

    private Map<String,String> apiResponse;

    private final ParamFormatService paramService;

    private final JsonService jsonService;

    @PostConstruct
    private void init() {
        this.apiResponse = new HashMap<>();
    }

    @Override
    public Map<String, String> getApiResponse() {
        return apiResponse;
    }

    @Override
    public void refreshApiResponse() {
        fetchApiResponse();
    }

    private void fetchApiResponse(){

        String year=this.paramService.getSolYear();
        String month= this.paramService.getSolMonth();
        String day=this.paramService.getSolDay();

        fetchAstroEventInfo(year, month);
        fetchLunPhInfoService(year, month, day);
        fetchDivisionInfo24(year, month);

    }

    private void fetchDivisionInfo24(String year, String month) {

        String url= getUrl(divisionsInfo24, year, month,null);
        String result=this.jsonService.getJson(url);

        apiResponse.put("divisionsInfo24",result);
    }

    private void fetchLunPhInfoService(String year, String month, String day) {
        String url=getUrl(lunPhInfoServiceUrl, year, month, day);
        String result=this.jsonService.getJson(url);

        apiResponse.put("lunPhInfoService",result);
    }

    private void fetchAstroEventInfo(String year, String month) {
        String url = getUrl(astroEventInfoUrl, year, month,null);
        String result = this.jsonService.getJson(url);

        apiResponse.put("astroEventInfo",result);
    }

    private String getUrl(String url,String year,String month,String day) {

        String solYear="solYear="+year;
        String solMonth="&solMonth=" + month;
        String solDay="&solDay="+day;
        String serviceKey="&serviceKey=" + openApiKey;
        return url + solYear+solMonth+solDay+serviceKey+"&_type=json";
    }


}
