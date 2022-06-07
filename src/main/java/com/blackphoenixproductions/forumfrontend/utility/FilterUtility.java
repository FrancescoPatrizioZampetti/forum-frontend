package com.blackphoenixproductions.forumfrontend.utility;

import com.blackphoenixproductions.forumfrontend.dto.Filter;
import com.blackphoenixproductions.forumfrontend.enums.BooleanOperator;
import com.blackphoenixproductions.forumfrontend.enums.QueryOperator;

import java.util.Map;

public class FilterUtility {

    public static Filter buildFilter(Map<String, String> paramsMap) {
        Filter root = Filter.builder().booleanOperator(BooleanOperator.AND).build();
        for (String key : paramsMap.keySet()){
            if(paramsMap.get(key) != null) {
                switch (key) {
                    case "title":
                    case "authorUsername":
                        root.getFilters().add(buildSpecificFilter(QueryOperator.LIKE, key, paramsMap.get(key)));
                        break;
                }
            }
        }
        return root;
    }

    private static Filter buildSpecificFilter(QueryOperator queryOperator, String key, String value){
        return Filter.builder()
                .queryOperator(queryOperator)
                .field(key)
                .value(value)
                .build();
    }

}
