package com.APSharing.vo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GroupService {
    public List<Map<String, Object>> groupAstroEvents(List<AstroEventItem> items) {
        Map<String, List<AstroEventItem>> groupedItems = items.stream()
                .collect(Collectors.groupingBy(AstroEventItem::getFormatLocdate));

        return groupedItems.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("locdate", entry.getKey());
                    map.put("events", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
