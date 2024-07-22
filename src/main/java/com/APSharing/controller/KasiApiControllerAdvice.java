package com.APSharing.controller;

import com.APSharing.service.GroupService;
import com.APSharing.service.JsonService;
import com.APSharing.service.api.ApiService;
import com.APSharing.vo.kasi.AstroEventItems;
import com.APSharing.vo.kasi.DivisionsItems;
import com.APSharing.vo.kasi.LunPhItems;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class KasiApiControllerAdvice {

    private final JsonService jsonService;

    private final GroupService groupService;

    private final @Qualifier("kasiApiService") ApiService kasiApiService;
    @ModelAttribute
    public void callAstroEventInfo(Model model){


        String result=this.kasiApiService.getApiResponse().get("astroEventInfo");

        AstroEventItems astroEventItems = this.jsonService.parseJson(result, AstroEventItems.class);
        List<Map<String,Object>> response=this.groupService.groupAstroEvents(astroEventItems.getAstroEventItems());

        model.addAttribute("astroEventInfo",response);
    }


    @ModelAttribute
    public void callLunPhInfoService(Model model){

        String result=this.kasiApiService.getApiResponse().get("lunPhInfoService");

        LunPhItems response=this.jsonService.parseJson(result,LunPhItems.class);
        model.addAttribute("lunPhInfoService",response.getLunPhItem());

    }



    @ModelAttribute
    public void callAnniversaryInfo24(Model model){

        String result=this.kasiApiService.getApiResponse().get("divisionsInfo24");

        DivisionsItems response=this.jsonService.parseJson(result, DivisionsItems.class);
        model.addAttribute("divisionsInfo24",response.getDivisionsItems());

    }


}
