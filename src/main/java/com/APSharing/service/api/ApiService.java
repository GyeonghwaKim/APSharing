package com.APSharing.service.api;

import java.util.Map;

public interface ApiService {
    Map<String, String> getApiResponse();
    void refreshApiResponse();

}
