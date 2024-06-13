package com.APSharing.controller;

import com.APSharing.service.GroupService;
import com.APSharing.service.ParamFormatService;
import com.APSharing.service.JsonService;
import com.APSharing.vo.kasi.AstroEventItems;
import com.APSharing.vo.kasi.DivisionsItems;
import com.APSharing.vo.kasi.LunPhItems;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class KasiApiControllerAdvice {


    @Value("${KasiKey}")
    private String openApiKey;
    @Value("${AstroEventInfoUrl}")
    private String astroEventInfoUrl;

    @Value("${DivisionsInfo24Url}")
    private String divisionsInfo24;

    @Value("${LunPhInfoServiceUrl}")
    private String lunPhInfoServiceUrl;


    private final ParamFormatService formatService;

    private final JsonService jsonService;

    private final GroupService groupService;

    @ModelAttribute
    public void callAstroEventInfo(Model model){

        String urlStr = astroEventInfoUrl +
                "solYear="+this.formatService.getSolYear()+
                "&solMonth="+this.formatService.getSolMonth()+
                "&serviceKey=" + openApiKey +
                "&_type=json";



        String result = this.jsonService.getJson(urlStr);

        AstroEventItems astroEventItems = this.jsonService.parseJson(result, AstroEventItems.class);
        List<Map<String,Object>> response=this.groupService.groupAstroEvents(astroEventItems.getAstroEventItems());

        model.addAttribute("astroEventInfo",response);
    }


    @ModelAttribute
    public void callLunPhInfoService(Model model){

        String urlStr = lunPhInfoServiceUrl +
                "solYear="+this.formatService.getSolYear()+"&solMonth="+
                this.formatService.getSolMonth()+"&solDay="+
                this.formatService.getSolDay()+
                "&serviceKey=" + openApiKey +
                "&_type=json";

        String result=this.jsonService.getJson(urlStr);

        LunPhItems response=this.jsonService.parseJson(result,LunPhItems.class);
        model.addAttribute("lunPhInfoService",response.getLunPhItem());

    }



    @ModelAttribute
    public void callAnniversaryInfo24(Model model){
        String urlStr = divisionsInfo24 +
                "solYear="+this.formatService.getSolYear()+
                "&solMonth="+this.formatService.getSolMonth()+
                "&serviceKey=" + openApiKey +
                "&_type=json";

        String result=this.jsonService.getJson(urlStr);

        DivisionsItems response=this.jsonService.parseJson(result, DivisionsItems.class);
        model.addAttribute("divisionsInfo24",response.getDivisionsItems());

    }


}
